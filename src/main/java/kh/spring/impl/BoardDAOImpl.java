package kh.spring.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

	@Override
	public List<BoardDTO> getAllArticles(int startNum, int endNum) {
		String sql = "select * from (select board.*, row_number() over(order by writedate) as num\r\n"
				+ "from board )where (num between ? and ?)";
		List<BoardDTO> result = template.query(sql, new RowMapper<BoardDTO>() {
			@Override
			public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				BoardDTO tmp = new BoardDTO();
				tmp.setSeq(rs.getInt("seq"));
				tmp.setTitle(rs.getString("title"));
				tmp.setContents(rs.getString("contents"));
				tmp.setWriter(rs.getString("writer"));
				tmp.setWritedate(rs.getString("writedate"));
				tmp.setViewcount(rs.getInt("viewcount"));
				tmp.setIp(rs.getString("ip"));
				return tmp;
			}
		}, startNum, endNum);
		return result;
	}

	@Override
	public int write(BoardDTO dto) {
		String sql = "insert into board values(?,?,?,?,sysdate,?,?)";
		return template.update(sql, dto.getSeq(), dto.getTitle(), dto.getContents(), dto.getWriter(),
				dto.getViewcount(), dto.getIp());
	}

	@Override
	public int modify(BoardDTO dto) {
		System.out.println("dao:" + dto.getSeq() + ":" + dto.getTitle() + ":" + dto.getContents());
		String sql = "update board set title=?, contents=?, writedate=sysdate where seq = ?";
		return template.update(sql, dto.getTitle(), dto.getContents(), dto.getSeq());
	}

	@Override
	public BoardDTO getArticle(int seq) {
		String sql = "select * from board where seq = ?";
		return template.queryForObject(sql, new RowMapper<BoardDTO>() {

			@Override
			public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				BoardDTO tmp = new BoardDTO();
				tmp.setSeq(rs.getInt("seq"));
				tmp.setTitle(rs.getString("title"));
				tmp.setContents(rs.getString("contents"));
				tmp.setViewcount(rs.getInt("viewcount"));
				tmp.setWriter(rs.getString("writer"));
				tmp.setWritedate(rs.getString("writedate"));
				tmp.setIp(rs.getString("ip"));
				return tmp;
			}
		}, seq);
	}

	@Override
	public int nextSeq() {
		String sql = "select board_seq.nextval from dual";
		int seq = template.queryForObject(sql, Integer.class);

		return seq;
	}

	@Override
	public int delete(int seq) {
		String sql = "delete from board where seq = ?";
		int result = template.update(sql, seq);
		return result;
	}

	@Override
	public String getBoardPageNavi(int currentPageNo) {
		String sql = "select count(*) as totalCount from board";
		int count = template.queryForObject(sql, Integer.class);
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
		String sql = "insert into comments values(?,comment_seq.nextval,?,?,sysdate,'192.168')";
		return template.update(sql,dto.getArticleNo(),dto.getComment_text(),dto.getWriter());
	}

	
	@Override
	public List<CommentDTO> commentsList(int seq) {
		String sql = "select * from comments where article_no = ?";

		List<CommentDTO> result = template.query(sql, new RowMapper<CommentDTO>() {

			@Override
			public CommentDTO mapRow(ResultSet rs, int rownum) throws SQLException {
				CommentDTO tmp = new CommentDTO();
				
				tmp.setArticleNo(rs.getInt("article_No"));
	            tmp.setComment_seq(rs.getInt("comment_seq"));
	            tmp.setComment_text(rs.getString("comment_text"));
	            tmp.setWriter(rs.getString("writer"));
	            tmp.setWriteDate(rs.getString("writeDate"));
	            tmp.setIp(rs.getString("ip"));
	            
				return tmp;
			}
			
		},seq);
		
		return result;
		
	}

	@Override
	public int commentRemove(int articleNo, int comment_seq) {
		String sql = "delete from comments where article_no=? and comment_seq=?";
		return template.update(sql,articleNo,comment_seq);
	}
	
	

}