package com.vub.validators;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import com.vub.model.Email;
import static org.junit.Assert.assertEquals;

/**
 * 
 * @author Tim
 * Testing all possibilities of the email validator on expected response
 */
public class EmailValidatorTest {

private static Validator validator;

   @BeforeClass
   public static void setUp() {
      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
      validator = factory.getValidator();
   }
   
   /**
    * Testing on just a string
    */
   @Test
   public void notRealEmail() {
      Email email = new Email();
      email.setEmail("test");

      Set<ConstraintViolation<Email>> constraintViolations =
      validator.validate(email);

      assertEquals(1, constraintViolations.size());
      assertEquals("Not a real email adress",constraintViolations.iterator().next().getMessage());
   }
   
   /**
    * Test on address with nothing in front of the @ symbol
    */
   @Test
   public void notRealEmail2() {
      Email email = new Email();
      email.setEmail("@gmail.com");

      Set<ConstraintViolation<Email>> constraintViolations =
      validator.validate(email);

      assertEquals(1, constraintViolations.size());
      assertEquals("Not a real email adress",constraintViolations.iterator().next().getMessage());
   }
   
   /**
    * Test on a real email address 
    */
   @Test
   public void realEmail() {
      Email email = new Email();
      email.setEmail("test@gmail.com");

      Set<ConstraintViolation<Email>> constraintViolations =
      validator.validate(email);

      assertEquals(0, constraintViolations.size());
   }
}