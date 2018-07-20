package kh.spring.interfaces;

import java.util.List;

import kh.spring.dto.BoardDTO;

public interface BoardDAO {
	public List<BoardDTO> getAllArticles(int startNum, int endNum);
	
	public int write(BoardDTO dto);
	
	public int modify(BoardDTO dto);
	
	public BoardDTO getArticle(int seq);
	
	public int nextSeq();
	
	public int delete(int seq);
	
	public String getBoardPageNavi(int currentPageNo); 
	
}
