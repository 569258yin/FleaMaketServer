package com.aygxy.fmaket.file.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aygxy.fmaket.bean.ImagePath;
import com.aygxy.fmaket.debug.DebugLog;
import com.aygxy.fmaket.param.GlobalParams;
import com.aygxy.fmaket.util.FileUtil;
import com.aygxy.fmaket.util.GsonUtil;

@Controller
@RequestMapping("file")
public class FileAction {

	private final static String ENCODING = "utf-8";

	static{

	}

	@RequestMapping("imgUpload.action")
	public void imageUpload(HttpServletRequest request,HttpServletResponse response){
		//创建工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//设置缓冲区的大小
		factory.setSizeThreshold(1024*1024*1024*5);
		String result = "";
		//本地服务器真实路径
		String imgPath = request.getSession().getServletContext().getRealPath(File.separator+"extimg");
		//上传时生成的临时文件保存目录
		String tempPath = request.getSession().getServletContext().getRealPath(File.separator+"temp");
		//设置上传文件临时文件的保存路径
		File tmpFile = new File(tempPath);
		if (!tmpFile.exists()) {
			//创建临时目录
			tmpFile.mkdir();
		}
		factory.setRepository(tmpFile);
		InputStream is = null;
		OutputStream os = null;
		try {
			//创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setProgressListener(new ProgressListener() {
				@Override
				public void update(long pBytesRead, long pContentLength, int arg2) {
					DebugLog.logger.info("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
				}
			});
			//设置编码格式 
			upload.setHeaderEncoding(ENCODING);
			//判断是否是表单提交
			if(!ServletFileUpload.isMultipartContent(request)){
				DebugLog.logger.info("不正确的文件格式");
				result = GsonUtil.objectToString(new ImagePath("-1", "不正确的文件格式"));
				response.getOutputStream().write(result.getBytes(ENCODING));
				return;
			}
			//设置图片最大限制
			upload.setFileSizeMax(1024*1024*1024);
			//使用解析器对上传的文件进行解析
			List<FileItem> list = upload.parseRequest(request);
			DebugLog.logger.debug("解析出的文件列表为"+list.toString());
			if(list.size() > 0 ){
				List<String> imageLists = new ArrayList<String>();
				for (FileItem fileItem : list) {
					//如果封装的是普通的数据
					if (fileItem.isFormField() && fileItem.getSize() > 0) {
						String requestName = fileItem.getFieldName();
						//解决普通输入项的中文乱码问题
						String requestValue = fileItem.getString(ENCODING);
						DebugLog.logger.info("文件上传携带参数==>"+requestName+":"+requestValue);
					}else{	//封装的是上传的文件
						//得到上传的文件名
						String fileName = fileItem.getName();
						DebugLog.logger.debug("上传文件的文件名："+fileName);
						if (fileName == null || fileName.trim().equals("")) {
							continue;
						}
						//注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，
						//如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
						//处理获取到的上传文件的文件名的路径部分，只保留文件名
						fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
						//得到上传文件的扩展名
						String fileExtName = fileName.substring(fileName.lastIndexOf(".")+1);
						DebugLog.logger.debug("上传的文件的扩展名是："+fileExtName);
						boolean allowCount = false;
						for(int i = 0;i<GlobalParams.IMAGE_ALLOW_END.length;i++){
							if(GlobalParams.IMAGE_ALLOW_END[i].equals(fileExtName)){
								allowCount = true;
								break;
							}
						}
						if(!allowCount){
							DebugLog.logger.info("文件后缀不正确");
							result = GsonUtil.objectToString(new ImagePath("-2", "文件格式不正确"));
							response.getOutputStream().write(result.getBytes(ENCODING));
							return;
						}
						//获取item上传的输入流
						//获取唯一名，随机生成
						String saveFileName= FileUtil.makeFileName(fileExtName);
						//根据文件名记性hash生成文件夹
						String filePath = FileUtil.makePath(saveFileName, "");
						String realFilePath = imgPath + filePath;
						//用于url生成
						String otherFilePath = request.getContextPath() + File.separator + "extimg" + filePath;
						//创建文件夹
						File filePathFile = new File(realFilePath);
						if (!filePathFile.exists()) {
							filePathFile.mkdirs();
						}
						//创建文件
						File file = new File(filePathFile,saveFileName);
						if (!file.exists()) {
							file.createNewFile();
						}
						DebugLog.logger.debug("本地创建的文件路径："+file.getAbsolutePath());
						is = fileItem.getInputStream();
						os = new FileOutputStream(file);
						//创建一个缓冲区
						byte[] buff = new byte[2048];
						int len = 0;
						while((len = is.read(buff)) != -1){
							os.write(buff, 0, len);
						}
						//删除处理文件的临时文件
						fileItem.delete();
						otherFilePath = GlobalParams.SERVER_URL + otherFilePath + File.separator + saveFileName;
						otherFilePath =  otherFilePath.replace('\\', '/');
						DebugLog.logger.debug("生成的图片url为:"+otherFilePath);
						imageLists.add(otherFilePath);
					}
				}
				result = GsonUtil.objectToString(new ImagePath(imageLists));
				response.getOutputStream().write(result.getBytes(ENCODING));
			}
		} catch (FileUploadException e) {
			DebugLog.logger.error("文件上传失败", e);
		} catch (UnsupportedEncodingException e) {
			DebugLog.logger.error("不支持的编码格式", e);
		} catch (IOException e) {
			DebugLog.logger.error("文件读写失败", e);
		} catch (Exception e) {
			DebugLog.logger.error("文件上传未知异常", e);
		}finally{
			//关闭流
			if(is != null){
				try {
					is.close();
				} catch (Exception e) {
				}
			}
			if(os != null){
				try {
					os.close();
				} catch (Exception e) {
				}
			}
		}
	}




}
