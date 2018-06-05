package org.prtiny.generator.controller;

import java.util.Date;

import org.prtiny.generator.reqres.UrlResponse;
import org.prtiny.generator.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/urlgenerator")
public class UrlController {
	
	private static final String TINYDOMAIN = "https://prtiny.ly/";
	private static final String SUCCESS = "success";
	private static final int SUCCESS_CODE = 200;
	private static final int WARNING_CODE = 422;
	private static final String ERROR = "error";
	private static final int ERROR_CODE = 500;

	@Autowired
	UrlService urlService;

	@RequestMapping(value = "shortUrl", method = RequestMethod.POST)
	public ModelAndView getShortUrl(
			@RequestParam(value = "longUrl") String longUrl,ModelAndView model) {
		UrlResponse res = new UrlResponse();
		Date date = new Date();
		model = new ModelAndView("index");
		if (longUrl != null && longUrl != "") {
			try {
				res.setShortUrl(TINYDOMAIN+urlService.generateShortUrl(longUrl, date));
				res.setLongUrl(longUrl);
				res.setStatus(SUCCESS);
				res.setCode(SUCCESS_CODE);
				model.addObject("response",res);
			} catch (Exception e) {
				res.setLongUrl(longUrl);
				res.setStatus(ERROR);
				res.setCode(ERROR_CODE);
				model.addObject("response",res);
			}
		}
		return model;

	}

	@RequestMapping(value = "longUrl", method = RequestMethod.POST)
	public ModelAndView getLongUrl(
			@RequestParam(value = "shortUrl") String shortUrl,ModelAndView model) {
		UrlResponse res = new UrlResponse();
		model = new ModelAndView("index");
		String newShortUrl=shortUrl.substring(shortUrl.lastIndexOf("/") + 1);
		System.out.println("newShortUrl="+newShortUrl);
		try {
		if (newShortUrl != null && !newShortUrl.isEmpty()) {		
			if(urlService.getLongUrl(newShortUrl) != null){
				res.setLongUrl(urlService.getLongUrl(newShortUrl));
				res.setShortUrl(shortUrl);
				res.setStatus(SUCCESS);
				res.setCode(SUCCESS_CODE);
				model.addObject("response",res);
			}else{
				res.setShortUrl(shortUrl);
				res.setStatus("Please give the tiny Url");
				res.setCode(WARNING_CODE);
				model.addObject("response",res);
			}
			} else{
				res.setShortUrl(shortUrl);
				res.setStatus("Please give the tiny Url");
				res.setCode(WARNING_CODE);
				model.addObject("response",res);
			}
		}catch (Exception e) {
			res.setShortUrl(shortUrl);
			res.setStatus(ERROR);
			res.setCode(ERROR_CODE);
			model.addObject("response",res);
		}
		return model;

	}

}
