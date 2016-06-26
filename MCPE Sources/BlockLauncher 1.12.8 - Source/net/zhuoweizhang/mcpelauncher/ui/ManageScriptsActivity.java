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
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.ClipboardManager;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import com.ipaulpro.afilechooser.FileChooserActivity;
import com.ipaulpro.afilechooser.utils.FileUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipFile;
import net.zhuoweizhang.mcpelauncher.CoffeeScriptCompiler;
import net.zhuoweizhang.mcpelauncher.MissingTextureException;
import net.zhuoweizhang.mcpelauncher.MpepInfo;
import net.zhuoweizhang.mcpelauncher.R.id;
import net.zhuoweizhang.mcpelauncher.R.integer;
import net.zhuoweizhang.mcpelauncher.R.layout;
import net.zhuoweizhang.mcpelauncher.R.menu;
import net.zhuoweizhang.mcpelauncher.R.string;
import net.zhuoweizhang.mcpelauncher.ScriptManager;
import net.zhuoweizhang.mcpelauncher.Utils;
import net.zhuoweizhang.mcpelauncher.patch.PatchUtils;
import org.mozilla.javascript.RhinoException;
import org.mozilla.javascript.WrappedException;

public class ManageScriptsActivity
  extends ListActivity
  implements View.OnClickListener, RefreshContentListThread.OnRefreshContentList
{
  private static final String[] ALL_SCRIPT_MIMETYPES = { "application/javascript", "text/coffeescript", "text/literate-coffeescript", "application/x-mpep" };
  private static final int DIALOG_IMPORT_FROM_CFGY = 6;
  private static final int DIALOG_IMPORT_FROM_CLIPBOARD = 9;
  private static final int DIALOG_IMPORT_FROM_CLIPBOARD_CODE = 10;
  private static final int DIALOG_IMPORT_FROM_INTENT = 11;
  private static final int DIALOG_IMPORT_FROM_URL = 7;
  private static final int DIALOG_IMPORT_SOURCES = 5;
  private static final int DIALOG_MANAGE_PATCH = 1;
  private static final int DIALOG_MANAGE_PATCH_CURRENTLY_DISABLED = 2;
  private static final int DIALOG_MANAGE_PATCH_CURRENTLY_ENABLED = 3;
  private static final int DIALOG_PATCH_INFO = 4;
  private static final int DIALOG_VERSION_INCOMPATIBLE = 8;
  private static final int REQUEST_IMPORT_PATCH = 212;
  private static char[] cfgyMappings = { 102, 116, 97, 109, 98, 113, 103, 114, 122, 111 };
  protected ArrayAdapter<ContentListItem> adapter;
  private ImageButton importButton;
  private String importClipboardName = "";
  protected CompoundButton master;
  private List<ContentListItem> patches;
  private Thread refreshThread;
  private ContentListItem selectedPatchItem;
  
  private void afterPatchToggle(ContentListItem paramContentListItem) {}
  
  private static byte[] bytesFromInputStream(InputStream paramInputStream, int paramInt)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(paramInt);
    try
    {
      byte[] arrayOfByte = new byte['?'];
      for (;;)
      {
        paramInt = paramInputStream.read(arrayOfByte);
        if (paramInt == -1) {
          break;
        }
        localByteArrayOutputStream.write(arrayOfByte, 0, paramInt);
      }
      paramInputStream = localByteArrayOutputStream.toByteArray();
    }
    finally
    {
      localByteArrayOutputStream.close();
    }
    localByteArrayOutputStream.close();
    return paramInputStream;
  }
  
  private static String cfgyIdToFilename(String paramString)
  {
    paramString = Integer.toString(Integer.parseInt(paramString, 36)).toCharArray();
    int i = 0;
    while (i < paramString.length)
    {
      paramString[i] = cfgyMappings[((char)(paramString[i] - '0'))];
      i += 1;
    }
    paramString = new String(paramString);
    System.out.println(paramString);
    return paramString;
  }
  
  private boolean checkModPkgTextureError(Exception paramException, File paramFile)
  {
    Object localObject = paramException;
    if ((paramException instanceof WrappedException)) {
      localObject = ((WrappedException)paramException).getWrappedException();
    }
    if ((paramFile != null) && (paramFile.getName().toLowerCase().endsWith(".modpkg"))) {}
    for (int i = 1; (i != 0) && ((localObject instanceof MissingTextureException)); i = 0) {
      try
      {
        ScriptManager.setEnabledWithoutLoad(paramFile, true);
        afterPatchToggle(new ContentListItem(paramFile, true));
        setResult(-1);
        return true;
      }
      catch (Exception paramException)
      {
        paramException.printStackTrace();
        reportError(paramException);
        return true;
      }
    }
    return false;
  }
  
  private AlertDialog createImportFromCfgyDialog()
  {
    final EditText localEditText = new EditText(this);
    new AlertDialog.Builder(this).setTitle(R.string.script_import_from_cfgy_id).setView(localEditText).setPositiveButton(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        ManageScriptsActivity.this.importFromCfgy(localEditText.getText().toString());
      }
    }).setNegativeButton(17039360, null).create();
  }
  
  private AlertDialog createImportFromClipboardCodeDialog()
  {
    final EditText localEditText = new EditText(this);
    localEditText.setId(20130805);
    new AlertDialog.Builder(this).setTitle(R.string.script_import_from_clipboard_code).setView(localEditText).setPositiveButton(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        ManageScriptsActivity.this.importFromClipboard(localEditText.getText().toString());
      }
    }).setNegativeButton(17039360, null).create();
  }
  
  private AlertDialog createImportFromClipboardDialog()
  {
    final EditText localEditText = new EditText(this);
    new AlertDialog.Builder(this).setTitle(R.string.script_import_from_clipboard_name).setView(localEditText).setPositiveButton(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        ManageScriptsActivity.access$702(ManageScriptsActivity.this, localEditText.getText().toString());
        ManageScriptsActivity.this.showDialog(10);
      }
    }).setNegativeButton(17039360, null).create();
  }
  
  private AlertDialog createImportFromIntentDialog()
  {
    new AlertDialog.Builder(this).setTitle("Unable to get script location").setMessage(R.string.script_import_from_intent_warning).setPositiveButton(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        ManageScriptsActivity.this.importFromIntent();
      }
    }).setNegativeButton(17039360, null).create();
  }
  
  private AlertDialog createImportFromUrlDialog()
  {
    final EditText localEditText = new EditText(this);
    new AlertDialog.Builder(this).setTitle(R.string.script_import_from_url_enter).setView(localEditText).setPositiveButton(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        ManageScriptsActivity.this.importFromUrl(localEditText.getText().toString());
      }
    }).setNegativeButton(17039360, null).create();
  }
  
  private AlertDialog createImportSourcesDialog()
  {
    Object localObject = getResources();
    String str1 = ((Resources)localObject).getString(R.string.script_import_from_local);
    String str2 = ((Resources)localObject).getString(R.string.script_import_from_cfgy);
    String str3 = ((Resources)localObject).getString(R.string.script_import_from_url);
    localObject = ((Resources)localObject).getString(R.string.script_import_from_clipboard);
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this).setTitle(R.string.script_import_from);
    DialogInterface.OnClickListener local4 = new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        if (paramAnonymousInt == 0) {
          ManageScriptsActivity.this.importPatchFromFile();
        }
        do
        {
          return;
          if (paramAnonymousInt == 1)
          {
            ManageScriptsActivity.this.showDialog(6);
            return;
          }
          if (paramAnonymousInt == 2)
          {
            ManageScriptsActivity.this.showDialog(7);
            return;
          }
        } while (paramAnonymousInt != 3);
        ManageScriptsActivity.this.showDialog(9);
      }
    };
    return localBuilder.setItems(new CharSequence[] { str1, str2, str3, localObject }, local4).create();
  }
  
  private AlertDialog createPatchInfoDialog()
  {
    return new AlertDialog.Builder(this).setTitle("Whoops! info fail").setMessage("Whoops - try again, this is a tiny fail").setPositiveButton(17039370, null).create();
  }
  
  private AlertDialog createVersionIncompatibleDialog()
  {
    return new AlertDialog.Builder(this).setMessage(R.string.script_minecraft_version_incompatible).setPositiveButton(17039370, null).create();
  }
  
  private void findScripts()
  {
    this.refreshThread = new Thread(new RefreshContentListThread(this, this));
    this.refreshThread.start();
  }
  
  private static URL getCfgyUrl(String paramString)
    throws MalformedURLException
  {
    paramString = paramString.trim();
    if ((paramString.length() >= 6) || (paramString.matches("[a-zA-Z]"))) {}
    for (int i = 1; i != 0; i = 0) {
      return new URL("http://modpe.cf.gy/mods/" + cfgyIdToFilename(paramString) + ".js");
    }
    return new URL("http://betamodpe2.cf.gy/user/getScr.php?scrid=" + paramString);
  }
  
  private String getPatchInfo(ContentListItem paramContentListItem)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getResources().getString(R.string.manage_patches_path));
    localStringBuilder.append(": ");
    localStringBuilder.append(paramContentListItem.file.getAbsolutePath());
    localStringBuilder.append('\n');
    return localStringBuilder.toString();
  }
  
  private void importFromCfgy(String paramString)
  {
    new ImportScriptFromCfgyTask(null).execute(new String[] { paramString });
  }
  
  private void importFromClipboard(String paramString)
  {
    try
    {
      File localFile = new File(getDir("modscripts", 0), this.importClipboardName + ".js");
      PrintWriter localPrintWriter = new PrintWriter(localFile);
      localPrintWriter.write(paramString);
      localPrintWriter.flush();
      localPrintWriter.close();
      ScriptManager.setEnabled(localFile, false);
      getMaxPatchCount();
      if (Utils.hasTooManyScripts()) {
        Toast.makeText(this, R.string.script_import_too_many, 0).show();
      }
      for (;;)
      {
        findScripts();
        return;
        ScriptManager.setEnabled(localFile, true);
      }
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
        reportError(paramString);
      }
    }
  }
  
  private void importFromIntent()
  {
    Uri localUri = getIntent().getData();
    new ImportScriptFromIntentTask(null).execute(new Uri[] { localUri });
  }
  
  private void importFromUrl(String paramString)
  {
    new ImportScriptFromUrlTask(null).execute(new String[] { paramString });
  }
  
  private boolean isValidPatch(ContentListItem paramContentListItem)
  {
    return paramContentListItem.file.length() >= 1L;
  }
  
  private void openManagePatchWindow(ContentListItem paramContentListItem)
  {
    this.selectedPatchItem = paramContentListItem;
    if (paramContentListItem.enabled) {}
    for (int i = 3;; i = 2)
    {
      showDialog(i);
      return;
    }
  }
  
  private void reportError(final Throwable paramThrowable)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        final StringWriter localStringWriter = new StringWriter();
        PrintWriter localPrintWriter = new PrintWriter(localStringWriter);
        if ((paramThrowable instanceof RhinoException))
        {
          String str = ((RhinoException)paramThrowable).lineSource();
          if (str != null) {
            localPrintWriter.println(str);
          }
        }
        paramThrowable.printStackTrace(localPrintWriter);
        new AlertDialog.Builder(ManageScriptsActivity.this).setTitle(R.string.manage_patches_import_error).setMessage(localStringWriter.toString()).setPositiveButton(17039370, null).setNeutralButton(17039361, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            ((ClipboardManager)ManageScriptsActivity.this.getSystemService("clipboard")).setText(localStringWriter.toString());
          }
        }).show();
      }
    });
  }
  
  private void reportScriptLoadError(Exception paramException)
  {
    reportError(paramException);
  }
  
  private boolean versionIsSupported()
  {
    boolean bool = false;
    try
    {
      int i = getPackageManager().getPackageInfo("com.mojang.minecraftpe", 0).versionCode;
      if (i == 500801011) {
        bool = true;
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return false;
  }
  
  private void viewSource(ContentListItem paramContentListItem)
  {
    try
    {
      File localFile = new File(getExternalFilesDir(null), "scripts");
      localFile.mkdirs();
      localFile = new File(localFile, paramContentListItem.file.getName());
      PatchUtils.copy(paramContentListItem.file, localFile);
      paramContentListItem = new Intent("android.intent.action.VIEW");
      paramContentListItem.setDataAndType(Uri.fromFile(localFile), "text/plain");
      startActivity(paramContentListItem);
      return;
    }
    catch (Exception paramContentListItem)
    {
      paramContentListItem.printStackTrace();
    }
  }
  
  protected AlertDialog createManagePatchDialog(int paramInt)
  {
    CharSequence localCharSequence1 = getResources().getText(R.string.manage_patches_info);
    CharSequence localCharSequence2 = getResources().getText(R.string.script_view_source);
    if (paramInt == -1)
    {
      localObject = new CharSequence[3];
      localObject[0] = getResources().getText(R.string.manage_patches_delete);
      localObject[1] = localCharSequence1;
      localObject[2] = localCharSequence2;
      new AlertDialog.Builder(this).setTitle("Patch name goes here").setItems((CharSequence[])localObject, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          if (paramAnonymousInt == 0) {}
          do
          {
            try
            {
              ManageScriptsActivity.this.deletePatch(ManageScriptsActivity.this.selectedPatchItem);
              ManageScriptsActivity.this.findScripts();
              return;
            }
            catch (Exception paramAnonymousDialogInterface)
            {
              paramAnonymousDialogInterface.printStackTrace();
              return;
            }
            if (paramAnonymousInt == 1)
            {
              ManageScriptsActivity.this.showDialog(4);
              return;
            }
            if (paramAnonymousInt == 2)
            {
              ManageScriptsActivity.this.viewSource(ManageScriptsActivity.this.selectedPatchItem);
              return;
            }
          } while (paramAnonymousInt != 3);
          ManageScriptsActivity.this.togglePatch(ManageScriptsActivity.this.selectedPatchItem);
          ManageScriptsActivity.this.findScripts();
        }
      }).create();
    }
    CharSequence[] arrayOfCharSequence = new CharSequence[4];
    arrayOfCharSequence[0] = getResources().getText(R.string.manage_patches_delete);
    arrayOfCharSequence[1] = localCharSequence1;
    arrayOfCharSequence[2] = localCharSequence2;
    if (paramInt == 0) {}
    for (Object localObject = getResources().getText(R.string.manage_patches_enable);; localObject = getResources().getText(R.string.manage_patches_disable))
    {
      arrayOfCharSequence[3] = localObject;
      localObject = arrayOfCharSequence;
      break;
    }
  }
  
  public void deletePatch(ContentListItem paramContentListItem)
    throws Exception
  {
    paramContentListItem.enabled = false;
    try
    {
      ScriptManager.setEnabled(paramContentListItem.file, false);
      setPatchListModified();
      paramContentListItem.file.delete();
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  public List<File> getFolders()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(getDir("modscripts", 0));
    return localArrayList;
  }
  
  protected int getMaxPatchCount()
  {
    return getResources().getInteger(R.integer.max_num_scripts);
  }
  
  public void importPatch()
  {
    showDialog(5);
  }
  
  public void importPatchFromFile()
  {
    Intent localIntent = FileUtils.createGetContentIntent();
    localIntent.setType("application/javascript");
    localIntent.setClass(this, FileChooserActivity.class);
    localIntent.putExtra("net.zhuoweizhang.afilechooser.extra.MIME_TYPES", ALL_SCRIPT_MIMETYPES);
    localIntent.putExtra("net.zhuoweizhang.afilechooser.extra.SORT_METHOD", "net.zhuoweizhang.afilechooser.extra.SORT_LAST_MODIFIED");
    startActivityForResult(localIntent, 212);
  }
  
  public boolean isEnabled(File paramFile)
  {
    return ScriptManager.isEnabled(paramFile);
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    switch (paramInt1)
    {
    default: 
      break;
    }
    do
    {
      return;
    } while (paramInt2 != -1);
    File localFile = FileUtils.getFile(paramIntent.getData());
    localObject = null;
    paramIntent = (Intent)localObject;
    for (;;)
    {
      try
      {
        if (!CoffeeScriptCompiler.isCoffeeScript(localFile)) {
          break label181;
        }
        paramIntent = (Intent)localObject;
        localObject = new File(getDir("modscripts", 0), CoffeeScriptCompiler.outputName(localFile.getName()));
        paramIntent = (Intent)localObject;
        paramIntent.printStackTrace();
      }
      catch (Exception localException1)
      {
        try
        {
          CoffeeScriptCompiler.compileFile(localFile, (File)localObject);
          paramIntent = (Intent)localObject;
          ScriptManager.setEnabled((File)localObject, false);
          paramIntent = (Intent)localObject;
          getMaxPatchCount();
          paramIntent = (Intent)localObject;
          if (!Utils.hasTooManyScripts()) {
            break label229;
          }
          paramIntent = (Intent)localObject;
          Toast.makeText(this, R.string.script_import_too_many, 0).show();
          paramIntent = (Intent)localObject;
          setPatchListModified();
          paramIntent = (Intent)localObject;
          findScripts();
          return;
        }
        catch (Exception localException2)
        {
          for (;;)
          {
            localObject = paramIntent;
            paramIntent = localException2;
          }
        }
        localException1 = localException1;
        localObject = paramIntent;
        paramIntent = localException1;
      }
      if (checkModPkgTextureError(paramIntent, (File)localObject)) {
        break;
      }
      reportError(paramIntent);
      Toast.makeText(this, R.string.manage_patches_import_error, 1).show();
      return;
      label181:
      paramIntent = (Intent)localObject;
      localObject = new File(getDir("modscripts", 0), localException1.getName());
      paramIntent = (Intent)localObject;
      PatchUtils.copy(localException1, (File)localObject);
      paramIntent = (Intent)localObject;
      ScriptManager.setOriginalLocation(localException1, (File)localObject);
      continue;
      label229:
      paramIntent = (Intent)localObject;
      ScriptManager.setEnabled((File)localObject, true);
      paramIntent = (Intent)localObject;
      afterPatchToggle(new ContentListItem((File)localObject, true));
    }
  }
  
  public void onClick(View paramView)
  {
    if (paramView == this.importButton) {
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
        ManageScriptsActivity.this.openManagePatchWindow((ContentListItem)ManageScriptsActivity.this.patches.get(paramAnonymousInt));
      }
    });
    this.importButton = ((ImageButton)findViewById(R.id.manage_patches_import_button));
    this.importButton.setOnClickListener(this);
    ScriptManager.androidContext = getApplicationContext();
    this.patches = new ArrayList();
    this.adapter = new ContentListAdapter(this, R.layout.patch_list_item, this.patches);
    setListAdapter(this.adapter);
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
    case 4: 
      return createPatchInfoDialog();
    case 5: 
      return createImportSourcesDialog();
    case 6: 
      return createImportFromCfgyDialog();
    case 7: 
      return createImportFromUrlDialog();
    case 8: 
      return createVersionIncompatibleDialog();
    case 9: 
      return createImportFromClipboardDialog();
    case 10: 
      return createImportFromClipboardCodeDialog();
    }
    return createImportFromIntentDialog();
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
              ManageScriptsActivity.this.findScripts();
            }
            for (;;)
            {
              paramAnonymousCompoundButton = PreferenceManager.getDefaultSharedPreferences(ManageScriptsActivity.this).edit();
              paramAnonymousCompoundButton.putBoolean("zz_script_enable", paramAnonymousBoolean);
              paramAnonymousCompoundButton.apply();
              ManageScriptsActivity.this.refreshABToggle();
              return;
              ((ArrayAdapter)ManageScriptsActivity.this.getListAdapter()).clear();
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
    refreshABToggle();
  }
  
  public void onPrepareDialog(int paramInt, Dialog paramDialog)
  {
    switch (paramInt)
    {
    case 5: 
    case 6: 
    case 7: 
    case 8: 
    case 9: 
    default: 
      super.onPrepareDialog(paramInt, paramDialog);
      return;
    case 1: 
    case 2: 
    case 3: 
      ((AlertDialog)paramDialog).setTitle(this.selectedPatchItem.toString(getResources()));
      return;
    case 4: 
      preparePatchInfo((AlertDialog)paramDialog, this.selectedPatchItem);
      return;
    case 10: 
      paramDialog = (AlertDialog)paramDialog;
      CharSequence localCharSequence = ((ClipboardManager)getSystemService("clipboard")).getText();
      ((EditText)paramDialog.findViewById(20130805)).setText(localCharSequence);
      return;
    }
    ((AlertDialog)paramDialog).setTitle(getIntent().getData().toString());
  }
  
  public void onRefreshComplete(final List<ContentListItem> paramList)
  {
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      ContentListItem localContentListItem = (ContentListItem)localIterator.next();
      try
      {
        if (localContentListItem.file.getName().toLowerCase().endsWith(".modpkg"))
        {
          ZipFile localZipFile = new ZipFile(localContentListItem.file);
          MpepInfo localMpepInfo = MpepInfo.fromZip(localZipFile);
          localZipFile.close();
          if (localMpepInfo != null) {
            localContentListItem.extraData = ("by " + localMpepInfo.authorName);
          }
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        ContentListItem.sort(paramList);
        ManageScriptsActivity.this.patches.clear();
        ManageScriptsActivity.this.patches.addAll(paramList);
        ManageScriptsActivity.this.adapter.notifyDataSetChanged();
        ArrayList localArrayList = new ArrayList(ManageScriptsActivity.this.patches.size());
        Iterator localIterator = ManageScriptsActivity.this.patches.iterator();
        while (localIterator.hasNext()) {
          localArrayList.add(((ContentListItem)localIterator.next()).file.getName());
        }
        ScriptManager.removeDeadEntries(localArrayList);
      }
    });
  }
  
  protected void onResume()
  {
    super.onResume();
    refreshABToggle();
  }
  
  protected void onStart()
  {
    super.onStart();
    findScripts();
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
    if ((Build.VERSION.SDK_INT >= 11) && (this.master != null))
    {
      SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
      this.master.setChecked(localSharedPreferences.getBoolean("zz_script_enable", true));
    }
  }
  
  protected void setPatchListModified() {}
  
  public void togglePatch(ContentListItem paramContentListItem)
  {
    boolean bool2 = true;
    getMaxPatchCount();
    if ((!paramContentListItem.enabled) && (Utils.hasTooManyScripts()))
    {
      Toast.makeText(this, R.string.script_import_too_many, 0).show();
      return;
    }
    for (;;)
    {
      try
      {
        File localFile = paramContentListItem.file;
        if (paramContentListItem.enabled) {
          continue;
        }
        bool1 = true;
        ScriptManager.setEnabled(localFile, bool1);
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        if (checkModPkgTextureError(localException, paramContentListItem.file)) {
          continue;
        }
        reportScriptLoadError(localException);
        continue;
        boolean bool1 = false;
        continue;
      }
      if (paramContentListItem.enabled) {
        continue;
      }
      bool1 = bool2;
      paramContentListItem.enabled = bool1;
      afterPatchToggle(paramContentListItem);
      return;
      bool1 = false;
    }
  }
  
  private class ImportScriptFromCfgyTask
    extends ManageScriptsActivity.ImportScriptTask<String>
  {
    private ImportScriptFromCfgyTask()
    {
      super(null);
    }
    
    /* Error */
    protected File doInBackground(String... paramVarArgs)
    {
      // Byte code:
      //   0: aload_1
      //   1: iconst_0
      //   2: aaload
      //   3: astore 12
      //   5: aconst_null
      //   6: astore 10
      //   8: aconst_null
      //   9: astore_1
      //   10: aconst_null
      //   11: astore 7
      //   13: iconst_0
      //   14: istore_2
      //   15: aconst_null
      //   16: astore 9
      //   18: aconst_null
      //   19: astore 8
      //   21: new 26	java/io/File
      //   24: dup
      //   25: aload_0
      //   26: getfield 14	net/zhuoweizhang/mcpelauncher/ui/ManageScriptsActivity$ImportScriptFromCfgyTask:this$0	Lnet/zhuoweizhang/mcpelauncher/ui/ManageScriptsActivity;
      //   29: ldc 28
      //   31: iconst_0
      //   32: invokevirtual 32	net/zhuoweizhang/mcpelauncher/ui/ManageScriptsActivity:getDir	(Ljava/lang/String;I)Ljava/io/File;
      //   35: new 34	java/lang/StringBuilder
      //   38: dup
      //   39: invokespecial 37	java/lang/StringBuilder:<init>	()V
      //   42: aload 12
      //   44: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   47: ldc 43
      //   49: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   52: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   55: invokespecial 50	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
      //   58: astore 11
      //   60: aload_1
      //   61: astore 6
      //   63: aload 9
      //   65: astore 5
      //   67: aload 10
      //   69: astore 4
      //   71: aload 12
      //   73: invokestatic 54	net/zhuoweizhang/mcpelauncher/ui/ManageScriptsActivity:access$1500	(Ljava/lang/String;)Ljava/net/URL;
      //   76: invokevirtual 60	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   79: checkcast 62	java/net/HttpURLConnection
      //   82: astore 12
      //   84: aload_1
      //   85: astore 6
      //   87: aload 9
      //   89: astore 5
      //   91: aload 10
      //   93: astore 4
      //   95: aload 12
      //   97: ldc 64
      //   99: ldc 66
      //   101: invokevirtual 70	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   104: aload_1
      //   105: astore 6
      //   107: aload 9
      //   109: astore 5
      //   111: aload 10
      //   113: astore 4
      //   115: aload 12
      //   117: iconst_1
      //   118: invokevirtual 74	java/net/HttpURLConnection:setDoInput	(Z)V
      //   121: aload_1
      //   122: astore 6
      //   124: aload 9
      //   126: astore 5
      //   128: aload 10
      //   130: astore 4
      //   132: aload 12
      //   134: invokevirtual 77	java/net/HttpURLConnection:connect	()V
      //   137: aload 9
      //   139: astore 5
      //   141: aload 10
      //   143: astore 4
      //   145: aload 12
      //   147: invokevirtual 81	java/net/HttpURLConnection:getResponseCode	()I
      //   150: istore_3
      //   151: iload_3
      //   152: istore_2
      //   153: aload 9
      //   155: astore 5
      //   157: aload 10
      //   159: astore 4
      //   161: aload 12
      //   163: invokevirtual 85	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
      //   166: astore 6
      //   168: aload 6
      //   170: astore_1
      //   171: iload_3
      //   172: istore_2
      //   173: iload_2
      //   174: sipush 400
      //   177: if_icmplt +47 -> 224
      //   180: aload_1
      //   181: ifnull +7 -> 188
      //   184: aload_1
      //   185: invokevirtual 90	java/io/InputStream:close	()V
      //   188: iconst_0
      //   189: ifeq +11 -> 200
      //   192: new 92	java/lang/NullPointerException
      //   195: dup
      //   196: invokespecial 93	java/lang/NullPointerException:<init>	()V
      //   199: athrow
      //   200: aconst_null
      //   201: areturn
      //   202: astore 4
      //   204: aload_1
      //   205: astore 6
      //   207: aload 9
      //   209: astore 5
      //   211: aload 10
      //   213: astore 4
      //   215: aload 12
      //   217: invokevirtual 96	java/net/HttpURLConnection:getErrorStream	()Ljava/io/InputStream;
      //   220: astore_1
      //   221: goto -48 -> 173
      //   224: aload_1
      //   225: ifnull +54 -> 279
      //   228: aload_1
      //   229: astore 6
      //   231: aload 9
      //   233: astore 5
      //   235: aload_1
      //   236: astore 4
      //   238: aload 12
      //   240: invokevirtual 99	java/net/HttpURLConnection:getContentLength	()I
      //   243: ifle +122 -> 365
      //   246: aload_1
      //   247: astore 6
      //   249: aload 9
      //   251: astore 5
      //   253: aload_1
      //   254: astore 4
      //   256: aload 12
      //   258: invokevirtual 99	java/net/HttpURLConnection:getContentLength	()I
      //   261: istore_2
      //   262: aload_1
      //   263: astore 6
      //   265: aload 9
      //   267: astore 5
      //   269: aload_1
      //   270: astore 4
      //   272: aload_1
      //   273: iload_2
      //   274: invokestatic 103	net/zhuoweizhang/mcpelauncher/ui/ManageScriptsActivity:access$1600	(Ljava/io/InputStream;I)[B
      //   277: astore 7
      //   279: aload_1
      //   280: astore 6
      //   282: aload 9
      //   284: astore 5
      //   286: aload_1
      //   287: astore 4
      //   289: new 105	java/lang/String
      //   292: dup
      //   293: aload 7
      //   295: invokespecial 108	java/lang/String:<init>	([B)V
      //   298: ldc 110
      //   300: ldc 112
      //   302: invokevirtual 116	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
      //   305: iconst_0
      //   306: invokestatic 122	android/util/Base64:decode	(Ljava/lang/String;I)[B
      //   309: astore 10
      //   311: aload_1
      //   312: astore 6
      //   314: aload 9
      //   316: astore 5
      //   318: aload_1
      //   319: astore 4
      //   321: new 124	java/io/FileOutputStream
      //   324: dup
      //   325: aload 11
      //   327: invokespecial 127	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
      //   330: astore 7
      //   332: aload 7
      //   334: aload 10
      //   336: invokevirtual 130	java/io/FileOutputStream:write	([B)V
      //   339: aload 7
      //   341: invokevirtual 133	java/io/FileOutputStream:flush	()V
      //   344: aload_1
      //   345: ifnull +7 -> 352
      //   348: aload_1
      //   349: invokevirtual 90	java/io/InputStream:close	()V
      //   352: aload 7
      //   354: ifnull +8 -> 362
      //   357: aload 7
      //   359: invokevirtual 134	java/io/FileOutputStream:close	()V
      //   362: aload 11
      //   364: areturn
      //   365: sipush 1024
      //   368: istore_2
      //   369: goto -107 -> 262
      //   372: astore 7
      //   374: aload 6
      //   376: astore_1
      //   377: aload 8
      //   379: astore 6
      //   381: aload 6
      //   383: astore 5
      //   385: aload_1
      //   386: astore 4
      //   388: aload 7
      //   390: invokevirtual 137	java/lang/Exception:printStackTrace	()V
      //   393: aload 6
      //   395: astore 5
      //   397: aload_1
      //   398: astore 4
      //   400: aload_0
      //   401: getfield 14	net/zhuoweizhang/mcpelauncher/ui/ManageScriptsActivity$ImportScriptFromCfgyTask:this$0	Lnet/zhuoweizhang/mcpelauncher/ui/ManageScriptsActivity;
      //   404: aload 7
      //   406: invokestatic 141	net/zhuoweizhang/mcpelauncher/ui/ManageScriptsActivity:access$1300	(Lnet/zhuoweizhang/mcpelauncher/ui/ManageScriptsActivity;Ljava/lang/Throwable;)V
      //   409: aload_1
      //   410: ifnull +7 -> 417
      //   413: aload_1
      //   414: invokevirtual 90	java/io/InputStream:close	()V
      //   417: aload 6
      //   419: ifnull -219 -> 200
      //   422: aload 6
      //   424: invokevirtual 134	java/io/FileOutputStream:close	()V
      //   427: aconst_null
      //   428: areturn
      //   429: astore_1
      //   430: aconst_null
      //   431: areturn
      //   432: astore_1
      //   433: aload 4
      //   435: ifnull +8 -> 443
      //   438: aload 4
      //   440: invokevirtual 90	java/io/InputStream:close	()V
      //   443: aload 5
      //   445: ifnull +8 -> 453
      //   448: aload 5
      //   450: invokevirtual 134	java/io/FileOutputStream:close	()V
      //   453: aload_1
      //   454: athrow
      //   455: astore_1
      //   456: goto -268 -> 188
      //   459: astore_1
      //   460: aconst_null
      //   461: areturn
      //   462: astore_1
      //   463: goto -111 -> 352
      //   466: astore_1
      //   467: goto -105 -> 362
      //   470: astore_1
      //   471: goto -54 -> 417
      //   474: astore 4
      //   476: goto -33 -> 443
      //   479: astore 4
      //   481: goto -28 -> 453
      //   484: astore 6
      //   486: aload 7
      //   488: astore 5
      //   490: aload_1
      //   491: astore 4
      //   493: aload 6
      //   495: astore_1
      //   496: goto -63 -> 433
      //   499: astore 4
      //   501: aload 7
      //   503: astore 6
      //   505: aload 4
      //   507: astore 7
      //   509: goto -128 -> 381
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	512	0	this	ImportScriptFromCfgyTask
      //   0	512	1	paramVarArgs	String[]
      //   14	355	2	i	int
      //   150	22	3	j	int
      //   69	91	4	arrayOfByte1	byte[]
      //   202	1	4	localException1	Exception
      //   213	226	4	localObject1	Object
      //   474	1	4	localException2	Exception
      //   479	1	4	localException3	Exception
      //   491	1	4	arrayOfString	String[]
      //   499	7	4	localException4	Exception
      //   65	424	5	localObject2	Object
      //   61	362	6	localObject3	Object
      //   484	10	6	localObject4	Object
      //   503	1	6	localException5	Exception
      //   11	347	7	localObject5	Object
      //   372	130	7	localException6	Exception
      //   507	1	7	localObject6	Object
      //   19	359	8	localObject7	Object
      //   16	299	9	localObject8	Object
      //   6	329	10	arrayOfByte2	byte[]
      //   58	305	11	localFile	File
      //   3	254	12	localObject9	Object
      // Exception table:
      //   from	to	target	type
      //   145	151	202	java/lang/Exception
      //   161	168	202	java/lang/Exception
      //   71	84	372	java/lang/Exception
      //   95	104	372	java/lang/Exception
      //   115	121	372	java/lang/Exception
      //   132	137	372	java/lang/Exception
      //   215	221	372	java/lang/Exception
      //   238	246	372	java/lang/Exception
      //   256	262	372	java/lang/Exception
      //   272	279	372	java/lang/Exception
      //   289	311	372	java/lang/Exception
      //   321	332	372	java/lang/Exception
      //   422	427	429	java/lang/Exception
      //   71	84	432	finally
      //   95	104	432	finally
      //   115	121	432	finally
      //   132	137	432	finally
      //   145	151	432	finally
      //   161	168	432	finally
      //   215	221	432	finally
      //   238	246	432	finally
      //   256	262	432	finally
      //   272	279	432	finally
      //   289	311	432	finally
      //   321	332	432	finally
      //   388	393	432	finally
      //   400	409	432	finally
      //   184	188	455	java/lang/Exception
      //   192	200	459	java/lang/Exception
      //   348	352	462	java/lang/Exception
      //   357	362	466	java/lang/Exception
      //   413	417	470	java/lang/Exception
      //   438	443	474	java/lang/Exception
      //   448	453	479	java/lang/Exception
      //   332	344	484	finally
      //   332	344	499	java/lang/Exception
    }
  }
  
  private class ImportScriptFromIntentTask
    extends ManageScriptsActivity.ImportScriptTask<Uri>
  {
    private ImportScriptFromIntentTask()
    {
      super(null);
    }
    
    /* Error */
    protected File doInBackground(Uri... paramVarArgs)
    {
      // Byte code:
      //   0: aload_1
      //   1: iconst_0
      //   2: aaload
      //   3: astore_1
      //   4: aconst_null
      //   5: astore_2
      //   6: aconst_null
      //   7: astore 4
      //   9: aconst_null
      //   10: astore 5
      //   12: aconst_null
      //   13: astore 6
      //   15: aload_1
      //   16: invokevirtual 30	android/net/Uri:getLastPathSegment	()Ljava/lang/String;
      //   19: astore_3
      //   20: new 32	java/io/File
      //   23: dup
      //   24: aload_0
      //   25: getfield 14	net/zhuoweizhang/mcpelauncher/ui/ManageScriptsActivity$ImportScriptFromIntentTask:this$0	Lnet/zhuoweizhang/mcpelauncher/ui/ManageScriptsActivity;
      //   28: ldc 34
      //   30: iconst_0
      //   31: invokevirtual 38	net/zhuoweizhang/mcpelauncher/ui/ManageScriptsActivity:getDir	(Ljava/lang/String;I)Ljava/io/File;
      //   34: aload_3
      //   35: invokespecial 41	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
      //   38: astore 7
      //   40: aload 5
      //   42: astore_3
      //   43: aload_0
      //   44: getfield 14	net/zhuoweizhang/mcpelauncher/ui/ManageScriptsActivity$ImportScriptFromIntentTask:this$0	Lnet/zhuoweizhang/mcpelauncher/ui/ManageScriptsActivity;
      //   47: invokevirtual 45	net/zhuoweizhang/mcpelauncher/ui/ManageScriptsActivity:getContentResolver	()Landroid/content/ContentResolver;
      //   50: aload_1
      //   51: invokevirtual 51	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
      //   54: astore_1
      //   55: aload_1
      //   56: astore 4
      //   58: aload 5
      //   60: astore_3
      //   61: aload_1
      //   62: astore_2
      //   63: aload_1
      //   64: sipush 1024
      //   67: invokestatic 55	net/zhuoweizhang/mcpelauncher/ui/ManageScriptsActivity:access$1600	(Ljava/io/InputStream;I)[B
      //   70: astore 8
      //   72: aload_1
      //   73: astore 4
      //   75: aload 5
      //   77: astore_3
      //   78: aload_1
      //   79: astore_2
      //   80: new 57	java/io/FileOutputStream
      //   83: dup
      //   84: aload 7
      //   86: invokespecial 60	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
      //   89: astore 5
      //   91: aload 5
      //   93: aload 8
      //   95: invokevirtual 64	java/io/FileOutputStream:write	([B)V
      //   98: aload 5
      //   100: invokevirtual 68	java/io/FileOutputStream:flush	()V
      //   103: aload_1
      //   104: ifnull +7 -> 111
      //   107: aload_1
      //   108: invokevirtual 73	java/io/InputStream:close	()V
      //   111: aload 5
      //   113: ifnull +8 -> 121
      //   116: aload 5
      //   118: invokevirtual 74	java/io/FileOutputStream:close	()V
      //   121: aload 7
      //   123: astore_1
      //   124: aload_1
      //   125: areturn
      //   126: astore 5
      //   128: aload 4
      //   130: astore_1
      //   131: aload 6
      //   133: astore 4
      //   135: aload 4
      //   137: astore_3
      //   138: aload_1
      //   139: astore_2
      //   140: aload 5
      //   142: invokevirtual 77	java/lang/Exception:printStackTrace	()V
      //   145: aconst_null
      //   146: astore_2
      //   147: aload_1
      //   148: ifnull +7 -> 155
      //   151: aload_1
      //   152: invokevirtual 73	java/io/InputStream:close	()V
      //   155: aload_2
      //   156: astore_1
      //   157: aload 4
      //   159: ifnull -35 -> 124
      //   162: aload 4
      //   164: invokevirtual 74	java/io/FileOutputStream:close	()V
      //   167: aconst_null
      //   168: areturn
      //   169: astore_1
      //   170: aconst_null
      //   171: areturn
      //   172: astore_1
      //   173: aload_2
      //   174: ifnull +7 -> 181
      //   177: aload_2
      //   178: invokevirtual 73	java/io/InputStream:close	()V
      //   181: aload_3
      //   182: ifnull +7 -> 189
      //   185: aload_3
      //   186: invokevirtual 74	java/io/FileOutputStream:close	()V
      //   189: aload_1
      //   190: athrow
      //   191: astore_1
      //   192: goto -81 -> 111
      //   195: astore_1
      //   196: goto -75 -> 121
      //   199: astore_1
      //   200: goto -45 -> 155
      //   203: astore_2
      //   204: goto -23 -> 181
      //   207: astore_2
      //   208: goto -19 -> 189
      //   211: astore 4
      //   213: aload 5
      //   215: astore_3
      //   216: aload_1
      //   217: astore_2
      //   218: aload 4
      //   220: astore_1
      //   221: goto -48 -> 173
      //   224: astore_2
      //   225: aload 5
      //   227: astore 4
      //   229: aload_2
      //   230: astore 5
      //   232: goto -97 -> 135
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	235	0	this	ImportScriptFromIntentTask
      //   0	235	1	paramVarArgs	Uri[]
      //   5	173	2	arrayOfUri1	Uri[]
      //   203	1	2	localException1	Exception
      //   207	1	2	localException2	Exception
      //   217	1	2	arrayOfUri2	Uri[]
      //   224	6	2	localException3	Exception
      //   19	197	3	localObject1	Object
      //   7	156	4	localObject2	Object
      //   211	8	4	localObject3	Object
      //   227	1	4	localObject4	Object
      //   10	107	5	localFileOutputStream	java.io.FileOutputStream
      //   126	100	5	localException4	Exception
      //   230	1	5	localException5	Exception
      //   13	119	6	localObject5	Object
      //   38	84	7	localFile	File
      //   70	24	8	arrayOfByte	byte[]
      // Exception table:
      //   from	to	target	type
      //   43	55	126	java/lang/Exception
      //   63	72	126	java/lang/Exception
      //   80	91	126	java/lang/Exception
      //   162	167	169	java/lang/Exception
      //   43	55	172	finally
      //   63	72	172	finally
      //   80	91	172	finally
      //   140	145	172	finally
      //   107	111	191	java/lang/Exception
      //   116	121	195	java/lang/Exception
      //   151	155	199	java/lang/Exception
      //   177	181	203	java/lang/Exception
      //   185	189	207	java/lang/Exception
      //   91	103	211	finally
      //   91	103	224	java/lang/Exception
    }
  }
  
  private class ImportScriptFromUrlTask
    extends ManageScriptsActivity.ImportScriptTask<String>
  {
    private ImportScriptFromUrlTask()
    {
      super(null);
    }
    
    /* Error */
    protected File doInBackground(String... paramVarArgs)
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore 12
      //   3: aconst_null
      //   4: astore 6
      //   6: aconst_null
      //   7: astore 9
      //   9: aconst_null
      //   10: astore 10
      //   12: iconst_0
      //   13: istore_2
      //   14: aconst_null
      //   15: astore 11
      //   17: aconst_null
      //   18: astore 7
      //   20: aconst_null
      //   21: astore 8
      //   23: aload 11
      //   25: astore 4
      //   27: aload 12
      //   29: astore 5
      //   31: new 26	java/net/URL
      //   34: dup
      //   35: aload_1
      //   36: iconst_0
      //   37: aaload
      //   38: invokespecial 29	java/net/URL:<init>	(Ljava/lang/String;)V
      //   41: astore 14
      //   43: aload 11
      //   45: astore 4
      //   47: aload 12
      //   49: astore 5
      //   51: getstatic 35	java/lang/System:out	Ljava/io/PrintStream;
      //   54: aload 14
      //   56: invokevirtual 41	java/io/PrintStream:println	(Ljava/lang/Object;)V
      //   59: aload 11
      //   61: astore 4
      //   63: aload 12
      //   65: astore 5
      //   67: aload 14
      //   69: invokevirtual 45	java/net/URL:getPath	()Ljava/lang/String;
      //   72: astore_1
      //   73: aload 11
      //   75: astore 4
      //   77: aload 12
      //   79: astore 5
      //   81: aload_1
      //   82: aload_1
      //   83: ldc 47
      //   85: invokevirtual 53	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
      //   88: iconst_1
      //   89: iadd
      //   90: invokevirtual 57	java/lang/String:substring	(I)Ljava/lang/String;
      //   93: astore 13
      //   95: aload 13
      //   97: astore_1
      //   98: aload 11
      //   100: astore 4
      //   102: aload 12
      //   104: astore 5
      //   106: aload 13
      //   108: invokevirtual 61	java/lang/String:length	()I
      //   111: iconst_1
      //   112: if_icmpge +6 -> 118
      //   115: ldc 63
      //   117: astore_1
      //   118: aload 11
      //   120: astore 4
      //   122: aload 12
      //   124: astore 5
      //   126: new 65	java/io/File
      //   129: dup
      //   130: aload_0
      //   131: getfield 14	net/zhuoweizhang/mcpelauncher/ui/ManageScriptsActivity$ImportScriptFromUrlTask:this$0	Lnet/zhuoweizhang/mcpelauncher/ui/ManageScriptsActivity;
      //   134: ldc 67
      //   136: iconst_0
      //   137: invokevirtual 71	net/zhuoweizhang/mcpelauncher/ui/ManageScriptsActivity:getDir	(Ljava/lang/String;I)Ljava/io/File;
      //   140: aload_1
      //   141: invokespecial 74	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
      //   144: astore 11
      //   146: aload 6
      //   148: astore 4
      //   150: aload 9
      //   152: astore 5
      //   154: aload 14
      //   156: invokevirtual 78	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   159: checkcast 80	java/net/HttpURLConnection
      //   162: astore 10
      //   164: aload 6
      //   166: astore 4
      //   168: aload 9
      //   170: astore 5
      //   172: aload 10
      //   174: ldc 82
      //   176: ldc 84
      //   178: invokevirtual 88	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   181: aload 6
      //   183: astore 4
      //   185: aload 9
      //   187: astore 5
      //   189: aload 10
      //   191: iconst_1
      //   192: invokevirtual 92	java/net/HttpURLConnection:setDoInput	(Z)V
      //   195: aload 6
      //   197: astore 4
      //   199: aload 9
      //   201: astore 5
      //   203: aload 10
      //   205: invokevirtual 96	java/net/HttpURLConnection:connect	()V
      //   208: aload 6
      //   210: astore 4
      //   212: aload 10
      //   214: invokevirtual 99	java/net/HttpURLConnection:getResponseCode	()I
      //   217: istore_3
      //   218: iload_3
      //   219: istore_2
      //   220: aload 6
      //   222: astore 4
      //   224: aload 10
      //   226: invokevirtual 103	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
      //   229: astore_1
      //   230: iload_3
      //   231: istore_2
      //   232: iload_2
      //   233: sipush 400
      //   236: if_icmplt +43 -> 279
      //   239: aload_1
      //   240: ifnull +7 -> 247
      //   243: aload_1
      //   244: invokevirtual 108	java/io/InputStream:close	()V
      //   247: iconst_0
      //   248: ifeq +11 -> 259
      //   251: new 110	java/lang/NullPointerException
      //   254: dup
      //   255: invokespecial 112	java/lang/NullPointerException:<init>	()V
      //   258: athrow
      //   259: aconst_null
      //   260: areturn
      //   261: astore_1
      //   262: aload 6
      //   264: astore 4
      //   266: aload 9
      //   268: astore 5
      //   270: aload 10
      //   272: invokevirtual 115	java/net/HttpURLConnection:getErrorStream	()Ljava/io/InputStream;
      //   275: astore_1
      //   276: goto -44 -> 232
      //   279: aload_1
      //   280: ifnonnull +25 -> 305
      //   283: aload_1
      //   284: ifnull +7 -> 291
      //   287: aload_1
      //   288: invokevirtual 108	java/io/InputStream:close	()V
      //   291: iconst_0
      //   292: ifeq +11 -> 303
      //   295: new 110	java/lang/NullPointerException
      //   298: dup
      //   299: invokespecial 112	java/lang/NullPointerException:<init>	()V
      //   302: athrow
      //   303: aconst_null
      //   304: areturn
      //   305: aload_1
      //   306: astore 4
      //   308: aload_1
      //   309: astore 5
      //   311: aload 10
      //   313: invokevirtual 118	java/net/HttpURLConnection:getContentLength	()I
      //   316: ifle +78 -> 394
      //   319: aload_1
      //   320: astore 4
      //   322: aload_1
      //   323: astore 5
      //   325: aload 10
      //   327: invokevirtual 118	java/net/HttpURLConnection:getContentLength	()I
      //   330: istore_2
      //   331: aload_1
      //   332: astore 4
      //   334: aload_1
      //   335: astore 5
      //   337: aload_1
      //   338: iload_2
      //   339: invokestatic 122	net/zhuoweizhang/mcpelauncher/ui/ManageScriptsActivity:access$1600	(Ljava/io/InputStream;I)[B
      //   342: astore 9
      //   344: aload_1
      //   345: astore 4
      //   347: aload_1
      //   348: astore 5
      //   350: new 124	java/io/FileOutputStream
      //   353: dup
      //   354: aload 11
      //   356: invokespecial 127	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
      //   359: astore 6
      //   361: aload 6
      //   363: aload 9
      //   365: invokevirtual 131	java/io/FileOutputStream:write	([B)V
      //   368: aload 6
      //   370: invokevirtual 134	java/io/FileOutputStream:flush	()V
      //   373: aload_1
      //   374: ifnull +7 -> 381
      //   377: aload_1
      //   378: invokevirtual 108	java/io/InputStream:close	()V
      //   381: aload 6
      //   383: ifnull +8 -> 391
      //   386: aload 6
      //   388: invokevirtual 135	java/io/FileOutputStream:close	()V
      //   391: aload 11
      //   393: areturn
      //   394: sipush 1024
      //   397: istore_2
      //   398: goto -67 -> 331
      //   401: astore 7
      //   403: aload 10
      //   405: astore_1
      //   406: aload 8
      //   408: astore 6
      //   410: aload 6
      //   412: astore 4
      //   414: aload_1
      //   415: astore 5
      //   417: aload_0
      //   418: getfield 14	net/zhuoweizhang/mcpelauncher/ui/ManageScriptsActivity$ImportScriptFromUrlTask:this$0	Lnet/zhuoweizhang/mcpelauncher/ui/ManageScriptsActivity;
      //   421: aload 7
      //   423: invokestatic 139	net/zhuoweizhang/mcpelauncher/ui/ManageScriptsActivity:access$1300	(Lnet/zhuoweizhang/mcpelauncher/ui/ManageScriptsActivity;Ljava/lang/Throwable;)V
      //   426: aload 6
      //   428: astore 4
      //   430: aload_1
      //   431: astore 5
      //   433: aload 7
      //   435: invokevirtual 142	java/lang/Exception:printStackTrace	()V
      //   438: aload_1
      //   439: ifnull +7 -> 446
      //   442: aload_1
      //   443: invokevirtual 108	java/io/InputStream:close	()V
      //   446: aload 6
      //   448: ifnull -189 -> 259
      //   451: aload 6
      //   453: invokevirtual 135	java/io/FileOutputStream:close	()V
      //   456: aconst_null
      //   457: areturn
      //   458: astore_1
      //   459: aconst_null
      //   460: areturn
      //   461: astore_1
      //   462: aload 5
      //   464: astore 6
      //   466: aload 4
      //   468: astore 5
      //   470: aload 6
      //   472: ifnull +8 -> 480
      //   475: aload 6
      //   477: invokevirtual 108	java/io/InputStream:close	()V
      //   480: aload 5
      //   482: ifnull +8 -> 490
      //   485: aload 5
      //   487: invokevirtual 135	java/io/FileOutputStream:close	()V
      //   490: aload_1
      //   491: athrow
      //   492: astore_1
      //   493: goto -246 -> 247
      //   496: astore_1
      //   497: goto -238 -> 259
      //   500: astore_1
      //   501: goto -210 -> 291
      //   504: astore_1
      //   505: goto -202 -> 303
      //   508: astore_1
      //   509: goto -128 -> 381
      //   512: astore_1
      //   513: goto -122 -> 391
      //   516: astore_1
      //   517: goto -71 -> 446
      //   520: astore 4
      //   522: goto -42 -> 480
      //   525: astore 4
      //   527: goto -37 -> 490
      //   530: astore_1
      //   531: aload 7
      //   533: astore 5
      //   535: aload 4
      //   537: astore 6
      //   539: goto -69 -> 470
      //   542: astore 4
      //   544: aload 6
      //   546: astore 5
      //   548: aload_1
      //   549: astore 6
      //   551: aload 4
      //   553: astore_1
      //   554: goto -84 -> 470
      //   557: astore 7
      //   559: aload 8
      //   561: astore 6
      //   563: aload 5
      //   565: astore_1
      //   566: goto -156 -> 410
      //   569: astore 7
      //   571: goto -161 -> 410
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	574	0	this	ImportScriptFromUrlTask
      //   0	574	1	paramVarArgs	String[]
      //   13	385	2	i	int
      //   217	14	3	j	int
      //   25	442	4	localObject1	Object
      //   520	1	4	localException1	Exception
      //   525	11	4	localException2	Exception
      //   542	10	4	localObject2	Object
      //   29	535	5	localObject3	Object
      //   4	558	6	localObject4	Object
      //   18	1	7	localObject5	Object
      //   401	131	7	localException3	Exception
      //   557	1	7	localException4	Exception
      //   569	1	7	localException5	Exception
      //   21	539	8	localObject6	Object
      //   7	357	9	arrayOfByte	byte[]
      //   10	394	10	localHttpURLConnection	java.net.HttpURLConnection
      //   15	377	11	localFile	File
      //   1	122	12	localObject7	Object
      //   93	14	13	str	String
      //   41	114	14	localURL	URL
      // Exception table:
      //   from	to	target	type
      //   212	218	261	java/lang/Exception
      //   224	230	261	java/lang/Exception
      //   31	43	401	java/lang/Exception
      //   51	59	401	java/lang/Exception
      //   67	73	401	java/lang/Exception
      //   81	95	401	java/lang/Exception
      //   106	115	401	java/lang/Exception
      //   126	146	401	java/lang/Exception
      //   451	456	458	java/lang/Exception
      //   31	43	461	finally
      //   51	59	461	finally
      //   67	73	461	finally
      //   81	95	461	finally
      //   106	115	461	finally
      //   126	146	461	finally
      //   417	426	461	finally
      //   433	438	461	finally
      //   243	247	492	java/lang/Exception
      //   251	259	496	java/lang/Exception
      //   287	291	500	java/lang/Exception
      //   295	303	504	java/lang/Exception
      //   377	381	508	java/lang/Exception
      //   386	391	512	java/lang/Exception
      //   442	446	516	java/lang/Exception
      //   475	480	520	java/lang/Exception
      //   485	490	525	java/lang/Exception
      //   154	164	530	finally
      //   172	181	530	finally
      //   189	195	530	finally
      //   203	208	530	finally
      //   212	218	530	finally
      //   224	230	530	finally
      //   270	276	530	finally
      //   311	319	530	finally
      //   325	331	530	finally
      //   337	344	530	finally
      //   350	361	530	finally
      //   361	373	542	finally
      //   154	164	557	java/lang/Exception
      //   172	181	557	java/lang/Exception
      //   189	195	557	java/lang/Exception
      //   203	208	557	java/lang/Exception
      //   270	276	557	java/lang/Exception
      //   311	319	557	java/lang/Exception
      //   325	331	557	java/lang/Exception
      //   337	344	557	java/lang/Exception
      //   350	361	557	java/lang/Exception
      //   361	373	569	java/lang/Exception
    }
  }
  
  private abstract class ImportScriptTask<I>
    extends AsyncTask<I, Void, File>
  {
    private ImportScriptTask() {}
    
    protected void onPostExecute(File paramFile)
    {
      if (paramFile == null)
      {
        Toast.makeText(ManageScriptsActivity.this, R.string.manage_patches_import_error, 0).show();
        return;
      }
      try
      {
        ScriptManager.setEnabled(paramFile, false);
        ManageScriptsActivity.this.getMaxPatchCount();
        if (Utils.hasTooManyScripts()) {
          Toast.makeText(ManageScriptsActivity.this, R.string.script_import_too_many, 0).show();
        }
        for (;;)
        {
          ManageScriptsActivity.this.findScripts();
          return;
          ScriptManager.setEnabled(paramFile, true);
        }
      }
      catch (Exception paramFile)
      {
        for (;;)
        {
          paramFile.printStackTrace();
          ManageScriptsActivity.this.reportError(paramFile);
          Toast.makeText(ManageScriptsActivity.this, R.string.manage_patches_import_error, 0).show();
        }
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\ui\ManageScriptsActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */