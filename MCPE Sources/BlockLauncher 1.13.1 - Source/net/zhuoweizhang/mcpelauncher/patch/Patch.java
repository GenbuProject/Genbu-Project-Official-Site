package net.zhuoweizhang.mcpelauncher.patch;

import java.util.ArrayList;
import java.util.List;

public abstract class Patch
{
  protected List<PatchSegment> segments = new ArrayList();
  
  public List<PatchSegment> getSegments()
  {
    return this.segments;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\patch\Patch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */