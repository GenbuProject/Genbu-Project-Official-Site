package com.ipaulpro.afilechooser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.File;
import java.util.ArrayList;

public class FileListAdapter
  extends BaseAdapter
{
  private static final int ICON_FILE = R.drawable.ic_file;
  private static final int ICON_FOLDER = R.drawable.ic_folder;
  private ArrayList<File> mFiles = new ArrayList();
  private LayoutInflater mInflater;
  
  public FileListAdapter(Context paramContext)
  {
    this.mInflater = LayoutInflater.from(paramContext);
  }
  
  public void add(File paramFile)
  {
    this.mFiles.add(paramFile);
  }
  
  public void clear()
  {
    this.mFiles.clear();
  }
  
  public int getCount()
  {
    return this.mFiles.size();
  }
  
  public Object getItem(int paramInt)
  {
    return this.mFiles.get(paramInt);
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramView = this.mInflater.inflate(R.layout.file, paramViewGroup, false);
      paramViewGroup = new ViewHolder(paramView);
      paramView.setTag(paramViewGroup);
      File localFile = (File)getItem(paramInt);
      paramViewGroup.nameView.setText(localFile.getName());
      paramViewGroup = paramViewGroup.iconView;
      if (!localFile.isDirectory()) {
        break label88;
      }
    }
    label88:
    for (paramInt = ICON_FOLDER;; paramInt = ICON_FILE)
    {
      paramViewGroup.setImageResource(paramInt);
      return paramView;
      paramViewGroup = (ViewHolder)paramView.getTag();
      break;
    }
  }
  
  public void setListItems(ArrayList<File> paramArrayList)
  {
    this.mFiles = paramArrayList;
  }
  
  static class ViewHolder
  {
    ImageView iconView;
    TextView nameView;
    
    ViewHolder(View paramView)
    {
      this.nameView = ((TextView)paramView.findViewById(R.id.file_name));
      this.iconView = ((ImageView)paramView.findViewById(R.id.file_icon));
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\ipaulpro\afilechooser\FileListAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */