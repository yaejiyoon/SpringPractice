package kh.spring.interfaces;

import java.util.List;

import kh.spring.dto.BoardDTO;

public interface BoardService {
	public List<BoardDTO> getAllArticles();

	public int write(BoardDTO dto);

	public int modify(BoardDTO dto);

	public BoardDTO getArticle(int seq);

	public int nextSeq();
	
	public int delete(int seq);
}
