package content.auth;

import content.users.structure.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {
    private final Users user; // Ссылка на вашу сущность Users

    public CustomUserDetails(Users user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Здесь вы можете вернуть роли пользователя, если они есть
        return null; // Или вернуть список ролей, если они есть
    }

    @Override
    public String getPassword() {
        return user.getPassword(); // Возвращаем пароль пользователя
    }

    @Override
    public String getUsername() {
        return user.getEmail(); // Возвращаем email как имя пользователя
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Логика для проверки, не истек ли срок действия аккаунта
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Логика для проверки, не заблокирован ли аккаунт
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Логика для проверки, не истек ли срок действия учетных данных
    }

    @Override
    public boolean isEnabled() {
        return user.isActive(); // Проверяем, активен ли пользователь
    }

    public Users getUser() {
        return user; // Метод для получения сущности Users
    }
}
