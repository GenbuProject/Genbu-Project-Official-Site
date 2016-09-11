package org.mozilla.javascript;

import java.util.Set;
import org.mozilla.javascript.ast.ErrorCollector;

public class CompilerEnvirons
{
  Set<String> activationNames;
  private boolean allowMemberExprAsFunctionName = false;
  private boolean allowSharpComments = false;
  private ErrorReporter errorReporter = DefaultErrorReporter.instance;
  private boolean generateDebugInfo = true;
  private boolean generateObserverCount = false;
  private boolean generatingSource = true;
  private boolean ideMode;
  private int languageVersion = 0;
  private int optimizationLevel = 0;
  private boolean recordingComments;
  private boolean recordingLocalJsDocComments;
  private boolean recoverFromErrors;
  private boolean reservedKeywordAsIdentifier = true;
  private boolean strictMode = false;
  private boolean warnTrailingComma;
  private boolean warningAsError = false;
  private boolean xmlAvailable = true;
  
  public static CompilerEnvirons ideEnvirons()
  {
    CompilerEnvirons localCompilerEnvirons = new CompilerEnvirons();
    localCompilerEnvirons.setRecoverFromErrors(true);
    localCompilerEnvirons.setRecordingComments(true);
    localCompilerEnvirons.setStrictMode(true);
    localCompilerEnvirons.setWarnTrailingComma(true);
    localCompilerEnvirons.setLanguageVersion(170);
    localCompilerEnvirons.setReservedKeywordAsIdentifier(true);
    localCompilerEnvirons.setIdeMode(true);
    localCompilerEnvirons.setErrorReporter(new ErrorCollector());
    return localCompilerEnvirons;
  }
  
  public Set<String> getActivationNames()
  {
    return this.activationNames;
  }
  
  public boolean getAllowSharpComments()
  {
    return this.allowSharpComments;
  }
  
  public final ErrorReporter getErrorReporter()
  {
    return this.errorReporter;
  }
  
  public final int getLanguageVersion()
  {
    return this.languageVersion;
  }
  
  public final int getOptimizationLevel()
  {
    return this.optimizationLevel;
  }
  
  public boolean getWarnTrailingComma()
  {
    return this.warnTrailingComma;
  }
  
  public void initFromContext(Context paramContext)
  {
    setErrorReporter(paramContext.getErrorReporter());
    this.languageVersion = paramContext.getLanguageVersion();
    if ((!paramContext.isGeneratingDebugChanged()) || (paramContext.isGeneratingDebug())) {}
    for (boolean bool = true;; bool = false)
    {
      this.generateDebugInfo = bool;
      this.reservedKeywordAsIdentifier = paramContext.hasFeature(3);
      this.allowMemberExprAsFunctionName = paramContext.hasFeature(2);
      this.strictMode = paramContext.hasFeature(11);
      this.warningAsError = paramContext.hasFeature(12);
      this.xmlAvailable = paramContext.hasFeature(6);
      this.optimizationLevel = paramContext.getOptimizationLevel();
      this.generatingSource = paramContext.isGeneratingSource();
      this.activationNames = paramContext.activationNames;
      this.generateObserverCount = paramContext.generateObserverCount;
      return;
    }
  }
  
  public final boolean isAllowMemberExprAsFunctionName()
  {
    return this.allowMemberExprAsFunctionName;
  }
  
  public final boolean isGenerateDebugInfo()
  {
    return this.generateDebugInfo;
  }
  
  public boolean isGenerateObserverCount()
  {
    return this.generateObserverCount;
  }
  
  public final boolean isGeneratingSource()
  {
    return this.generatingSource;
  }
  
  public boolean isIdeMode()
  {
    return this.ideMode;
  }
  
  public boolean isRecordingComments()
  {
    return this.recordingComments;
  }
  
  public boolean isRecordingLocalJsDocComments()
  {
    return this.recordingLocalJsDocComments;
  }
  
  public final boolean isReservedKeywordAsIdentifier()
  {
    return this.reservedKeywordAsIdentifier;
  }
  
  public final boolean isStrictMode()
  {
    return this.strictMode;
  }
  
  public final boolean isXmlAvailable()
  {
    return this.xmlAvailable;
  }
  
  public boolean recoverFromErrors()
  {
    return this.recoverFromErrors;
  }
  
  public final boolean reportWarningAsError()
  {
    return this.warningAsError;
  }
  
  public void setActivationNames(Set<String> paramSet)
  {
    this.activationNames = paramSet;
  }
  
  public void setAllowMemberExprAsFunctionName(boolean paramBoolean)
  {
    this.allowMemberExprAsFunctionName = paramBoolean;
  }
  
  public void setAllowSharpComments(boolean paramBoolean)
  {
    this.allowSharpComments = paramBoolean;
  }
  
  public void setErrorReporter(ErrorReporter paramErrorReporter)
  {
    if (paramErrorReporter == null) {
      throw new IllegalArgumentException();
    }
    this.errorReporter = paramErrorReporter;
  }
  
  public void setGenerateDebugInfo(boolean paramBoolean)
  {
    this.generateDebugInfo = paramBoolean;
  }
  
  public void setGenerateObserverCount(boolean paramBoolean)
  {
    this.generateObserverCount = paramBoolean;
  }
  
  public void setGeneratingSource(boolean paramBoolean)
  {
    this.generatingSource = paramBoolean;
  }
  
  public void setIdeMode(boolean paramBoolean)
  {
    this.ideMode = paramBoolean;
  }
  
  public void setLanguageVersion(int paramInt)
  {
    Context.checkLanguageVersion(paramInt);
    this.languageVersion = paramInt;
  }
  
  public void setOptimizationLevel(int paramInt)
  {
    Context.checkOptimizationLevel(paramInt);
    this.optimizationLevel = paramInt;
  }
  
  public void setRecordingComments(boolean paramBoolean)
  {
    this.recordingComments = paramBoolean;
  }
  
  public void setRecordingLocalJsDocComments(boolean paramBoolean)
  {
    this.recordingLocalJsDocComments = paramBoolean;
  }
  
  public void setRecoverFromErrors(boolean paramBoolean)
  {
    this.recoverFromErrors = paramBoolean;
  }
  
  public void setReservedKeywordAsIdentifier(boolean paramBoolean)
  {
    this.reservedKeywordAsIdentifier = paramBoolean;
  }
  
  public void setStrictMode(boolean paramBoolean)
  {
    this.strictMode = paramBoolean;
  }
  
  public void setWarnTrailingComma(boolean paramBoolean)
  {
    this.warnTrailingComma = paramBoolean;
  }
  
  public void setXmlAvailable(boolean paramBoolean)
  {
    this.xmlAvailable = paramBoolean;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\CompilerEnvirons.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */