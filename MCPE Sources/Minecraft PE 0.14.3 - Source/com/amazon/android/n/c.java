package com.amazon.android.n;

import com.amazon.android.framework.util.KiwiLogger;
import java.util.Date;

final class c
  extends b
{
  c(d paramd, Object paramObject, Date paramDate)
  {
    super(paramObject, paramDate);
  }
  
  protected final void doExpiration()
  {
    if (KiwiLogger.ERROR_ON) {
      d.a().error("Woah, non-expirable value was expired!!!!");
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\n\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */