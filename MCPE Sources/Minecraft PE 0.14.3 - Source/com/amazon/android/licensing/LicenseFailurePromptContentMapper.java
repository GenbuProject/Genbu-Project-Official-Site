package com.amazon.android.licensing;

import com.amazon.android.b.d;
import com.amazon.android.b.e;
import com.amazon.android.b.f;
import com.amazon.android.framework.exception.KiwiException;
import com.amazon.android.framework.prompt.PromptContent;
import com.amazon.android.h.c;
import java.util.HashMap;
import java.util.Map;

public class LicenseFailurePromptContentMapper
{
  private final Map mappings = new HashMap();
  
  public LicenseFailurePromptContentMapper()
  {
    register(com.amazon.android.b.g.class, i.a);
    register(f.class, i.a);
    register(com.amazon.android.b.a.class, i.b);
    register(d.class, i.c);
    register(e.class, new m());
    register(com.amazon.android.b.h.class, i.e);
    register(com.amazon.android.b.b.class, i.e);
    register(com.amazon.android.s.a.class, new j());
    register(com.amazon.android.h.a.class, i.e);
    register(com.amazon.android.h.b.class, i.e);
    register(c.class, i.e);
  }
  
  private void register(Class paramClass, PromptContent paramPromptContent)
  {
    register(paramClass, new g(this, paramPromptContent));
  }
  
  private void register(Class paramClass, h paramh)
  {
    com.amazon.android.d.a.b(this.mappings.containsKey(paramClass), "mapping exists for type: " + paramClass);
    this.mappings.put(paramClass, paramh);
  }
  
  public PromptContent map(KiwiException paramKiwiException)
  {
    h localh = (h)this.mappings.get(paramKiwiException.getClass());
    if (localh == null) {
      return null;
    }
    return localh.a(paramKiwiException);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\licensing\LicenseFailurePromptContentMapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */