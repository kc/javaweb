package com.example.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

public class RequestLogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        chain.doFilter(request, response); // voer de volgende in de chain uit
        long duration = System.currentTimeMillis() - start;

        System.out.println(("Request took (ms): " + duration));
    }

}
