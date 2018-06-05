package org.prtiny.generator.service.impl;

import java.util.Date;
import java.util.Random;

import org.prtiny.generator.dao.UrlDao;
import org.prtiny.generator.domain.UrlDo;
import org.prtiny.generator.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;


@Service("urlService")
public class UrlServiceImpl implements UrlService {

	@Autowired
    private UrlDao dao;
	
	@Override
	public String generateShortUrl(String longUrl, Date date) {
		String shortUrl=dao.findShortUrlByLong(longUrl);
		if(shortUrl!=null)
		{
			return shortUrl;
		}
		else{
		String key=generateUrl(longUrl);
		UrlDo urlDo= new UrlDo();
		urlDo.setCreateTs(date);
		urlDo.setLongUrl(longUrl);
		urlDo.setShortUrl(key);
		dao.saveUrl(urlDo);
		return key;
		}
	}

	public String generateUrl(String longUrl)
	{
		final int keyLength=7;
		Random randomVal = new Random();
		final char charArr[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9'};
			String key = "";
			boolean flag = true;
			while (flag) {
				key = "";
				for (int i = 0; i < keyLength; i++) {
					key += charArr[randomVal.nextInt(62)];
				}
				if (!getByShortUrl(key)) {
					flag = false;
				}
			}
			return key;
	}
	@Override
	public String getLongUrl(String shortUrl) {
		return dao.getLongUrl(shortUrl);
	}

	@Override
	public boolean getByShortUrl(String shortUrl) {
		return dao.getShortUrl(shortUrl);
	}

}
