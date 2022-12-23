package tw.SianHao.config;

import java.util.ArrayList;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/*相當於mvc-servlet.xml的Java程式組態
 <context:annotation-config/> 
 <mvc:annotation-driven/>
 <context:component-scan base-package="tw.SianHao"/>
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "tw.SianHao")
public class WebAppConfig implements WebMvcConfigurer {
	
	@Bean
	public MappingJackson2JsonView jsonView() {
		MappingJackson2JsonView mappingJsonView = new MappingJackson2JsonView();
		mappingJsonView.setPrettyPrint(true);
		return mappingJsonView;
	}
	
	@Bean
	public Jaxb2Marshaller jaxbMarshaller() {
		Jaxb2Marshaller jaxb = new Jaxb2Marshaller();
		jaxb.setPackagesToScan("tw.SianHao");
		return jaxb;
	}
	
	@Bean
	public ContentNegotiatingViewResolver negoViewResolver() {
		ContentNegotiatingViewResolver vr1 = new ContentNegotiatingViewResolver();
		
		ArrayList<View> list = new ArrayList<View>();
		list.add(jsonView());
		
		vr1.setDefaultViews(list);
		return vr1;
	}

	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/resources/images/");//前面是虛擬路徑後面是實際路徑
		registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/resources/css/");//前面是虛擬路徑後面是實際路徑
	}





	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "membersmain.controller");//前面是虛擬路徑後面是實際路徑(會跳轉)
		registry.addViewController("/funny.day").setViewName("loginSystem");//前面是虛擬路徑後面是實際路徑(會跳轉成虛擬路徑)
	}



	@Bean
	public InternalResourceViewResolver viewResolver1() {
		InternalResourceViewResolver irv1 = new InternalResourceViewResolver();
		irv1.setPrefix("/WEB-INF/pages/");
		irv1.setSuffix(".jsp");
		irv1.setOrder(6);
		return irv1;
	}

	@Bean
	@Autowired
	public InternalResourceViewResolver viewResolver2() {
		InternalResourceViewResolver irv2 = new InternalResourceViewResolver();
		irv2.setPrefix("/WEB-INF/pages/");
		irv2.setSuffix(".html");
		irv2.setOrder(6);
		return irv2;
	}
	
	@Bean 
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver cmr1 = new CommonsMultipartResolver();
		cmr1.setDefaultEncoding("UTF-8");
		return cmr1;
	}
	
	
//	有空在處理多國語言java設定組態
//	@Bean
//	public ResourceBundleMessageSource ms() {
//		ResourceBundleMessageSource rbms1 = new ResourceBundleMessageSource();
//		rbms1.setBasename("i18n.message");
//		rbms1.setDefaultEncoding("UTF-8");
//		return rbms1;
//	}
//	
//	 @Bean
//	    public LocaleResolver localeResolver() {
//	        SessionLocaleResolver slr = new SessionLocaleResolver();
//	        slr.setDefaultLocale(Locale.TAIWAN);
//	        return slr;
//	    }
//
//	    /**
//	    * 默認攔截器 其中locale表示切換語言的參數名
//	    */
//	    @Bean
//	    public LocaleChangeInterceptor localeChangeInterceptor() {
//	        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
//	        lci.setParamName("locale");
//	        return lci;
//	    }
//	    
//	    @Override
//	    public void addInterceptors(InterceptorRegistry registry){
//	        registry.addInterceptor(localeChangeInterceptor());
//	    }
}
