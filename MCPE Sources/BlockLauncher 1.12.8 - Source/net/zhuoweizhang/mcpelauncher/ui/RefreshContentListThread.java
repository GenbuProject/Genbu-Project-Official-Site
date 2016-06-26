package net.zhuoweizhang.mcpelauncher.ui;

import android.app.Activity;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RefreshContentListThread
  implements Runnable
{
  protected final Activity mContext;
  protected final OnRefreshContentList mListener;
  
  public RefreshContentListThread(Activity paramActivity, OnRefreshContentList paramOnRefreshContentList)
  {
    this.mContext = paramActivity;
    this.mListener = paramOnRefreshContentList;
  }
  
  private void combOneFolder(File paramFile, List<ContentListItem> paramList)
  {
    if (!paramFile.exists()) {
      System.err.println("no storage folder");
    }
    for (;;)
    {
      return;
      paramFile = paramFile.listFiles();
      int j = paramFile.length;
      int i = 0;
      while (i < j)
      {
        File localFile = paramFile[i];
        paramList.add(new ContentListItem(localFile, this.mListener.isEnabled(localFile)));
        i += 1;
      }
    }
  }
  
  public void run()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.mListener.getFolders().iterator();
    while (localIterator.hasNext()) {
      combOneFolder((File)localIterator.next(), localArrayList);
    }
    ContentListItem.sort(localArrayList);
    this.mListener.onRefreshComplete(localArrayList);
  }
  
  public static abstract interface OnRefreshContentList
  {
    public abstract List<File> getFolders();
    
    public abstract boolean isEnabled(File paramFile);
    
    public abstract void onRefreshComplete(List<ContentListItem> paramList);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\ui\RefreshContentListThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */