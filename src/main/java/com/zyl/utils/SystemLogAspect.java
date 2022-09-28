package com.zyl.utils;

import com.zyl.annotation.SystemControllerLog;
import com.zyl.entity.Log;
import com.zyl.mapper.LogMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import cn.hutool.core.date.DateUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Date;

@Aspect
@Component
public class SystemLogAspect {

    @Resource
    private LogMapper logMapper;
    /***
     * 定义controller切入点拦截规则，拦截SystemControllerLog注解的方法
     */
//    @Pointcut("@annotation(com.zyl.annotation.SystemControllerLog)")
//    public void controllerAspect(){}

    @Around("@annotation(systemControllerLog)")
    public Object recordLog(ProceedingJoinPoint joinPoint,SystemControllerLog systemControllerLog) throws Throwable{
//        // 获取方法签名
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        // 获取方法
//        Method method = methodSignature.getMethod();
//        //获取方法的注解
//        SystemControllerLog systemControllerLog
//                =method.getAnnotation(SystemControllerLog.class);
        //注解名字获取
        String operateType = systemControllerLog.descrption();
        System.out.println("operateType = " + operateType);
        Log systemLog = new Log();

//        systemLog.setOp(operateType);
        //从安全上下文中获取用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        System.out.println("username888888888888888888888888 = " + username);
        systemLog.setContent(operateType);
        systemLog.setUser(username);

        //获取ip地址
        String ip = HttpContextUtil.getIpAddress();
        systemLog.setIp(ip);
        Object result = null;
        try {
            result=joinPoint.proceed();
//            systemLog.setOp("正常");
        } catch (SQLException e) {
            // 相当于异常通知部分
//            systemLog.set("失败");// 设置操作结果
        } finally {
            // 相当于最终通知
            systemLog.setTime(DateUtil.formatDateTime(new Date()));//获取时间
            logMapper.insertSelective(systemLog);// 添加日志记录
        }
        return result ;

    }
}
