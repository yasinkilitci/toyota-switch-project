package com.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Pointcut;
import org.spring.util.SpringFactoryProvider;
import org.springframework.context.ApplicationContext;

@Aspect
public class LoggingAspect {
	
	/*
	@Before("execution(public * *(..))")
	public void logging_PublicAdvice(){
		System.err.println("LOGGER: Bir Public Metot Çalýþacak!");
	}
	*/
	
	@Before("execution(public * com.dao.UserDAO.LoginYap(..))")
	public void logging_LoginAdvice(JoinPoint joinpoint){
		System.err.println("LOGGER: Kullanýcý Giriþ Yaptý!");
		Object args[] = joinpoint.getArgs();
		String kuladi = (String)args[0];
		String sifre = (String)args[1];
		ApplicationContext ctx = SpringFactoryProvider.getApplicationContext();
		LoggingService service = ctx.getBean("loggingService",LoggingService.class);
		String mesaj = service.prepareLogMessageForLogin(kuladi, sifre);
		service.insertLogMessage(mesaj);
	}
	
	@After("execution(protected void com.servlets.LogoutServlet.doPost(..))")
	public void logging_LogoutAdvice(){
		System.err.println("LOGGER: Kullanýcý Çýkýþ Yaptý!");
	}
	
	/* Shortcut to Pointcut Method */
	@After("encryptionMethod()")
	public void logging_EncrytionAdvice(){
		System.err.println("LOGGER: SHA-256 Þifreleme Yapýldý!");
	}
	
	/* Pointcuts are dummy methods of the same execution placeholders.
	 * You can call @After("nameofdummymethod") on anything about the same execution */
	@Pointcut("within(com.encryption.PasswordCodec)")
	public void encryptionMethod(){}
	
}
