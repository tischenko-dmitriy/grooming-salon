package ru.otus.example.grooming.gsclient.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.otus.example.grooming.gsclient.logging.annotations.Action;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
public class LogWriter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @Around(value = "@annotation(ru.otus.example.grooming.gsclient.logging.annotations.Action)")
    public Object writeInfoLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        final String LOG_STRING_TEMPLATE =
                "START-AT: %s METHOD: %s API-METHOD: %s WORK-TIME: %s";
        Date startTime = new Date();
        Object proceed = proceedingJoinPoint.proceed();
        logger.info(String.format(LOG_STRING_TEMPLATE,
                simpleDateFormat.format(startTime),
                proceedingJoinPoint.getSignature(),
                getActionName(proceedingJoinPoint),
                (new Date()).getTime() - startTime.getTime()
        ));
        return proceed;
    }

    private String getActionName(JoinPoint jp) {
        Method method = ((MethodSignature) jp.getSignature()).getMethod();
        return method.getAnnotation(Action.class).name();
    }

}
