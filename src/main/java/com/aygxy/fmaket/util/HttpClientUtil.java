package com.aygxy.fmaket.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.log4j.Logger;

/**
 * ===========================================================================
 * 对于 此 httpClient再封装类，分为两种情况:
 * 1.不需要证书的 http https 请求 http请求也可以使用
 * 2.需要证书的https请求  支持单向认证和双向认证，必须文件为  client.keystore  或 client.p12 需要密码     可配置文件 trust.keystore
 *
 * 关于 .cer（证书） 和 .p12（密钥对） 和.keystore（用于保存证书，将.cer加入到.keystore） 之间的关系
 *
 * 服务器端和客户端均可生成证书  .cer
 *
 * 服务器端先生成.keystore（keytool -validity 36500 -genkey -v -alias tomcat -keyalg RSA -keystore D:\tomcat.keystore）
 * 然后利用keystore制作出.cer证书 （keytool -export -v -alias tomcat -keystore d:/tomcat.keystore -rfc -file d:/tomcat.cer）
 *
 * 客户端可生成.p12的密钥对（keytool -validity 36500 -genkeypair -v -alias client -keyalg RSA -storetype PKCS12 -keystore D:\client.p12）
 * 然后由.p12可生成.cer证书(keytool -export -v -alias client -keystore d:/client.p12 -storetype PKCS12 -rfc -file d:/client.cer)
 *
 * ***
 * 对于服务器端下发的证书（.cer，可由浏览器下载）文件可转换成.keystore（keytool -import -alias cacerts -file test.cer -keystore test.keystore）密码自己设置，用于https加载。
 *
 * ============================================================================
 */

/**
 * HttpClient工具类封装，支持ssl双向认证.
 */
public final class HttpClientUtil {
	/**  */
	private static Logger log = Logger.getLogger(HttpClientUtil.class);
	/**
	 * 超时时间
	 */
	private static final int TIMEOUT = 5 * 1000;
	/**
	 * 编码格式
	 */
	private  final String ENCODING = "UTF-8";
	private  final String KEY_STORE_PASSWORD = "";
	private  final String KEY_STORE_TRUST_PASSWORD = "";


	private HttpClientUtil() {
		//        try {
		//            init();
		//        } catch (Exception e) {
		//            log.error("初始化https出错", e);
		//            throw new RuntimeException(e);
		//        }
	}

	;
	private static HttpClientUtil instance;
	/**
	 * ssl 连接池
	 */
	private HttpClientConnectionManager connectionManager = null;

	public static HttpClientUtil Instance() {
		if (instance == null) {
			synchronized (HttpClientUtil.class) {
				if (instance == null) {
					instance = new HttpClientUtil();
				}
			}
		}
		return instance;
	}

	private CloseableHttpClient createHttpsClient() {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
					return true;
				}
			}).useProtocol("TLS").build();
			SSLConnectionSocketFactory sslFactory = new SSLConnectionSocketFactory(sslContext);
			return HttpClients.custom()
					.setSSLSocketFactory(sslFactory)
					.build();
		} catch (KeyManagementException e) {
			throw new RuntimeException(e);
		} catch (KeyStoreException e) {
			throw new RuntimeException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	//======================================Http Get ===============================================

	/**
	 * 信任所有，不需要证书
	 * http  or https get 请求方式 timeOut = 5000
	 * @param url
	 * @param headers
	 * @return
	 * @throws IOException
	 */
	public String get(final String url, final Map<String, String> headers) throws IOException {
		return get(url, headers, TIMEOUT);
	}



	/**
	 * 信任所有，不需要证书
	 * http  or https get 请求方式
	 * @param url
	 * @param headers
	 * @param timeout
	 * @return
	 * @throws IOException
	 */
	public String get(final String url, final Map<String, String> headers, final int timeout) throws IOException {
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(getRequestConfig(timeout));
		if (headers != null && !headers.isEmpty()) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				httpGet.addHeader(entry.getKey(), entry.getValue());
			}
		}
		return doExecute(httpGet);
	}

	public String get(final String url, final String sign) throws IOException {
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(getRequestConfig(TIMEOUT));
		httpGet.addHeader("sign", sign);
		return doExecute(httpGet);
	}

	public String get(final String url) throws IOException {
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(getRequestConfig(TIMEOUT));
		return doExecute(httpGet);
	}

	//======================================Http Post ==============================================

	/**
	 * Http Https post 请求
	 * @param url
	 * @param headers
	 * @param params  body内容
	 * @return
	 * @throws IOException
	 */
	public String post(String url, Map<String, String> headers, Map<String, String> params) throws IOException {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(getRequestConfig(TIMEOUT));
		if (headers != null && !headers.isEmpty()) {
			if (headers != null && !headers.isEmpty()) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					httpPost.addHeader(entry.getKey(), entry.getValue());
				}
			}
		}
		if (params != null) {
			List<NameValuePair> nvps = new ArrayList<>();
			for (Map.Entry<String, String> entry : params.entrySet()) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		}
		return doExecute(httpPost);
	}

	public String post(String url, String body, String sign) throws IOException {
		Map<String, String> headers = new HashMap<>();
		headers.put("sign", sign);
		headers.put("Content-Type", "application/json; charset=utf-8");
		return post(url, headers, body, TIMEOUT);
	}

	public String post(String url) throws IOException {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(getRequestConfig(TIMEOUT));
		httpPost.addHeader("Content-Type", "text/xml; charset=utf-8");
		return doExecute(httpPost);
	}

	public String post(String url, String body) throws IOException {
		return post(url, body, TIMEOUT);
	}

	public String post(String url, String body, int timeout) throws IOException {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(getRequestConfig(timeout));
		httpPost.addHeader("Content-Type", "application/json; charset=utf-8");
		//, ContentType.APPLICATION_JSON
		httpPost.setEntity(new StringEntity(body, ENCODING));
		return doExecute(httpPost);
	}

	/**
	 * Http  Post请求
	 *
	 * @param url
	 * @param headers 请求头信息
	 * @param body    请求内容
	 * @param timeout 超时时间
	 * @return
	 * @throws IOException
	 */
	public String post(String url, Map<String, String> headers, String body, int timeout) throws IOException {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(getRequestConfig(timeout));
		if (headers != null && !headers.isEmpty()) {
			if (headers != null && !headers.isEmpty()) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					httpPost.addHeader(entry.getKey(), entry.getValue());
				}
			}
		}
		System.out.print(httpPost.getURI());
		httpPost.setEntity(new StringEntity(body, ENCODING));
		return doExecute(httpPost);
	}

	private RequestConfig getRequestConfig(int timeout) {
		return RequestConfig.custom().setConnectTimeout(timeout).setSocketTimeout(timeout).build();
	}

	private String doExecute(HttpUriRequest request) throws IOException {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		try {
			httpClient = createHttpsClient();
			response = httpClient.execute(request);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				return stream2Str(response.getEntity().getContent());
			} else {
				log.error(String.format("remote server response %d", statusCode));
				return null;
			}
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (Exception e) {
					log.error("response close error", e);
				}
			}
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (Exception e) {
					log.error("httpClient close error", e);
				}
			}
		}
	}

	//=====================================https  get 需要证书====================================================
	/**
	 * 需要证书验证
	 * https get 请求方式 timeOut = 5000
	 * @param url
	 * @param headers
	 * @return
	 * @throws IOException
	 */
	public String httpsGet(final String url, final Map<String, String> headers) throws IOException {
		return httpsGet(url, headers, TIMEOUT);
	}

	public String httpsGet(String url, Map<String, String> headers, int timeout) throws IOException {
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(getRequestConfig(timeout));
		if (headers != null && !headers.isEmpty()) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				httpGet.addHeader(entry.getKey(), entry.getValue());
			}
		}
		return doHttpsExecute(httpGet);
	}

	public String httpsGet(String url) throws IOException {
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(getRequestConfig(TIMEOUT));
		return doHttpsExecute(httpGet);
	}
	//=====================================https post 添加证书认证============================================
	/**
	 * 需要证书验证
	 * Https post 请求
	 * @param url
	 * @param headers
	 * @param params  body内容，键值对形式发送
	 * @return
	 * @throws IOException
	 */
	public String httpsPost(String url, Map<String, String> headers, Map<String, String> params) throws IOException {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(getRequestConfig(TIMEOUT));
		if (headers != null && !headers.isEmpty()) {
			if (headers != null && !headers.isEmpty()) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					httpPost.addHeader(entry.getKey(), entry.getValue());
				}
			}
		}
		if (params != null) {
			List<NameValuePair> nvps = new ArrayList<>();
			for (Map.Entry<String, String> entry : params.entrySet()) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		}
		return doHttpsExecute(httpPost);
	}

	/**
	 *
	 * @param url
	 * @param body
	 * @param sign  会放在头部信息中
	 * @return
	 * @throws IOException
	 */
	public String httsPost(String url, String body, String sign) throws IOException {
		Map<String, String> headers = new HashMap<>();
		headers.put("sign", sign);
		headers.put("Content-Type", "application/json; charset=utf-8");
		return httsPost(url, headers, body, TIMEOUT);
	}

	public String httsPost(String url, String body) throws IOException {
		return httsPost(url, body, TIMEOUT);
	}

	public String httsPost(String url, String body, int timeout) throws IOException {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(getRequestConfig(timeout));

		httpPost.addHeader("Content-Type", "text/plain; charset=utf-8");
		//, ContentType.APPLICATION_JSON
		httpPost.setEntity(new StringEntity(body, ENCODING));
		return doHttpsExecute(httpPost);
	}

	/**
	 * Http  Post请求
	 * @param url
	 * @param headers 请求头信息
	 * @param body    请求内容
	 * @param timeout 超时时间
	 * @return
	 * @throws IOException
	 */
	public String httsPost(String url, Map<String, String> headers, String body, int timeout) throws IOException {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(getRequestConfig(timeout));
		if (headers != null && !headers.isEmpty()) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				httpPost.addHeader(entry.getKey(), entry.getValue());
			}
		}
		httpPost.setEntity(new StringEntity(body));
		return doHttpsExecute(httpPost);
	}

	/**
	 * 发送https请求
	 * 为双向请求
	 *
	 * @param request
	 * @return
	 */
	private String doHttpsExecute(HttpUriRequest request) throws IOException {
		if (connectionManager == null) {
			try {
				init();
			} catch (Exception e) {
				log.error("初始化SSL证书异常！", e);
			}
			if(connectionManager == null){
				return null;
			}
		}
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		try {
			httpClient = HttpClients.custom()
					.setConnectionManager(connectionManager).build();
			response = httpClient.execute(request);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				return stream2Str(response.getEntity().getContent());
			} else {
				log.error(String.format("remote server response %d", statusCode));
				return null;
			}
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (Exception e) {
					log.error("response close error", e);
				}
			}
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (Exception e) {
					log.error("httpClient close error", e);
				}
			}
		}
	}


	public String stream2Str(InputStream inputStream) {
		StringBuilder sbu = new StringBuilder();
		InputStreamReader inputStreamReader;
		try {
			inputStreamReader = new InputStreamReader(inputStream, ENCODING);
			BufferedReader reader = new BufferedReader(inputStreamReader);
			String line;
			while ((line = reader.readLine()) != null) {
				sbu.append(line);
			}
			return sbu.toString();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(" stream2Str from inputStream Error!", e);
		}
		return null;
	}

	/**
	 * 初始化https双向认证机制
	 * @throws Exception
	 */
	private void init() throws Exception {
		SSLContext sslContext = null;
		File ksFile = new File("");
		File tsFile = new File("cacert/mykeystore.jks");
		log.debug(tsFile.getAbsolutePath()+"");
		//双向认证
		if(ksFile.exists() && tsFile.exists()){
			KeyStore keyStore = KeyStore.getInstance("p12");
			KeyStore trustStore = KeyStore.getInstance("jks");
			InputStream ksIn = null;
			InputStream tsIn = null;
			try {
				ksIn = new FileInputStream(ksFile);
				keyStore.load(ksIn, this.KEY_STORE_PASSWORD.toCharArray());
				tsIn = new FileInputStream(tsFile);
				trustStore.load(tsIn, this.KEY_STORE_TRUST_PASSWORD.toCharArray());
			}catch (Exception e) {
				log.error("加载证书出错", e);
			}finally{
				if (ksIn != null) {
					try {
						ksIn.close();
					} catch (Exception e) {}
				}
				if (tsIn != null) {
					try {
						tsIn.close();
					} catch (Exception e) {}
				}
			}
			sslContext = SSLContexts.custom()
					.loadKeyMaterial(keyStore, this.KEY_STORE_PASSWORD.toCharArray())
					.loadTrustMaterial(trustStore, new TrustSelfSignedStrategy())
					.build();
		}else if(tsFile.exists()){  //单向认证
			sslContext = SSLContexts.custom()
					.loadTrustMaterial(tsFile,
							this.KEY_STORE_TRUST_PASSWORD.toCharArray(),
							new TrustSelfSignedStrategy())
					.build();
		}else{  	// 信任所有
			sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
					return true;
				}
			}).build();
		}
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,
				new String[]{"TLSv1"},
				null,
				SSLConnectionSocketFactory.getDefaultHostnameVerifier());
		Registry registry = RegistryBuilder.create()
				.register("https", sslsf).build();
		connectionManager = new PoolingHttpClientConnectionManager(registry);
	}
}


