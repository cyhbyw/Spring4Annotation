package com.cyh.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAspects {

    @Pointcut("execution(public int com.cyh.aop.MathCalculator.*(..))")
    public void pointcut() {}

    /**
     * 可以使用类名+方法名
     */
    @Before("com.cyh.aop.LogAspects.pointcut()")
    public void logStart(JoinPoint joinPoint) {
        System.out.println(
                "@Before: " + joinPoint.getSignature().getName() + " 运行，参数列表是: " + Arrays.asList(joinPoint.getArgs()));
    }

    /**
     * 前置通知是在方法执行之前，一定执行
     * 对应地理解：后置通知是在方法执行之后，也一定执行
     * @param joinPoint
     */
    @After("com.cyh.aop.LogAspects.pointcut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println("@After: " + joinPoint.getSignature().getName() + " 结束");
    }

    /**
     * 正常返航
     * JoinPoint joinPoint 一定要写在第一位
     */
    @AfterReturning(value = "pointcut()", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result) {
        System.out.println("@AfterReturning: " + joinPoint.getSignature().getName() + " 正常结束，结果是: " + result);
    }

    @AfterThrowing(value = "pointcut()", throwing = "e")
    public void logException(JoinPoint joinPoint, Exception e) {
        System.out.println("@AfterThrowing: " + joinPoint.getSignature().getName() + " 出异常啦: " + e);
    }
}
