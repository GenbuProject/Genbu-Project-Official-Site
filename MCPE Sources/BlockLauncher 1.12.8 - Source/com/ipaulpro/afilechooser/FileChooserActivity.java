package com.ipaulpro.afilechooser;

import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import com.ipaulpro.afilechooser.utils.FileUtils;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class FileChooserActivity
  extends ListActivity
{
  private static final String BREADCRUMB = "breadcrumb";
  private static final boolean DEBUG = true;
  private static final String HIDDEN_PREFIX = ".";
  public static final String MIME_TYPE_ALL = "*/*";
  private static final String PATH = "path";
  private static final String POSTIION = "position";
  public static final int REQUEST_CODE = 6384;
  private static final String TAG = "ChooserActivity";
  private Set<String> extendedMimeTypes = new HashSet();
  private ArrayList<String> mBreadcrumb = new ArrayList();
  private Comparator<File> mComparator = new Comparator()
  {
    public int compare(File paramAnonymousFile1, File paramAnonymousFile2)
    {
      return paramAnonymousFile1.getName().toLowerCase().compareTo(paramAnonymousFile2.getName().toLowerCase());
    }
  };
  private FileFilter mDirFilter = new FileFilter()
  {
    public boolean accept(File paramAnonymousFile)
    {
      String str = paramAnonymousFile.getName();
      return (paramAnonymousFile.isDirectory()) && (!str.startsWith("."));
    }
  };
  private File mExternalDir;
  private boolean mExternalStorageAvailable = false;
  private BroadcastReceiver mExternalStorageReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      Log.d("ChooserActivity", "External storage broadcast recieved: " + paramAnonymousIntent.getData());
      FileChooserActivity.this.updateExternalStorageState();
    }
  };
  private boolean mExternalStorageWriteable = false;
  private FileFilter mFileFilter = new FileFilter()
  {
    public boolean accept(File paramAnonymousFile)
    {
      String str1 = paramAnonymousFile.getName();
      String str2 = FileUtils.getMimeType(FileChooserActivity.this, paramAnonymousFile);
      return (paramAnonymousFile.isFile()) && (!str1.startsWith(".")) && ((str2.equals(FileChooserActivity.this.getIntent().getType())) || (FileChooserActivity.this.extendedMimeTypes.contains(str2)));
    }
  };
  private Comparator<File> mLastModifiedComparator = new Comparator()
  {
    public int compare(File paramAnonymousFile1, File paramAnonymousFile2)
    {
      long l1 = paramAnonymousFile1.lastModified();
      long l2 = paramAnonymousFile2.lastModified();
      if (l1 == l2) {
        return 0;
      }
      if (l1 < l2) {
        return 1;
      }
      return -1;
    }
  };
  private ArrayList<File> mList = new ArrayList();
  private String mPath;
  
  private void fillList(int paramInt)
  {
    Log.d("ChooserActivity", "Current path: " + this.mPath);
    setTitle(this.mPath);
    ((FileListAdapter)getListAdapter()).clear();
    Object localObject = new File(this.mPath);
    File[] arrayOfFile = ((File)localObject).listFiles(this.mDirFilter);
    int j;
    int i;
    File localFile;
    if (arrayOfFile != null)
    {
      Arrays.sort(arrayOfFile, this.mComparator);
      j = arrayOfFile.length;
      i = 0;
      while (i < j)
      {
        localFile = arrayOfFile[i];
        this.mList.add(localFile);
        i += 1;
      }
    }
    localObject = ((File)localObject).listFiles(this.mFileFilter);
    if (localObject != null)
    {
      Arrays.sort((Object[])localObject, this.mComparator);
      j = localObject.length;
      i = 0;
      while (i < j)
      {
        localFile = localObject[i];
        this.mList.add(localFile);
        i += 1;
      }
    }
    if ((arrayOfFile == null) && (localObject == null)) {
      Log.d("ChooserActivity", "Directory is empty");
    }
    ((FileListAdapter)getListAdapter()).setListItems(this.mList);
    ((FileListAdapter)getListAdapter()).notifyDataSetChanged();
    getListView().setSelection(paramInt);
  }
  
  private void handleExternalStorageState(boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((!paramBoolean1) && (isIntentGetContent()))
    {
      Log.d("ChooserActivity", "External Storage was disconnected");
      onFileDisconnect();
      finish();
    }
  }
  
  private void restoreMe(Bundle paramBundle)
  {
    if (paramBundle.containsKey("path")) {}
    for (String str = paramBundle.getString("path");; str = this.mExternalDir.getAbsolutePath())
    {
      this.mPath = str;
      this.mBreadcrumb = paramBundle.getStringArrayList("breadcrumb");
      fillList(paramBundle.getInt("position"));
      return;
    }
  }
  
  private void startWatchingExternalStorage()
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
    localIntentFilter.addAction("android.intent.action.MEDIA_REMOVED");
    registerReceiver(this.mExternalStorageReceiver, localIntentFilter);
    if (isIntentGetContent()) {
      updateExternalStorageState();
    }
  }
  
  private void stopWatchingExternalStorage()
  {
    unregisterReceiver(this.mExternalStorageReceiver);
  }
  
  private void updateBreadcrumb(boolean paramBoolean)
  {
    if (paramBoolean) {
      this.mBreadcrumb.add(this.mPath);
    }
    int i;
    do
    {
      return;
      if (this.mExternalDir.getAbsolutePath().equals(this.mPath))
      {
        onFileSelectCancel();
        finish();
        return;
      }
      i = this.mBreadcrumb.size();
    } while (i <= 1);
    this.mBreadcrumb.remove(i - 1);
    this.mPath = ((String)this.mBreadcrumb.get(i - 2));
    fillList(0);
  }
  
  private void updateExternalStorageState()
  {
    String str = Environment.getExternalStorageState();
    if ("mounted".equals(str))
    {
      this.mExternalStorageWriteable = true;
      this.mExternalStorageAvailable = true;
    }
    for (;;)
    {
      handleExternalStorageState(this.mExternalStorageAvailable, this.mExternalStorageWriteable);
      return;
      if ("mounted_ro".equals(str))
      {
        this.mExternalStorageAvailable = true;
        this.mExternalStorageWriteable = false;
      }
      else
      {
        this.mExternalStorageWriteable = false;
        this.mExternalStorageAvailable = false;
      }
    }
  }
  
  protected boolean isIntentGetContent()
  {
    String str = getIntent().getAction();
    Log.d("ChooserActivity", "Intent Action: " + str);
    return "android.intent.action.GET_CONTENT".equals(str);
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    switch (paramInt1)
    {
    }
    for (;;)
    {
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
      return;
      if (paramInt2 == -1) {
        try
        {
          onFileSelect(new File(FileUtils.getPath(this, paramIntent.getData())));
        }
        catch (Exception localException)
        {
          onFileError(localException);
        }
      } else if (paramInt2 == 0) {
        onFileSelectCancel();
      }
    }
  }
  
  public void onBackPressed()
  {
    updateBreadcrumb(false);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Object localObject1 = getIntent().getStringArrayExtra("net.zhuoweizhang.afilechooser.extra.MIME_TYPES");
    this.extendedMimeTypes.clear();
    if (localObject1 != null)
    {
      int j = localObject1.length;
      int i = 0;
      while (i < j)
      {
        Object localObject2 = localObject1[i];
        this.extendedMimeTypes.add(localObject2);
        i += 1;
      }
    }
    this.mExternalDir = Environment.getExternalStorageDirectory();
    localObject1 = getIntent().getStringExtra("net.zhuoweizhang.afilechooser.extra.SORT_METHOD");
    if ((localObject1 != null) && (((String)localObject1).equals("net.zhuoweizhang.afilechooser.extra.SORT_LAST_MODIFIED"))) {
      this.mComparator = this.mLastModifiedComparator;
    }
    if (getListAdapter() == null) {
      setListAdapter(new FileListAdapter(this));
    }
    if (paramBundle != null) {
      restoreMe(paramBundle);
    }
    do
    {
      return;
      this.mPath = this.mExternalDir.getAbsolutePath();
      paramBundle = getIntent().getStringExtra("startPath");
      if (paramBundle != null) {
        this.mPath = paramBundle;
      }
      updateBreadcrumb(true);
    } while (!isIntentGetContent());
    setContentView(R.layout.explorer);
    fillList(0);
  }
  
  protected void onFileDisconnect()
  {
    Log.d("ChooserActivity", "External storage disconnected");
  }
  
  protected void onFileError(Exception paramException)
  {
    Log.e("ChooserActivity", "Error selecting file", paramException);
  }
  
  protected void onFileSelect(File paramFile)
  {
    Log.d("ChooserActivity", "File selected: " + paramFile.getAbsolutePath());
  }
  
  protected void onFileSelectCancel()
  {
    Log.d("ChooserActivity", "File selection canceled");
  }
  
  protected void onListItemClick(ListView paramListView, View paramView, int paramInt, long paramLong)
  {
    super.onListItemClick(paramListView, paramView, paramInt, paramLong);
    paramListView = (File)this.mList.get(paramInt);
    this.mPath = paramListView.getAbsolutePath();
    Log.d("ChooserActivity", "Selected file: " + this.mPath);
    if (paramListView != null)
    {
      if (paramListView.isDirectory())
      {
        updateBreadcrumb(true);
        fillList(0);
      }
    }
    else {
      return;
    }
    paramView = new Intent();
    paramView.setData(Uri.fromFile(paramListView));
    setResult(-1, paramView);
    finish();
  }
  
  protected void onPause()
  {
    super.onPause();
    stopWatchingExternalStorage();
  }
  
  protected void onResume()
  {
    super.onResume();
    startWatchingExternalStorage();
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putString("path", this.mPath);
    paramBundle.putStringArrayList("breadcrumb", this.mBreadcrumb);
    paramBundle.putInt("position", getListView().getFirstVisiblePosition());
  }
  
  protected void showFileChooser()
  {
    showFileChooser(null, null);
  }
  
  protected void showFileChooser(String paramString1, String paramString2)
  {
    String str = paramString1;
    if (TextUtils.isEmpty(paramString1)) {
      str = getString(R.string.select_file);
    }
    paramString1 = paramString2;
    if (TextUtils.isEmpty(paramString2)) {
      paramString1 = "*/*";
    }
    paramString2 = new Intent("android.intent.action.GET_CONTENT");
    paramString2.setType(paramString1.toLowerCase());
    paramString2.addCategory("android.intent.category.OPENABLE");
    try
    {
      startActivityForResult(Intent.createChooser(paramString2, str), 6384);
      return;
    }
    catch (ActivityNotFoundException paramString1)
    {
      onFileError(paramString1);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\ipaulpro\afilechooser\FileChooserActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */