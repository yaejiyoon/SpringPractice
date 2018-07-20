package kh.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import kh.spring.dto.MemberDTO;
import kh.spring.util.EncryptUtils;

@Aspect
@Component
public class TestAspect {
	
	@Pointcut("execution(* kh.spring.impl.*Impl.joinMember(..))") 
	public void encryptPwPointCut() {}
	
	@Before("encryptPwPointCut()")
	public void encryptPw(JoinPoint jp) {
		MemberDTO dto = (MemberDTO)jp.getArgs()[0];
		dto.setPw(EncryptUtils.getSha512(dto.getPw()));
	}
	
	@Pointcut("execution(* kh.spring.impl.*Impl.login(..))") 
	public void loginPointCut() {}
	
	@Before("loginPointCut()")
	public String encryptLogin(JoinPoint jp) {
		String pw = (String) jp.getArgs()[0];
		System.out.println(EncryptUtils.getSha512(pw));
		return EncryptUtils.getSha512(pw);
	}
	
}
