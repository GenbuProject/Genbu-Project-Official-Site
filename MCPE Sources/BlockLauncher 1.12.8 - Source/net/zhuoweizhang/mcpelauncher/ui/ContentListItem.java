package net.zhuoweizhang.mcpelauncher.ui;

import android.content.res.Resources;
import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import net.zhuoweizhang.mcpelauncher.R.string;

public class ContentListItem
{
  public final String displayName;
  public boolean enabled = true;
  public String extraData = null;
  public final File file;
  
  public ContentListItem(File paramFile, boolean paramBoolean)
  {
    this.file = paramFile;
    this.displayName = paramFile.getName();
    this.enabled = paramBoolean;
  }
  
  public static void sort(List<ContentListItem> paramList)
  {
    Collections.sort(paramList, new ContentListComparator());
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder().append(this.displayName);
    if (this.extraData != null)
    {
      str = " " + this.extraData + " ";
      localStringBuilder = localStringBuilder.append(str);
      if (!this.enabled) {
        break label81;
      }
    }
    label81:
    for (String str = "";; str = " ".concat("(disabled)"))
    {
      return str;
      str = "";
      break;
    }
  }
  
  public String toString(Resources paramResources)
  {
    StringBuilder localStringBuilder = new StringBuilder().append(this.displayName);
    Object localObject;
    if (this.extraData != null)
    {
      localObject = " " + this.extraData + " ";
      localObject = localStringBuilder.append((String)localObject);
      if (!this.enabled) {
        break label81;
      }
    }
    label81:
    for (paramResources = "";; paramResources = " ".concat(paramResources.getString(R.string.manage_patches_disabled)))
    {
      return paramResources;
      localObject = "";
      break;
    }
  }
  
  public static final class ContentListComparator
    implements Comparator<ContentListItem>
  {
    public int compare(ContentListItem paramContentListItem1, ContentListItem paramContentListItem2)
    {
      return paramContentListItem1.displayName.toLowerCase().compareTo(paramContentListItem2.displayName.toLowerCase());
    }
    
    public boolean equals(ContentListItem paramContentListItem1, ContentListItem paramContentListItem2)
    {
      return paramContentListItem1.displayName.toLowerCase().equals(paramContentListItem2.displayName.toLowerCase());
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\ui\ContentListItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */