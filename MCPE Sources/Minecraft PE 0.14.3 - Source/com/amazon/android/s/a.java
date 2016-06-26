package com.amazon.android.s;

import com.amazon.android.framework.exception.KiwiException;
import com.amazon.android.k.b;
import com.amazon.android.k.c;
import java.util.Iterator;

public class a
  extends KiwiException
{
  private static final long serialVersionUID = 1L;
  public final com.amazon.android.k.a a;
  
  public a(com.amazon.android.k.a parama)
  {
    super("LICENSE_VERIFICATION_FAILURE", "VERIFICATION_ERRORS", a(parama));
    com.amazon.android.d.a.a(parama.a(), "Created a verification exception with a Verifier that has no errors");
    this.a = parama;
  }
  
  private static String a(com.amazon.android.k.a parama)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    parama = parama.iterator();
    while (parama.hasNext())
    {
      b localb = (b)parama.next();
      if (localStringBuilder.length() != 0) {
        localStringBuilder.append(",");
      }
      localStringBuilder.append(localb.a.a());
    }
    return localStringBuilder.toString();
  }
  
  public String toString()
  {
    return this.a.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\s\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */