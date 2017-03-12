package com.aygxy.fmaket.util;

import java.io.File;
import java.util.UUID;

public class FileUtil {
	/**
	 * 根据文件后缀生成一个随机的文件名
	 * @param fileExtName
	 * @return
	 */
	public static String makeFileName(String fileExtName){  
		//为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
		return UUID.randomUUID().toString() + "." + fileExtName;
	}

	/**
	 * 为防止一个目录下面出现太多文件，要使用hash算法打散存储
	 *
	 * @param filename 文件名，要根据文件名生成存储目录
	 * @param savePath 文件存储路径
	 * @return 新的存储目录
	 */ 
	public static String makePath(String filename,String savePath){
		//得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
		int hashcode = filename.hashCode();
		int dir1 = hashcode&0xf;  //0--15
		int dir2 = (hashcode&0xf0)>>4;  //0-15
		int dir3 = (int) (100000 + Math.random()*999999);
		//构造新的保存目录
		String dir = savePath + File.separator + dir1 + File.separator + dir2 + File.separator + dir3;  //upload\2\3  upload\3\5
//		//File既可以代表文件也可以代表目录
//		File file = new File(dir);
//		//如果目录不存在
//		if(!file.exists()){
//			//创建目录
//			file.mkdirs();
//		}
		return dir;
	}
	
	/**
	 * 得到要删除图片的真实路径
	 * @param request
	 * @param tempPath
	 * @return
	 */
	public static String getLocalImageUrl(String tempPath){
		String url = "";
		String[] strs = tempPath.split("/");
		for (int i = 0; i < strs.length; i++) {
			System.out.println(strs[i]);
		}
//		url = GlobalParams.path+File.separator+strs[5]+File.separator+strs[6];
		System.out.println(url);		
		return url;
	}
	
	
	public static void main(String[] args) {
		System.out.println(makePath("1.jpg", "Path"));
	}
}
