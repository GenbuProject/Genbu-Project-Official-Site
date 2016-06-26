package com.amazon.android.q;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class a
  implements Serializable, Iterable
{
  private static final long serialVersionUID = 1L;
  final List a = new ArrayList();
  
  public final boolean a()
  {
    return this.a.isEmpty();
  }
  
  public final int b()
  {
    return this.a.size();
  }
  
  public final Iterator iterator()
  {
    return this.a.iterator();
  }
  
  public final String toString()
  {
    return "MetricBatch: [" + this.a + "]";
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\q\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */