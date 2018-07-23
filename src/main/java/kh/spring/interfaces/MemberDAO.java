package kh.spring.interfaces;


import kh.spring.dto.MemberDTO;

public interface MemberDAO {
	public int joinMember(MemberDTO dto);
	public int memberOut(String id);
	public boolean login(MemberDTO dto);
	public MemberDTO memberInfo(String id);
	public int infoModify(MemberDTO dto);
	
	
}
