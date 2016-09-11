package net.zhuoweizhang.mcpelauncher.ui;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.ipaulpro.afilechooser.FileChooserActivity;
import com.ipaulpro.afilechooser.utils.FileUtils;
import com.joshuahuelsman.patchtool.PTPatch;
import com.mojang.minecraftpe.MainActivity;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.zhuoweizhang.mcpelauncher.MinecraftVersion;
import net.zhuoweizhang.mcpelauncher.PatchManager;
import net.zhuoweizhang.mcpelauncher.R.id;
import net.zhuoweizhang.mcpelauncher.R.layout;
import net.zhuoweizhang.mcpelauncher.R.menu;
import net.zhuoweizhang.mcpelauncher.R.string;
import net.zhuoweizhang.mcpelauncher.Utils;
import net.zhuoweizhang.mcpelauncher.patch.PatchUtils;

public class ManagePatchesActivity
  extends ListActivity
  implements View.OnClickListener, RefreshContentListThread.OnRefreshContentList
{
  private static final int DIALOG_MANAGE_PATCH = 1;
  private static final int DIALOG_MANAGE_PATCH_CURRENTLY_DISABLED = 2;
  private static final int DIALOG_MANAGE_PATCH_CURRENTLY_ENABLED = 3;
  private static final int DIALOG_PATCH_INFO = 4;
  private static final int REQUEST_IMPORT_PATCH = 212;
  private ImageButton importButton;
  private byte[] libBytes = null;
  protected CompoundButton master = null;
  private List<ContentListItem> patches;
  private boolean prePatchConfigure = true;
  private Thread refreshThread;
  private ContentListItem selectedPatchItem;
  
  private void afterPatchToggle(ContentListItem paramContentListItem)
  {
    if (!isValidPatch(paramContentListItem))
    {
      PatchManager.getPatchManager(this).setEnabled(paramContentListItem.file, false);
      new AlertDialog.Builder(this).setMessage(getResources().getString(R.string.manage_patches_invalid_patches) + " " + paramContentListItem.displayName).setPositiveButton(17039370, null).show();
      return;
    }
    if (this.prePatchConfigure)
    {
      setPatchListModified();
      return;
    }
    if (canLivePatch(paramContentListItem)) {
      try
      {
        livePatch(paramContentListItem);
        Utils.getPrefs(1).edit().putBoolean("force_prepatch", true).apply();
        return;
      }
      catch (Exception paramContentListItem)
      {
        paramContentListItem.printStackTrace();
        return;
      }
    }
    setPatchListModified();
  }
  
  private AlertDialog createPatchInfoDialog()
  {
    return new AlertDialog.Builder(this).setTitle("Whoops! info fail").setMessage("Whoops - try again, this is a tiny fail").setPositiveButton(17039370, null).create();
  }
  
  private void findPatches()
  {
    this.refreshThread = new Thread(new RefreshContentListThread(this, this));
    this.refreshThread.start();
  }
  
  private String getPatchInfo(ContentListItem paramContentListItem)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getResources().getString(R.string.manage_patches_path));
    localStringBuilder.append(": ");
    localStringBuilder.append(paramContentListItem.file.getAbsolutePath());
    localStringBuilder.append('\n');
    PTPatch localPTPatch = new PTPatch();
    localPTPatch.loadPatch(paramContentListItem.file);
    paramContentListItem = localPTPatch.getDescription();
    if (paramContentListItem.length() > 0)
    {
      localStringBuilder.append(getResources().getString(R.string.manage_patches_metadata));
      localStringBuilder.append(": ");
      localStringBuilder.append(paramContentListItem);
    }
    for (;;)
    {
      return localStringBuilder.toString();
      localStringBuilder.append(getResources().getString(R.string.manage_patches_no_metadata));
    }
  }
  
  private boolean isValidPatch(ContentListItem paramContentListItem)
  {
    return paramContentListItem.file.length() >= 6L;
  }
  
  private void openManagePatchWindow(ContentListItem paramContentListItem)
  {
    this.selectedPatchItem = paramContentListItem;
    if ((this.prePatchConfigure) || (canLivePatch(paramContentListItem)))
    {
      if (paramContentListItem.enabled) {}
      for (int i = 3;; i = 2)
      {
        showDialog(i);
        return;
      }
    }
    Toast.makeText(this, "This patch cannot be disabled in game.", 0).show();
  }
  
  public boolean canLivePatch(ContentListItem paramContentListItem)
  {
    try
    {
      boolean bool = PatchUtils.canLivePatch(paramContentListItem.file);
      return bool;
    }
    catch (Exception paramContentListItem) {}
    return false;
  }
  
  protected AlertDialog createManagePatchDialog(int paramInt)
  {
    CharSequence localCharSequence = getResources().getText(R.string.manage_patches_info);
    if (paramInt == -1)
    {
      localObject = new CharSequence[2];
      localObject[0] = getResources().getText(R.string.manage_patches_delete);
      localObject[1] = localCharSequence;
      new AlertDialog.Builder(this).setTitle("Patch name goes here").setItems((CharSequence[])localObject, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          if (paramAnonymousInt == 0) {}
          do
          {
            try
            {
              ManagePatchesActivity.this.deletePatch(ManagePatchesActivity.this.selectedPatchItem);
              ManagePatchesActivity.this.findPatches();
              return;
            }
            catch (Exception paramAnonymousDialogInterface)
            {
              paramAnonymousDialogInterface.printStackTrace();
              return;
            }
            if (paramAnonymousInt == 1)
            {
              ManagePatchesActivity.this.showDialog(4);
              return;
            }
          } while (paramAnonymousInt != 2);
          ManagePatchesActivity.this.togglePatch(ManagePatchesActivity.this.selectedPatchItem);
          ManagePatchesActivity.this.findPatches();
        }
      }).create();
    }
    CharSequence[] arrayOfCharSequence = new CharSequence[3];
    arrayOfCharSequence[0] = getResources().getText(R.string.manage_patches_delete);
    arrayOfCharSequence[1] = localCharSequence;
    if (paramInt == 0) {}
    for (Object localObject = getResources().getText(R.string.manage_patches_enable);; localObject = getResources().getText(R.string.manage_patches_disable))
    {
      arrayOfCharSequence[2] = localObject;
      localObject = arrayOfCharSequence;
      break;
    }
  }
  
  public void deletePatch(ContentListItem paramContentListItem)
    throws Exception
  {
    paramContentListItem.enabled = false;
    if (!this.prePatchConfigure)
    {
      livePatch(paramContentListItem);
      Utils.getPrefs(1).edit().putBoolean("force_prepatch", true).apply();
    }
    setPatchListModified();
    paramContentListItem.file.delete();
  }
  
  public List<File> getFolders()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(getDir("ptpatches", 0));
    localArrayList.add(new File(Environment.getExternalStorageDirectory(), "Android/data/com.snowbound.pockettool.free/Patches"));
    localArrayList.add(new File(Environment.getExternalStorageDirectory(), "Android/data/com.joshuahuelsman.pockettool/Patches"));
    return localArrayList;
  }
  
  public void importPatch()
  {
    Intent localIntent = FileUtils.createGetContentIntent();
    localIntent.setType("application/x-ptpatch");
    localIntent.setClass(this, FileChooserActivity.class);
    localIntent.putExtra("net.zhuoweizhang.afilechooser.extra.SORT_METHOD", "net.zhuoweizhang.afilechooser.extra.SORT_LAST_MODIFIED");
    startActivityForResult(localIntent, 212);
  }
  
  public boolean isEnabled(File paramFile)
  {
    return PatchManager.getPatchManager(this).isEnabled(paramFile);
  }
  
  public void livePatch(ContentListItem paramContentListItem)
    throws Exception
  {
    Object localObject2 = getPackageManager().getApplicationInfo("com.mojang.minecraftpe", 0);
    Object localObject1 = getDir("patched", 0);
    localObject2 = new File(((ApplicationInfo)localObject2).nativeLibraryDir + "/libminecraftpe.so");
    new File((File)localObject1, "libminecraftpe.so");
    localObject1 = new PTPatch();
    ((PTPatch)localObject1).loadPatch(paramContentListItem.file);
    if (paramContentListItem.enabled)
    {
      PatchUtils.patch(MainActivity.minecraftLibBuffer, (PTPatch)localObject1);
      return;
    }
    if (this.libBytes == null)
    {
      this.libBytes = new byte[(int)((File)localObject2).length()];
      paramContentListItem = new FileInputStream((File)localObject2);
      paramContentListItem.read(this.libBytes);
      paramContentListItem.close();
    }
    PatchUtils.unpatch(MainActivity.minecraftLibBuffer, this.libBytes, (PTPatch)localObject1);
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    switch (paramInt1)
    {
    }
    do
    {
      return;
    } while (paramInt2 != -1);
    paramIntent = FileUtils.getFile(paramIntent.getData());
    for (;;)
    {
      File localFile;
      try
      {
        localFile = new File(getDir("ptpatches", 0), paramIntent.getName());
        PatchUtils.copy(paramIntent, localFile);
        PatchManager.getPatchManager(this).setEnabled(localFile, false);
        if (Utils.hasTooManyPatches())
        {
          Toast.makeText(this, R.string.manage_patches_too_many, 0).show();
          setPatchListModified();
          findPatches();
          return;
        }
      }
      catch (Exception paramIntent)
      {
        paramIntent.printStackTrace();
        Toast.makeText(this, R.string.manage_patches_import_error, 1).show();
        return;
      }
      PatchManager.getPatchManager(this).setEnabled(localFile, true);
      afterPatchToggle(new ContentListItem(localFile, true));
    }
  }
  
  public void onClick(View paramView)
  {
    if (paramView.equals(this.importButton)) {
      importPatch();
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    Utils.setLanguageOverride();
    super.onCreate(paramBundle);
    setResult(0);
    setContentView(R.layout.manage_patches);
    paramBundle = getListView();
    paramBundle.setTextFilterEnabled(true);
    paramBundle.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        ManagePatchesActivity.this.openManagePatchWindow((ContentListItem)ManagePatchesActivity.this.patches.get(paramAnonymousInt));
      }
    });
    this.importButton = ((ImageButton)findViewById(R.id.manage_patches_import_button));
    this.importButton.setOnClickListener(this);
    this.prePatchConfigure = getIntent().getBooleanExtra("prePatchConfigure", true);
    PatchUtils.minecraftVersion = MinecraftVersion.get(this);
  }
  
  public Dialog onCreateDialog(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return super.onCreateDialog(paramInt);
    case 1: 
      return createManagePatchDialog(-1);
    case 3: 
      return createManagePatchDialog(1);
    case 2: 
      return createManagePatchDialog(0);
    }
    return createPatchInfoDialog();
  }
  
  @TargetApi(14)
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    if (Build.VERSION.SDK_INT >= 11)
    {
      getMenuInflater().inflate(R.menu.ab_master, paramMenu);
      this.master = ((CompoundButton)paramMenu.findItem(R.id.ab_switch_container).getActionView().findViewById(R.id.ab_switch));
      if (this.master != null)
      {
        this.master.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
          public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
          {
            if (paramAnonymousBoolean) {
              ManagePatchesActivity.this.findPatches();
            }
            for (;;)
            {
              paramAnonymousCompoundButton = Utils.getPrefs(0).edit();
              paramAnonymousCompoundButton.putBoolean("zz_manage_patches", paramAnonymousBoolean);
              paramAnonymousCompoundButton.apply();
              ManagePatchesActivity.this.refreshABToggle();
              return;
              ((ArrayAdapter)ManagePatchesActivity.this.getListAdapter()).clear();
            }
          }
        });
        refreshABToggle();
      }
      for (;;)
      {
        return true;
        System.err.println("WTF?");
      }
    }
    return false;
  }
  
  protected void onPause()
  {
    super.onPause();
    this.libBytes = null;
  }
  
  public void onPrepareDialog(int paramInt, Dialog paramDialog)
  {
    switch (paramInt)
    {
    default: 
      super.onPrepareDialog(paramInt, paramDialog);
      return;
    case 1: 
    case 2: 
    case 3: 
      ((AlertDialog)paramDialog).setTitle(this.selectedPatchItem.toString(getResources()));
      return;
    }
    preparePatchInfo((AlertDialog)paramDialog, this.selectedPatchItem);
  }
  
  public void onRefreshComplete(final List<ContentListItem> paramList)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        ContentListItem.sort(paramList);
        ManagePatchesActivity.access$002(ManagePatchesActivity.this, paramList);
        Object localObject = new ContentListAdapter(ManagePatchesActivity.this, R.layout.patch_list_item, ManagePatchesActivity.this.patches);
        ManagePatchesActivity.this.setListAdapter((ListAdapter)localObject);
        localObject = new ArrayList(ManagePatchesActivity.this.patches.size());
        Iterator localIterator = ManagePatchesActivity.this.patches.iterator();
        while (localIterator.hasNext()) {
          ((List)localObject).add(((ContentListItem)localIterator.next()).file.getAbsolutePath());
        }
        PatchManager.getPatchManager(ManagePatchesActivity.this).removeDeadEntries((Collection)localObject);
      }
    });
  }
  
  protected void onStart()
  {
    super.onStart();
    findPatches();
  }
  
  public void preparePatchInfo(AlertDialog paramAlertDialog, ContentListItem paramContentListItem)
  {
    paramAlertDialog.setTitle(paramContentListItem.toString(getResources()));
    try
    {
      paramContentListItem = getPatchInfo(paramContentListItem);
      paramAlertDialog.setMessage(paramContentListItem);
      return;
    }
    catch (Exception paramContentListItem)
    {
      for (;;)
      {
        paramContentListItem = "Cannot show info: " + paramContentListItem.getStackTrace();
      }
    }
  }
  
  protected void refreshABToggle()
  {
    if ((Build.VERSION.SDK_INT >= 11) && (this.master != null)) {
      this.master.setChecked(Utils.getPrefs(0).getBoolean("zz_manage_patches", true));
    }
  }
  
  protected void setPatchListModified()
  {
    setResult(-1);
    Utils.getPrefs(1).edit().putBoolean("force_prepatch", true).apply();
  }
  
  public void togglePatch(ContentListItem paramContentListItem)
  {
    boolean bool2 = true;
    if ((!paramContentListItem.enabled) && (Utils.hasTooManyPatches()))
    {
      Toast.makeText(this, R.string.manage_patches_too_many, 0).show();
      return;
    }
    PatchManager localPatchManager = PatchManager.getPatchManager(this);
    File localFile = paramContentListItem.file;
    if (!paramContentListItem.enabled)
    {
      bool1 = true;
      localPatchManager.setEnabled(localFile, bool1);
      if (paramContentListItem.enabled) {
        break label81;
      }
    }
    label81:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      paramContentListItem.enabled = bool1;
      afterPatchToggle(paramContentListItem);
      return;
      bool1 = false;
      break;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\ui\ManagePatchesActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */