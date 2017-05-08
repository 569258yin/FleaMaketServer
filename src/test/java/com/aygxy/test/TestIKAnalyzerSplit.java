package com.aygxy.test;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

public class TestIKAnalyzerSplit {
	
	@Test
	public void test1(){
		String text = "基于java语言开发的轻量级的中文分词工具包,尹豪爱李盼盼呀呀";
		Map<String, String> map = new HashMap<>();

	    map.put("智能切分", segText(text, true));
	    
	    System.out.println(map);
	    
	}
	
	private String segText(String text, boolean useSmart) {
	    StringBuilder result = new StringBuilder();
	    IKSegmenter ik = new IKSegmenter(new StringReader(text), useSmart);        
	    try {
	        Lexeme word = null;
	        while((word=ik.next())!=null) {          
	            result.append(word.getLexemeText()).append(" ");
	        }
	    } catch (IOException ex) {
	        throw new RuntimeException(ex);
	    }
	    return result.toString();
	}
}
