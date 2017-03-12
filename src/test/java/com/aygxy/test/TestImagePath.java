package com.aygxy.test;

import java.util.UUID;

import org.eclipse.jetty.util.security.Credential.MD5;
import org.junit.Test;

public class TestImagePath {
	
	@Test
	public void test1(){
		String md5 = MD5.digest(UUID.randomUUID().toString());
		System.out.println(md5);
		System.out.println(md5.hashCode());
	}
}
