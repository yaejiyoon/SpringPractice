package kh.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kh.spring.dto.BoardDTO;
import kh.spring.interfaces.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;

	@RequestMapping("/boardList.do")
	public ModelAndView toBoardList() {
		List<BoardDTO> result = service.getAllArticles();
	@RequestMapping("/article.do")
	public ModelAndView toArticle() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("article.jsp");
		return mav;
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		mav.setViewName("boardList.jsp");
		return mav;
	}
	}
	
	@RequestMapping("/write.do")
	public ModelAndView toWrite() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("write.jsp");
		return mav;
	}
		
}
