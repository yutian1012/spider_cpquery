package org.ipph.spider.aop;

import org.aspectj.lang.JoinPoint;  
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Page;  

@Component
@Aspect
public class LogAspect {
	
	private static Logger logger=LoggerFactory.getLogger(LogAspect.class);
	
	@Pointcut(value = "@annotation(pageProcessorLog)")
    public void log(PageProcessorLog pageProcessorLog){}  
  
    @Before("log(pageProcessorLog)")  
    public void deBefore(JoinPoint joinPoint,PageProcessorLog pageProcessorLog) throws Throwable {  
    	
    	System.out.println("**********************************************************************");
    	
    	Page page=null;
    	
    	Object[] args=joinPoint.getArgs();
    	if(null!=args&&args.length>0) {
    		if(args[0] instanceof Page) {
    			page=(Page)args[0];
    		}
    	}
    	
    	if(null==page) {
    		logger.info("无法解析数据对象!!!!");
    	}else {
    		logger.info("准备解析数据:"+page.getRequest().getUrl());
    	}
    	
    }  
  
   /* @AfterReturning(returning = "ret", pointcut = "webLog()")  
    public void doAfterReturning(Object ret) throws Throwable {  
        // 处理完请求，返回内容  
        System.out.println("方法的返回值 : " + ret);  
    }  
  
    //后置异常通知  
    @AfterThrowing("webLog()")  
    public void throwss(JoinPoint jp){  
        System.out.println("方法异常时执行.....");  
    }  
  
    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行  
    @After("webLog()")  
    public void after(JoinPoint jp){  
        System.out.println("方法最后执行.....");  
    }  
  
    //环绕通知,环绕增强，相当于MethodInterceptor  
    @Around("webLog()")  
    public Object arround(ProceedingJoinPoint pjp) {
        System.out.println("方法环绕start.....");  
        try {  
            Object o =  pjp.proceed();  
            System.out.println("方法环绕proceed，结果是 :" + o);  
            return o;  
        } catch (Throwable e) {
            e.printStackTrace();  
            return null;  
        }  
    }  */
}
