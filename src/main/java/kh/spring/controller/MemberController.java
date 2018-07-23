package kh.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kh.spring.dto.MemberDTO;
import kh.spring.interfaces.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@RequestMapping("/loginPage.do")
	public String loginPage() {
		return "redirect:login.jsp";
	}
	
	@RequestMapping("/Join.do")
	public String join() {
		return "redirect:join.jsp";
	}
	
	@RequestMapping("/joinProc.do")
	public ModelAndView joinMember(MemberDTO dto) {
		int result = service.joinMember(dto);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		mav.setViewName("joinProc.jsp");
		
		return mav;
	}
	
	@RequestMapping("/login.do")
	public ModelAndView login(String id, String pw, HttpSession session) {
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setPw(pw);
		
		boolean result = service.login(dto);
		
		System.out.println(result);
		
		if(result) {
			session.setAttribute("loginId", id);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		mav.setViewName("loginProc.jsp");
		
		return mav;
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpServletRequest req) {
		req.getSession().invalidate();
		
		return "redirect:login.jsp";
	}
	
	@RequestMapping("memberOut.do")
	public ModelAndView memberOut(HttpServletRequest req) {
		String id = req.getSession().getAttribute("loginId").toString();
		
		int result = service.memberOut(id);
		
		if(result>0) {
			req.getSession().invalidate();
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		mav.setViewName("memberOut.jsp");
		
		return mav;
	}
	
	
	@RequestMapping("/info.do")
	public ModelAndView memberInfo(HttpServletRequest req) {
		String id = req.getSession().getAttribute("loginId").toString();
		
		List<MemberDTO> result = service.memberInfo(id);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		mav.setViewName("memberInfo.jsp");
		
		return mav;
	}
	
	@RequestMapping("/modi.do")
	public ModelAndView infoModify(HttpServletRequest req) {
		String id = req.getSession().getAttribute("loginId").toString();
		
		List<MemberDTO> result = service.memberInfo(id);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		mav.setViewName("infoModify.jsp");
		
		return mav;
	}
	
	@RequestMapping("modiProc.do")
	public ModelAndView modiProc(MemberDTO dto) {
		int result = service.infoModify(dto);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		mav.setViewName("infoModifyProc.jsp");
		
		return mav;
	}
	
}
