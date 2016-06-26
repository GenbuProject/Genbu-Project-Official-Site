package com.amazon.android.framework.prompt;

import com.amazon.android.framework.task.Task;

final class h
  implements Task
{
  h(PromptManagerImpl paramPromptManagerImpl, Prompt paramPrompt) {}
  
  public final void execute()
  {
    PromptManagerImpl.a(this.b, this.a);
  }
  
  public final String toString()
  {
    return "Prompt Presentation on Main Thread: " + this.a + ", " + this.a.getExpiration();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\framework\prompt\h.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */