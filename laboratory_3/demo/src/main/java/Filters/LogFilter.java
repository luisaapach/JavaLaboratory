package Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "LogFilter", urlPatterns = {"/input.jsp","/result.jsp"}, dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ERROR, DispatcherType.INCLUDE})
public class LogFilter implements Filter {
    private FilterConfig filterConfig;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletContext context = this.filterConfig.getServletContext();
        context.log("ServerName - "+servletRequest.getServerName() + " Client IP - " + servletRequest.getRemoteAddr());
        context.log(" Parameters - ");
//        for (Map.Entry<String,String[]> entry : servletRequest.getParameterMap().entrySet()) {
//            StringBuilder s = new StringBuilder("");
//            s.append("Key = ").append(entry.getKey()).append(" : [");
//            for (String value : entry.getValue()){
//               s.append(value);
//            }
//            s.append("]");
//            context.log(s.toString());
//        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }
}
