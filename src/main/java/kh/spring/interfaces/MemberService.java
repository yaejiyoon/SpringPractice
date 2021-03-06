package kh.spring.interfaces;

import java.util.List;

import kh.spring.dto.MemberDTO;

public interface MemberService {
	public int joinMember(MemberDTO dto);
	public boolean login(MemberDTO dto);
	public int memberOut(String id);
	public MemberDTO memberInfo(String id);
	public int infoModify(MemberDTO dto);
}
