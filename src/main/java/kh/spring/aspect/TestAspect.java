package kh.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import kh.spring.dto.MemberDTO;
import kh.spring.util.EncryptUtils;

@Aspect
@Component
public class TestAspect {
	@Pointcut("execution(* kh.spring.impl.*ServiceImpl.joinMember*(..))")
	public void getPointCut() {}
	
	@Before("getPointCut()")
	public void encrypt(JoinPoint jp) {
		MemberDTO dto = (MemberDTO)jp.getArgs()[0];
		dto.setPw(EncryptUtils.getSha256(dto.getPw()));
		
		System.out.println(dto.getPw());
		
	}
	
	@Pointcut("execution(* kh.spring.impl.*ServiceImpl.login*(..))")
	public void getPassword() {}
	
	@Before("getPassword()")
	public void password(JoinPoint jp) {
		MemberDTO dto = (MemberDTO)jp.getArgs()[0];
		
		dto.setPw(EncryptUtils.getSha256(dto.getPw()));
		System.out.println("로그인 : "+dto.getPw());

	}
	
	
}
