// package com.asm.config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// import com.asm.interceptor.AuthInterceptor;

// @Configuration
// public class AuthInterConfig implements WebMvcConfigurer {

// @Autowired
// AuthInterceptor auth;

// @Override
// public void addInterceptors(InterceptorRegistry registry) {
// registry.addInterceptor(auth)
// .addPathPatterns("/car/**")
// .excludePathPatterns("/car/index","/car/dsxe","/car/login/**","/car/register/**"
// ,"/car/forgotpassword/**","/car/logout");
// }
// }
