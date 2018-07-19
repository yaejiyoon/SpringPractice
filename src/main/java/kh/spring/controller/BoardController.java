package kh.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kh.spring.dto.BoardDTO;

@Controller
public class BoardController {

	@RequestMapping("/article.do")
	public ModelAndView toArticle() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("article.jsp");
		return mav;
		
	}
	
	@RequestMapping("/write.do")
	public ModelAndView toWrite() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("write.jsp");
		return mav;
	}
		
}
