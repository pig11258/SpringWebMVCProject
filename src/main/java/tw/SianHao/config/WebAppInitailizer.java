package tw.SianHao.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//相當於web.xml的Java程式組態
public class WebAppInitailizer extends AbstractAnnotationConfigDispatcherServletInitializer {

	//設定相當於beans.config.xml的Java程式組態
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RootAppConfig.class};
//		return null;
	}

	//設定相當於mvc-servlet.xml的Java程式組態(MVC設定)
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class [] {WebAppConfig.class};
//		return null;
	}

	//設定url-pattern
	@Override
	protected String[] getServletMappings() {
		return new String [] {"/"};//"/"或"/*"代表所有網址
//		return null;
	}

	//設定filter
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter cef1 = new CharacterEncodingFilter("UTF-8",true);
		return new Filter [] {cef1};
	}

}
