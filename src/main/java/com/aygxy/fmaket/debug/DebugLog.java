package com.aygxy.fmaket.debug;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class DebugLog {
	public static Logger logger; 
	private static String log4jconfig;
	
	static{
		log4jconfig = DebugLog.class.getClassLoader().getResource("log4j.properties").getPath();
		PropertyConfigurator.configure(log4jconfig);
		logger = Logger.getLogger("FMaketServer"); 
		System.setErr(new PrintStream(new CustomOutputStream()));
	}
	
	public DebugLog() {
		PropertyConfigurator.configure(log4jconfig);
		logger = Logger.getLogger("FMaketServer"); 
		System.setErr(new PrintStream(new CustomOutputStream()));
	}
}

class CustomOutputStream extends OutputStream {
	StringBuffer toWrite = new StringBuffer();
	@Override
	public final void write(int b) throws IOException {
		if(b != 10 && b != 13) {
			
			toWrite = toWrite.append((char) b);
		} else if ( b == 10 || b == 13) {
			if (toWrite.length() > 0) {
				DebugLog.logger.error("!! " + toWrite.toString());
				toWrite.setLength(0);
			}
		}
	}
	
	@Override
	public final void write(byte[] b) throws IOException {
		DebugLog.logger.error("!! " + new String(b));
	}	
}