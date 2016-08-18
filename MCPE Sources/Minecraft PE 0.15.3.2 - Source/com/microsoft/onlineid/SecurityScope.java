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
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof ISecurityScope)) {
        return false;
      }
      paramObject = (ISecurityScope)paramObject;
    } while ((getTarget().equalsIgnoreCase(((ISecurityScope)paramObject).getTarget())) && (Strings.equalsIgnoreCase(getPolicy(), ((ISecurityScope)paramObject).getPolicy())));
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


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\SecurityScope.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */