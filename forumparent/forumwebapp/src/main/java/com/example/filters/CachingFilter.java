package com.example.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class CachingFilter implements Filter {

    private static byte[] cachedResponse;
    private long lastCachedTimeMillis;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (cachedResponse == null || System.currentTimeMillis() > lastCachedTimeMillis + 5000) {
            // first time or cache is expired: do real request to servlet and cache this data
            System.out.println("Building cache");

            HttpServletResponse res = (HttpServletResponse) response;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            CachedResponse cachedResponse = new CachedResponse(res, new ServletOutputStream() {
                @Override public boolean isReady() { return false; }

                @Override public void setWriteListener(WriteListener writeListener) { }

                @Override public void write(int b) throws IOException { bos.write(b); }
            });

            // instead of forwarding the response, forward the cachedResponse, so we can extract the data from it when it returns.
            chain.doFilter(request, cachedResponse);

            res.flushBuffer();
            bos.close();

            CachingFilter.cachedResponse = bos.toByteArray();
            lastCachedTimeMillis = System.currentTimeMillis();
        }
        // don't forward to servlet with chain.doFilter, but immediately return cached data
        response.getOutputStream().write(cachedResponse);
    }
}
