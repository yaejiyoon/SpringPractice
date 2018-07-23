package kh.spring.interfaces;

import java.util.List;
import java.util.Map;

import kh.spring.dto.BoardDTO;
import kh.spring.dto.CommentDTO;

public interface BoardService {
	public List<BoardDTO> getAllArticles(Map<String,Integer> map);

	public int write(BoardDTO dto);

	public int modify(BoardDTO dto);

	public BoardDTO getArticle(int seq);
	
	public int comment(CommentDTO dto);
	
	public List<CommentDTO> commentsList(int seq);

	public int nextSeq();

	public int delete(int seq);

	public String getBoardPageNavi(int currentPageNo);
	
	public int commentRemove(CommentDTO dto);
}
