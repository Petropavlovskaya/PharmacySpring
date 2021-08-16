package by.petropavlovskaja.springpharmacy.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
public class AspectForLogging {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(public * by.petropavlovskaja.springpharmacy.dao.AccountDaoImpl.*(..))")
    public void callAtAllPublicService() {
    }

    @Before("callAtAllPublicService()")
    public void beforeCallingPublicService(JoinPoint jp) {
        String args = Arrays.stream(jp.getArgs())
                .map(a->a.toString())
                .collect(Collectors.joining(","));
        logger.info("before " + jp.toString() + ", args = [" + args + "]");

        System.out.println("------------------ AspectJ -------------------");
        logger.info("Test logger information!!!");
    }


}
