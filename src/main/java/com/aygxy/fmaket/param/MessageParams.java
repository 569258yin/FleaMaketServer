package com.aygxy.fmaket.param;

public class MessageParams {
	/** token无效 */
	public static final String TOKEN_ERROR = "{\"code\":0,\"message\":\"token无效\"}";
	/** 数据安全校验失败 */
	public static final String SIGN_ERROR = "{\"code\":-1,\"message\":\"数据校验失败\"}";
	/** 文件上传失败  */
	public static final String FILE_UPLOAD_ERROR = "{\"code\":-1,\"message\":\"文件上传失败\"}";
	
}
