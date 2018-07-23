package kh.spring.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kh.spring.dto.BoardDTO;
import kh.spring.dto.CommentDTO;
import kh.spring.interfaces.BoardDAO;
import kh.spring.interfaces.BoardService;

@Component
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO dao;
	
	@Override
	public List<BoardDTO> getAllArticles(Map<String,Integer> map) {
		return dao.getAllArticles(map); 
	}

	@Override
	public int write(BoardDTO dto) {
		return dao.write(dto);
	}

	@Override
	public int modify(BoardDTO dto) {
		return dao.modify(dto);
	}

	@Override
	public BoardDTO getArticle(int seq) {
		return dao.getArticle(seq);
	}

	@Override
	public int nextSeq() {
		return dao.nextSeq();
	}

	@Override
	public int delete(int seq) {
		return dao.delete(seq);
	}

	@Override
	public String getBoardPageNavi(int currentPageNo) {
		return dao.getBoardPageNavi(currentPageNo);
	}

	@Override
	public int comment(CommentDTO dto) {
		return dao.comment(dto);
	}

	@Override
	public List<CommentDTO> commentsList(int seq) {
		return dao.commentsList(seq);
	}

	@Override
	public int commentRemove(CommentDTO dto) {
		return dao.commentRemove(dto);
	}

}
