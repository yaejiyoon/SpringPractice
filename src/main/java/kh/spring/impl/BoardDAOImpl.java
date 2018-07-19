package kh.spring.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import kh.spring.dto.BoardDTO;
import kh.spring.interfaces.BoardDAO;

@Component
public class BoardDAOImpl implements BoardDAO{
	
	@Autowired
	private JdbcTemplate template;

	@Override
	public List<BoardDTO> getAllArticles() {
		String sql = "select * from board";
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
		});
		return result;
	}

}
