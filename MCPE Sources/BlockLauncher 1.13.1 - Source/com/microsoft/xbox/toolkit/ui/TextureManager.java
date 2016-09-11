package com.microsoft.xbox.toolkit.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import com.microsoft.xbox.toolkit.BackgroundThreadWaitor;
import com.microsoft.xbox.toolkit.MemoryMonitor;
import com.microsoft.xbox.toolkit.MultiMap;
import com.microsoft.xbox.toolkit.StreamUtil;
import com.microsoft.xbox.toolkit.ThreadManager;
import com.microsoft.xbox.toolkit.ThreadSafePriorityQueue;
import com.microsoft.xbox.toolkit.TimeMonitor;
import com.microsoft.xbox.toolkit.XLEAssert;
import com.microsoft.xbox.toolkit.XLEFileCache;
import com.microsoft.xbox.toolkit.XLEFileCacheManager;
import com.microsoft.xbox.toolkit.XLEMemoryCache;
import com.microsoft.xbox.toolkit.XLEThread;
import com.microsoft.xbox.toolkit.network.AbstractXLEHttpClient;
import com.microsoft.xbox.toolkit.network.HttpClientFactory;
import com.microsoft.xbox.toolkit.network.XLEHttpStatusAndStream;
import com.microsoft.xbox.toolkit.network.XLEThreadPool;
import com.microsoft.xboxtcui.R.id;
import com.microsoft.xboxtcui.XboxTcuiSdk;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.apache.http.client.methods.HttpGet;

public class TextureManager
{
  private static final int ANIM_TIME = 100;
  private static final int BITMAP_CACHE_MAX_FILE_SIZE_IN_BYTES = 5242880;
  private static final String BMP_FILE_CACHE_DIR_NAME = "texture";
  private static final int BMP_FILE_CACHE_SIZE = 2000;
  private static final int DECODE_THREAD_WAIT_TIMEOUT_MS = 3000;
  private static final int TEXTURE_TIMEOUT_MS = 15000;
  private static final long TIME_TO_RETRY_MS = 300000L;
  public static TextureManager instance = new TextureManager();
  private XLEMemoryCache<TextureManagerScaledNetworkBitmapRequest, XLEBitmap> bitmapCache = new XLEMemoryCache(1048576 * Math.min(getNetworkBitmapCacheSizeInMB(), 50), 5242880);
  private XLEFileCache bitmapFileCache = XLEFileCacheManager.createCache("texture", 2000);
  private Thread decodeThread = null;
  private HashSet<TextureManagerScaledNetworkBitmapRequest> inProgress = new HashSet();
  private Object listLock = new Object();
  private HashMap<TextureManagerScaledResourceBitmapRequest, XLEBitmap> resourceBitmapCache = new HashMap();
  private TimeMonitor stopwatch = new TimeMonitor();
  private HashMap<TextureManagerScaledNetworkBitmapRequest, RetryEntry> timeToRetryCache = new HashMap();
  private ThreadSafePriorityQueue<TextureManagerDownloadRequest> toDecode = new ThreadSafePriorityQueue();
  private MultiMap<TextureManagerScaledNetworkBitmapRequest, ImageView> waitingForImage = new MultiMap();
  
  public TextureManager()
  {
    this.stopwatch.start();
    this.decodeThread = new XLEThread(new TextureManagerDecodeThread(null), "XLETextureDecodeThread");
    this.decodeThread.setDaemon(true);
    this.decodeThread.setPriority(4);
    this.decodeThread.start();
  }
  
  public static TextureManager Instance()
  {
    return instance;
  }
  
  private void bindToViewInternal(String paramString, ImageView paramImageView, TextureBindingOption paramTextureBindingOption)
  {
    TextureManagerScaledNetworkBitmapRequest localTextureManagerScaledNetworkBitmapRequest = new TextureManagerScaledNetworkBitmapRequest(paramString, paramTextureBindingOption);
    XLEBitmap localXLEBitmap = null;
    for (;;)
    {
      synchronized (this.listLock)
      {
        if (this.waitingForImage.containsValue(paramImageView)) {
          this.waitingForImage.removeValue(paramImageView);
        }
        if (!invalidUrl(paramString))
        {
          localXLEBitmap = (XLEBitmap)this.bitmapCache.get(localTextureManagerScaledNetworkBitmapRequest);
          if (localXLEBitmap != null) {
            break label284;
          }
          Object localObject1 = (RetryEntry)this.timeToRetryCache.get(localTextureManagerScaledNetworkBitmapRequest);
          if (localObject1 == null) {
            break label278;
          }
          if (((RetryEntry)localObject1).isExpired())
          {
            i = 1;
            localObject1 = localXLEBitmap;
            if (i != 0)
            {
              if (paramTextureBindingOption.resourceIdForLoading != -1)
              {
                localXLEBitmap = loadResource(paramTextureBindingOption.resourceIdForLoading);
                if (localXLEBitmap == null) {
                  break label272;
                }
                bool = true;
                XLEAssert.assertTrue(bool);
              }
              this.waitingForImage.put(localTextureManagerScaledNetworkBitmapRequest, paramImageView);
              localObject1 = localXLEBitmap;
              if (!this.inProgress.contains(localTextureManagerScaledNetworkBitmapRequest))
              {
                this.inProgress.add(localTextureManagerScaledNetworkBitmapRequest);
                load(localTextureManagerScaledNetworkBitmapRequest);
                localObject1 = localXLEBitmap;
              }
            }
            setImage(paramImageView, (XLEBitmap)localObject1);
            if ((paramImageView instanceof XLEImageView)) {
              ((XLEImageView)paramImageView).TEST_loadingOrLoadedImageUrl = paramString;
            }
            return;
          }
          if (paramTextureBindingOption.resourceIdForError == -1) {
            break label290;
          }
          localXLEBitmap = loadResource(paramTextureBindingOption.resourceIdForError);
          break label290;
        }
        if (paramTextureBindingOption.resourceIdForError != -1)
        {
          localXLEBitmap = loadResource(paramTextureBindingOption.resourceIdForError);
          XLEAssert.assertNotNull(localXLEBitmap);
          i = 0;
        }
      }
      int i = 0;
      continue;
      label272:
      boolean bool = false;
      continue;
      label278:
      i = 1;
      continue;
      label284:
      i = 0;
      continue;
      label290:
      i = 0;
    }
  }
  
  private BitmapFactory.Options computeInSampleSizeOptions(int paramInt1, int paramInt2, BitmapFactory.Options paramOptions)
  {
    int j = 1;
    boolean bool = true;
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    int i = j;
    if (validResizeDimention(paramInt1, paramInt2))
    {
      i = j;
      if (paramOptions.outWidth > paramInt1)
      {
        i = j;
        if (paramOptions.outHeight > paramInt2)
        {
          i = (int)Math.pow(2.0D, Math.min((int)Math.floor(Math.log(paramOptions.outWidth / paramInt1) / Math.log(2.0D)), (int)Math.floor(Math.log(paramOptions.outHeight / paramInt2) / Math.log(2.0D))));
          if (i < 1) {
            break label131;
          }
        }
      }
    }
    for (;;)
    {
      XLEAssert.assertTrue(bool);
      localOptions.inSampleSize = i;
      return localOptions;
      label131:
      bool = false;
    }
  }
  
  private XLEBitmap createScaledBitmap(XLEBitmap paramXLEBitmap, int paramInt1, int paramInt2)
  {
    XLEBitmap localXLEBitmap = paramXLEBitmap;
    float f;
    if (validResizeDimention(paramInt1, paramInt2))
    {
      localXLEBitmap = paramXLEBitmap;
      if (paramXLEBitmap.getBitmap() != null)
      {
        f = paramXLEBitmap.getBitmap().getHeight() / paramXLEBitmap.getBitmap().getWidth();
        if (paramInt2 / paramInt1 >= f) {
          break label74;
        }
        paramInt1 = Math.max(1, (int)(paramInt2 / f));
      }
    }
    for (;;)
    {
      localXLEBitmap = XLEBitmap.createScaledBitmap8888(paramXLEBitmap, paramInt1, paramInt2, true);
      return localXLEBitmap;
      label74:
      paramInt2 = Math.max(1, (int)(f * paramInt1));
    }
  }
  
  private void drainWaitingForImage(TextureManagerScaledNetworkBitmapRequest paramTextureManagerScaledNetworkBitmapRequest, XLEBitmap paramXLEBitmap)
  {
    if (this.waitingForImage.containsKey(paramTextureManagerScaledNetworkBitmapRequest))
    {
      Iterator localIterator = this.waitingForImage.get(paramTextureManagerScaledNetworkBitmapRequest).iterator();
      while (localIterator.hasNext())
      {
        ImageView localImageView = (ImageView)localIterator.next();
        if (localImageView != null) {
          if ((localImageView instanceof XLEImageView)) {
            setXLEImageView(paramTextureManagerScaledNetworkBitmapRequest, (XLEImageView)localImageView, paramXLEBitmap);
          } else {
            setView(paramTextureManagerScaledNetworkBitmapRequest, localImageView, paramXLEBitmap);
          }
        }
      }
    }
  }
  
  private int getNetworkBitmapCacheSizeInMB()
  {
    return Math.max(0, MemoryMonitor.instance().getMemoryClass() - 64) / 2 + 12;
  }
  
  private static boolean invalidUrl(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }
  
  private void load(TextureManagerScaledNetworkBitmapRequest paramTextureManagerScaledNetworkBitmapRequest)
  {
    if (invalidUrl(paramTextureManagerScaledNetworkBitmapRequest.url)) {
      return;
    }
    paramTextureManagerScaledNetworkBitmapRequest = new TextureManagerDownloadThreadWorker(new TextureManagerDownloadRequest(paramTextureManagerScaledNetworkBitmapRequest));
    XLEThreadPool.textureThreadPool.run(paramTextureManagerScaledNetworkBitmapRequest);
  }
  
  private void setImage(ImageView paramImageView, XLEBitmap paramXLEBitmap)
  {
    if (paramXLEBitmap == null) {}
    for (paramXLEBitmap = null;; paramXLEBitmap = paramXLEBitmap.getBitmap())
    {
      OnBitmapSetListener localOnBitmapSetListener = (OnBitmapSetListener)paramImageView.getTag(R.id.image_callback);
      if (localOnBitmapSetListener != null) {
        localOnBitmapSetListener.onBeforeImageSet(paramImageView, paramXLEBitmap);
      }
      paramImageView.setImageBitmap(paramXLEBitmap);
      paramImageView.setTag(R.id.image_bound, Boolean.valueOf(true));
      if (localOnBitmapSetListener != null) {
        localOnBitmapSetListener.onAfterImageSet(paramImageView, paramXLEBitmap);
      }
      return;
    }
  }
  
  private void setView(final TextureManagerScaledNetworkBitmapRequest paramTextureManagerScaledNetworkBitmapRequest, final ImageView paramImageView, final XLEBitmap paramXLEBitmap)
  {
    ThreadManager.UIThreadPost(new Runnable()
    {
      public void run()
      {
        boolean bool;
        if (Thread.currentThread() == ThreadManager.UIThread) {
          bool = true;
        }
        for (;;)
        {
          XLEAssert.assertTrue(bool);
          synchronized (TextureManager.this.listLock)
          {
            bool = TextureManager.this.waitingForImage.keyValueMatches(paramTextureManagerScaledNetworkBitmapRequest, paramImageView);
            if (bool) {
              TextureManager.this.setImage(paramImageView, paramXLEBitmap);
            }
          }
          synchronized (TextureManager.this.listLock)
          {
            TextureManager.this.waitingForImage.removeValue(paramImageView);
            return;
            bool = false;
            continue;
            localObject2 = finally;
            throw ((Throwable)localObject2);
          }
        }
      }
    });
  }
  
  private void setXLEImageView(final TextureManagerScaledNetworkBitmapRequest paramTextureManagerScaledNetworkBitmapRequest, final XLEImageView paramXLEImageView, final XLEBitmap paramXLEBitmap)
  {
    ThreadManager.UIThreadPost(new Runnable()
    {
      public void run()
      {
        boolean bool;
        if (Thread.currentThread() == ThreadManager.UIThread) {
          bool = true;
        }
        for (;;)
        {
          XLEAssert.assertTrue(bool);
          synchronized (TextureManager.this.listLock)
          {
            bool = TextureManager.this.waitingForImage.keyValueMatches(paramTextureManagerScaledNetworkBitmapRequest, paramXLEImageView);
            if (bool)
            {
              final float f = paramXLEImageView.getAlpha();
              if (!paramXLEImageView.getShouldAnimate()) {
                break label137;
              }
              paramXLEImageView.animate().alpha(0.0F).setDuration(100L).setListener(new AnimatorListenerAdapter()
              {
                public void onAnimationEnd(Animator paramAnonymous2Animator)
                {
                  TextureManager.2.this.val$view.setFinal(true);
                  TextureManager.this.setImage(TextureManager.2.this.val$view, TextureManager.2.this.val$bitmap);
                  TextureManager.2.this.val$view.animate().alpha(f).setDuration(100L).setListener(null);
                }
              });
            }
          }
          synchronized (TextureManager.this.listLock)
          {
            TextureManager.this.waitingForImage.removeValue(paramXLEImageView);
            return;
            bool = false;
            continue;
            localObject2 = finally;
            throw ((Throwable)localObject2);
            label137:
            TextureManager.this.setImage(paramXLEImageView, paramXLEBitmap);
          }
        }
      }
    });
  }
  
  private static boolean validResizeDimention(int paramInt1, int paramInt2)
  {
    if ((paramInt1 == 0) || (paramInt2 == 0)) {
      throw new UnsupportedOperationException();
    }
    return (paramInt1 > 0) && (paramInt2 > 0);
  }
  
  public void bindToView(int paramInt1, ImageView paramImageView, int paramInt2, int paramInt3)
  {
    bindToView(paramInt1, paramImageView, paramInt2, paramInt3, null);
  }
  
  public void bindToView(int paramInt1, ImageView paramImageView, int paramInt2, int paramInt3, OnBitmapSetListener paramOnBitmapSetListener)
  {
    boolean bool2 = false;
    if (Thread.currentThread() == ThreadManager.UIThread) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      XLEAssert.assertTrue(bool1);
      paramOnBitmapSetListener = loadResource(paramInt1);
      bool1 = bool2;
      if (paramOnBitmapSetListener != null) {
        bool1 = true;
      }
      XLEAssert.assertTrue(bool1);
      if ((paramImageView instanceof XLEImageView)) {
        ((XLEImageView)paramImageView).TEST_loadingOrLoadedImageUrl = Integer.toString(paramInt1);
      }
      setImage(paramImageView, paramOnBitmapSetListener);
      return;
    }
  }
  
  public void bindToView(URI paramURI, ImageView paramImageView, int paramInt1, int paramInt2)
  {
    if (Thread.currentThread() == ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      if ((paramInt1 != 0) && (paramInt2 != 0)) {
        break;
      }
      throw new UnsupportedOperationException();
    }
    if (paramURI == null) {}
    for (paramURI = null;; paramURI = paramURI.toString())
    {
      bindToViewInternal(paramURI, paramImageView, new TextureBindingOption(paramInt1, paramInt2));
      return;
    }
  }
  
  public void bindToView(URI paramURI, ImageView paramImageView, TextureBindingOption paramTextureBindingOption)
  {
    boolean bool;
    if (Thread.currentThread() == ThreadManager.UIThread)
    {
      bool = true;
      XLEAssert.assertTrue(bool);
      if (paramURI != null) {
        break label37;
      }
    }
    label37:
    for (paramURI = null;; paramURI = paramURI.toString())
    {
      bindToViewInternal(paramURI, paramImageView, paramTextureBindingOption);
      return;
      bool = false;
      break;
    }
  }
  
  public void bindToViewFromFile(String paramString, ImageView paramImageView, int paramInt1, int paramInt2)
  {
    if (Thread.currentThread() == ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      if ((paramInt1 != 0) && (paramInt2 != 0)) {
        break;
      }
      throw new UnsupportedOperationException();
    }
    bindToViewInternal(paramString, paramImageView, new TextureBindingOption(paramInt1, paramInt2));
  }
  
  public void bindToViewFromFile(String paramString, ImageView paramImageView, TextureBindingOption paramTextureBindingOption)
  {
    if (Thread.currentThread() == ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      bindToViewInternal(paramString, paramImageView, paramTextureBindingOption);
      return;
    }
  }
  
  public boolean isBusy()
  {
    for (;;)
    {
      synchronized (this.listLock)
      {
        if (!this.inProgress.isEmpty())
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  public XLEBitmap loadResource(int paramInt)
  {
    TextureManagerScaledResourceBitmapRequest localTextureManagerScaledResourceBitmapRequest = new TextureManagerScaledResourceBitmapRequest(paramInt);
    XLEBitmap localXLEBitmap = (XLEBitmap)this.resourceBitmapCache.get(localTextureManagerScaledResourceBitmapRequest);
    Object localObject = localXLEBitmap;
    if (localXLEBitmap == null)
    {
      localObject = new BitmapFactory.Options();
      ((BitmapFactory.Options)localObject).inJustDecodeBounds = true;
      BitmapFactory.decodeResource(XboxTcuiSdk.getResources(), localTextureManagerScaledResourceBitmapRequest.resourceId, (BitmapFactory.Options)localObject);
      localObject = XLEBitmap.decodeResource(XboxTcuiSdk.getResources(), localTextureManagerScaledResourceBitmapRequest.resourceId);
      this.resourceBitmapCache.put(localTextureManagerScaledResourceBitmapRequest, localObject);
    }
    XLEAssert.assertNotNull(localObject);
    return (XLEBitmap)localObject;
  }
  
  public XLEBitmap.XLEBitmapDrawable loadScaledResourceDrawable(int paramInt)
  {
    XLEBitmap localXLEBitmap = loadResource(paramInt);
    if (localXLEBitmap == null) {
      return null;
    }
    return localXLEBitmap.getDrawable();
  }
  
  public void logMemoryUsage() {}
  
  public void preload(int paramInt) {}
  
  public void preload(URI paramURI) {}
  
  public void preloadFromFile(String paramString) {}
  
  public void purgeResourceBitmapCache()
  {
    this.resourceBitmapCache.clear();
  }
  
  public void setCachingEnabled(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = getNetworkBitmapCacheSizeInMB();; i = 0)
    {
      this.bitmapCache = new XLEMemoryCache(i, 5242880);
      this.bitmapFileCache = XLEFileCacheManager.createCache("texture", 2000, paramBoolean);
      this.resourceBitmapCache = new HashMap();
      return;
    }
  }
  
  public void unsafeClearBitmapCache() {}
  
  private static class RetryEntry
  {
    private static final long SEC = 1000L;
    private static final long[] TIMES_MS = { 5000L, 9000L, 19000L, 37000L, 75000L, 150000L, 300000L };
    private int curIdx = 0;
    private long currStart = System.currentTimeMillis();
    
    public boolean isExpired()
    {
      return this.currStart + TIMES_MS[this.curIdx] < System.currentTimeMillis();
    }
    
    public void startNext()
    {
      if (this.curIdx < TIMES_MS.length - 1) {
        this.curIdx += 1;
      }
      this.currStart = System.currentTimeMillis();
    }
  }
  
  private class TextureManagerDecodeThread
    implements Runnable
  {
    private TextureManagerDecodeThread() {}
    
    public void run()
    {
      TextureManagerDownloadRequest localTextureManagerDownloadRequest = (TextureManagerDownloadRequest)TextureManager.this.toDecode.pop();
      if (localTextureManagerDownloadRequest.stream != null) {
        BackgroundThreadWaitor.getInstance().waitForReady(3000);
      }
      for (;;)
      {
        try
        {
          localObject1 = new ByteArrayOutputStream();
          StreamUtil.CopyStream((OutputStream)localObject1, localTextureManagerDownloadRequest.stream);
          localObject1 = ((ByteArrayOutputStream)localObject1).toByteArray();
          localObject3 = new BitmapFactory.Options();
          ((BitmapFactory.Options)localObject3).inJustDecodeBounds = true;
          BitmapFactory.decodeStream(new ByteArrayInputStream((byte[])localObject1), null, (BitmapFactory.Options)localObject3);
          localObject4 = TextureManager.this.computeInSampleSizeOptions(localTextureManagerDownloadRequest.key.bindingOption.width, localTextureManagerDownloadRequest.key.bindingOption.height, (BitmapFactory.Options)localObject3);
          int i = ((BitmapFactory.Options)localObject3).outWidth / ((BitmapFactory.Options)localObject4).inSampleSize;
          i = ((BitmapFactory.Options)localObject3).outHeight / ((BitmapFactory.Options)localObject4).inSampleSize;
          localObject3 = XLEBitmap.decodeStream(new ByteArrayInputStream((byte[])localObject1), (BitmapFactory.Options)localObject4);
          if ((localTextureManagerDownloadRequest.key.bindingOption.useFileCache) && (!TextureManager.this.bitmapFileCache.contains(localTextureManagerDownloadRequest.key))) {
            TextureManager.this.bitmapFileCache.save(localTextureManagerDownloadRequest.key, new ByteArrayInputStream((byte[])localObject1));
          }
          localObject1 = TextureManager.this.createScaledBitmap((XLEBitmap)localObject3, localTextureManagerDownloadRequest.key.bindingOption.width, localTextureManagerDownloadRequest.key.bindingOption.height);
        }
        catch (Exception localException)
        {
          Object localObject1;
          Object localObject3;
          localXLEBitmap = null;
          continue;
          if (localTextureManagerDownloadRequest.key.bindingOption.resourceIdForError == -1) {
            continue;
          }
          localXLEBitmap = TextureManager.this.loadResource(localTextureManagerDownloadRequest.key.bindingOption.resourceIdForError);
          Object localObject4 = (TextureManager.RetryEntry)TextureManager.this.timeToRetryCache.get(localTextureManagerDownloadRequest.key);
          if (localObject4 == null) {
            continue;
          }
          ((TextureManager.RetryEntry)localObject4).startNext();
          continue;
          TextureManager.this.timeToRetryCache.put(localTextureManagerDownloadRequest.key, new TextureManager.RetryEntry());
          continue;
        }
        BackgroundThreadWaitor.getInstance().waitForReady(3000);
        localObject3 = TextureManager.this.listLock;
        if (localObject1 != null) {
          try
          {
            TextureManager.this.bitmapCache.add(localTextureManagerDownloadRequest.key, localObject1, ((XLEBitmap)localObject1).getByteCount());
            TextureManager.this.timeToRetryCache.remove(localTextureManagerDownloadRequest.key);
            TextureManager.this.drainWaitingForImage(localTextureManagerDownloadRequest.key, (XLEBitmap)localObject1);
            TextureManager.this.inProgress.remove(localTextureManagerDownloadRequest.key);
            break;
          }
          finally {}
        }
        XLEBitmap localXLEBitmap = null;
      }
    }
  }
  
  private class TextureManagerDownloadThreadWorker
    implements Runnable
  {
    private TextureManagerDownloadRequest request;
    
    public TextureManagerDownloadThreadWorker(TextureManagerDownloadRequest paramTextureManagerDownloadRequest)
    {
      this.request = paramTextureManagerDownloadRequest;
    }
    
    private InputStream downloadFromAssets(String paramString)
    {
      try
      {
        paramString = XboxTcuiSdk.getAssetManager().open(paramString);
        return paramString;
      }
      catch (IOException paramString) {}
      return null;
    }
    
    private InputStream downloadFromWeb(String paramString)
    {
      Object localObject = null;
      try
      {
        paramString = new HttpGet(URI.create(paramString));
        XLEHttpStatusAndStream localXLEHttpStatusAndStream = HttpClientFactory.textureFactory.getHttpClient(15000).getHttpStatusAndStreamInternal(paramString, false);
        paramString = (String)localObject;
        if (localXLEHttpStatusAndStream.statusCode == 200) {
          paramString = localXLEHttpStatusAndStream.stream;
        }
        return paramString;
      }
      catch (Exception paramString) {}
      return null;
    }
    
    public void run()
    {
      boolean bool;
      if ((this.request.key != null) && (this.request.key.url != null)) {
        bool = true;
      }
      for (;;)
      {
        XLEAssert.assertTrue(bool);
        this.request.stream = null;
        try
        {
          if (this.request.key.url.startsWith("http")) {
            if (this.request.key.bindingOption.useFileCache)
            {
              this.request.stream = TextureManager.this.bitmapFileCache.getInputStreamForRead(this.request.key);
              if (this.request.stream == null) {
                this.request.stream = downloadFromWeb(this.request.key.url);
              }
            }
          }
          synchronized (TextureManager.this.listLock)
          {
            for (;;)
            {
              TextureManager.this.toDecode.push(this.request);
              return;
              bool = false;
              break;
              this.request.stream = downloadFromWeb(this.request.key.url);
            }
            this.request.stream = downloadFromAssets(this.request.key.url);
          }
        }
        catch (Exception localException)
        {
          for (;;) {}
        }
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\ui\TextureManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */