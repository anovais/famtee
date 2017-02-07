package br.framework.conf;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Author: Andre Novais <br>
 * Date: 04/2016 <br>
 * Description: Configuração do SpringMVC <br>
 * Indica as classes de inicialização do sistema: {@link AppWebConfiguration} ; {@link JPAConfiguration} ; {@link SecurityConfiguration}
 *
 */
public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer{

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer#getRootConfigClasses()
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{AppWebConfiguration.class, JPAConfiguration.class,SecurityConfiguration.class};
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer#getServletConfigClasses()
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		//Determina quais classes deverão ser carregadas ao subir da aplicação
		//aqui determinamos que a AppWebConfiguration vai realizar todo o mapeamento dos controllers
		return new Class[]{};
	}

	/* (non-Javadoc);
	 * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#getServletMappings()
	 * Mapeia as URL's que serão tratadas por essa ServletDispatcher
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}
	
	
	
	
}
