package content.auth;

import content.handler.exceptions.UnauthorizedException;
import content.users.structure.Users;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthenticationAspect {

    @Autowired
    private CurrentUserService currentUserService;

    @Around("@annotation(RequiresAuthentication)") // Укажите аннотацию, которую вы будете использовать
    public Object checkAuthentication(ProceedingJoinPoint joinPoint) throws Throwable {
        Users currentUser = currentUserService.getCurrentUser();
        if (currentUser == null) {
            throw new UnauthorizedException("Неавторизованный доступ");
        }
        return joinPoint.proceed(); // Продолжаем выполнение метода
    }
}

