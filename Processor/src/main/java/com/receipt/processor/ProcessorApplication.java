package com.receipt.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ProcessorApplication {

	
	  @RequestMapping("/")
	  public String home() {
	    return "Receipt Processor Docker Application!";
	  }
	
	public static void main(String[] args) {
		SpringApplication.run(ProcessorApplication.class, args);
	}

}
