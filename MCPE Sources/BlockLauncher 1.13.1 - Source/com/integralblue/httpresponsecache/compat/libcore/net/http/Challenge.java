package com.integralblue.httpresponsecache.compat.libcore.net.http;

public final class Challenge
{
  final String realm;
  final String scheme;
  
  public Challenge(String paramString1, String paramString2)
  {
    this.scheme = paramString1;
    this.realm = paramString2;
  }
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof Challenge)) && (((Challenge)paramObject).scheme.equals(this.scheme)) && (((Challenge)paramObject).realm.equals(this.realm));
  }
  
  public int hashCode()
  {
    return this.scheme.hashCode() + this.realm.hashCode() * 31;
  }
  
  public String toString()
  {
    return "Challenge[" + this.scheme + " " + this.realm + "]";
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\integralblue\httpresponsecache\compat\libcore\net\http\Challenge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */