package com.vub.validators;

import java.util.regex.Matcher;  
import java.util.regex.Pattern;  

import javax.validation.ConstraintValidator;  
import javax.validation.ConstraintValidatorContext;  

import com.vub.model.UserDao;
  
public class UserValidator implements ConstraintValidator<ValidUserName, String> {  


 public void initialize(ValidUserName validUserName) {    
 }  
  
 
 public boolean isValid(String userName, ConstraintValidatorContext context) {  
  
  UserDao userDao = new UserDao();
  return userDao.checkIfUserNameAvailable(userName);
  
 }  
}
