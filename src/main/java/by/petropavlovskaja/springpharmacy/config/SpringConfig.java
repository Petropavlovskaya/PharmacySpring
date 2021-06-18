package by.petropavlovskaja.springpharmacy.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("by.petropavlovskaja.springpharmacy")
@EnableWebMvc  // equals <mvc:annotation-driven />  Turn on SpringMVC
public class SpringConfig implements WebMvcConfigurer {

}
