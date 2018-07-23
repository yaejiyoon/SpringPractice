package kh.spring.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import kh.spring.dto.MemberDTO;
import kh.spring.interfaces.MemberDAO;

@Component
public class MemberDAOImpl implements MemberDAO{

	@Autowired
	//private JdbcTemplate template;
	private SqlSessionTemplate template;
	
	@Override
	public int joinMember(MemberDTO dto) {
		return template.insert("Member.joinMember",dto);
	}
	
	@Override
	public int memberOut(String id) {
		return template.delete("Member.memberOut",id);
	}
	
	@Override
	public boolean login(MemberDTO dto) {
		boolean result = false;

		int count = template.selectOne("Member.login",dto);

		if (count > 0) {
		    result = true;
		}

		return result;
	}
	
	@Override
	public List<MemberDTO> memberInfo(String id) {
		
		return template.selectList("Member.memberInfo",id);

	}
	
	@Override
	public int infoModify(MemberDTO dto) {
		return template.update("Member.infoModify",dto);
	}
	

}
