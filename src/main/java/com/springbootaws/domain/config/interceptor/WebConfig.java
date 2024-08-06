package com.springbootaws.domain.config.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/post/insertPost","/post/{postId}/edit","/post/{postId}/delete",
                        "/product/book/insert","product/book/{bookId}/edit","product/book/{bookId}/delete",
                        "/order","/orders/{orderId}/cancel");
    }
}
