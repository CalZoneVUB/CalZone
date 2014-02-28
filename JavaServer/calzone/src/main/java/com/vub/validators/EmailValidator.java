package com.vub.validators;

//import java.util.regex.Matcher;  
//import java.util.regex.Pattern;  

import javax.validation.ConstraintValidator;  
import javax.validation.ConstraintValidatorContext;  

import com.vub.dao.UserDao;
  
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {  


 public void initialize(ValidEmail validEmail) {    
 }  
  
 
 public boolean isValid(String email, ConstraintValidatorContext context) {  
  
  UserDao userDao = new UserDao();
  return userDao.checkIfEmailAvailable(email);
 }  
}
