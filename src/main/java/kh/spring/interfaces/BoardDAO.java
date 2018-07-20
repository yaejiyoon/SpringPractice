package kh.spring.interfaces;

import java.util.List;

import kh.spring.dto.BoardDTO;
import kh.spring.dto.CommentDTO;

public interface BoardDAO {
	public List<BoardDTO> getAllArticles();
	
	public int write(BoardDTO dto);
	
	public int modify(BoardDTO dto);
	
	public BoardDTO getArticle(int seq);
	
	public int nextSeq();
	
	public int delete(int seq);
	
	
	public int comment(CommentDTO dto);
	
	public List<CommentDTO> commentsList(int seq);
}
