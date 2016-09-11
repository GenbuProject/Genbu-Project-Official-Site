package com.google.android.gms.fitness;

import com.google.android.gms.common.api.Scope;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class zza
{
  public static Scope zza(Scope paramScope)
  {
    Scope localScope;
    if (paramScope.equals(new Scope("https://www.googleapis.com/auth/fitness.activity.read"))) {
      localScope = new Scope("https://www.googleapis.com/auth/fitness.activity.write");
    }
    do
    {
      return localScope;
      if (paramScope.equals(new Scope("https://www.googleapis.com/auth/fitness.location.read"))) {
        return new Scope("https://www.googleapis.com/auth/fitness.location.write");
      }
      if (paramScope.equals(new Scope("https://www.googleapis.com/auth/fitness.body.read"))) {
        return new Scope("https://www.googleapis.com/auth/fitness.body.write");
      }
      localScope = paramScope;
    } while (!paramScope.equals(new Scope("https://www.googleapis.com/auth/fitness.nutrition.read")));
    return new Scope("https://www.googleapis.com/auth/fitness.nutrition.write");
  }
  
  public static Set<Scope> zze(Collection<Scope> paramCollection)
  {
    HashSet localHashSet = new HashSet(paramCollection.size());
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      Scope localScope1 = (Scope)localIterator.next();
      Scope localScope2 = zza(localScope1);
      if ((localScope2.equals(localScope1)) || (!paramCollection.contains(localScope2))) {
        localHashSet.add(localScope1);
      }
    }
    return localHashSet;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\fitness\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */