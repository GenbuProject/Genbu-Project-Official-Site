package com.amazon.android.framework.prompt;

import com.amazon.android.framework.task.Task;

final class e
  implements Task
{
  e(PromptManagerImpl paramPromptManagerImpl, Prompt paramPrompt) {}
  
  public final void execute()
  {
    PromptManagerImpl.b(this.b, this.a);
  }
  
  public final String toString()
  {
    return "PromptManager:removeExpiredPrompt: " + this.a;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\framework\prompt\e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */