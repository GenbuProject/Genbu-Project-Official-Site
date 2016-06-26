package com.amazon.android.framework.resource;

import com.amazon.android.framework.util.KiwiLogger;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class c
  implements a
{
  private static KiwiLogger a = new KiwiLogger("ResourceManagerImpl");
  private List b = new ArrayList();
  private boolean c = false;
  
  public c()
  {
    a(this);
  }
  
  private Object a(Class paramClass)
  {
    Iterator localIterator = this.b.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if (paramClass.isAssignableFrom(localObject.getClass())) {
        return localObject;
      }
    }
    return null;
  }
  
  public final void a()
  {
    Iterator localIterator = this.b.iterator();
    while (localIterator.hasNext()) {
      b(localIterator.next());
    }
  }
  
  public final void a(Object paramObject)
  {
    if (KiwiLogger.TRACE_ON) {
      a.trace("Registering resource: " + paramObject);
    }
    com.amazon.android.d.a.a();
    if (this.c) {
      throw new IllegalStateException("Attempt made to register resource after population has begun!");
    }
    com.amazon.android.d.a.a(paramObject, "resource");
    if (a(paramObject.getClass()) != null) {}
    for (boolean bool = true;; bool = false)
    {
      com.amazon.android.d.a.b(bool, "Resource already registered: " + paramObject);
      this.b.add(paramObject);
      return;
    }
  }
  
  public final void b(Object paramObject)
  {
    com.amazon.android.d.a.a(paramObject, "target");
    if (KiwiLogger.TRACE_ON) {
      a.trace("Populating: " + paramObject);
    }
    if (!paramObject.getClass().getName().startsWith("com.amazon.android")) {
      a.trace("Ignoring: " + paramObject + ", not a kiwi class");
    }
    Field localField;
    for (Class localClass = paramObject.getClass(); localClass != Object.class; localClass = localClass.getSuperclass())
    {
      Field[] arrayOfField = localClass.getDeclaredFields();
      int k = arrayOfField.length;
      int i = 0;
      if (i < k)
      {
        localField = arrayOfField[i];
        int j;
        label133:
        Object localObject2;
        Object localObject1;
        if (localField.getAnnotation(Resource.class) != null)
        {
          j = 1;
          if (j != 0)
          {
            localObject2 = localField.getType();
            localObject1 = a((Class)localObject2);
            localObject2 = "no resource found for type: " + localObject2;
            if (localObject1 == null) {
              break label215;
            }
          }
        }
        label215:
        for (boolean bool = true;; bool = false)
        {
          com.amazon.android.d.a.a(bool, (String)localObject2);
          localField.setAccessible(true);
          try
          {
            localField.set(paramObject, localObject1);
            i += 1;
          }
          catch (Exception paramObject)
          {
            throw new com.amazon.android.d.b("Failed to populate field: " + localField, (Throwable)paramObject);
          }
          j = 0;
          break label133;
        }
      }
    }
    if ((paramObject instanceof b)) {
      ((b)paramObject).onResourcesPopulated();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\framework\resource\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */