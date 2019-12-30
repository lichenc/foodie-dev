package com.imooc.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceLogAspect {
    private static Logger logger = LoggerFactory.getLogger(ServiceLogAspect.class);
    /**
     * AOP的通知有五种：
     * ①前置通知：在方法调用之前执行
     * ②后置通知：在方法正常调用之后执行
     * ③环绕通知：在方法调用之前和之后，都分别可以执行的通知
     * ④异常通知：如果在方法调用过程中发送异常，则通知
     * ⑤最终通知：在方法调用之后执行，可以理解为try catch的finally
     * 第一步 * 代表接收所有类型
     * 第二步 com.imooc.service.impl 检测的包名
     * 第三步 .. 代表该包以及其子包下的所有类方法
     * 第四步 *  代表类名，*代表所有类
     * 第五步 *(..) *代表类中的方法名，(..)表示方法中的任何参数
     */
     @Around("execution(* com.imooc.service.impl..*.*(..))")
     public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {
         logger.info("======== 执行{}.{}========",
                 joinPoint.getTarget().getClass(),
                 joinPoint.getSignature().getName());
         Long startTime = System.currentTimeMillis();

         Object result = joinPoint.proceed();//执行方法

         Long endTime = System.currentTimeMillis();
         Long takeTime = endTime - startTime;

         if (takeTime > 3000){
             logger.error("接口调用时间过长，用时{}毫秒",takeTime);
         }else if(takeTime > 2000){
             logger.warn("接口调用时间为{}毫秒",takeTime);
         }else {
             logger.info("接口调用时间为{}毫秒",takeTime);
         }
         return result;
     }
}
