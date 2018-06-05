package org.prtiny.generator.service;

import java.util.Date;

public interface UrlService {
	String generateShortUrl(String longUrl,Date date);

	String getLongUrl(String shortUrl);
	
	boolean getByShortUrl(String shortUrl);
}
