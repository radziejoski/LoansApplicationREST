package pl.lalowicz.loans.webservices.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by radoslaw.lalowicz on 2017-05-11.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "pl.lalowicz.loans.webservices")
public class LoanWebServicesConfiguration {

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
}
