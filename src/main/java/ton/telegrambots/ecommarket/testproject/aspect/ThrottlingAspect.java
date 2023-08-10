package ton.telegrambots.ecommarket.testproject.aspect;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ton.telegrambots.ecommarket.testproject.service.ThrottlingService;

@Aspect
@Component
public class ThrottlingAspect {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ThrottlingService throttlingService;

    @Pointcut("@annotation(ton.telegrambots.ecommarket.testproject.aspect.Throttled)")
    public void isAnnotatedPointcut() {
    }

    @Around("isAnnotatedPointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        var address = request.getRemoteAddr();
        if (address != null) {
            throttlingService.enforce(address);
        }
        return pjp.proceed();
    }
}
