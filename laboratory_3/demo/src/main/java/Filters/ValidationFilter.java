package Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

@WebFilter(filterName = "ValidationFilter", urlPatterns = {"/Controller"}, dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ERROR, DispatcherType.INCLUDE})
public class ValidationFilter implements Filter {
    private ServletRequestWrapper wrapper = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        if(httpRequest.getMethod().equalsIgnoreCase("POST")){
            boolean wordEmpty = false;
            boolean wordFounded;
            boolean descriptionEmpty = false;
            boolean descriptionFounded;
            boolean languageEmpty = false;
            boolean languageFounded;

            String word = servletRequest.getParameter("word");
            wordFounded = (word != null);
            if(wordFounded) wordEmpty=word.length()==0;
            String description = servletRequest.getParameter("definition");
            descriptionFounded = (description != null);
            if(descriptionFounded) descriptionEmpty=description.length()==0;

            if(!wordFounded || !descriptionFounded || descriptionEmpty || wordEmpty) {
                servletRequest.setAttribute("supported_languages",new Utils.Languages().getLanguages());
                servletRequest.getRequestDispatcher("/input.jsp").forward(servletRequest, servletResponse);
                return;
            }

            String language = servletRequest.getParameter("language");
            languageFounded = language!=null;
            if(languageFounded)languageEmpty=language.length()==0;

            if(!languageFounded || languageEmpty){
                wrapper = new HttpServletRequestWrapper((HttpServletRequest) servletRequest) {
                    @Override
                    public String getParameter(String name) {
                        if (name.equals("language"))
                        {
                            return this.getServletContext().getInitParameter("defaultLanguage");
                        }
                        else
                        {
                            return servletRequest.getParameter(name);
                        }

                    }
                };
            }
            if(wrapper!=null){
                filterChain.doFilter(wrapper,servletResponse);
            }
            else{
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }
        else
            filterChain.doFilter(servletRequest,servletResponse);

        }


}
