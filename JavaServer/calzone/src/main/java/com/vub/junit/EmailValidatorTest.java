package com.vub.junit;

import static org.junit.Assert.fail;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import com.vub.model.Email;
import static org.junit.Assert.assertEquals;

public class EmailValidatorTest {

private static Validator validator;

   @BeforeClass
   public static void setUp() {
      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
      validator = factory.getValidator();
   }

   @Test
   public void notRealEmail() {
      Email email = new Email();
      email.setEmail("test");

      Set<ConstraintViolation<Email>> constraintViolations =
      validator.validate(email);

      assertEquals(1, constraintViolations.size());
      assertEquals("Not a real email adress",constraintViolations.iterator().next().getMessage());
   }
   
   @Test
   public void notRealEmail2() {
      Email email = new Email();
      email.setEmail("@gmail.com");

      Set<ConstraintViolation<Email>> constraintViolations =
      validator.validate(email);

      assertEquals(1, constraintViolations.size());
      assertEquals("Not a real email adress",constraintViolations.iterator().next().getMessage());
   }
   
   @Test
   public void realEmail() {
      Email email = new Email();
      email.setEmail("test@gmail.com");

      Set<ConstraintViolation<Email>> constraintViolations =
      validator.validate(email);

      assertEquals(0, constraintViolations.size());
   }
}