package com.aygxy.test;

import java.util.HashMap;
import java.util.Map;

import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.SegmentationAlgorithm;
import org.apdplat.word.segmentation.Word;
import org.junit.Test;

public class TestWordSplit {
	
	@Test
	public void test1(){
		Map<String, String> maps = segMore("我国工人阶级和广大劳动群众要更加紧密地团结在党中央周围");
	}
	
	public Map<String, String> segMore(String text) {
	    Map<String, String> map = new HashMap<>();
	    map.put(SegmentationAlgorithm.MinimalWordCount.getDes(), seg(text, SegmentationAlgorithm.MinimalWordCount));
	    return map;
	}
	private static String seg(String text, SegmentationAlgorithm segmentationAlgorithm) {
	    StringBuilder result = new StringBuilder();
	    for(Word word : WordSegmenter.segWithStopWords(text, segmentationAlgorithm)){
	        result.append(word.getText()).append(" ");
	    }
	    return result.toString();
	}
}
