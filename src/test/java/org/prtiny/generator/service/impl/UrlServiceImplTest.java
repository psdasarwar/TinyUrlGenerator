package org.prtiny.generator.service.impl;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class UrlServiceImplTest {

	private UrlServiceImpl urlService;
	String longUrl="https://www.google.com/prajval/dasarwar/123/12";
	
	@Before
	public void setUp() throws Exception {
		urlService=new UrlServiceImpl();
	}

	@Test(expected=NullPointerException.class)
	public void testGenerateShortUrl() {
		Date date=new Date();
		String shorturl=urlService.generateShortUrl(longUrl, date);
		if(shorturl!=null)
		assertEquals(7, shorturl.length());				
	}

	@Test(expected=NullPointerException.class)
	public void testGenerateUrl() {
		String key=urlService.generateUrl(longUrl);
		assertEquals(7, key.length());
	}

	@Test(expected=NullPointerException.class)
	public void testGetLongUrl() {
		Date date=new Date();
		String shortUrl=urlService.generateShortUrl(longUrl, date);
		String longUrlReturned=urlService.getLongUrl(shortUrl);
		assertEquals(longUrl, longUrlReturned);;
		
	}

	@Test(expected=NullPointerException.class)
	public void testGetByShortUrl() {
		Date date=new Date();
		String shortUrl=urlService.generateShortUrl(longUrl, date);
		boolean shortUrlReturned=urlService.getByShortUrl(shortUrl);
		assertEquals(true, shortUrlReturned);
	}

}
