package edu.vku.searchjob.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.io.IOException;

@Component
public class LoginFailureHandlerextends extends SimpleUrlAuthenticationFailureHandler{

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        String error = "";
        if (username.equals("") || password.equals("")) {
            setDefaultFailureUrl("/login?error");
        } else {
            setDefaultFailureUrl("/login?wrong");
        }
//        if (!username.equals("") || !password.equals("")) {
//            System.out.println("Rỗng password");
//        }
        request.setAttribute("msg", "Đằng nhập thất bại");
        request.setAttribute("param", "error");

        String errorMessage = "Tên đăng nhập  hoặc tài khoản không hợp lệ !";

        if (exception instanceof BadCredentialsException) {
            errorMessage = "Invalid Username or Password";
        } else if (exception instanceof InsufficientAuthenticationException) {
            errorMessage = "Invalid Secret key";
        }
        request.setAttribute("errorMessage", errorMessage);


        super.onAuthenticationFailure(request, response, exception);
//        request.setAttribute("errorMessage",errorMessage);
//        request.getRequestDispatcher("/login?errorMessage").forward(request,response);
//        response.sendRedirect("/login");

//        response.sendRedirect(request.getContextPath() + "/login");
//        super.onAuthenticationFailure(request, response, exception);

    }
}

//extends SimpleUrlAuthenticationFailureHandler {
//
//@Override
//public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//        // Đặt thông báo lỗi vào request attribute để sử dụng trong trang đăng nhập
//        request.setAttribute("errorMessage", "Tài khoản hoặc mật khẩu không đúng.");
//
//        // Chuyển hướng đến trang đăng nhập
//        setDefaultFailureUrl("/signIn?error=true");
//        super.onAuthenticationFailure(request, response, exception);
//        }
//        }