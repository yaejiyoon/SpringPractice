package kh.spring.interfaces;

import java.util.List;

import kh.spring.dto.MemberDTO;

public interface MemberDAO {
	public int joinMember(MemberDTO dto);
	public int memberOut(String id);
	public boolean login(MemberDTO dto);
//	public boolean login(String id, String pw);
	public List<MemberDTO> memberInfo(String id);
	public int infoModify(MemberDTO dto);
}
