package br.framework.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.framework.controller.HomeController;
import br.framework.core.context.SaveInstrumentContext;
import br.framework.core.context.TRLCalculatorContext;
import br.framework.core.context.activity.LoadFirstAssessmentActivity;
import br.framework.core.facade.AssessmentFacade;
import br.framework.core.facade.IFacade;
import br.framework.dao.GenericDAO;
import br.framework.domain.GenericEntity;
import br.framework.domain.secutiry.Role;
import br.framework.helper.FileSaver;

import com.mongodb.MongoClient;

/**
 * Author: Andre Novais <br>
 * Date: 26/04/2016 <br>
 * Description: Classe de configuração de beans importantes para o framework SPRING MVC <br> 
 * Define os pacotes e classes que serão escaneadas pelo Spring.
 */
@EnableWebMvc
@ComponentScan(basePackageClasses={LoadFirstAssessmentActivity.class,HomeController.class, GenericDAO.class, FileSaver.class, Role.class, GenericEntity.class, AppWebConfiguration.class, TRLCalculatorContext.class,SaveInstrumentContext.class,AssessmentFacade.class }) //classes que deverão ser escaneadas durante a subida da aplicação
public class AppWebConfiguration extends WebMvcConfigurerAdapter{

	@Bean
	public InternalResourceViewResolver 	internalResourceViewResolver() {
		InternalResourceViewResolver resolver = 	new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
		
	}
	
	
	@Bean
	public MessageSource messageSource(){
		ReloadableResourceBundleMessageSource bundle =	new ReloadableResourceBundleMessageSource();
		bundle.setBasename("/WEB-INF/messages");
		
		bundle.setDefaultEncoding("UTF-8");
		bundle.setCacheSeconds(1);
		return bundle;
	}
	
	
	@Bean
	public FormattingConversionService mvcConversionService() {
			DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(true);
			
			DateFormatterRegistrar registrar =	new DateFormatterRegistrar();
			registrar.setFormatter(new DateFormatter("yyyy-MM-dd"));
			registrar.registerFormatters(conversionService);
			DateFormatterRegistrar registrar2 =	new DateFormatterRegistrar();
			registrar2.setFormatter(new DateFormatter("dd/MM/yyyy"));
			registrar2.registerFormatters(conversionService);
			return conversionService;
	}
	
	@Bean
	public MultipartResolver multipartResolver(){
		return new StandardServletMultipartResolver();
		//return new CommonsMultipartResolver();
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	/**
	 * 
	 * @return o cliente para conexão com MongoDB
	 */
	@Bean
	public MongoClient buildMongoClient(){
		return new MongoClient();
	}
}
