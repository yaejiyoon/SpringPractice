package kh.spring.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import kh.spring.dto.BoardDTO;
import kh.spring.dto.CommentDTO;
import kh.spring.dto.MemberDTO;
import kh.spring.interfaces.BoardDAO;

@Component
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	private JdbcTemplate template;
	
	@Autowired
	private SqlSessionTemplate template2;

	@Override
	public List<BoardDTO> getAllArticles(Map<String,Integer> map) {
	
		return template2.selectList("Board.getAllArticles",map);
	}

	@Override
	public int write(BoardDTO dto) {
		return template2.insert("Board.write",dto);
	}

	@Override
	public int modify(BoardDTO dto) {
		return template2.update("Board.modify",dto);
	}

	@Override
	public BoardDTO getArticle(int seq) {
		return template2.selectOne("Board.getArticle",seq);
	}

	@Override
	public int nextSeq() {
		return template2.selectOne("Board.nextSeq");
	}

	@Override
	public int delete(int seq) {
		return template2.delete("Board.delete",seq);
	}

	@Override
	public String getBoardPageNavi(int currentPageNo) {
		int count = template2.selectOne("Board.getBoardPageNavi");
		System.out.println("navi: " + count);

		int recordTotalCount = count;

		int recordCountPerPage = 10;
		int naviCountPerPage = 10;
		int pageTotalCount = 0;

		if (recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}

		int currentPage = currentPageNo;

		if (currentPage < 1) {
			currentPage = 1;
		} else if (currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		
		// 네비게이터 스타트번호와 끝번호 나타내기
		int startNavi = (currentPage - 1) / naviCountPerPage * naviCountPerPage + 1;
		// 네비게이터가 시작 값; 딱 떨어지는 값의 시작페이지가 이상하기 때문에 1을 빼줘야한다.
		// currentPage / naviCountPerPage * naviCountPerPage + 1;
		int endNavi = startNavi + (naviCountPerPage - 1); // 네비게이터 끝 값

		System.out.println("startNavi:" + startNavi);
		System.out.println("endNavi:" + endNavi);

		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}

		boolean needPrev = true;
		boolean needNext = true;

		if (startNavi == 1) {
			needPrev = false;
		}

		if (endNavi == pageTotalCount) {
			needNext = false;
		}

		StringBuilder sb = new StringBuilder();

		if (needPrev) {
			sb.append("<li><a href='boardList.do?currentPage=" + (startNavi - 1)
					+ "'aria-label='Previous'><span aria-hidden='true'>&raquo;</span></a></li>");
		}

		for (int i = startNavi; i <= endNavi; i++) {
			if (currentPage == i) {
				sb.append("<a href='boardList.do?currentPage=" + i + "'> <b>" + i + "</b></a>");
			} else {
				sb.append("<a href='boardList.do?currentPage=" + i + "'>" + i + "</a>");
			}

			if (needNext) {
				sb.append("<a href='boardList.do?currentPage=" + (endNavi + 1)
						+ "'aria-label='Next'><span aria-hidden='true'>&raquo;</span></a>");
			}

		}
		return sb.toString();
	}

	@Override
	public int comment(CommentDTO dto) {
		return template2.insert("Board.comment",dto);
	}

	
	@Override
	public List<CommentDTO> commentsList(int seq) {
		return template2.selectList("Board.commentsList",seq);
		
	}

	@Override
	public int commentRemove(CommentDTO dto) {
		return template2.delete("Board.commentRemove",dto);
	}
	
	

}