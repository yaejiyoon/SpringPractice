package kh.spring.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kh.spring.dto.MemberDTO;
import kh.spring.interfaces.MemberDAO;
import kh.spring.interfaces.MemberService;

@Component
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDAO dao;
	
	@Override
	public int joinMember(MemberDTO dto) {
		return dao.joinMember(dto);
	}

	@Override
	public int memberOut(String id) {
		return dao.memberOut(id);
	}

	@Override
	public boolean login(String id, String pw) {
		return dao.login(id, pw);
	}

	@Override
	public MemberDTO memberInfo(String id) {
		return dao.memberInfo(id);
	}

	@Override
	public int infoModify(MemberDTO dto) {
		return dao.infoModify(dto);
	}

}
