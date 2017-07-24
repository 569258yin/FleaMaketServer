package com.aygxy.fmaket.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.apache.log4j.Logger;

import sun.util.logging.resources.logging;

public class Utility {
	private static Logger logger = Logger.getLogger(Utility.class);
	
	/**
	 * 获取IP
	 * @return
	 */
	public static String getIP() {  
		String IP = null;  
		StringBuilder IPStringBuilder = new StringBuilder();  
		try {  
			Enumeration<NetworkInterface> networkInterfaceEnumeration = NetworkInterface.getNetworkInterfaces();  
			while (networkInterfaceEnumeration.hasMoreElements()) {  
				NetworkInterface networkInterface = networkInterfaceEnumeration.nextElement();  
				Enumeration<InetAddress> inetAddressEnumeration = networkInterface.getInetAddresses();  
				while (inetAddressEnumeration.hasMoreElements()) {  
					InetAddress inetAddress = inetAddressEnumeration.nextElement();  
					logger.info("inetAddress = "+inetAddress.toString());
					logger.info("inetAddress.getHostAddress().toString()");
					if (!inetAddress.isLoopbackAddress()&&   
							!inetAddress.isLinkLocalAddress()&&   
							inetAddress.isSiteLocalAddress()) {  
						IPStringBuilder.append(inetAddress.getHostAddress().toString()+"\n");  
					}  
				}  
			}  
		} catch (SocketException ex) {  
			System.out.println("获取IP出错");
		}  

		IP = IPStringBuilder.toString().trim();  
		IP = "182.254.240.98";
		return IP;  
	}
}
