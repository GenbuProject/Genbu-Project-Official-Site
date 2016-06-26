package com.microsoft.onlineid.sts;

import java.security.SecureRandom;

public class DeviceCredentialGenerator
{
  static final String LegalPasswordCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-_=+[]{}/?;:'\",.<>`~";
  static final String LegalUsernameCharacters = "abcdefghijklmnopqrstuvwxyz";
  static final String LogicalDevicePrefix = "02";
  static final int PasswordLength = 16;
  static final int UsernameLength = 20;
  private final SecureRandom _randomNumberGenerator;
  
  public DeviceCredentialGenerator()
  {
    this._randomNumberGenerator = new SecureRandom();
  }
  
  DeviceCredentialGenerator(SecureRandom paramSecureRandom)
  {
    this._randomNumberGenerator = paramSecureRandom;
  }
  
  private String generateRandomString(String paramString, int paramInt)
  {
    char[] arrayOfChar = new char[paramInt];
    int i = paramString.length();
    paramInt = 0;
    while (paramInt < arrayOfChar.length)
    {
      arrayOfChar[paramInt] = paramString.charAt(this._randomNumberGenerator.nextInt(i));
      paramInt += 1;
    }
    return new String(arrayOfChar);
  }
  
  public DeviceCredentials generate()
  {
    return new DeviceCredentials("02" + generateRandomString("abcdefghijklmnopqrstuvwxyz", 20 - "02".length()), generateRandomString("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-_=+[]{}/?;:'\",.<>`~", 16));
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\sts\DeviceCredentialGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */