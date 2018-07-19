package kh.spring.interfaces;

import java.util.List;

import kh.spring.dto.BoardDTO;

public interface BoardService {
	public List<BoardDTO> getAllArticles();
}
