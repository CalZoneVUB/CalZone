<<<<<<< .merge_file_a05748
package com.vub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
public class HelloController {
	@RequestMapping(value = "/")
	public String sayHello(Model model) {
		
		model.addAttribute("greeting", "Hello World");
		return "hello";
	}
}
=======
package com.vub.controller;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vub.model.ActivationKey;

@Controller 
public class HelloController {
	@RequestMapping(value = "/hello")
	public String sayHello(Model model) {
		model.addAttribute("greeting", "Hello World");
		return "hello";
	}
}
>>>>>>> .merge_file_a05256
