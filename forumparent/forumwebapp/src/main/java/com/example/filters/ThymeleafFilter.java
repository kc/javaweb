package com.example.filters;

import com.example.ThymeleafWebApplication;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * In this filter we make the thymeleaf functionality available via the WebshopApplication.
 * An instance is added as a request attribure. In this way the thymeleaf fucntionality is available for all requests.
 */
@WebFilter(urlPatterns = "/*")
public class ThymeleafFilter implements Filter {

    private ServletContext servletContext;
    private ThymeleafWebApplication application;

    public void init(final FilterConfig filterConfig) throws ServletException {
        this.servletContext = filterConfig.getServletContext();
        this.application = new ThymeleafWebApplication(this.servletContext);
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        setEngineOnRequestThymeleafURIs((HttpServletRequest) request, (HttpServletResponse) response);
        chain.doFilter(request, response);
    }

    private void setEngineOnRequestThymeleafURIs(HttpServletRequest request, HttpServletResponse response) {
        // This prevents triggering engine executions for resource URLs
        if (request.getRequestURI().startsWith("/css") ||
                request.getRequestURI().startsWith("/images") ||
                request.getRequestURI().startsWith("/favicon")) {
        } else {
            request.setAttribute("engine", application.getTemplateEngine());
        }
    }
}
