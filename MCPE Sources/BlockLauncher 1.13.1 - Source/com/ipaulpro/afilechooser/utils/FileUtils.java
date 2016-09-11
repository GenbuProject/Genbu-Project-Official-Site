package com.ipaulpro.afilechooser.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore.Audio.Media;
import android.provider.MediaStore.Images.Thumbnails;
import android.provider.MediaStore.Video.Media;
import android.provider.MediaStore.Video.Thumbnails;
import android.util.Log;
import com.ipaulpro.afilechooser.R.xml;
import java.io.File;
import java.io.FileFilter;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FileUtils
{
  private static final boolean DEBUG = false;
  public static final String EXTRA_MIME_TYPES = "net.zhuoweizhang.afilechooser.extra.MIME_TYPES";
  public static final String EXTRA_SORT_METHOD = "net.zhuoweizhang.afilechooser.extra.SORT_METHOD";
  private static final String HIDDEN_PREFIX = ".";
  public static final String MIME_TYPE_APP = "application/*";
  public static final String MIME_TYPE_AUDIO = "audio/*";
  public static final String MIME_TYPE_IMAGE = "image/*";
  public static final String MIME_TYPE_TEXT = "text/*";
  public static final String MIME_TYPE_VIDEO = "video/*";
  public static final String SORT_LAST_MODIFIED = "net.zhuoweizhang.afilechooser.extra.SORT_LAST_MODIFIED";
  static final String TAG = "FileUtils";
  private static Comparator<File> mComparator = new Comparator()
  {
    public int compare(File paramAnonymousFile1, File paramAnonymousFile2)
    {
      return paramAnonymousFile1.getName().toLowerCase().compareTo(paramAnonymousFile2.getName().toLowerCase());
    }
  };
  private static FileFilter mDirFilter = new FileFilter()
  {
    public boolean accept(File paramAnonymousFile)
    {
      String str = paramAnonymousFile.getName();
      return (paramAnonymousFile.isDirectory()) && (!str.startsWith("."));
    }
  };
  private static FileFilter mFileFilter = new FileFilter()
  {
    public boolean accept(File paramAnonymousFile)
    {
      String str = paramAnonymousFile.getName();
      return (paramAnonymousFile.isFile()) && (!str.startsWith("."));
    }
  };
  
  public static Intent createGetContentIntent()
  {
    Intent localIntent = new Intent("android.intent.action.GET_CONTENT");
    localIntent.setType("*/*");
    localIntent.addCategory("android.intent.category.OPENABLE");
    return localIntent;
  }
  
  public static String getExtension(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    int i = paramString.lastIndexOf(".");
    if (i >= 0) {
      return paramString.substring(i);
    }
    return "";
  }
  
  public static File getFile(Uri paramUri)
  {
    if (paramUri != null)
    {
      paramUri = paramUri.getPath();
      if (paramUri != null) {
        return new File(paramUri);
      }
    }
    return null;
  }
  
  public static File getFile(File paramFile, String paramString)
  {
    return getFile(paramFile.getAbsolutePath(), paramString);
  }
  
  public static File getFile(String paramString1, String paramString2)
  {
    String str = "/";
    if (paramString1.endsWith("/")) {
      str = "";
    }
    return new File(paramString1 + str + paramString2);
  }
  
  public static List<File> getFileList(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    paramString = new File(paramString);
    File[] arrayOfFile = paramString.listFiles(mDirFilter);
    int j;
    int i;
    if (arrayOfFile != null)
    {
      Arrays.sort(arrayOfFile, mComparator);
      j = arrayOfFile.length;
      i = 0;
      while (i < j)
      {
        localArrayList.add(arrayOfFile[i]);
        i += 1;
      }
    }
    paramString = paramString.listFiles(mFileFilter);
    if (paramString != null)
    {
      Arrays.sort(paramString, mComparator);
      j = paramString.length;
      i = 0;
      while (i < j)
      {
        localArrayList.add(paramString[i]);
        i += 1;
      }
    }
    return localArrayList;
  }
  
  public static String getMimeType(Context paramContext, File paramFile)
  {
    Object localObject = null;
    MimeTypes localMimeTypes = getMimeTypes(paramContext);
    paramContext = (Context)localObject;
    if (paramFile != null) {
      paramContext = localMimeTypes.getMimeType(paramFile.getName());
    }
    return paramContext;
  }
  
  private static MimeTypes getMimeTypes(Context paramContext)
  {
    MimeTypeParser localMimeTypeParser = new MimeTypeParser();
    paramContext = paramContext.getResources().getXml(R.xml.mimetypes);
    try
    {
      paramContext = localMimeTypeParser.fromXmlResource(paramContext);
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static String getPath(Context paramContext, Uri paramUri)
    throws URISyntaxException
  {
    if ("content".equalsIgnoreCase(paramUri.getScheme())) {}
    try
    {
      paramContext = paramContext.getContentResolver().query(paramUri, new String[] { "_data" }, null, null, null);
      int i = paramContext.getColumnIndexOrThrow("_data");
      if (!paramContext.moveToFirst()) {
        break label79;
      }
      paramContext = paramContext.getString(i);
      return paramContext;
    }
    catch (Exception paramContext) {}
    if ("file".equalsIgnoreCase(paramUri.getScheme())) {
      return paramUri.getPath();
    }
    label79:
    return null;
  }
  
  public static File getPathWithoutFilename(File paramFile)
  {
    if (paramFile != null)
    {
      if (paramFile.isDirectory()) {
        return paramFile;
      }
      String str = paramFile.getName();
      paramFile = paramFile.getAbsolutePath();
      str = paramFile.substring(0, paramFile.length() - str.length());
      paramFile = str;
      if (str.endsWith("/")) {
        paramFile = str.substring(0, str.length() - 1);
      }
      return new File(paramFile);
    }
    return null;
  }
  
  public static String getReadableFileSize(int paramInt)
  {
    DecimalFormat localDecimalFormat = new DecimalFormat("###.#");
    float f1 = 0.0F;
    String str2 = " KB";
    String str1 = str2;
    if (paramInt > 1024)
    {
      float f2 = paramInt / 1024;
      f1 = f2;
      str1 = str2;
      if (f2 > 1024.0F)
      {
        f1 = f2 / 1024.0F;
        if (f1 <= 1024.0F) {
          break label94;
        }
        f1 /= 1024.0F;
      }
    }
    label94:
    for (str1 = " GB";; str1 = " MB") {
      return String.valueOf(localDecimalFormat.format(f1) + str1);
    }
  }
  
  public static Bitmap getThumbnail(Context paramContext, Uri paramUri)
  {
    return getThumbnail(paramContext, paramUri, getMimeType(paramContext, getFile(paramUri)));
  }
  
  public static Bitmap getThumbnail(Context paramContext, Uri paramUri, String paramString)
  {
    Object localObject1 = null;
    if (isMediaUri(paramUri)) {
      Log.e("FileUtils", "You can only retrieve thumbnails for images and videos.");
    }
    for (;;)
    {
      return (Bitmap)localObject1;
      Object localObject3 = null;
      Object localObject2 = null;
      localObject1 = localObject2;
      if (paramUri == null) {
        continue;
      }
      ContentResolver localContentResolver = paramContext.getContentResolver();
      localObject1 = null;
      Uri localUri = null;
      try
      {
        paramUri = localContentResolver.query(paramUri, null, null, null, null);
        paramContext = (Context)localObject3;
        localUri = paramUri;
        localObject1 = paramUri;
        int i;
        if (paramUri.moveToFirst())
        {
          localUri = paramUri;
          localObject1 = paramUri;
          i = paramUri.getInt(0);
          localUri = paramUri;
          localObject1 = paramUri;
          if (!paramString.contains("video")) {
            break label138;
          }
          localUri = paramUri;
          localObject1 = paramUri;
          paramContext = MediaStore.Video.Thumbnails.getThumbnail(localContentResolver, i, 1, null);
        }
        for (;;)
        {
          localObject1 = paramContext;
          if (paramUri == null) {
            break;
          }
          paramUri.close();
          return paramContext;
          label138:
          paramContext = (Context)localObject3;
          localUri = paramUri;
          localObject1 = paramUri;
          if (paramString.contains("image/*"))
          {
            localUri = paramUri;
            localObject1 = paramUri;
            paramContext = MediaStore.Images.Thumbnails.getThumbnail(localContentResolver, i, 1, null);
          }
        }
      }
      catch (Exception paramContext)
      {
        localObject1 = localObject2;
        if (localUri == null) {
          continue;
        }
        localUri.close();
        return null;
      }
      finally
      {
        if (localObject1 != null) {
          ((Cursor)localObject1).close();
        }
      }
    }
  }
  
  public static Bitmap getThumbnail(Context paramContext, File paramFile)
  {
    return getThumbnail(paramContext, getUri(paramFile), getMimeType(paramContext, paramFile));
  }
  
  public static Uri getUri(File paramFile)
  {
    if (paramFile != null) {
      return Uri.fromFile(paramFile);
    }
    return null;
  }
  
  public static boolean isLocal(String paramString)
  {
    return (paramString != null) && (!paramString.startsWith("http://"));
  }
  
  public static boolean isMediaUri(Uri paramUri)
  {
    paramUri = paramUri.toString();
    return (paramUri.startsWith(MediaStore.Audio.Media.INTERNAL_CONTENT_URI.toString())) || (paramUri.startsWith(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI.toString())) || (paramUri.startsWith(MediaStore.Video.Media.INTERNAL_CONTENT_URI.toString())) || (paramUri.startsWith(MediaStore.Video.Media.EXTERNAL_CONTENT_URI.toString()));
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\ipaulpro\afilechooser\utils\FileUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */