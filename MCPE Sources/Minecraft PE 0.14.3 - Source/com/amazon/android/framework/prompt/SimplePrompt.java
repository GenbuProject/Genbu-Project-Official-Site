package com.amazon.android.framework.prompt;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import com.amazon.android.d.a;

public abstract class SimplePrompt
  extends Prompt
{
  protected final PromptContent content;
  
  public SimplePrompt(PromptContent paramPromptContent)
  {
    a.a(paramPromptContent, "content");
    this.content = paramPromptContent;
  }
  
  protected abstract void doAction();
  
  protected boolean doCompatibilityCheck(Activity paramActivity)
  {
    return this.content.isVisible();
  }
  
  public final Dialog doCreate(Activity paramActivity)
  {
    paramActivity = new AlertDialog.Builder(paramActivity);
    paramActivity.setTitle(this.content.getTitle()).setMessage(this.content.getMessage()).setCancelable(false).setNeutralButton(this.content.getButtonLabel(), new g(this));
    return paramActivity.create();
  }
  
  protected void doExpiration(d paramd)
  {
    doAction();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\framework\prompt\SimplePrompt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */