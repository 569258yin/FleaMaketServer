package com.aygxy.fmaket.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import com.aygxy.fmaket.debug.DebugLog;

public class IKAnalyzerUtils {
	
	public void test1(){
		String text = "基于java语言开发的轻量级的中文分词工具包,尹豪爱李盼盼呀呀";
		Map<String, String> map = new HashMap<>();

//	    map.put("智能切分", segText(text, true));
	    
	    System.out.println(map);
	    
	}
	
	public synchronized static Set<String> segText(String text, boolean useSmart) {
	    Set<String> resSet = new HashSet<>();
	    IKSegmenter ik = new IKSegmenter(new StringReader(text), useSmart);        
	    try {
	        Lexeme word = null;
	        while((word=ik.next())!=null) {          
	           resSet.add(word.getLexemeText());
	        }
	    } catch (IOException e) {
	       DebugLog.logger.error("分词匹配错误: "+text,e);
	    }
	    return resSet;
	}
}
