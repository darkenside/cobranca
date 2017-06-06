package com.omalotech.cobranca;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;


@Component
@Scope("singleton")
public class MessageResource {

	
	@Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }	
	
	public String mensagemProperties(String key){
		
		  try {
	return	messageSource().getMessage(key, null,new Locale("pt", "BR") );
		  } catch(Exception e){
			  return "key não encontrada: " + key;
			  
		  }
	}
	
	
}
