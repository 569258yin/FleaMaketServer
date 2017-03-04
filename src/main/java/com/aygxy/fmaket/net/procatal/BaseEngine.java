package com.aygxy.fmaket.net.procatal;


import org.apache.commons.codec.digest.DigestUtils;

import com.aygxy.fmaket.debug.DebugLog;
import com.aygxy.fmaket.param.GlobalParams;
import com.aygxy.fmaket.util.DES;
import com.aygxy.fmaket.util.GsonUtil;

/**
 * 1.创建并格式化body
 * 2.调用getMessageToJson（）将要发送的数据格式化成json字符串 此时传递body和请求码
 * 3.调用httpclient向服务器发送数据
 * 4.将获取的数据格式化调用getResult（）
 * 
 * @author yh
 * 
 */
public class BaseEngine {

	private static DES des = new DES();
	/**
	 * 将服务器端发来的信息进行解析，并数据校验
	 * @param resposeStr
	 * @return
	 */
	public static IMessage getResult(String resposeStr) {
		if (resposeStr != null) {
			try {
				//将json字符串转换为对象bean
				DesMessage desMsg = (DesMessage) GsonUtil.stringToObjectByType(resposeStr,DesMessage.class);
				// 解析出字符串
				String bodyinfo = desMsg.getBody();
				Header header = desMsg.getHeader();
				// 将body解密
				String orgbody = des.authcode(bodyinfo, "ENCODE",
						GlobalParams.DESPASSWORD);
				// 拿出时间戳
				String md5info = header.getTimestamp() + header.getIpaddress()
						+ orgbody;
				String digest = DigestUtils.md5Hex(md5info);
				// 数据校验
				Body body = null;
				if (digest.equals(header.getDigest())) {
					IMessage result = new IMessage();
					body = (Body) GsonUtil.stringToObjectByBean(orgbody,Body.class);
					result.setHeader(header);
					result.setBody(body);
					return result;
				}else{
					DebugLog.logger.error("BaseEngine getResult 数据校验失败---》"+resposeStr);
				}
			} catch (Exception e) {
				e.printStackTrace();
				DebugLog.logger.error("BaseEngine getResult "+resposeStr);
			}
		}
		return null;
	}

	/**
	 * 获取发送数据的字符串
	 * @param body   已经设置好json数据了的body，还未经过加密
	 * @param code   本次请求编号
	 * @return
	 */
	public static String getMessageToJson(Body body, String code) {
		if(null != body) {
			try {
				// 1.封装body内容
				String bodyInfo = GsonUtil.objectToString(body);
				// 2.封装header内容
				Header header = new Header();
				header.setCompress("DES");
				header.setSource("Client");
				header.serializableHeader(bodyInfo);
				// 3.设置请求码
				header.setTransactiontype(code);
				// 4.加密body信息
				String desbody = des.authcode(bodyInfo, "DECODE",
                        GlobalParams.DESPASSWORD);
				// 5.封装成加密后的Message
				DesMessage desMessage = new DesMessage();
				desMessage.setHeader(header);
				desMessage.setBody(desbody);
				return GsonUtil.objectToString(desMessage);
			} catch (Exception e) {
				DebugLog.logger.error("BaseEngine getMessageToJson body:"+body.toString(),e);
				e.printStackTrace();
			}
		}
		return null;
	}

}
