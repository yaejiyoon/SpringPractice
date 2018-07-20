package kh.spring.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import kh.spring.dto.BoardDTO;
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
		
		
		String path = req.getSession().getServletContext().getRealPath("/files/");
		
		Map returnObject = new HashMap();
		
		try {
			MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) req;
			Iterator iter = mhsr.getFileNames(); 
			MultipartFile mfile = null; 
			String fieldName = ""; 
			List resultList = new ArrayList();
			
			//  디렉토리가 없다면 생성
			File dir = new File(path); 
			if (!dir.isDirectory()) { 
				dir.mkdirs(); }
			
			// 값이 나올때까지 
			while (iter.hasNext()) { 
				fieldName = (String) iter.next(); // 내용을 가져와서 
				mfile = mhsr.getFile(fieldName); 
				String origName; origName = new String(mfile.getOriginalFilename().getBytes("8859_1"), "UTF-8"); //한글꺠짐 방지 
				
				// 파일명이 없다면 
				if ("".equals(origName)) { 
					continue; 
					} 
				
				// 파일 명 변경(uuid로 암호화) 
				String ext = origName.substring(origName.lastIndexOf('.')); // 확장자 
				String saveFileName = getUuid() + ext; 
				// 설정한 path에 파일저장 
				File serverFile = new File(path + File.separator + saveFileName); 
				mfile.transferTo(serverFile); 
				
				Map file = new HashMap(); 
				file.put("origName", origName); 
				file.put("sfile", serverFile); 
				resultList.add(file); 
				}
			
			returnObject.put("files", resultList); 
			returnObject.put("params", mhsr.getParameterMap());



		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
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
	
	private String getUuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
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
		mav.setViewName("article.do?seq=" + seq + "");

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
}
