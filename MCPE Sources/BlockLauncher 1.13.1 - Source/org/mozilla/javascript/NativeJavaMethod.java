package org.mozilla.javascript;

import java.lang.reflect.Array;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class NativeJavaMethod
  extends BaseFunction
{
  private static final int PREFERENCE_AMBIGUOUS = 3;
  private static final int PREFERENCE_EQUAL = 0;
  private static final int PREFERENCE_FIRST_ARG = 1;
  private static final int PREFERENCE_SECOND_ARG = 2;
  private static MethodWatcher blockLauncherMethodWatcher = null;
  private static final boolean debug = false;
  static final long serialVersionUID = -3440381785576412928L;
  private String functionName;
  MemberBox[] methods;
  private transient CopyOnWriteArrayList<ResolvedOverload> overloadCache;
  
  public NativeJavaMethod(Method paramMethod, String paramString)
  {
    this(new MemberBox(paramMethod), paramString);
  }
  
  NativeJavaMethod(MemberBox paramMemberBox, String paramString)
  {
    this.functionName = paramString;
    this.methods = new MemberBox[] { paramMemberBox };
  }
  
  NativeJavaMethod(MemberBox[] paramArrayOfMemberBox)
  {
    this.functionName = paramArrayOfMemberBox[0].getName();
    this.methods = paramArrayOfMemberBox;
  }
  
  NativeJavaMethod(MemberBox[] paramArrayOfMemberBox, String paramString)
  {
    this.functionName = paramString;
    this.methods = paramArrayOfMemberBox;
  }
  
  static int findFunction(Context paramContext, MemberBox[] paramArrayOfMemberBox, Object[] paramArrayOfObject)
  {
    if (paramArrayOfMemberBox.length == 0)
    {
      k = -1;
      return k;
    }
    if (paramArrayOfMemberBox.length == 1)
    {
      paramContext = paramArrayOfMemberBox[0];
      paramArrayOfMemberBox = paramContext.argTypes;
      j = paramArrayOfMemberBox.length;
      if (paramContext.vararg)
      {
        j -= 1;
        i = j;
        if (j > paramArrayOfObject.length) {
          return -1;
        }
      }
      else
      {
        i = j;
        if (j != paramArrayOfObject.length) {
          return -1;
        }
      }
      j = 0;
      while (j != i)
      {
        if (!NativeJavaObject.canConvert(paramArrayOfObject[j], paramArrayOfMemberBox[j])) {
          return -1;
        }
        j += 1;
      }
      return 0;
    }
    int j = -1;
    Object localObject1 = null;
    int i = 0;
    int k = 0;
    label114:
    Object localObject2;
    Class[] arrayOfClass;
    int n;
    int m;
    if (k < paramArrayOfMemberBox.length)
    {
      localObject2 = paramArrayOfMemberBox[k];
      arrayOfClass = ((MemberBox)localObject2).argTypes;
      n = arrayOfClass.length;
      if (((MemberBox)localObject2).vararg)
      {
        n -= 1;
        m = n;
        if (n <= paramArrayOfObject.length) {
          break label187;
        }
      }
    }
    for (;;)
    {
      k += 1;
      break label114;
      m = n;
      if (n == paramArrayOfObject.length)
      {
        label187:
        n = 0;
        for (;;)
        {
          if (n >= m) {
            break label224;
          }
          if (!NativeJavaObject.canConvert(paramArrayOfObject[n], arrayOfClass[n])) {
            break;
          }
          n += 1;
        }
        label224:
        if (j < 0)
        {
          j = k;
        }
        else
        {
          n = 0;
          m = 0;
          int i1 = -1;
          label245:
          int i2;
          label261:
          MemberBox localMemberBox;
          if (i1 != i) {
            if (i1 == -1)
            {
              i2 = j;
              localMemberBox = paramArrayOfMemberBox[i2];
              if ((!paramContext.hasFeature(13)) || ((localMemberBox.member().getModifiers() & 0x1) == (((MemberBox)localObject2).member().getModifiers() & 0x1))) {
                break label352;
              }
              if ((localMemberBox.member().getModifiers() & 0x1) != 0) {
                break label343;
              }
              n += 1;
            }
          }
          for (;;)
          {
            i1 += 1;
            break label245;
            i2 = localObject1[i1];
            break label261;
            label343:
            m += 1;
            continue;
            label352:
            i2 = preferSignature(paramArrayOfObject, arrayOfClass, ((MemberBox)localObject2).vararg, localMemberBox.argTypes, localMemberBox.vararg);
            if (i2 == 3)
            {
              if (n != i + 1) {
                break label484;
              }
              j = k;
              i = 0;
              break;
            }
            if (i2 == 1)
            {
              n += 1;
            }
            else
            {
              if (i2 != 2) {
                break label428;
              }
              m += 1;
            }
          }
          label428:
          if (i2 != 0) {
            Kit.codeBug();
          }
          if ((localMemberBox.isStatic()) && (localMemberBox.getDeclaringClass().isAssignableFrom(((MemberBox)localObject2).getDeclaringClass()))) {
            if (i1 == -1)
            {
              j = k;
            }
            else
            {
              localObject1[i1] = k;
              continue;
              label484:
              if (m != i + 1)
              {
                localObject2 = localObject1;
                if (localObject1 == null) {
                  localObject2 = new int[paramArrayOfMemberBox.length - 1];
                }
                localObject2[i] = k;
                localObject1 = localObject2;
                i += 1;
                continue;
                if (j < 0) {
                  return -1;
                }
                k = j;
                if (i == 0) {
                  break;
                }
                paramContext = new StringBuilder();
                k = -1;
                if (k != i)
                {
                  if (k == -1) {}
                  for (m = j;; m = localObject1[k])
                  {
                    paramContext.append("\n    ");
                    paramContext.append(paramArrayOfMemberBox[m].toJavaDeclaration());
                    k += 1;
                    break;
                  }
                }
                localObject2 = paramArrayOfMemberBox[j];
                localObject1 = ((MemberBox)localObject2).getName();
                localObject2 = ((MemberBox)localObject2).getDeclaringClass().getName();
                if (paramArrayOfMemberBox[0].isCtor()) {
                  throw Context.reportRuntimeError3("msg.constructor.ambiguous", localObject1, scriptSignature(paramArrayOfObject), paramContext.toString());
                }
                throw Context.reportRuntimeError4("msg.method.ambiguous", localObject2, localObject1, scriptSignature(paramArrayOfObject), paramContext.toString());
              }
            }
          }
        }
      }
    }
  }
  
  private static int preferSignature(Object[] paramArrayOfObject, Class<?>[] paramArrayOfClass1, boolean paramBoolean1, Class<?>[] paramArrayOfClass2, boolean paramBoolean2)
  {
    int k = 0;
    int j = 0;
    int i = j;
    Class<?> localClass1;
    label36:
    Class<?> localClass2;
    label81:
    label90:
    int m;
    if (k < paramArrayOfObject.length)
    {
      if ((paramBoolean1) && (k >= paramArrayOfClass1.length))
      {
        localClass1 = paramArrayOfClass1[(paramArrayOfClass1.length - 1)];
        if ((!paramBoolean2) || (k < paramArrayOfClass2.length)) {
          break label81;
        }
      }
      for (localClass2 = paramArrayOfClass2[(paramArrayOfClass2.length - 1)];; localClass2 = paramArrayOfClass2[k])
      {
        if (localClass1 != localClass2) {
          break label90;
        }
        k += 1;
        break;
        localClass1 = paramArrayOfClass1[k];
        break label36;
      }
      Object localObject = paramArrayOfObject[k];
      i = NativeJavaObject.getConversionWeight(localObject, localClass1);
      m = NativeJavaObject.getConversionWeight(localObject, localClass2);
      if (i >= m) {
        break label144;
      }
      i = 1;
    }
    for (;;)
    {
      i = j | i;
      j = i;
      if (i != 3) {
        break;
      }
      return i;
      label144:
      if (i > m) {
        i = 2;
      } else if (i == 0)
      {
        if (localClass1.isAssignableFrom(localClass2)) {
          i = 2;
        } else if (localClass2.isAssignableFrom(localClass1)) {
          i = 1;
        } else {
          i = 3;
        }
      }
      else {
        i = 3;
      }
    }
  }
  
  private static void printDebug(String paramString, MemberBox paramMemberBox, Object[] paramArrayOfObject) {}
  
  static String scriptSignature(Object[] paramArrayOfObject)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    if (i != paramArrayOfObject.length)
    {
      Object localObject = paramArrayOfObject[i];
      if (localObject == null) {
        localObject = "null";
      }
      for (;;)
      {
        if (i != 0) {
          localStringBuilder.append(',');
        }
        localStringBuilder.append((String)localObject);
        i += 1;
        break;
        if ((localObject instanceof Boolean)) {
          localObject = "boolean";
        } else if ((localObject instanceof String)) {
          localObject = "string";
        } else if ((localObject instanceof Number)) {
          localObject = "number";
        } else if ((localObject instanceof Scriptable))
        {
          if ((localObject instanceof Undefined)) {
            localObject = "undefined";
          } else if ((localObject instanceof Wrapper)) {
            localObject = ((Wrapper)localObject).unwrap().getClass().getName();
          } else if ((localObject instanceof Function)) {
            localObject = "function";
          } else {
            localObject = "object";
          }
        }
        else {
          localObject = JavaMembers.javaSignature(localObject.getClass());
        }
      }
    }
    return localStringBuilder.toString();
  }
  
  public static void setMethodWatcher(MethodWatcher paramMethodWatcher)
  {
    if (blockLauncherMethodWatcher == null) {
      blockLauncherMethodWatcher = paramMethodWatcher;
    }
  }
  
  public Object call(Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    int i = 0;
    int j = 0;
    if (this.methods.length == 0) {
      throw new RuntimeException("No methods defined for call");
    }
    int k = findCachedFunction(paramContext, paramArrayOfObject);
    if (k < 0)
    {
      paramContext = this.methods[0].method().getDeclaringClass();
      throw Context.reportRuntimeError1("msg.java.no_such_method", paramContext.getName() + '.' + getFunctionName() + '(' + scriptSignature(paramArrayOfObject) + ')');
    }
    MemberBox localMemberBox = this.methods[k];
    Object localObject4 = localMemberBox.argTypes;
    Object localObject2;
    Object localObject1;
    if (localMemberBox.vararg)
    {
      localObject2 = new Object[localObject4.length];
      i = 0;
      while (i < localObject4.length - 1)
      {
        localObject2[i] = Context.jsToJava(paramArrayOfObject[i], localObject4[i]);
        i += 1;
      }
      if ((paramArrayOfObject.length == localObject4.length) && ((paramArrayOfObject[(paramArrayOfObject.length - 1)] == null) || ((paramArrayOfObject[(paramArrayOfObject.length - 1)] instanceof NativeArray)) || ((paramArrayOfObject[(paramArrayOfObject.length - 1)] instanceof NativeJavaArray))))
      {
        localObject1 = Context.jsToJava(paramArrayOfObject[(paramArrayOfObject.length - 1)], localObject4[(localObject4.length - 1)]);
        localObject2[(localObject4.length - 1)] = localObject1;
      }
    }
    for (paramArrayOfObject = (Object[])localObject2;; paramArrayOfObject = (Object[])localObject1)
    {
      if (localMemberBox.isStatic())
      {
        localObject1 = null;
        if ((blockLauncherMethodWatcher == null) || (blockLauncherMethodWatcher.canCall(localMemberBox.method(), localObject1))) {
          break label543;
        }
        paramContext = Undefined.instance;
      }
      label543:
      do
      {
        do
        {
          return paramContext;
          Class localClass = localObject4[(localObject4.length - 1)].getComponentType();
          Object localObject3 = Array.newInstance(localClass, paramArrayOfObject.length - localObject4.length + 1);
          i = j;
          for (;;)
          {
            localObject1 = localObject3;
            if (i >= Array.getLength(localObject3)) {
              break;
            }
            Array.set(localObject3, i, Context.jsToJava(paramArrayOfObject[(localObject4.length - 1 + i)], localClass));
            i += 1;
          }
          for (localObject1 = paramArrayOfObject; i < localObject1.length; localObject1 = localObject2)
          {
            localClass = localObject1[i];
            localObject3 = Context.jsToJava(localClass, localObject4[i]);
            localObject2 = localObject1;
            if (localObject3 != localClass)
            {
              localObject2 = localObject1;
              if (paramArrayOfObject == localObject1) {
                localObject2 = (Object[])((Object[])localObject1).clone();
              }
              localObject2[i] = localObject3;
            }
            i += 1;
          }
          localObject4 = localMemberBox.getDeclaringClass();
          for (localObject2 = paramScriptable2;; localObject2 = ((Scriptable)localObject2).getPrototype())
          {
            if (localObject2 == null) {
              throw Context.reportRuntimeError3("msg.nonjava.method", getFunctionName(), ScriptRuntime.toString(paramScriptable2), ((Class)localObject4).getName());
            }
            if ((localObject2 instanceof Wrapper))
            {
              localObject3 = ((Wrapper)localObject2).unwrap();
              localObject1 = localObject3;
              if (((Class)localObject4).isInstance(localObject3)) {
                break;
              }
            }
          }
          paramArrayOfObject = localMemberBox.invoke(localObject1, paramArrayOfObject);
          paramScriptable2 = localMemberBox.method().getReturnType();
          paramScriptable1 = paramContext.getWrapFactory().wrap(paramContext, paramScriptable1, paramArrayOfObject, paramScriptable2);
          paramContext = paramScriptable1;
        } while (paramScriptable1 != null);
        paramContext = paramScriptable1;
      } while (paramScriptable2 != Void.TYPE);
      return Undefined.instance;
    }
  }
  
  String decompile(int paramInt1, int paramInt2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if ((paramInt2 & 0x1) != 0)
    {
      paramInt1 = 1;
      if (paramInt1 == 0)
      {
        localStringBuilder.append("function ");
        localStringBuilder.append(getFunctionName());
        localStringBuilder.append("() {");
      }
      localStringBuilder.append("/*\n");
      localStringBuilder.append(toString());
      if (paramInt1 == 0) {
        break label94;
      }
    }
    label94:
    for (String str = "*/\n";; str = "*/}\n")
    {
      localStringBuilder.append(str);
      return localStringBuilder.toString();
      paramInt1 = 0;
      break;
    }
  }
  
  int findCachedFunction(Context arg1, Object[] paramArrayOfObject)
  {
    if (this.methods.length > 1)
    {
      int i;
      if (this.overloadCache != null)
      {
        Iterator localIterator = this.overloadCache.iterator();
        while (localIterator.hasNext())
        {
          ResolvedOverload localResolvedOverload = (ResolvedOverload)localIterator.next();
          if (localResolvedOverload.matches(paramArrayOfObject)) {
            i = localResolvedOverload.index;
          }
        }
      }
      int j;
      do
      {
        return i;
        this.overloadCache = new CopyOnWriteArrayList();
        j = findFunction(???, this.methods, paramArrayOfObject);
        i = j;
      } while (this.overloadCache.size() >= this.methods.length * 2);
      synchronized (this.overloadCache)
      {
        paramArrayOfObject = new ResolvedOverload(paramArrayOfObject, j);
        if (!this.overloadCache.contains(paramArrayOfObject)) {
          this.overloadCache.add(0, paramArrayOfObject);
        }
        return j;
      }
    }
    return findFunction(???, this.methods, paramArrayOfObject);
  }
  
  public String getFunctionName()
  {
    return this.functionName;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    int j = this.methods.length;
    if (i != j)
    {
      if (this.methods[i].isMethod())
      {
        Method localMethod = this.methods[i].method();
        localStringBuilder.append(JavaMembers.javaSignature(localMethod.getReturnType()));
        localStringBuilder.append(' ');
        localStringBuilder.append(localMethod.getName());
      }
      for (;;)
      {
        localStringBuilder.append(JavaMembers.liveConnectSignature(this.methods[i].argTypes));
        localStringBuilder.append('\n');
        i += 1;
        break;
        localStringBuilder.append(this.methods[i].getName());
      }
    }
    return localStringBuilder.toString();
  }
  
  public static abstract interface MethodWatcher
  {
    public abstract boolean canCall(Method paramMethod, Object paramObject);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\NativeJavaMethod.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */