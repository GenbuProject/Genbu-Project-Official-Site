package com.amazon.android.framework.prompt;

public class PromptContent
{
  private final String buttonLabel;
  private final String message;
  private final boolean shouldDownload;
  private final String title;
  private final boolean visible;
  
  public PromptContent(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    this(paramString1, paramString2, paramString3, paramBoolean, false);
  }
  
  public PromptContent(String paramString1, String paramString2, String paramString3, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.title = paramString1;
    this.message = paramString2;
    this.buttonLabel = paramString3;
    this.visible = paramBoolean1;
    this.shouldDownload = paramBoolean2;
  }
  
  public String getButtonLabel()
  {
    return this.buttonLabel;
  }
  
  public String getMessage()
  {
    return this.message;
  }
  
  public String getTitle()
  {
    return this.title;
  }
  
  public boolean isVisible()
  {
    return this.visible;
  }
  
  public boolean shouldDownload()
  {
    return this.shouldDownload;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("PromptContent: [ title:");
    localStringBuilder.append(this.title).append(", message: ").append(this.message).append(", label: ").append(this.buttonLabel).append(", visible: ").append(this.visible).append(", shouldDownload: ").append(this.shouldDownload).append("]");
    return localStringBuilder.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\framework\prompt\PromptContent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */