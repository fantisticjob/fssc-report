package com.longfor.fsscreport;

import com.longfor.fsscreport.clear.controller.DwCpClearUpDetailController;
import com.longfor.fsscreport.controller.BpmController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;



@SpringBootApplication
public class FsscReportApplication extends SpringBootServletInitializer {

	
	public static ConfigurableApplicationContext 
	configurableApplicationContext = null;

	
	
	
	    
	
	public static void main(String[] args) {
    
		configurableApplicationContext  = SpringApplication.run(FsscReportApplication.class, args);

        DwCpClearUpDetailController bpmController = (DwCpClearUpDetailController) configurableApplicationContext.getBean(DwCpClearUpDetailController.class);
        //bpmController.updataClearDetailStatus("asdfasdf");
		
		
	}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
