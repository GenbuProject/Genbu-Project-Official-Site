package org.mozilla.javascript;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class JavaMembers
{
  private Class<?> cl;
  NativeJavaMethod ctors;
  private Map<String, FieldAndMethods> fieldAndMethods;
  private Map<String, Object> members;
  private Map<String, FieldAndMethods> staticFieldAndMethods;
  private Map<String, Object> staticMembers;
  
  JavaMembers(Scriptable paramScriptable, Class<?> paramClass)
  {
    this(paramScriptable, paramClass, false);
  }
  
  JavaMembers(Scriptable paramScriptable, Class<?> paramClass, boolean paramBoolean)
  {
    Context localContext;
    try
    {
      localContext = ContextFactory.getGlobal().enterContext();
      ClassShutter localClassShutter = localContext.getClassShutter();
      if ((localClassShutter != null) && (!localClassShutter.visibleToScripts(paramClass.getName()))) {
        throw Context.reportRuntimeError1("msg.access.prohibited", paramClass.getName());
      }
    }
    finally
    {
      Context.exit();
    }
    this.members = new HashMap();
    this.staticMembers = new HashMap();
    this.cl = paramClass;
    reflect(paramScriptable, paramBoolean, localContext.hasFeature(13));
    Context.exit();
  }
  
  private static void discoverAccessibleMethods(Class<?> paramClass, Map<MethodSignature, Method> paramMap, boolean paramBoolean1, boolean paramBoolean2)
  {
    int j = 0;
    Object localObject2;
    Object localObject1;
    int k;
    int i;
    MethodSignature localMethodSignature1;
    if (!Modifier.isPublic(paramClass.getModifiers()))
    {
      localObject2 = paramClass;
      if (!paramBoolean2) {}
    }
    else
    {
      localObject1 = paramClass;
      if (!paramBoolean1)
      {
        if (paramBoolean2) {
          localObject1 = paramClass;
        }
      }
      else {
        for (;;)
        {
          if (localObject1 != null) {
            try
            {
              paramClass = ((Class)localObject1).getDeclaredMethods();
              k = paramClass.length;
              i = 0;
              if (i < k)
              {
                localObject2 = paramClass[i];
                int m = ((Method)localObject2).getModifiers();
                if ((Modifier.isPublic(m)) || (Modifier.isProtected(m)) || (paramBoolean2))
                {
                  localMethodSignature1 = new MethodSignature((Method)localObject2);
                  if (!paramMap.containsKey(localMethodSignature1))
                  {
                    if ((paramBoolean2) && (!((Method)localObject2).isAccessible())) {
                      ((Method)localObject2).setAccessible(true);
                    }
                    paramMap.put(localMethodSignature1, localObject2);
                  }
                }
              }
              else
              {
                paramClass = ((Class)localObject1).getSuperclass();
                localObject1 = paramClass;
              }
            }
            catch (SecurityException paramClass)
            {
              localObject2 = localObject1;
            }
          }
        }
      }
    }
    for (;;)
    {
      try
      {
        paramClass = ((Class)localObject1).getMethods();
        localObject2 = localObject1;
        k = paramClass.length;
        i = 0;
        if (i >= k) {
          break label420;
        }
        localMethodSignature1 = paramClass[i];
        localObject2 = localObject1;
        localMethodSignature2 = new MethodSignature(localMethodSignature1);
        localObject2 = localObject1;
        if (paramMap.containsKey(localMethodSignature2)) {
          break label430;
        }
        localObject2 = localObject1;
        paramMap.put(localMethodSignature2, localMethodSignature1);
      }
      catch (SecurityException paramClass)
      {
        MethodSignature localMethodSignature2;
        Context.reportWarning("Could not discover accessible methods of class " + ((Class)localObject2).getName() + " due to lack of privileges, " + "attemping superclasses/interfaces.");
      }
      localObject2 = paramClass;
      localObject1 = paramClass.getMethods();
      localObject2 = paramClass;
      k = localObject1.length;
      i = 0;
      if (i < k)
      {
        localMethodSignature1 = localObject1[i];
        localObject2 = paramClass;
        localMethodSignature2 = new MethodSignature(localMethodSignature1);
        localObject2 = paramClass;
        if (!paramMap.containsKey(localMethodSignature2))
        {
          localObject2 = paramClass;
          paramMap.put(localMethodSignature2, localMethodSignature1);
        }
        i += 1;
        continue;
        paramClass = ((Class)localObject2).getInterfaces();
        k = paramClass.length;
        i = j;
        while (i < k)
        {
          discoverAccessibleMethods(paramClass[i], paramMap, paramBoolean1, paramBoolean2);
          i += 1;
        }
        paramClass = ((Class)localObject2).getSuperclass();
        if (paramClass != null) {
          discoverAccessibleMethods(paramClass, paramMap, paramBoolean1, paramBoolean2);
        }
      }
      label420:
      return;
      i += 1;
      break;
      label430:
      i += 1;
    }
  }
  
  private static Method[] discoverAccessibleMethods(Class<?> paramClass, boolean paramBoolean1, boolean paramBoolean2)
  {
    HashMap localHashMap = new HashMap();
    discoverAccessibleMethods(paramClass, localHashMap, paramBoolean1, paramBoolean2);
    return (Method[])localHashMap.values().toArray(new Method[localHashMap.size()]);
  }
  
  private static MemberBox extractGetMethod(MemberBox[] paramArrayOfMemberBox, boolean paramBoolean)
  {
    int j = paramArrayOfMemberBox.length;
    int i = 0;
    while (i < j)
    {
      MemberBox localMemberBox = paramArrayOfMemberBox[i];
      if ((localMemberBox.argTypes.length == 0) && ((!paramBoolean) || (localMemberBox.isStatic())))
      {
        if (localMemberBox.method().getReturnType() == Void.TYPE) {
          break;
        }
        return localMemberBox;
      }
      i += 1;
    }
    return null;
  }
  
  private static MemberBox extractSetMethod(Class<?> paramClass, MemberBox[] paramArrayOfMemberBox, boolean paramBoolean)
  {
    int i = 1;
    while (i <= 2)
    {
      int k = paramArrayOfMemberBox.length;
      int j = 0;
      while (j < k)
      {
        MemberBox localMemberBox = paramArrayOfMemberBox[j];
        if ((!paramBoolean) || (localMemberBox.isStatic()))
        {
          Class[] arrayOfClass = localMemberBox.argTypes;
          if (arrayOfClass.length == 1) {
            if (i == 1)
            {
              if (arrayOfClass[0] != paramClass) {}
            }
            else {
              do
              {
                return localMemberBox;
                if (i != 2) {
                  Kit.codeBug();
                }
              } while (arrayOfClass[0].isAssignableFrom(paramClass));
            }
          }
        }
        j += 1;
      }
      i += 1;
    }
    return null;
  }
  
  private static MemberBox extractSetMethod(MemberBox[] paramArrayOfMemberBox, boolean paramBoolean)
  {
    int j = paramArrayOfMemberBox.length;
    int i = 0;
    while (i < j)
    {
      MemberBox localMemberBox = paramArrayOfMemberBox[i];
      if (((!paramBoolean) || (localMemberBox.isStatic())) && (localMemberBox.method().getReturnType() == Void.TYPE) && (localMemberBox.argTypes.length == 1)) {
        return localMemberBox;
      }
      i += 1;
    }
    return null;
  }
  
  private MemberBox findExplicitFunction(String paramString, boolean paramBoolean)
  {
    int j = paramString.indexOf('(');
    Object localObject2;
    if (j < 0)
    {
      localObject2 = null;
      return (MemberBox)localObject2;
    }
    Object localObject1;
    label29:
    int i;
    if (paramBoolean)
    {
      localObject1 = this.staticMembers;
      if ((!paramBoolean) || (j != 0)) {
        break label135;
      }
      i = 1;
      label40:
      if (i == 0) {
        break label140;
      }
      localObject1 = this.ctors.methods;
    }
    for (;;)
    {
      label53:
      if (localObject1 != null)
      {
        int k = localObject1.length;
        i = 0;
        for (;;)
        {
          if (i < k)
          {
            localObject2 = localObject1[i];
            String str = liveConnectSignature(((MemberBox)localObject2).argTypes);
            if ((str.length() + j == paramString.length()) && (paramString.regionMatches(j, str, 0, str.length()))) {
              break;
            }
            i += 1;
            continue;
            localObject1 = this.members;
            break label29;
            label135:
            i = 0;
            break label40;
            label140:
            str = paramString.substring(0, j);
            localObject2 = ((Map)localObject1).get(str);
            localObject1 = localObject2;
            if (!paramBoolean)
            {
              localObject1 = localObject2;
              if (localObject2 == null) {
                localObject1 = this.staticMembers.get(str);
              }
            }
            if (!(localObject1 instanceof NativeJavaMethod)) {
              break label213;
            }
            localObject1 = ((NativeJavaMethod)localObject1).methods;
            break label53;
          }
        }
      }
      return null;
      label213:
      localObject1 = null;
    }
  }
  
  private MemberBox findGetter(boolean paramBoolean, Map<String, Object> paramMap, String paramString1, String paramString2)
  {
    paramString1 = paramString1.concat(paramString2);
    if (paramMap.containsKey(paramString1))
    {
      paramMap = paramMap.get(paramString1);
      if ((paramMap instanceof NativeJavaMethod)) {
        return extractGetMethod(((NativeJavaMethod)paramMap).methods, paramBoolean);
      }
    }
    return null;
  }
  
  private Constructor<?>[] getAccessibleConstructors(boolean paramBoolean)
  {
    if ((paramBoolean) && (this.cl != ScriptRuntime.ClassClass)) {
      try
      {
        Constructor[] arrayOfConstructor = this.cl.getDeclaredConstructors();
        AccessibleObject.setAccessible(arrayOfConstructor, true);
        return arrayOfConstructor;
      }
      catch (SecurityException localSecurityException)
      {
        Context.reportWarning("Could not access constructor  of class " + this.cl.getName() + " due to lack of privileges.");
      }
    }
    return this.cl.getConstructors();
  }
  
  private Field[] getAccessibleFields(boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((paramBoolean2) || (paramBoolean1)) {}
    for (;;)
    {
      int i;
      try
      {
        ArrayList localArrayList = new ArrayList();
        Object localObject = this.cl;
        if (localObject != null)
        {
          Field[] arrayOfField = ((Class)localObject).getDeclaredFields();
          int j = arrayOfField.length;
          i = 0;
          if (i < j)
          {
            Field localField = arrayOfField[i];
            int k = localField.getModifiers();
            if ((!paramBoolean2) && (!Modifier.isPublic(k)) && (!Modifier.isProtected(k))) {
              break label153;
            }
            if (!localField.isAccessible()) {
              localField.setAccessible(true);
            }
            localArrayList.add(localField);
            break label153;
          }
          localObject = ((Class)localObject).getSuperclass();
          continue;
        }
        localObject = (Field[])localArrayList.toArray(new Field[localArrayList.size()]);
        return (Field[])localObject;
      }
      catch (SecurityException localSecurityException) {}
      return this.cl.getFields();
      label153:
      i += 1;
    }
  }
  
  private Object getExplicitFunction(Scriptable paramScriptable, String paramString, Object paramObject, boolean paramBoolean)
  {
    Map localMap;
    MemberBox localMemberBox;
    Scriptable localScriptable;
    if (paramBoolean)
    {
      localMap = this.staticMembers;
      paramObject = null;
      localMemberBox = findExplicitFunction(paramString, paramBoolean);
      if (localMemberBox != null)
      {
        localScriptable = ScriptableObject.getFunctionPrototype(paramScriptable);
        if (!localMemberBox.isCtor()) {
          break label78;
        }
        paramObject = new NativeJavaConstructor(localMemberBox);
        ((NativeJavaConstructor)paramObject).setPrototype(localScriptable);
        localMap.put(paramString, paramObject);
      }
    }
    label78:
    do
    {
      do
      {
        return paramObject;
        localMap = this.members;
        break;
        paramScriptable = localMap.get(localMemberBox.getName());
        paramObject = paramScriptable;
      } while (!(paramScriptable instanceof NativeJavaMethod));
      paramObject = paramScriptable;
    } while (((NativeJavaMethod)paramScriptable).methods.length <= 1);
    paramScriptable = new NativeJavaMethod(localMemberBox, paramString);
    paramScriptable.setPrototype(localScriptable);
    localMap.put(paramString, paramScriptable);
    return paramScriptable;
  }
  
  static String javaSignature(Class<?> paramClass)
  {
    if (!paramClass.isArray()) {
      return paramClass.getName();
    }
    int i = 0;
    int j;
    do
    {
      j = i + 1;
      localObject = paramClass.getComponentType();
      i = j;
      paramClass = (Class<?>)localObject;
    } while (((Class)localObject).isArray());
    paramClass = ((Class)localObject).getName();
    if (j == 1) {
      return paramClass.concat("[]");
    }
    Object localObject = new StringBuilder(paramClass.length() + "[]".length() * j);
    ((StringBuilder)localObject).append(paramClass);
    while (j != 0)
    {
      j -= 1;
      ((StringBuilder)localObject).append("[]");
    }
    return ((StringBuilder)localObject).toString();
  }
  
  static String liveConnectSignature(Class<?>[] paramArrayOfClass)
  {
    int j = paramArrayOfClass.length;
    if (j == 0) {
      return "()";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append('(');
    int i = 0;
    while (i != j)
    {
      if (i != 0) {
        localStringBuilder.append(',');
      }
      localStringBuilder.append(javaSignature(paramArrayOfClass[i]));
      i += 1;
    }
    localStringBuilder.append(')');
    return localStringBuilder.toString();
  }
  
  static JavaMembers lookupClass(Scriptable paramScriptable, Class<?> paramClass1, Class<?> paramClass2, boolean paramBoolean)
  {
    ClassCache localClassCache = ClassCache.get(paramScriptable);
    Map localMap = localClassCache.getClassCacheMap();
    Object localObject1 = paramClass1;
    for (;;)
    {
      Object localObject2 = (JavaMembers)localMap.get(localObject1);
      if (localObject2 != null)
      {
        paramScriptable = (Scriptable)localObject2;
        if (localObject1 != paramClass1)
        {
          localMap.put(paramClass1, localObject2);
          paramScriptable = (Scriptable)localObject2;
        }
        label58:
        return paramScriptable;
      }
      try
      {
        localObject2 = new JavaMembers(localClassCache.getAssociatedScope(), (Class)localObject1, paramBoolean);
        paramScriptable = (Scriptable)localObject2;
        if (!localClassCache.isCachingEnabled()) {
          break label58;
        }
        localMap.put(localObject1, localObject2);
        paramScriptable = (Scriptable)localObject2;
        if (localObject1 == paramClass1) {
          break label58;
        }
        localMap.put(paramClass1, localObject2);
        return (JavaMembers)localObject2;
      }
      catch (SecurityException localSecurityException)
      {
        if ((paramClass2 != null) && (paramClass2.isInterface()))
        {
          localObject2 = null;
          paramScriptable = paramClass2;
        }
        for (;;)
        {
          localObject1 = paramScriptable;
          paramClass2 = (Class<?>)localObject2;
          break;
          Class localClass = ((Class)localObject1).getSuperclass();
          paramScriptable = localClass;
          localObject2 = paramClass2;
          if (localClass == null)
          {
            if (!((Class)localObject1).isInterface()) {
              break label186;
            }
            paramScriptable = ScriptRuntime.ObjectClass;
            localObject2 = paramClass2;
          }
        }
        label186:
        throw localSecurityException;
      }
    }
  }
  
  private void reflect(Scriptable paramScriptable, boolean paramBoolean1, boolean paramBoolean2)
  {
    Object localObject4 = discoverAccessibleMethods(this.cl, paramBoolean1, paramBoolean2);
    int j = localObject4.length;
    int i = 0;
    Object localObject1;
    Object localObject6;
    Object localObject7;
    label103:
    Object localObject3;
    if (i < j)
    {
      localObject5 = localObject4[i];
      if (Modifier.isStatic(((Method)localObject5).getModifiers())) {}
      for (localObject1 = this.staticMembers;; localObject1 = this.members)
      {
        localObject6 = ((Method)localObject5).getName();
        localObject7 = ((Map)localObject1).get(localObject6);
        if (localObject7 != null) {
          break label103;
        }
        ((Map)localObject1).put(localObject6, localObject5);
        i += 1;
        break;
      }
      if ((localObject7 instanceof ObjArray)) {}
      for (localObject1 = (ObjArray)localObject7;; localObject1 = localObject3)
      {
        ((ObjArray)localObject1).add(localObject5);
        break;
        if (!(localObject7 instanceof Method)) {
          Kit.codeBug();
        }
        localObject3 = new ObjArray();
        ((ObjArray)localObject3).add(localObject7);
        ((Map)localObject1).put(localObject6, localObject3);
      }
    }
    i = 0;
    label324:
    int k;
    while (i != 2)
    {
      if (i == 0)
      {
        j = 1;
        if (j == 0) {
          break label324;
        }
      }
      for (localObject1 = this.staticMembers;; localObject1 = this.members)
      {
        localObject4 = ((Map)localObject1).entrySet().iterator();
        for (;;)
        {
          if (!((Iterator)localObject4).hasNext()) {
            break label405;
          }
          localObject5 = (Map.Entry)((Iterator)localObject4).next();
          localObject3 = ((Map.Entry)localObject5).getValue();
          if (!(localObject3 instanceof Method)) {
            break;
          }
          localObject3 = new MemberBox[] { new MemberBox((Method)localObject3) };
          localObject3 = new NativeJavaMethod((MemberBox[])localObject3);
          if (paramScriptable != null) {
            ScriptRuntime.setFunctionProtoAndParent((BaseFunction)localObject3, paramScriptable);
          }
          ((Map)localObject1).put(((Map.Entry)localObject5).getKey(), localObject3);
        }
        j = 0;
        break;
      }
      localObject6 = (ObjArray)localObject3;
      k = ((ObjArray)localObject6).size();
      if (k < 2) {
        Kit.codeBug();
      }
      localObject3 = new MemberBox[k];
      j = 0;
      while (j != k)
      {
        localObject3[j] = new MemberBox((Method)((ObjArray)localObject6).get(j));
        j += 1;
      }
      label405:
      i += 1;
    }
    Object localObject5 = getAccessibleFields(paramBoolean1, paramBoolean2);
    j = localObject5.length;
    i = 0;
    for (;;)
    {
      Object localObject2;
      if (i < j)
      {
        localObject1 = localObject5[i];
        localObject6 = ((Field)localObject1).getName();
        k = ((Field)localObject1).getModifiers();
        for (;;)
        {
          try
          {
            paramBoolean1 = Modifier.isStatic(k);
            if (paramBoolean1)
            {
              localObject3 = this.staticMembers;
              localObject4 = ((Map)localObject3).get(localObject6);
              if (localObject4 == null)
              {
                ((Map)localObject3).put(localObject6, localObject1);
                break label1323;
              }
            }
            else
            {
              localObject3 = this.members;
              continue;
            }
            if (!(localObject4 instanceof NativeJavaMethod)) {
              break;
            }
            localObject7 = new FieldAndMethods(paramScriptable, ((NativeJavaMethod)localObject4).methods, (Field)localObject1);
            if (paramBoolean1)
            {
              localObject1 = this.staticFieldAndMethods;
              localObject4 = localObject1;
              if (localObject1 == null)
              {
                localObject4 = new HashMap();
                if (!paramBoolean1) {
                  break label667;
                }
                this.staticFieldAndMethods = ((Map)localObject4);
              }
              ((Map)localObject4).put(localObject6, localObject7);
              ((Map)localObject3).put(localObject6, localObject7);
            }
          }
          catch (SecurityException localSecurityException)
          {
            Context.reportWarning("Could not access field " + (String)localObject6 + " of class " + this.cl.getName() + " due to lack of privileges.");
          }
          localObject2 = this.fieldAndMethods;
          continue;
          label667:
          this.fieldAndMethods = ((Map)localObject4);
        }
        if ((localObject4 instanceof Field))
        {
          if (((Field)localObject4).getDeclaringClass().isAssignableFrom(((Field)localObject2).getDeclaringClass())) {
            ((Map)localObject3).put(localObject6, localObject2);
          }
        }
        else {
          Kit.codeBug();
        }
      }
      else
      {
        i = 0;
        if (i != 2) {
          if (i == 0)
          {
            paramBoolean1 = true;
            label741:
            if (!paramBoolean1) {
              break label1091;
            }
            localObject4 = this.staticMembers;
            label751:
            localObject6 = new HashMap();
            localObject7 = ((Map)localObject4).keySet().iterator();
          }
        }
        label774:
        label845:
        label892:
        label1051:
        label1091:
        label1106:
        label1296:
        label1314:
        label1318:
        for (;;)
        {
          char c;
          if (((Iterator)localObject7).hasNext())
          {
            paramScriptable = (String)((Iterator)localObject7).next();
            boolean bool1 = paramScriptable.startsWith("get");
            boolean bool2 = paramScriptable.startsWith("set");
            boolean bool3 = paramScriptable.startsWith("is");
            if ((!bool1) && (!bool3) && (!bool2)) {
              continue;
            }
            if (bool3)
            {
              j = 2;
              localObject2 = paramScriptable.substring(j);
              if (((String)localObject2).length() == 0) {
                continue;
              }
              c = ((String)localObject2).charAt(0);
              if (!Character.isUpperCase(c)) {
                break label1314;
              }
              if (((String)localObject2).length() != 1) {
                break label1106;
              }
              paramScriptable = ((String)localObject2).toLowerCase();
            }
          }
          for (;;)
          {
            if (((Map)localObject6).containsKey(paramScriptable)) {
              break label1318;
            }
            localObject3 = ((Map)localObject4).get(paramScriptable);
            if ((localObject3 != null) && ((!paramBoolean2) || (!(localObject3 instanceof Member)) || (!Modifier.isPrivate(((Member)localObject3).getModifiers())))) {
              break label774;
            }
            localObject5 = findGetter(paramBoolean1, (Map)localObject4, "get", (String)localObject2);
            if (localObject5 == null) {
              localObject5 = findGetter(paramBoolean1, (Map)localObject4, "is", (String)localObject2);
            }
            for (;;)
            {
              localObject2 = "set".concat((String)localObject2);
              if (((Map)localObject4).containsKey(localObject2))
              {
                localObject2 = ((Map)localObject4).get(localObject2);
                if ((localObject2 instanceof NativeJavaMethod))
                {
                  localObject3 = (NativeJavaMethod)localObject2;
                  if (localObject5 != null)
                  {
                    localObject2 = extractSetMethod(((MemberBox)localObject5).method().getReturnType(), ((NativeJavaMethod)localObject3).methods, paramBoolean1);
                    if (((NativeJavaMethod)localObject3).methods.length <= 1) {
                      break label1296;
                    }
                  }
                }
              }
              for (;;)
              {
                ((Map)localObject6).put(paramScriptable, new BeanProperty((MemberBox)localObject5, (MemberBox)localObject2, (NativeJavaMethod)localObject3));
                break label774;
                paramBoolean1 = false;
                break label741;
                localObject4 = this.members;
                break label751;
                j = 3;
                break label845;
                if (Character.isUpperCase(((String)localObject2).charAt(1))) {
                  break label1314;
                }
                paramScriptable = Character.toLowerCase(c) + ((String)localObject2).substring(1);
                break label892;
                localObject2 = extractSetMethod(((NativeJavaMethod)localObject3).methods, paramBoolean1);
                break label1051;
                paramScriptable = ((Map)localObject6).keySet().iterator();
                while (paramScriptable.hasNext())
                {
                  localObject2 = (String)paramScriptable.next();
                  ((Map)localObject4).put(localObject2, ((Map)localObject6).get(localObject2));
                }
                i += 1;
                break;
                paramScriptable = getAccessibleConstructors(paramBoolean2);
                localObject2 = new MemberBox[paramScriptable.length];
                i = 0;
                while (i != paramScriptable.length)
                {
                  localObject2[i] = new MemberBox(paramScriptable[i]);
                  i += 1;
                }
                this.ctors = new NativeJavaMethod((MemberBox[])localObject2, this.cl.getSimpleName());
                return;
                localObject3 = null;
                continue;
                localObject3 = null;
                localObject2 = null;
              }
            }
            paramScriptable = (Scriptable)localObject2;
          }
        }
        break;
      }
      label1323:
      i += 1;
    }
  }
  
  /* Error */
  Object get(Scriptable paramScriptable, String paramString, Object paramObject, boolean paramBoolean)
  {
    // Byte code:
    //   0: iload 4
    //   2: ifeq +81 -> 83
    //   5: aload_0
    //   6: getfield 74	org/mozilla/javascript/JavaMembers:staticMembers	Ljava/util/Map;
    //   9: astore 5
    //   11: aload 5
    //   13: aload_2
    //   14: invokeinterface 251 2 0
    //   19: astore 5
    //   21: aload 5
    //   23: astore 6
    //   25: iload 4
    //   27: ifne +24 -> 51
    //   30: aload 5
    //   32: astore 6
    //   34: aload 5
    //   36: ifnonnull +15 -> 51
    //   39: aload_0
    //   40: getfield 74	org/mozilla/javascript/JavaMembers:staticMembers	Ljava/util/Map;
    //   43: aload_2
    //   44: invokeinterface 251 2 0
    //   49: astore 6
    //   51: aload 6
    //   53: astore 5
    //   55: aload 6
    //   57: ifnonnull +35 -> 92
    //   60: aload_0
    //   61: aload_1
    //   62: aload_2
    //   63: aload_3
    //   64: iload 4
    //   66: invokespecial 515	org/mozilla/javascript/JavaMembers:getExplicitFunction	(Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;Ljava/lang/Object;Z)Ljava/lang/Object;
    //   69: astore_2
    //   70: aload_2
    //   71: astore 5
    //   73: aload_2
    //   74: ifnonnull +18 -> 92
    //   77: getstatic 521	org/mozilla/javascript/Scriptable:NOT_FOUND	Ljava/lang/Object;
    //   80: astore_2
    //   81: aload_2
    //   82: areturn
    //   83: aload_0
    //   84: getfield 72	org/mozilla/javascript/JavaMembers:members	Ljava/util/Map;
    //   87: astore 5
    //   89: goto -78 -> 11
    //   92: aload 5
    //   94: astore_2
    //   95: aload 5
    //   97: instanceof 517
    //   100: ifne -19 -> 81
    //   103: invokestatic 524	org/mozilla/javascript/Context:getContext	()Lorg/mozilla/javascript/Context;
    //   106: astore 6
    //   108: aload 5
    //   110: instanceof 492
    //   113: ifeq +62 -> 175
    //   116: aload 5
    //   118: checkcast 492	org/mozilla/javascript/BeanProperty
    //   121: astore_2
    //   122: aload_2
    //   123: getfield 528	org/mozilla/javascript/BeanProperty:getter	Lorg/mozilla/javascript/MemberBox;
    //   126: ifnonnull +7 -> 133
    //   129: getstatic 521	org/mozilla/javascript/Scriptable:NOT_FOUND	Ljava/lang/Object;
    //   132: areturn
    //   133: aload_2
    //   134: getfield 528	org/mozilla/javascript/BeanProperty:getter	Lorg/mozilla/javascript/MemberBox;
    //   137: aload_3
    //   138: getstatic 532	org/mozilla/javascript/Context:emptyArgs	[Ljava/lang/Object;
    //   141: invokevirtual 536	org/mozilla/javascript/MemberBox:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   144: astore_3
    //   145: aload_2
    //   146: getfield 528	org/mozilla/javascript/BeanProperty:getter	Lorg/mozilla/javascript/MemberBox;
    //   149: invokevirtual 195	org/mozilla/javascript/MemberBox:method	()Ljava/lang/reflect/Method;
    //   152: invokevirtual 198	java/lang/reflect/Method:getReturnType	()Ljava/lang/Class;
    //   155: astore_2
    //   156: aload_1
    //   157: invokestatic 539	org/mozilla/javascript/ScriptableObject:getTopLevelScope	(Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;
    //   160: astore_1
    //   161: aload 6
    //   163: invokevirtual 543	org/mozilla/javascript/Context:getWrapFactory	()Lorg/mozilla/javascript/WrapFactory;
    //   166: aload 6
    //   168: aload_1
    //   169: aload_3
    //   170: aload_2
    //   171: invokevirtual 549	org/mozilla/javascript/WrapFactory:wrap	(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
    //   174: areturn
    //   175: aload 5
    //   177: checkcast 295	java/lang/reflect/Field
    //   180: astore_2
    //   181: iload 4
    //   183: ifeq +5 -> 188
    //   186: aconst_null
    //   187: astore_3
    //   188: aload_2
    //   189: aload_3
    //   190: invokevirtual 550	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   193: astore_3
    //   194: aload_2
    //   195: invokevirtual 553	java/lang/reflect/Field:getType	()Ljava/lang/Class;
    //   198: astore_2
    //   199: goto -43 -> 156
    //   202: astore_1
    //   203: aload_1
    //   204: invokestatic 557	org/mozilla/javascript/Context:throwAsScriptRuntimeEx	(Ljava/lang/Throwable;)Ljava/lang/RuntimeException;
    //   207: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	208	0	this	JavaMembers
    //   0	208	1	paramScriptable	Scriptable
    //   0	208	2	paramString	String
    //   0	208	3	paramObject	Object
    //   0	208	4	paramBoolean	boolean
    //   9	167	5	localObject1	Object
    //   23	144	6	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   108	133	202	java/lang/Exception
    //   133	156	202	java/lang/Exception
    //   175	181	202	java/lang/Exception
    //   188	199	202	java/lang/Exception
  }
  
  Map<String, FieldAndMethods> getFieldAndMethodsObjects(Scriptable paramScriptable, Object paramObject, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (Object localObject = this.staticFieldAndMethods; localObject == null; localObject = this.fieldAndMethods) {
      return null;
    }
    HashMap localHashMap = new HashMap(((Map)localObject).size());
    localObject = ((Map)localObject).values().iterator();
    while (((Iterator)localObject).hasNext())
    {
      FieldAndMethods localFieldAndMethods1 = (FieldAndMethods)((Iterator)localObject).next();
      FieldAndMethods localFieldAndMethods2 = new FieldAndMethods(paramScriptable, localFieldAndMethods1.methods, localFieldAndMethods1.field);
      localFieldAndMethods2.javaObject = paramObject;
      localHashMap.put(localFieldAndMethods1.field.getName(), localFieldAndMethods2);
    }
    return localHashMap;
  }
  
  Object[] getIds(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (Map localMap = this.staticMembers;; localMap = this.members) {
      return localMap.keySet().toArray(new Object[localMap.size()]);
    }
  }
  
  boolean has(String paramString, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (Map localMap = this.staticMembers; localMap.get(paramString) != null; localMap = this.members) {
      return true;
    }
    if (findExplicitFunction(paramString, paramBoolean) != null) {}
    for (paramBoolean = true;; paramBoolean = false) {
      return paramBoolean;
    }
  }
  
  void put(Scriptable paramScriptable, String paramString, Object paramObject1, Object paramObject2, boolean paramBoolean)
  {
    if (paramBoolean) {}
    Object localObject1;
    for (Map localMap = this.staticMembers;; localMap = this.members)
    {
      Object localObject2 = localMap.get(paramString);
      localObject1 = localObject2;
      if (!paramBoolean)
      {
        localObject1 = localObject2;
        if (localObject2 == null) {
          localObject1 = this.staticMembers.get(paramString);
        }
      }
      if (localObject1 != null) {
        break;
      }
      throw reportMemberNotFound(paramString);
    }
    if ((localObject1 instanceof FieldAndMethods)) {
      localObject1 = ((FieldAndMethods)localMap.get(paramString)).field;
    }
    for (;;)
    {
      if ((localObject1 instanceof BeanProperty))
      {
        localObject1 = (BeanProperty)localObject1;
        if (((BeanProperty)localObject1).setter == null) {
          throw reportMemberNotFound(paramString);
        }
        if ((((BeanProperty)localObject1).setters == null) || (paramObject2 == null)) {
          paramScriptable = Context.jsToJava(paramObject2, localObject1.setter.argTypes[0]);
        }
      }
      for (;;)
      {
        try
        {
          ((BeanProperty)localObject1).setter.invoke(paramObject1, new Object[] { paramScriptable });
          return;
        }
        catch (Exception paramScriptable)
        {
          throw Context.throwAsScriptRuntimeEx(paramScriptable);
        }
        ((BeanProperty)localObject1).setters.call(Context.getContext(), ScriptableObject.getTopLevelScope(paramScriptable), paramScriptable, new Object[] { paramObject2 });
        return;
        if (!(localObject1 instanceof Field))
        {
          if (localObject1 == null) {}
          for (paramScriptable = "msg.java.internal.private";; paramScriptable = "msg.java.method.assign") {
            throw Context.reportRuntimeError1(paramScriptable, paramString);
          }
        }
        paramScriptable = (Field)localObject1;
        paramString = Context.jsToJava(paramObject2, paramScriptable.getType());
        try
        {
          paramScriptable.set(paramObject1, paramString);
          return;
        }
        catch (IllegalAccessException paramString)
        {
          if ((paramScriptable.getModifiers() & 0x10) == 0) {
            throw Context.throwAsScriptRuntimeEx(paramString);
          }
        }
        catch (IllegalArgumentException paramString)
        {
          throw Context.reportRuntimeError3("msg.java.internal.field.type", paramObject2.getClass().getName(), paramScriptable, paramObject1.getClass().getName());
        }
      }
    }
  }
  
  RuntimeException reportMemberNotFound(String paramString)
  {
    return Context.reportRuntimeError2("msg.java.member.not.found", this.cl.getName(), paramString);
  }
  
  private static final class MethodSignature
  {
    private final Class<?>[] args;
    private final String name;
    
    private MethodSignature(String paramString, Class<?>[] paramArrayOfClass)
    {
      this.name = paramString;
      this.args = paramArrayOfClass;
    }
    
    MethodSignature(Method paramMethod)
    {
      this(paramMethod.getName(), paramMethod.getParameterTypes());
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = false;
      boolean bool1 = bool2;
      if ((paramObject instanceof MethodSignature))
      {
        paramObject = (MethodSignature)paramObject;
        bool1 = bool2;
        if (((MethodSignature)paramObject).name.equals(this.name))
        {
          bool1 = bool2;
          if (Arrays.equals(this.args, ((MethodSignature)paramObject).args)) {
            bool1 = true;
          }
        }
      }
      return bool1;
    }
    
    public int hashCode()
    {
      return this.name.hashCode() ^ this.args.length;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\JavaMembers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */