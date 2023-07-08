package com.ohwoo.Config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] { RootConfig.class, SecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] { ServletConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		// TODO Auto-generated method stub
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		HiddenHttpMethodFilter hidenFilter = new HiddenHttpMethodFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		return new Filter[] { filter, hidenFilter };
	}
	
	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		// TODO Auto-generated method stub
		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
		MultipartConfigElement multipart = new MultipartConfigElement("C:\\Users\\kowko\\Desktop\\img", -1, -1, -1);
		registration.setMultipartConfig(multipart);
	}

	 public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")
	                .allowedOrigins("*");
	 }
	
}
