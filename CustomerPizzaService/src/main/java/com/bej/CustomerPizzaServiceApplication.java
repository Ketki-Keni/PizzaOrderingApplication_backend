package com.bej;

import com.bej.filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class CustomerPizzaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerPizzaServiceApplication.class, args);
	}

//	@Bean
//	public FilterRegistrationBean filterUrl() {
//		FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
//		filterRegistrationBean.setFilter(new JwtFilter());
//		filterRegistrationBean.addUrlPatterns("/api/v2/user/*");
//		return filterRegistrationBean;
//	}
}
