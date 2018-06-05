package org.prtiny.generator.dao;

import org.prtiny.generator.domain.UrlDo;

public interface UrlDao {
	
	String getLongUrl(String shortUrl);
	
	boolean getShortUrl(String keyUrl);

	void saveUrl(UrlDo urlDo);

	String findShortUrlByLong(String longUrl);
}
