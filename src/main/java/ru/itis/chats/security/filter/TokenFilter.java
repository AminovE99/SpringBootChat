package ru.itis.chats.security.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.itis.chats.rep.TokenRep;
import ru.itis.chats.security.auth.TokenAuth;
import ru.itis.chats.serv.TokenServiceImpl;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenFilter implements Filter {

    private static final String AUTH_HEADER = "AUTH";
    @Autowired
    private TokenRep tokenRep;

    @Autowired
    private TokenServiceImpl tokenServ;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = response.getHeader(AUTH_HEADER);
        System.out.println(token + " tokenF");
        Cookie cookie[] = request.getCookies();
        boolean norm = false;
        if (request.getRequestURI().equals("/chat")) {
            for (Cookie cookie1 : cookie) {
                if (cookie1.getName().equals("AUTH") && tokenServ.isNotExpired(cookie1.getValue())) norm = true;
            }
        }
        if (token != null && norm) {
            TokenAuth tokenAuth = new TokenAuth();
            tokenAuth.setToken(token);
            SecurityContextHolder.getContext().setAuthentication(tokenAuth);
            response.setHeader(AUTH_HEADER, token);
            filterChain.doFilter(request, response);
            return;
        }
        filterChain.doFilter(request, response);
        return;
    }
}