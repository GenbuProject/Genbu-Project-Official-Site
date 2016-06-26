package com.amazon.android.q;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class b
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  public final Map a = new HashMap();
  
  public b(String paramString)
  {
    this.a.put("name", paramString);
    this.a.put("time", String.valueOf(System.currentTimeMillis()));
  }
  
  public final b a(String paramString1, String paramString2)
  {
    this.a.put(paramString1, paramString2);
    return this;
  }
  
  public String toString()
  {
    return "Metric: [" + this.a.toString() + "]";
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\q\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */