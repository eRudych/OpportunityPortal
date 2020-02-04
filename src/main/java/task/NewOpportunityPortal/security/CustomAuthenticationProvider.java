package task.NewOpportunityPortal.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import task.NewOpportunityPortal.entity.User;
import task.NewOpportunityPortal.service.UserService;

import java.util.Collections;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserService service;

    @Override
    public Authentication authenticate(Authentication auth) {
        String login = auth.getName();
        String password = auth.getCredentials()
                .toString();
        User user=service.getUserByLogin(login);
        if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
            return new UsernamePasswordAuthenticationToken
                    (login, password, Collections.emptyList());
        } else {
            throw new
                    BadCredentialsException("External system authentication failed");
        }
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}
