package kh.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kh.spring.dto.BoardDTO;
import kh.spring.dto.CommentDTO;
import kh.spring.interfaces.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService service;

	@RequestMapping("/index.do")
	public ModelAndView toIndex() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index.jsp");
		return mav;

	}

	@RequestMapping("/boardList.do")
	public ModelAndView toBoardList(HttpServletRequest req) {
		int currentPage = 0;
		String currentPageString = req.getParameter("currentPage");

		if (currentPageString == null) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(currentPageString);
		}
		
		Map<String, Integer> map = new HashMap<>();
		map.put("startNum", currentPage * 10 - 9);
		map.put("endNum", currentPage * 10);

		List<BoardDTO> result = service.getAllArticles(map);
		String page = service.getBoardPageNavi(currentPage);
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		mav.addObject("page",page);
		mav.setViewName("boardList.jsp");
		return mav;
	}

	@RequestMapping("/article.do")
	public ModelAndView toArticle(HttpServletRequest req) {
		int seq = Integer.parseInt(req.getParameter("seq"));
		BoardDTO dto = service.getArticle(seq);
		
		List<CommentDTO> commentList = service.commentsList(seq);
		
		System.out.println("commentList : "+commentList.size());

		ModelAndView mav = new ModelAndView();
		mav.addObject("commentList", commentList);
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

		String loginId = (String) req.getSession().getAttribute("loginId");

		dto.setWriter(loginId);
		dto.setIp(req.getRemoteAddr());

		int nseq = service.nextSeq();
		System.out.println("nseq = " + nseq);
		dto.setSeq(nseq);
		int result = service.write(dto);

		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		mav.addObject("nseq", nseq);
		mav.setViewName("writeProc.jsp");
		return mav;
	}

	@RequestMapping("/modify.do")
	public ModelAndView toModify(int seq) {
		System.out.println("modify.do: " + seq);
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
		mav.setViewName("article.do?seq=" + seq );

		return mav;
	}

	@RequestMapping("/delete.do")
	public ModelAndView toDelete(@RequestParam int seq) {
		int result = service.delete(seq);

		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		mav.setViewName("delete.jsp");
		return mav;
	}
	
	@RequestMapping("/comment.do")
	public ModelAndView comment(CommentDTO dto) {
		int result = service.comment(dto);
		int seq = dto.getArticle_no();
		
		System.out.println("commentdo no : "+seq);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("seq", seq);
		mav.addObject("result", result);
		mav.setViewName("commentProc.jsp");
		
		return mav;
	}
	
	@RequestMapping("/commentRemove.do")
	public ModelAndView commentRemove(String articleNo, String commentNo) {
		
		int article_no = Integer.parseInt(articleNo);
		int comment_seq = Integer.parseInt(commentNo);
		
		System.out.println("article_no" + article_no);
		System.out.println("articleNo"+articleNo);
		
		CommentDTO dto = new CommentDTO();
		dto.setArticle_no(article_no);
		dto.setComment_seq(comment_seq);
		
		System.out.println(dto.getComment_seq());
		System.out.println(dto.getArticle_no());
		int result = service.commentRemove(dto);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		mav.setViewName("article.do?seq=" + article_no );
		
		return mav;
	}
	
}
