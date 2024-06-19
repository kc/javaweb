package com.example.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

// Turn on to bypass everything and stop the application from working
// @WebFilter("/*")
public class StopEverythingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        System.out.println("STOPPING THE WORLD....");
        // chain.doFilter();
    }
}
