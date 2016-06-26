package net.zhuoweizhang.mcpelauncher;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;

public class BlockContextFactory
  extends ContextFactory
{
  protected Context makeContext()
  {
    Context localContext = super.makeContext();
    localContext.setWrapFactory(ScriptManager.getWrapFactory());
    return localContext;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\BlockContextFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */