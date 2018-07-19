package kh.spring.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kh.spring.dto.BoardDTO;
import kh.spring.interfaces.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;

	@Autowired
	private BoardService service;

	
	@RequestMapping("/index.do")
	public ModelAndView toIndex() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index.jsp");
		return mav;

	}
	
	@RequestMapping("/boardList.do")
	public ModelAndView toBoardList() {
		List<BoardDTO> result = service.getAllArticles();
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		mav.setViewName("boardList.jsp");
		return mav;
	}
	
	@RequestMapping("/article.do")
	public ModelAndView toArticle(HttpServletRequest req) {
		int seq = Integer.parseInt(req.getParameter("seq"));
		BoardDTO dto = service.getArticle(seq);

		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", dto);
		mav.setViewName("article.jsp");
		
		return mav;

	}

	@RequestMapping("/write.do")
	public ModelAndView toWrite() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("write.jsp");
		return mav;
	}

	@RequestMapping("/writeProc.do")
	public ModelAndView toWriteProc(@ModelAttribute BoardDTO dto, HttpServletRequest req, HttpServletResponse res) {
		System.out.println("writeProc: " + dto.getTitle() + " : " + dto.getContents());
		String asd = req.getParameter("title");
		System.out.println("asd:"+asd);
		
		String loginId = (String) req.getSession().getAttribute("loginId");

		// dto.setWriter(loginId);
		dto.setWriter("최인형");
		dto.setIp(req.getRemoteAddr());

		int result = service.write(dto);

		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		mav.setViewName("writeProc.jsp");
		return mav;
	}
	
	@RequestMapping("/modify.do")
	public ModelAndView toModify(int seq) {
		System.out.println("modify.do: "+seq);
		BoardDTO dto = service.getArticle(seq);

		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", dto);
		mav.setViewName("modify.jsp");
		
		return mav;
	}
	
	@RequestMapping("/modifyProc.do")
	public ModelAndView toModifyProc(@ModelAttribute BoardDTO dto, HttpServletRequest req) {
		int seq = Integer.parseInt(req.getParameter("seq"));
		dto.setSeq(seq);
		
		int result = service.modify(dto);
		System.out.println(result);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		mav.setViewName("article.do?seq="+seq+"");
		
		return mav;
	}
}
