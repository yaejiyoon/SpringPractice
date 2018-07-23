package kh.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
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
		dto.setPw(EncryptUtils.getSha256(dto.getPw()));
	}
	
	@Pointcut("execution(* kh.spring.impl.*Impl.login(..))") 
	public void loginPointCut() {}
	
	@Around("loginPointCut()")
	public boolean encryptLogin(ProceedingJoinPoint pjp) {
		
		String id = pjp.getArgs()[0].toString();
		String pw = pjp.getArgs()[1].toString();
		
		pw = EncryptUtils.getSha256(pw);
		System.out.println(pw);
		
		boolean result = false;
		
		try {
			result = (Boolean)pjp.proceed(new Object[] {id,pw}); 
			
		} catch (Throwable e) {
			e.printStackTrace();
		} 
		
		return result;
		
	}
	
}
