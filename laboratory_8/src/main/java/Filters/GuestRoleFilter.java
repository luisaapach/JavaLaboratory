package Filters;

import Entities.UserEntity;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GuestRoleFilter implements Filter {
    @Override
    public void init(FilterConfig cfg) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain next) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;

        UserEntity currentUser = (UserEntity) request.getSession().getAttribute("user");
        if(currentUser==null || !currentUser.getRole().equals("guest"))
        {
            HttpServletResponse r = (HttpServletResponse) response;
            r.sendRedirect(request.getContextPath() + "/login.xhtml");
            return;
        }

        next.doFilter(req, response);
    }

    @Override
    public void destroy() {
    }
}