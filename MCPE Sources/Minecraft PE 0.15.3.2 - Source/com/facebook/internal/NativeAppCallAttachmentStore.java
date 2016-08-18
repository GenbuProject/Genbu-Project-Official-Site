package com.facebook.internal;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.util.Log;
import com.facebook.FacebookContentProvider;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public final class NativeAppCallAttachmentStore
{
  static final String ATTACHMENTS_DIR_NAME = "com.facebook.NativeAppCallAttachmentStore.files";
  private static final String TAG = NativeAppCallAttachmentStore.class.getName();
  private static File attachmentsDirectory;
  
  public static void addAttachments(Collection<Attachment> paramCollection)
  {
    if ((paramCollection == null) || (paramCollection.size() == 0)) {}
    for (;;)
    {
      return;
      if (attachmentsDirectory == null) {
        cleanupAllAttachments();
      }
      ensureAttachmentsDirectoryExists();
      Object localObject1 = new ArrayList();
      try
      {
        paramCollection = paramCollection.iterator();
        while (paramCollection.hasNext())
        {
          localObject2 = (Attachment)paramCollection.next();
          if (((Attachment)localObject2).shouldCreateFile)
          {
            localFile = getAttachmentFile(((Attachment)localObject2).callId, ((Attachment)localObject2).attachmentName, true);
            ((List)localObject1).add(localFile);
            if (((Attachment)localObject2).bitmap == null) {
              break label172;
            }
            processAttachmentBitmap(((Attachment)localObject2).bitmap, localFile);
          }
        }
      }
      catch (IOException paramCollection)
      {
        for (;;)
        {
          Object localObject2;
          File localFile;
          Log.e(TAG, "Got unexpected exception:" + paramCollection);
          localObject1 = ((List)localObject1).iterator();
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = (File)((Iterator)localObject1).next();
            try
            {
              ((File)localObject2).delete();
            }
            catch (Exception localException) {}
          }
          label172:
          if (localException.originalUri != null) {
            processAttachmentFile(localException.originalUri, localException.isContentUri, localFile);
          }
        }
        throw new FacebookException(paramCollection);
      }
    }
  }
  
  public static void cleanupAllAttachments()
  {
    Utility.deleteDirectory(getAttachmentsDirectory());
  }
  
  public static void cleanupAttachmentsForCall(UUID paramUUID)
  {
    paramUUID = getAttachmentsDirectoryForCall(paramUUID, false);
    if (paramUUID != null) {
      Utility.deleteDirectory(paramUUID);
    }
  }
  
  public static Attachment createAttachment(UUID paramUUID, Bitmap paramBitmap)
  {
    Validate.notNull(paramUUID, "callId");
    Validate.notNull(paramBitmap, "attachmentBitmap");
    return new Attachment(paramUUID, paramBitmap, null, null);
  }
  
  public static Attachment createAttachment(UUID paramUUID, Uri paramUri)
  {
    Validate.notNull(paramUUID, "callId");
    Validate.notNull(paramUri, "attachmentUri");
    return new Attachment(paramUUID, null, paramUri, null);
  }
  
  static File ensureAttachmentsDirectoryExists()
  {
    File localFile = getAttachmentsDirectory();
    localFile.mkdirs();
    return localFile;
  }
  
  static File getAttachmentFile(UUID paramUUID, String paramString, boolean paramBoolean)
    throws IOException
  {
    paramUUID = getAttachmentsDirectoryForCall(paramUUID, paramBoolean);
    if (paramUUID == null) {
      return null;
    }
    try
    {
      paramUUID = new File(paramUUID, URLEncoder.encode(paramString, "UTF-8"));
      return paramUUID;
    }
    catch (UnsupportedEncodingException paramUUID) {}
    return null;
  }
  
  static File getAttachmentsDirectory()
  {
    try
    {
      if (attachmentsDirectory == null) {
        attachmentsDirectory = new File(FacebookSdk.getApplicationContext().getCacheDir(), "com.facebook.NativeAppCallAttachmentStore.files");
      }
      File localFile = attachmentsDirectory;
      return localFile;
    }
    finally {}
  }
  
  static File getAttachmentsDirectoryForCall(UUID paramUUID, boolean paramBoolean)
  {
    if (attachmentsDirectory == null) {
      paramUUID = null;
    }
    File localFile;
    do
    {
      do
      {
        return paramUUID;
        localFile = new File(attachmentsDirectory, paramUUID.toString());
        paramUUID = localFile;
      } while (!paramBoolean);
      paramUUID = localFile;
    } while (localFile.exists());
    localFile.mkdirs();
    return localFile;
  }
  
  public static File openAttachment(UUID paramUUID, String paramString)
    throws FileNotFoundException
  {
    if ((Utility.isNullOrEmpty(paramString)) || (paramUUID == null)) {
      throw new FileNotFoundException();
    }
    try
    {
      paramUUID = getAttachmentFile(paramUUID, paramString, false);
      return paramUUID;
    }
    catch (IOException paramUUID)
    {
      throw new FileNotFoundException();
    }
  }
  
  private static void processAttachmentBitmap(Bitmap paramBitmap, File paramFile)
    throws IOException
  {
    paramFile = new FileOutputStream(paramFile);
    try
    {
      paramBitmap.compress(Bitmap.CompressFormat.JPEG, 100, paramFile);
      return;
    }
    finally
    {
      Utility.closeQuietly(paramFile);
    }
  }
  
  private static void processAttachmentFile(Uri paramUri, boolean paramBoolean, File paramFile)
    throws IOException
  {
    paramFile = new FileOutputStream(paramFile);
    if (!paramBoolean) {}
    for (;;)
    {
      try
      {
        paramUri = new FileInputStream(paramUri.getPath());
        Utility.copyAndCloseInputStream(paramUri, paramFile);
        return;
      }
      finally
      {
        Utility.closeQuietly(paramFile);
      }
      paramUri = FacebookSdk.getApplicationContext().getContentResolver().openInputStream(paramUri);
    }
  }
  
  public static final class Attachment
  {
    private final String attachmentName;
    private final String attachmentUrl;
    private Bitmap bitmap;
    private final UUID callId;
    private boolean isContentUri;
    private Uri originalUri;
    private boolean shouldCreateFile;
    
    private Attachment(UUID paramUUID, Bitmap paramBitmap, Uri paramUri)
    {
      this.callId = paramUUID;
      this.bitmap = paramBitmap;
      this.originalUri = paramUri;
      if (paramUri != null)
      {
        paramBitmap = paramUri.getScheme();
        if ("content".equalsIgnoreCase(paramBitmap))
        {
          this.isContentUri = true;
          if ((paramUri.getAuthority() != null) && (!paramUri.getAuthority().startsWith("media")))
          {
            this.shouldCreateFile = bool;
            label70:
            if (this.shouldCreateFile) {
              break label187;
            }
            paramBitmap = null;
            label79:
            this.attachmentName = paramBitmap;
            if (this.shouldCreateFile) {
              break label197;
            }
          }
        }
      }
      label187:
      label197:
      for (paramUUID = this.originalUri.toString();; paramUUID = FacebookContentProvider.getAttachmentUrl(FacebookSdk.getApplicationId(), paramUUID, this.attachmentName))
      {
        this.attachmentUrl = paramUUID;
        return;
        bool = false;
        break;
        if ("file".equalsIgnoreCase(paramUri.getScheme()))
        {
          this.shouldCreateFile = true;
          break label70;
        }
        if (Utility.isWebUri(paramUri)) {
          break label70;
        }
        throw new FacebookException("Unsupported scheme for media Uri : " + paramBitmap);
        if (paramBitmap != null)
        {
          this.shouldCreateFile = true;
          break label70;
        }
        throw new FacebookException("Cannot share media without a bitmap or Uri set");
        paramBitmap = UUID.randomUUID().toString();
        break label79;
      }
    }
    
    public String getAttachmentUrl()
    {
      return this.attachmentUrl;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\internal\NativeAppCallAttachmentStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */