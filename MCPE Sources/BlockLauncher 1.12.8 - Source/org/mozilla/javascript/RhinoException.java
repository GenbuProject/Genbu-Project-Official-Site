package org.mozilla.javascript;

import java.io.CharArrayWriter;
import java.io.FilenameFilter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class RhinoException
  extends RuntimeException
{
  private static final Pattern JAVA_STACK_PATTERN = Pattern.compile("_c_(.*)_\\d+");
  static final long serialVersionUID = 1883500631321581169L;
  private static StackStyle stackStyle = StackStyle.V8;
  private int columnNumber;
  int[] interpreterLineData;
  Object interpreterStackInfo;
  private int lineNumber;
  private String lineSource;
  private String sourceName;
  
  static
  {
    String str = System.getProperty("rhino.stack.style");
    if (str != null)
    {
      if (!"Rhino".equalsIgnoreCase(str)) {
        break label40;
      }
      stackStyle = StackStyle.RHINO;
    }
    label40:
    do
    {
      return;
      if ("Mozilla".equalsIgnoreCase(str))
      {
        stackStyle = StackStyle.MOZILLA;
        return;
      }
    } while (!"V8".equalsIgnoreCase(str));
  }
  
  RhinoException()
  {
    Evaluator localEvaluator = Context.createInterpreter();
    if (localEvaluator != null) {
      localEvaluator.captureStackInfo(this);
    }
  }
  
  RhinoException(String paramString)
  {
    super(paramString);
    paramString = Context.createInterpreter();
    if (paramString != null) {
      paramString.captureStackInfo(this);
    }
  }
  
  static String formatStackTrace(ScriptStackElement[] paramArrayOfScriptStackElement, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    String str = SecurityUtilities.getSystemProperty("line.separator");
    if ((stackStyle == StackStyle.V8) && (!"null".equals(paramString)))
    {
      localStringBuilder.append(paramString);
      localStringBuilder.append(str);
    }
    int j = paramArrayOfScriptStackElement.length;
    int i = 0;
    if (i < j)
    {
      paramString = paramArrayOfScriptStackElement[i];
      switch (stackStyle)
      {
      }
      for (;;)
      {
        localStringBuilder.append(str);
        i += 1;
        break;
        paramString.renderMozillaStyle(localStringBuilder);
        continue;
        paramString.renderV8Style(localStringBuilder);
        continue;
        paramString.renderJavaStyle(localStringBuilder);
      }
    }
    return localStringBuilder.toString();
  }
  
  private String generateStackTrace()
  {
    Object localObject = new CharArrayWriter();
    super.printStackTrace(new PrintWriter((Writer)localObject));
    localObject = ((CharArrayWriter)localObject).toString();
    Evaluator localEvaluator = Context.createInterpreter();
    if (localEvaluator != null) {
      return localEvaluator.getPatchedStack(this, (String)localObject);
    }
    return null;
  }
  
  public static StackStyle getStackStyle()
  {
    return stackStyle;
  }
  
  public static void setStackStyle(StackStyle paramStackStyle)
  {
    stackStyle = paramStackStyle;
  }
  
  public static void useMozillaStackStyle(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (StackStyle localStackStyle = StackStyle.MOZILLA;; localStackStyle = StackStyle.RHINO)
    {
      stackStyle = localStackStyle;
      return;
    }
  }
  
  public static boolean usesMozillaStackStyle()
  {
    return stackStyle == StackStyle.MOZILLA;
  }
  
  public final int columnNumber()
  {
    return this.columnNumber;
  }
  
  public String details()
  {
    return super.getMessage();
  }
  
  public final String getMessage()
  {
    Object localObject = details();
    if ((this.sourceName == null) || (this.lineNumber <= 0)) {
      return (String)localObject;
    }
    localObject = new StringBuilder((String)localObject);
    ((StringBuilder)localObject).append(" (");
    if (this.sourceName != null) {
      ((StringBuilder)localObject).append(this.sourceName);
    }
    if (this.lineNumber > 0)
    {
      ((StringBuilder)localObject).append('#');
      ((StringBuilder)localObject).append(this.lineNumber);
    }
    ((StringBuilder)localObject).append(')');
    return ((StringBuilder)localObject).toString();
  }
  
  public ScriptStackElement[] getScriptStack()
  {
    return getScriptStack(-1, null);
  }
  
  public ScriptStackElement[] getScriptStack(int paramInt, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject2 = (ScriptStackElement[][])null;
    Object localObject1 = localObject2;
    if (this.interpreterStackInfo != null)
    {
      localObject3 = Context.createInterpreter();
      localObject1 = localObject2;
      if ((localObject3 instanceof Interpreter)) {
        localObject1 = ((Interpreter)localObject3).getScriptStackElements(this);
      }
    }
    Object localObject3 = getStackTrace();
    int i;
    int n;
    int j;
    int i1;
    label80:
    Object localObject4;
    String str;
    label181:
    int k;
    int i2;
    int m;
    if (paramString == null)
    {
      i = 1;
      int i3 = localObject3.length;
      n = 0;
      j = 0;
      i1 = 0;
      if (n >= i3) {
        break label513;
      }
      localObject4 = localObject3[n];
      str = ((StackTraceElement)localObject4).getFileName();
      if ((!((StackTraceElement)localObject4).getMethodName().startsWith("_c_")) || (((StackTraceElement)localObject4).getLineNumber() <= -1) || (str == null) || (str.endsWith(".java"))) {
        break label312;
      }
      localObject2 = ((StackTraceElement)localObject4).getMethodName();
      Matcher localMatcher = JAVA_STACK_PATTERN.matcher((CharSequence)localObject2);
      if (("_c_script_0".equals(localObject2)) || (!localMatcher.find())) {
        break label230;
      }
      localObject2 = localMatcher.group(1);
      if ((i != 0) || (!paramString.equals(localObject2))) {
        break label236;
      }
      k = 1;
      i2 = i1;
      m = j;
    }
    for (;;)
    {
      n += 1;
      i = k;
      j = m;
      i1 = i2;
      break label80;
      i = 0;
      break;
      label230:
      localObject2 = null;
      break label181;
      label236:
      k = i;
      m = j;
      i2 = i1;
      if (i != 0) {
        if (paramInt >= 0)
        {
          k = i;
          m = j;
          i2 = i1;
          if (j >= paramInt) {}
        }
        else
        {
          localArrayList.add(new ScriptStackElement(str, (String)localObject2, ((StackTraceElement)localObject4).getLineNumber()));
          m = j + 1;
          k = i;
          i2 = i1;
          continue;
          label312:
          k = i;
          m = j;
          i2 = i1;
          if ("org.mozilla.javascript.Interpreter".equals(((StackTraceElement)localObject4).getClassName()))
          {
            k = i;
            m = j;
            i2 = i1;
            if ("interpretLoop".equals(((StackTraceElement)localObject4).getMethodName()))
            {
              k = i;
              m = j;
              i2 = i1;
              if (localObject1 != null)
              {
                k = i;
                m = j;
                i2 = i1;
                if (localObject1.length > i1)
                {
                  localObject2 = localObject1[i1];
                  int i4 = localObject2.length;
                  k = 0;
                  if (k < i4)
                  {
                    localObject4 = localObject2[k];
                    if ((i == 0) && (paramString.equals(((ScriptStackElement)localObject4).functionName)))
                    {
                      m = 1;
                      i2 = j;
                    }
                    for (;;)
                    {
                      k += 1;
                      i = m;
                      j = i2;
                      break;
                      m = i;
                      i2 = j;
                      if (i != 0) {
                        if (paramInt >= 0)
                        {
                          m = i;
                          i2 = j;
                          if (j >= paramInt) {}
                        }
                        else
                        {
                          localArrayList.add(localObject4);
                          i2 = j + 1;
                          m = i;
                        }
                      }
                    }
                    label513:
                    return (ScriptStackElement[])localArrayList.toArray(new ScriptStackElement[localArrayList.size()]);
                  }
                  i2 = i1 + 1;
                  k = i;
                  m = j;
                }
              }
            }
          }
        }
      }
    }
  }
  
  public String getScriptStackTrace()
  {
    return getScriptStackTrace(-1, null);
  }
  
  public String getScriptStackTrace(int paramInt, String paramString)
  {
    return formatStackTrace(getScriptStack(paramInt, paramString), details());
  }
  
  @Deprecated
  public String getScriptStackTrace(FilenameFilter paramFilenameFilter)
  {
    return getScriptStackTrace();
  }
  
  public final void initColumnNumber(int paramInt)
  {
    if (paramInt <= 0) {
      throw new IllegalArgumentException(String.valueOf(paramInt));
    }
    if (this.columnNumber > 0) {
      throw new IllegalStateException();
    }
    this.columnNumber = paramInt;
  }
  
  public final void initLineNumber(int paramInt)
  {
    if (paramInt <= 0) {
      throw new IllegalArgumentException(String.valueOf(paramInt));
    }
    if (this.lineNumber > 0) {
      throw new IllegalStateException();
    }
    this.lineNumber = paramInt;
  }
  
  public final void initLineSource(String paramString)
  {
    if (paramString == null) {
      throw new IllegalArgumentException();
    }
    if (this.lineSource != null) {
      throw new IllegalStateException();
    }
    this.lineSource = paramString;
  }
  
  public final void initSourceName(String paramString)
  {
    if (paramString == null) {
      throw new IllegalArgumentException();
    }
    if (this.sourceName != null) {
      throw new IllegalStateException();
    }
    this.sourceName = paramString;
  }
  
  public final int lineNumber()
  {
    return this.lineNumber;
  }
  
  public final String lineSource()
  {
    return this.lineSource;
  }
  
  public void printStackTrace(PrintStream paramPrintStream)
  {
    if (this.interpreterStackInfo == null)
    {
      super.printStackTrace(paramPrintStream);
      return;
    }
    paramPrintStream.print(generateStackTrace());
  }
  
  public void printStackTrace(PrintWriter paramPrintWriter)
  {
    if (this.interpreterStackInfo == null)
    {
      super.printStackTrace(paramPrintWriter);
      return;
    }
    paramPrintWriter.print(generateStackTrace());
  }
  
  final void recordErrorOrigin(String paramString1, int paramInt1, String paramString2, int paramInt2)
  {
    int i = paramInt1;
    if (paramInt1 == -1) {
      i = 0;
    }
    if (paramString1 != null) {
      initSourceName(paramString1);
    }
    if (i != 0) {
      initLineNumber(i);
    }
    if (paramString2 != null) {
      initLineSource(paramString2);
    }
    if (paramInt2 != 0) {
      initColumnNumber(paramInt2);
    }
  }
  
  public final String sourceName()
  {
    return this.sourceName;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\RhinoException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */