package com.amazon.android.framework.prompt;

import android.app.Activity;
import android.app.Dialog;
import com.amazon.android.i.d;

public abstract interface PromptManager
  extends d
{
  public abstract Dialog onCreateDialog(Activity paramActivity, int paramInt);
  
  public abstract void onWindowFocusChanged(Activity paramActivity, boolean paramBoolean);
  
  public abstract void present(Prompt paramPrompt);
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\framework\prompt\PromptManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */