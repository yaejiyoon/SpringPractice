package kh.spring.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import kh.spring.dto.MemberDTO;
import kh.spring.interfaces.MemberDAO;

@Component
public class MemberDAOImpl implements MemberDAO{

	@Autowired
	private JdbcTemplate template;
	
	@Override
	public int joinMember(MemberDTO dto) {
		String sql = "insert into member values(member_seq.nextval,?,?,?,?)";
		return template.update(sql,dto.getId(),dto.getPw(),dto.getName(),dto.getEmail());
	}
	
	@Override
	public int memberOut(String id) {
		String sql = "delete from member where id=?";
		return template.update(sql,id);
	}



	@Override
	public boolean login(MemberDTO dto) {
		String sql = "select count(*) from member where id=? and pw=?";
		boolean result = false;

		int count = template.queryForObject(
		                    sql, new Object[] { dto.getId(),dto.getPw() }, Integer.class);

		if (count > 0) {
		    result = true;
		}

		return result;
	}
	
	@Override
	public MemberDTO memberInfo(String id) {
		String sql = "select * from member where id=?";

	      RowMapper<MemberDTO> mapper = new RowMapper<MemberDTO>() {
	         public MemberDTO mapRow(ResultSet rs, int rowNum) {
	        	 MemberDTO tmp = new MemberDTO();
	            try {
	               tmp.setSeq(rs.getInt("seq"));
	               tmp.setId(rs.getString("id"));
	               tmp.setPw(rs.getString("pw"));
	               tmp.setName(rs.getString("name"));
	               tmp.setEmail(rs.getString("email"));
	               
	            } catch (SQLException e) {
	               e.printStackTrace();
	            }
	            return tmp;
	         }
	      };

	      return this.template.queryForObject(sql, mapper, id);

	}
	@Override
	public int infoModify(MemberDTO dto) {
		String sql = "update member set pw=?,name=?,email=? where seq=?";
		return template.update(sql,dto.getPw(),dto.getName(),dto.getEmail(),dto.getSeq());
	}
	

}
