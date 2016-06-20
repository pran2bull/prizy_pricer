package org.prizy.pricer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@Configuration
@PropertySource(value = {"classpath:portal.properties"})
@ComponentScan(basePackages = {"org.prizy.pricer"})
public class PortalWebAppConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment environment;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/static/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/img/**").addResourceLocations("/static/img/").setCachePeriod(31556926);
        registry.addResourceHandler("/js/**").addResourceLocations("/static/js/").setCachePeriod(31556926);
        registry.addResourceHandler("/wro/**").addResourceLocations("/wro/").setCachePeriod(31556926);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/views/");
        resolver.setContentType("text/html: charset=UTF-8");
        resolver.setSuffix(".jsp");
        return resolver;
    }

}
