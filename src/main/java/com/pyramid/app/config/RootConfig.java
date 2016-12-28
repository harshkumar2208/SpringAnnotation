package com.pyramid.app.config;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Create the "root" application context to be provided to the ContextLoaderListener. The returned
 * context is delegated to ContextLoaderListener.ContextLoaderListener(WebApplicationContext) and
 * will be established as the parent context for any DispatcherServlet application contexts. As
 * such, it typically contains middle-tier services, data sources, etc.
 * 
 * @author Cognizant
 */
@Configuration
@ComponentScan({ "com.pyramid.app.service" })
public class RootConfig {
  
  /*final public static String DOZER_MAPPING_CONFIG_FILE = "dozer-mappings.xml";
  
  @Bean
  public Context context() throws Exception {
    return new InitialContext();
  }*/
  
}