package Filters;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "ResponseFilter", urlPatterns = {"/result.jsp","/error.jsp"}, dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ERROR, DispatcherType.INCLUDE})
public class ResponseFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ResponseWrapper wrapper = new ResponseWrapper( (HttpServletResponse)servletResponse );
        filterChain.doFilter(servletRequest, wrapper);
        // after I send the request, I process the response
        String content = wrapper.toString();
        Document parsedDoc = Jsoup.parse(content);
        Elements titles = parsedDoc.select("h1");

        for (Element title : titles) {
            title.attr("style","border: 5px outset red;\n" +
                    "  background-color: lightblue;\n" +
                    "  text-align: center;");
        }
        PrintWriter out = servletResponse.getWriter();
        System.out.println(parsedDoc.toString());
        out.write(parsedDoc.toString());
    }
}
