package com.vub.validators;

import java.lang.annotation.Documented;  
import java.lang.annotation.ElementType;  
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;  
import java.lang.annotation.Target;  

import javax.validation.Constraint;  
//import javax.validation.Payload;  
  
@Documented  
@Retention(RetentionPolicy.RUNTIME)  
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.CONSTRUCTOR,ElementType.PARAMETER,ElementType.ANNOTATION_TYPE})  
@Constraint(validatedBy=EmailValidator.class)  
public @interface ValidEmail {  
   
 String message();  
  
    Class[] groups() default {};  
      
    Class[] payload() default {};  
}  
