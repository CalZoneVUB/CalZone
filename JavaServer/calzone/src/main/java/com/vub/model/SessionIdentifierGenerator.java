package com.vub.model;

import java.math.BigInteger;
import java.security.SecureRandom;

public final class SessionIdentifierGenerator
{

  private SecureRandom random = new SecureRandom();

  public String nextSessionId()
  {
    return new BigInteger(130, random).toString(32);
  }
  
  public String nextSessionId(int i)
  {
    return new BigInteger(130, random).toString(i);
  }

}
