package org.mozilla.javascript;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import org.mozilla.javascript.json.JsonParser;
import org.mozilla.javascript.json.JsonParser.ParseException;

public final class NativeJSON
  extends IdScriptableObject
{
  private static final int Id_parse = 2;
  private static final int Id_stringify = 3;
  private static final int Id_toSource = 1;
  private static final Object JSON_TAG = "JSON";
  private static final int LAST_METHOD_ID = 3;
  private static final int MAX_ID = 3;
  private static final int MAX_STRINGIFY_GAP_LENGTH = 10;
  static final long serialVersionUID = -4567599697595654984L;
  
  static void init(Scriptable paramScriptable, boolean paramBoolean)
  {
    NativeJSON localNativeJSON = new NativeJSON();
    localNativeJSON.activatePrototypeMap(3);
    localNativeJSON.setPrototype(getObjectPrototype(paramScriptable));
    localNativeJSON.setParentScope(paramScriptable);
    if (paramBoolean) {
      localNativeJSON.sealObject();
    }
    ScriptableObject.defineProperty(paramScriptable, "JSON", localNativeJSON, 2);
  }
  
  private static String ja(NativeArray paramNativeArray, StringifyState paramStringifyState)
  {
    if (paramStringifyState.stack.search(paramNativeArray) != -1) {
      throw ScriptRuntime.typeError0("msg.cyclic.value");
    }
    paramStringifyState.stack.push(paramNativeArray);
    String str = paramStringifyState.indent;
    paramStringifyState.indent += paramStringifyState.gap;
    LinkedList localLinkedList = new LinkedList();
    long l2 = paramNativeArray.getLength();
    long l1 = 0L;
    if (l1 < l2)
    {
      Object localObject;
      if (l1 > 2147483647L)
      {
        localObject = str(Long.toString(l1), paramNativeArray, paramStringifyState);
        label104:
        if (localObject != Undefined.instance) {
          break label144;
        }
        localLinkedList.add("null");
      }
      for (;;)
      {
        l1 = 1L + l1;
        break;
        localObject = str(Integer.valueOf((int)l1), paramNativeArray, paramStringifyState);
        break label104;
        label144:
        localLinkedList.add(localObject);
      }
    }
    if (localLinkedList.isEmpty()) {
      paramNativeArray = "[]";
    }
    for (;;)
    {
      paramStringifyState.stack.pop();
      paramStringifyState.indent = str;
      return paramNativeArray;
      if (paramStringifyState.gap.length() == 0)
      {
        paramNativeArray = '[' + join(localLinkedList, ",") + ']';
      }
      else
      {
        paramNativeArray = join(localLinkedList, ",\n" + paramStringifyState.indent);
        paramNativeArray = "[\n" + paramStringifyState.indent + paramNativeArray + '\n' + str + ']';
      }
    }
  }
  
  private static String jo(Scriptable paramScriptable, StringifyState paramStringifyState)
  {
    if (paramStringifyState.stack.search(paramScriptable) != -1) {
      throw ScriptRuntime.typeError0("msg.cyclic.value");
    }
    paramStringifyState.stack.push(paramScriptable);
    String str2 = paramStringifyState.indent;
    paramStringifyState.indent += paramStringifyState.gap;
    if (paramStringifyState.propertyList != null) {}
    LinkedList localLinkedList;
    for (Object[] arrayOfObject = paramStringifyState.propertyList.toArray();; arrayOfObject = paramScriptable.getIds())
    {
      localLinkedList = new LinkedList();
      int j = arrayOfObject.length;
      int i = 0;
      while (i < j)
      {
        Object localObject1 = arrayOfObject[i];
        Object localObject2 = str(localObject1, paramScriptable, paramStringifyState);
        if (localObject2 != Undefined.instance)
        {
          String str1 = quote(localObject1.toString()) + ":";
          localObject1 = str1;
          if (paramStringifyState.gap.length() > 0) {
            localObject1 = str1 + " ";
          }
          localLinkedList.add((String)localObject1 + localObject2);
        }
        i += 1;
      }
    }
    if (localLinkedList.isEmpty()) {
      paramScriptable = "{}";
    }
    for (;;)
    {
      paramStringifyState.stack.pop();
      paramStringifyState.indent = str2;
      return paramScriptable;
      if (paramStringifyState.gap.length() == 0)
      {
        paramScriptable = '{' + join(localLinkedList, ",") + '}';
      }
      else
      {
        paramScriptable = join(localLinkedList, ",\n" + paramStringifyState.indent);
        paramScriptable = "{\n" + paramStringifyState.indent + paramScriptable + '\n' + str2 + '}';
      }
    }
  }
  
  private static String join(Collection<Object> paramCollection, String paramString)
  {
    if ((paramCollection == null) || (paramCollection.isEmpty())) {
      return "";
    }
    paramCollection = paramCollection.iterator();
    if (!paramCollection.hasNext()) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder(paramCollection.next().toString());
    while (paramCollection.hasNext()) {
      localStringBuilder.append(paramString).append(paramCollection.next().toString());
    }
    return localStringBuilder.toString();
  }
  
  private static Object parse(Context paramContext, Scriptable paramScriptable, String paramString)
  {
    try
    {
      paramContext = new JsonParser(paramContext, paramScriptable).parseValue(paramString);
      return paramContext;
    }
    catch (JsonParser.ParseException paramContext)
    {
      throw ScriptRuntime.constructError("SyntaxError", paramContext.getMessage());
    }
  }
  
  public static Object parse(Context paramContext, Scriptable paramScriptable, String paramString, Callable paramCallable)
  {
    paramString = parse(paramContext, paramScriptable, paramString);
    Scriptable localScriptable = paramContext.newObject(paramScriptable);
    localScriptable.put("", localScriptable, paramString);
    return walk(paramContext, paramScriptable, paramCallable, localScriptable, "");
  }
  
  private static String quote(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramString.length() + 2);
    localStringBuilder.append('"');
    int j = paramString.length();
    int i = 0;
    if (i < j)
    {
      char c = paramString.charAt(i);
      switch (c)
      {
      default: 
        if (c < ' ')
        {
          localStringBuilder.append("\\u");
          localStringBuilder.append(String.format("%04x", new Object[] { Integer.valueOf(c) }));
        }
        break;
      }
      for (;;)
      {
        i += 1;
        break;
        localStringBuilder.append("\\\"");
        continue;
        localStringBuilder.append("\\\\");
        continue;
        localStringBuilder.append("\\b");
        continue;
        localStringBuilder.append("\\f");
        continue;
        localStringBuilder.append("\\n");
        continue;
        localStringBuilder.append("\\r");
        continue;
        localStringBuilder.append("\\t");
        continue;
        localStringBuilder.append(c);
      }
    }
    localStringBuilder.append('"');
    return localStringBuilder.toString();
  }
  
  private static String repeat(char paramChar, int paramInt)
  {
    char[] arrayOfChar = new char[paramInt];
    Arrays.fill(arrayOfChar, paramChar);
    return new String(arrayOfChar);
  }
  
  private static Object str(Object paramObject, Scriptable paramScriptable, StringifyState paramStringifyState)
  {
    Object localObject1;
    Object localObject2;
    if ((paramObject instanceof String))
    {
      localObject1 = getProperty(paramScriptable, (String)paramObject);
      localObject2 = localObject1;
      if ((localObject1 instanceof Scriptable))
      {
        localObject2 = localObject1;
        if ((getProperty((Scriptable)localObject1, "toJSON") instanceof Callable)) {
          localObject2 = callMethod(paramStringifyState.cx, (Scriptable)localObject1, "toJSON", new Object[] { paramObject });
        }
      }
      if (paramStringifyState.replacer == null) {
        break label322;
      }
    }
    label319:
    label322:
    for (paramObject = paramStringifyState.replacer.call(paramStringifyState.cx, paramStringifyState.scope, paramScriptable, new Object[] { paramObject, localObject2 });; paramObject = localObject2)
    {
      if ((paramObject instanceof NativeNumber)) {
        paramObject = Double.valueOf(ScriptRuntime.toNumber(paramObject));
      }
      for (;;)
      {
        if (paramObject == null)
        {
          return "null";
          localObject1 = getProperty(paramScriptable, ((Number)paramObject).intValue());
          break;
          if ((paramObject instanceof NativeString))
          {
            paramObject = ScriptRuntime.toString(paramObject);
            continue;
          }
          if (!(paramObject instanceof NativeBoolean)) {
            break label319;
          }
          paramObject = ((NativeBoolean)paramObject).getDefaultValue(ScriptRuntime.BooleanClass);
          continue;
        }
        if (paramObject.equals(Boolean.TRUE)) {
          return "true";
        }
        if (paramObject.equals(Boolean.FALSE)) {
          return "false";
        }
        if ((paramObject instanceof CharSequence)) {
          return quote(paramObject.toString());
        }
        if ((paramObject instanceof Number))
        {
          double d = ((Number)paramObject).doubleValue();
          if ((d == d) && (d != Double.POSITIVE_INFINITY) && (d != Double.NEGATIVE_INFINITY)) {
            return ScriptRuntime.toString(paramObject);
          }
          return "null";
        }
        if (((paramObject instanceof Scriptable)) && (!(paramObject instanceof Callable)))
        {
          if ((paramObject instanceof NativeArray)) {
            return ja((NativeArray)paramObject, paramStringifyState);
          }
          return jo((Scriptable)paramObject, paramStringifyState);
        }
        return Undefined.instance;
      }
    }
  }
  
  public static Object stringify(Context paramContext, Scriptable paramScriptable, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    Object localObject3 = "";
    Object localObject2 = null;
    Object localObject4 = null;
    Object localObject1;
    if ((paramObject2 instanceof Callable))
    {
      localObject1 = (Callable)paramObject2;
      if (!(paramObject3 instanceof NativeNumber)) {
        break label271;
      }
      paramObject2 = Double.valueOf(ScriptRuntime.toNumber(paramObject3));
    }
    for (;;)
    {
      label40:
      int i;
      if ((paramObject2 instanceof Number))
      {
        i = Math.min(10, (int)ScriptRuntime.toInteger(paramObject2));
        if (i > 0)
        {
          paramObject2 = repeat(' ', i);
          label72:
          paramObject3 = Integer.valueOf(i);
        }
      }
      for (;;)
      {
        paramContext = new StringifyState(paramContext, paramScriptable, "", (String)paramObject2, (Callable)localObject1, (List)localObject2, paramObject3);
        paramObject2 = new NativeObject();
        ((ScriptableObject)paramObject2).setParentScope(paramScriptable);
        ((ScriptableObject)paramObject2).setPrototype(ScriptableObject.getObjectPrototype(paramScriptable));
        ((ScriptableObject)paramObject2).defineProperty("", paramObject1, 0);
        return str("", (Scriptable)paramObject2, paramContext);
        localObject1 = localObject4;
        if (!(paramObject2 instanceof NativeArray)) {
          break;
        }
        LinkedList localLinkedList = new LinkedList();
        paramObject2 = (NativeArray)paramObject2;
        Integer[] arrayOfInteger = ((NativeArray)paramObject2).getIndexIds();
        int j = arrayOfInteger.length;
        i = 0;
        localObject1 = localObject4;
        localObject2 = localLinkedList;
        if (i >= j) {
          break;
        }
        localObject1 = ((NativeArray)paramObject2).get(arrayOfInteger[i].intValue(), (Scriptable)paramObject2);
        if (((localObject1 instanceof String)) || ((localObject1 instanceof Number))) {
          localLinkedList.add(localObject1);
        }
        for (;;)
        {
          i += 1;
          break;
          if (((localObject1 instanceof NativeString)) || ((localObject1 instanceof NativeNumber))) {
            localLinkedList.add(ScriptRuntime.toString(localObject1));
          }
        }
        label271:
        if (!(paramObject3 instanceof NativeString)) {
          break label357;
        }
        paramObject2 = ScriptRuntime.toString(paramObject3);
        break label40;
        paramObject2 = "";
        break label72;
        paramObject3 = localObject3;
        if ((paramObject2 instanceof String))
        {
          localObject3 = (String)paramObject2;
          paramObject3 = localObject3;
          if (((String)localObject3).length() > 10)
          {
            localObject3 = ((String)localObject3).substring(0, 10);
            paramObject3 = paramObject2;
            paramObject2 = localObject3;
            continue;
          }
        }
        localObject3 = paramObject2;
        paramObject2 = paramObject3;
        paramObject3 = localObject3;
      }
      label357:
      paramObject2 = paramObject3;
    }
  }
  
  private static Object walk(Context paramContext, Scriptable paramScriptable1, Callable paramCallable, Scriptable paramScriptable2, Object paramObject)
  {
    Object localObject1;
    Scriptable localScriptable;
    long l1;
    label61:
    Object localObject3;
    if ((paramObject instanceof Number))
    {
      localObject1 = paramScriptable2.get(((Number)paramObject).intValue(), paramScriptable2);
      if (!(localObject1 instanceof Scriptable)) {
        break label359;
      }
      localScriptable = (Scriptable)localObject1;
      if (!(localScriptable instanceof NativeArray)) {
        break label212;
      }
      long l2 = ((NativeArray)localScriptable).getLength();
      l1 = 0L;
      if (l1 >= l2) {
        break label359;
      }
      if (l1 <= 2147483647L) {
        break label156;
      }
      localObject2 = Long.toString(l1);
      localObject3 = walk(paramContext, paramScriptable1, paramCallable, localScriptable, localObject2);
      if (localObject3 != Undefined.instance) {
        break label140;
      }
      localScriptable.delete((String)localObject2);
    }
    for (;;)
    {
      l1 += 1L;
      break label61;
      localObject1 = paramScriptable2.get((String)paramObject, paramScriptable2);
      break;
      label140:
      localScriptable.put((String)localObject2, localScriptable, localObject3);
      continue;
      label156:
      i = (int)l1;
      localObject2 = walk(paramContext, paramScriptable1, paramCallable, localScriptable, Integer.valueOf(i));
      if (localObject2 == Undefined.instance) {
        localScriptable.delete(i);
      } else {
        localScriptable.put(i, localScriptable, localObject2);
      }
    }
    label212:
    Object localObject2 = localScriptable.getIds();
    int j = localObject2.length;
    int i = 0;
    if (i < j)
    {
      localObject3 = localObject2[i];
      Object localObject4 = walk(paramContext, paramScriptable1, paramCallable, localScriptable, localObject3);
      if (localObject4 == Undefined.instance) {
        if ((localObject3 instanceof Number)) {
          localScriptable.delete(((Number)localObject3).intValue());
        }
      }
      for (;;)
      {
        i += 1;
        break;
        localScriptable.delete((String)localObject3);
        continue;
        if ((localObject3 instanceof Number)) {
          localScriptable.put(((Number)localObject3).intValue(), localScriptable, localObject4);
        } else {
          localScriptable.put((String)localObject3, localScriptable, localObject4);
        }
      }
    }
    label359:
    return paramCallable.call(paramContext, paramScriptable1, paramScriptable2, new Object[] { paramObject, localObject1 });
  }
  
  public Object execIdCall(IdFunctionObject paramIdFunctionObject, Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    Object localObject4 = null;
    Object localObject2 = null;
    Object localObject1 = null;
    Object localObject3 = null;
    if (!paramIdFunctionObject.hasTag(JSON_TAG)) {
      return super.execIdCall(paramIdFunctionObject, paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject);
    }
    int i = paramIdFunctionObject.methodId();
    switch (i)
    {
    default: 
      throw new IllegalStateException(String.valueOf(i));
    case 1: 
      return "JSON";
    case 2: 
      paramScriptable2 = ScriptRuntime.toString(paramArrayOfObject, 0);
      paramIdFunctionObject = (IdFunctionObject)localObject3;
      if (paramArrayOfObject.length > 1) {
        paramIdFunctionObject = paramArrayOfObject[1];
      }
      if ((paramIdFunctionObject instanceof Callable)) {
        return parse(paramContext, paramScriptable1, paramScriptable2, (Callable)paramIdFunctionObject);
      }
      return parse(paramContext, paramScriptable1, paramScriptable2);
    }
    paramIdFunctionObject = (IdFunctionObject)localObject4;
    switch (paramArrayOfObject.length)
    {
    default: 
      paramIdFunctionObject = paramArrayOfObject[2];
    case 2: 
      paramScriptable2 = paramArrayOfObject[1];
      paramArrayOfObject = paramArrayOfObject[0];
    }
    for (;;)
    {
      return stringify(paramContext, paramScriptable1, paramArrayOfObject, paramScriptable2, paramIdFunctionObject);
      paramScriptable2 = null;
      paramIdFunctionObject = (IdFunctionObject)localObject2;
      break;
      paramScriptable2 = null;
      paramArrayOfObject = null;
      paramIdFunctionObject = (IdFunctionObject)localObject1;
    }
  }
  
  protected int findPrototypeId(String paramString)
  {
    String str;
    int i;
    switch (paramString.length())
    {
    case 6: 
    case 7: 
    default: 
      str = null;
      i = 0;
    }
    while ((str != null) && (str != paramString) && (!str.equals(paramString)))
    {
      return 0;
      i = 2;
      str = "parse";
      continue;
      i = 1;
      str = "toSource";
      continue;
      i = 3;
      str = "stringify";
    }
    return i;
  }
  
  public String getClassName()
  {
    return "JSON";
  }
  
  protected void initPrototypeId(int paramInt)
  {
    int i = 3;
    if (paramInt <= 3)
    {
      String str;
      switch (paramInt)
      {
      default: 
        throw new IllegalStateException(String.valueOf(paramInt));
      case 1: 
        i = 0;
        str = "toSource";
      }
      for (;;)
      {
        initPrototypeMethod(JSON_TAG, paramInt, str, i);
        return;
        i = 2;
        str = "parse";
        continue;
        str = "stringify";
      }
    }
    throw new IllegalStateException(String.valueOf(paramInt));
  }
  
  private static class StringifyState
  {
    Context cx;
    String gap;
    String indent;
    List<Object> propertyList;
    Callable replacer;
    Scriptable scope;
    Object space;
    Stack<Scriptable> stack = new Stack();
    
    StringifyState(Context paramContext, Scriptable paramScriptable, String paramString1, String paramString2, Callable paramCallable, List<Object> paramList, Object paramObject)
    {
      this.cx = paramContext;
      this.scope = paramScriptable;
      this.indent = paramString1;
      this.gap = paramString2;
      this.replacer = paramCallable;
      this.propertyList = paramList;
      this.space = paramObject;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\NativeJSON.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */