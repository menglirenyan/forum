package com.example.forum.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration // 标记这是一个 Spring 配置类
public class CorsConfig {

    // 定义一个 CorsFilter 的 Bean，让 Spring 自动加载
    public CorsFilter corsFilter() {
        // 1. 创建跨域配置对象
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*"); // 开发阶段允许所有来源（生产环境要改！）
        config.addAllowedMethod("*"); // 允许所有 HTTP 方法（GET、POST、PUT 等）
        config.addAllowedHeader("*"); // 允许所有请求头（如 Token、Content-Type 等）

        // 2. 配置生效的路径（所有接口都允许跨域）
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // 对所有路径生效

        // 3. 创建并返回 CorsFilter
        return new CorsFilter(source);
    }
}