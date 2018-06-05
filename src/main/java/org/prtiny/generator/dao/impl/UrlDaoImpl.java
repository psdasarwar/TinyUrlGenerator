package org.prtiny.generator.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.prtiny.generator.dao.UrlDao;
import org.prtiny.generator.domain.UrlDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("urlDao")
@Transactional
public class UrlDaoImpl implements UrlDao {

	@Autowired
    private SessionFactory sessionFactory;
	
	static final Logger logger = LoggerFactory.getLogger(UrlDaoImpl.class);

	@Override
	public String getLongUrl(String shortUrl) {
		logger.info("getting long url for short url:-"+shortUrl);
		Session session=sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(UrlDo.class);
		cr.add(Restrictions.eq("shortUrl", shortUrl));
		List<UrlDo> results = cr.list();
		String longUrl=null;
		if(results.size()>0){
		for (UrlDo urlDo : results) {
			longUrl=urlDo.getLongUrl();
		}
		}
		logger.info("Found long url for short url:-"+shortUrl+"="+longUrl);
		return longUrl;
	}

	@Override
	public boolean getShortUrl(String keyUrl) {
		logger.info("finding short url present in Db:-"+keyUrl);
		Session session=sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(UrlDo.class);
		cr.add(Restrictions.eq("shortUrl", keyUrl));
		List<UrlDo> results = cr.list();
		if(results.size()>0)
			return true;
		else
			return false;
	}

	@Override
	public void saveUrl(UrlDo urlDo) {
		logger.info("Saving short and long url:-"+ urlDo.getShortUrl()+"="+urlDo.getLongUrl());
		Session session=sessionFactory.getCurrentSession();
		session.save(urlDo);
	}

	@Override
	public String findShortUrlByLong(String longUrl) {
		logger.info("finding short url by long url:-"+longUrl);
		Session session=sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(UrlDo.class);
		cr.add(Restrictions.eq("longUrl", longUrl));
		List<UrlDo> results = cr.list();
		String shortUrl=null;
		if(results.size()>0){
		for (UrlDo urlDo : results) {
			shortUrl=urlDo.getShortUrl();
		}
		}
		logger.info("found short url for long url:-"+ longUrl+"="+shortUrl);
		return shortUrl;
	}

}
