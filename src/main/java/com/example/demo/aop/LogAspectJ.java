package com.example.demo.aop;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 10337
 * 	使用Slf4j记录日志，以后切换实现的时候可以不修改代码，只修改依赖就行
 */
@Aspect
@Component
@Slf4j
public class LogAspectJ {

	//日志记录切点，匹配所有service层方法，记录service层日志
	// execution(方法修饰符(可选)  返回类型  类路径 方法名  参数  异常模式(可选)) 
	// 1）execution(public * *(..))——表示匹配所有public方法
	// 2）execution(* set*(..))——表示所有以“set”开头的方法
	// 3）execution(* com.xyz.service.AccountService.*(..))——表示匹配所有AccountService接口的方法
	// 4）execution(* com.xyz.service.*.*(..))——表示匹配service包下所有的方法
	// 5）execution(* com.xyz.service..*.*(..))——表示匹配service包和它的子包下的方法
	@Pointcut("execution(* com.example.demo.service..*.*(..))")
	public void logAspect() {};
	
	@Before("logAspect()")
	public void befordMethod(JoinPoint joinPoint) {
		
		//spring提供的RequestContextHolder可以用来获取请求信息
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
		
		log.info("前置通知 : {}.{}开始执行", joinPoint.getSignature().getDeclaringTypeName(),joinPoint.getSignature().getName());
		log.info("参数列表 : {}", Arrays.toString(joinPoint.getArgs()));
		log.info("请求类型 : {}", request.getMethod());
	}
	
	@AfterReturning(returning="returnObj",pointcut="logAspect()")
	private void afterReturn(JoinPoint joinPoint, Object returnObj) {
		log.info("后置通知 : {}.{}执行结束,返回值为 : {}", joinPoint.getSignature().getDeclaringTypeName(),  joinPoint.getSignature().getName(), returnObj != null ? returnObj.toString() : "");
	}
	
	@AfterThrowing(pointcut="logAspect()",throwing="ex")
	public void afterThrowException(JoinPoint joinPoint, Exception ex) {
		log.error("异常通知 : {}.{},异常为 : {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), ex.getMessage());
	}
}
