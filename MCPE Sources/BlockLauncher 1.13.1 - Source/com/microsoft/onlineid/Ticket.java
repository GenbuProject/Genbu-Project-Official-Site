package com.microsoft.onlineid;

import com.microsoft.onlineid.internal.Objects;
import com.microsoft.onlineid.internal.Strings;
import java.io.Serializable;
import java.util.Date;

public class Ticket
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private final Date _expiry;
  private final ISecurityScope _scope;
  private final String _value;
  
  public Ticket(ISecurityScope paramISecurityScope, Date paramDate, String paramString)
  {
    Objects.verifyArgumentNotNull(paramISecurityScope, "scope");
    Objects.verifyArgumentNotNull(paramDate, "expiry");
    Strings.verifyArgumentNotNullOrEmpty(paramString, "value");
    this._scope = paramISecurityScope;
    this._expiry = paramDate;
    this._value = paramString;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (!(paramObject instanceof Ticket))) {
        break;
      }
      paramObject = (Ticket)paramObject;
    } while ((Objects.equals(this._scope, ((Ticket)paramObject)._scope)) && (Objects.equals(this._expiry, ((Ticket)paramObject)._expiry)) && (Objects.equals(this._value, ((Ticket)paramObject)._value)));
    return false;
    return false;
  }
  
  public Date getExpiry()
  {
    return this._expiry;
  }
  
  public ISecurityScope getScope()
  {
    return this._scope;
  }
  
  public String getValue()
  {
    return this._value;
  }
  
  public int hashCode()
  {
    return Objects.hashCode(this._scope) + Objects.hashCode(this._expiry) + Objects.hashCode(this._value);
  }
  
  public String toString()
  {
    return "Ticket{scope: " + this._scope + ", expiry: " + this._expiry + "}";
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\onlineid\Ticket.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */