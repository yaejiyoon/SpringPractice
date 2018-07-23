package kh.spring.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kh.spring.dto.BoardDTO;
import kh.spring.dto.FilesDTO;
import kh.spring.dto.CommentDTO;
import kh.spring.interfaces.BoardDAO;
import kh.spring.interfaces.BoardService;

@Component
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO dao;
	
	@Override
	public List<BoardDTO> getAllArticles(int startNum, int endNum) {
		return dao.getAllArticles(startNum, endNum); 
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
	public int uploadFile(FilesDTO dto) {
		return dao.uploadFile(dto);
	}

	@Override
	public List<FilesDTO> getFiles(int article_no) {
		return dao.getFiles(article_no);
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
	public int commentRemove(int articleNo, int comment_seq) {
		return dao.commentRemove(articleNo, comment_seq);
	}

}
