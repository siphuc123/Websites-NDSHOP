package com.nhom3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nhom3.dao.*;
import com.nhom3.service.*;

@Controller
public class HomeController {

	@Autowired
	SessionService sessionService;
	@Autowired
	CookieService cookieService;
	@Autowired
	SecurityRestTemplate restTemplate;
	@Autowired
	ProductDAO prdao;

	@RequestMapping({ "/", "/home/index", "/home" })
	public String home() {
//		if (!lang.isEmpty()) {
//		if (lang.get().equals("en")) {
//			sessionService.set("flag", "united-kingdom.png");
//			cookieService.add("language1", "en", 240);
//			sessionService.set("language", "en");
//			return "page/index_en";
//		} else if (lang.get().equals("vi")) {
//			sessionService.set("flag", "vietnam.png");
//			cookieService.add("language1", "vi", 240);
//			sessionService.set("language", "vi");
//			return "page/index";
//		}
//	}
//	String language = "vi";
//	model.addAttribute("flag", "vietnam.png");
//	if (cookieService.get("language1") != null) {
//		language = cookieService.get("language1").getValue();
//		sessionService.set("language", language);
//		if (language.equals("vi")) {
//			sessionService.set("flag", "vietnam.png");
//			return "page/index";
//		} else if (language.equals("en")) {
//			sessionService.set("flag", "united-kingdom.png");
//			return "page/index_en";
//		}
//	} else {
//		cookieService.add("language1", "vi", 240);
//		sessionService.set("language", "vi");
//	}
		return "page/index";
	}

	@RequestMapping({ "/admin", "/admin/home/index", "/admin/home" })
	public String admin() {
		return "redirect:/admin/index.html";
	}
}
