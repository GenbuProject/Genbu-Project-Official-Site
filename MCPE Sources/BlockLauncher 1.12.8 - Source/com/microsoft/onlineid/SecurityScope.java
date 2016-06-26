package com.microsoft.onlineid;

import com.microsoft.onlineid.internal.Objects;
import com.microsoft.onlineid.internal.Strings;
import java.util.Locale;

public class SecurityScope
  implements ISecurityScope
{
  private static final long serialVersionUID = 1L;
  private String _oAuthString;
  private final String _policy;
  private final String _target;
  
  public SecurityScope(String paramString1, String paramString2)
  {
    Strings.verifyArgumentNotNullOrEmpty(paramString1, "target");
    this._target = paramString1;
    this._policy = paramString2;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    if (this == paramObject) {}
    do
    {
      boolean bool1 = true;
      do
      {
        do
        {
          return bool1;
          bool1 = bool2;
        } while (!(paramObject instanceof ISecurityScope));
        paramObject = (ISecurityScope)paramObject;
        bool1 = bool2;
      } while (!getTarget().equalsIgnoreCase(((ISecurityScope)paramObject).getTarget()));
    } while (Strings.equalsIgnoreCase(getPolicy(), ((ISecurityScope)paramObject).getPolicy()));
    return false;
  }
  
  public String getPolicy()
  {
    return this._policy;
  }
  
  public String getTarget()
  {
    return this._target;
  }
  
  public int hashCode()
  {
    return Objects.hashCode(toString());
  }
  
  public String toString()
  {
    if (this._oAuthString == null) {
      this._oAuthString = String.format(Locale.US, "service::%s::%s", new Object[] { this._target, this._policy });
    }
    return this._oAuthString;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\SecurityScope.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */