package net.zhuoweizhang.mcpelauncher.api.modpe;

import java.util.HashMap;
import java.util.Map;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSStaticFunction;

public class RendererManager
{
  public static void defineClasses(Scriptable paramScriptable)
    throws Exception
  {
    ScriptableObject.defineClass(paramScriptable, NativeRendererApi.class);
  }
  
  private static native int nativeCreateHumanoidRenderer();
  
  public static native int nativeCreateItemSpriteRenderer(int paramInt);
  
  private static native void nativeModelAddBox(int paramInt1, String paramString, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt2, int paramInt3, int paramInt4, float paramFloat4, int paramInt5, int paramInt6, boolean paramBoolean, float paramFloat5, float paramFloat6);
  
  private static native void nativeModelClear(int paramInt, String paramString);
  
  private static native boolean nativeModelPartExists(int paramInt, String paramString);
  
  public static native void nativeModelSetRotationPoint(int paramInt, String paramString, float paramFloat1, float paramFloat2, float paramFloat3);
  
  public static class NativeModel
  {
    private final int rendererId;
    
    private NativeModel(int paramInt)
    {
      this.rendererId = paramInt;
    }
    
    public RendererManager.NativeModelPart getPart(String paramString)
    {
      if (!RendererManager.nativeModelPartExists(this.rendererId, paramString)) {
        throw new RuntimeException("The model part " + paramString + " does not exist.");
      }
      return new RendererManager.NativeModelPart(this.rendererId, paramString, null);
    }
  }
  
  public static class NativeModelPart
  {
    private String modelPartName;
    private int rendererId;
    private float textureHeight = 32.0F;
    private float textureWidth = 64.0F;
    private int textureX;
    private int textureY;
    private boolean transparent;
    
    private NativeModelPart(int paramInt, String paramString)
    {
      this.rendererId = paramInt;
      this.modelPartName = paramString;
    }
    
    public void addBox(float paramFloat1, float paramFloat2, float paramFloat3, int paramInt1, int paramInt2, int paramInt3)
    {
      addBox(paramFloat1, paramFloat2, paramFloat3, paramInt1, paramInt2, paramInt3, 0.0F);
    }
    
    public void addBox(float paramFloat1, float paramFloat2, float paramFloat3, int paramInt1, int paramInt2, int paramInt3, float paramFloat4)
    {
      RendererManager.nativeModelAddBox(this.rendererId, this.modelPartName, paramFloat1, paramFloat2, paramFloat3, paramInt1, paramInt2, paramInt3, paramFloat4, this.textureX, this.textureY, this.transparent, this.textureWidth, this.textureHeight);
    }
    
    public NativeModelPart clear()
    {
      RendererManager.nativeModelClear(this.rendererId, this.modelPartName);
      return this;
    }
    
    public NativeModelPart setRotationPoint(float paramFloat1, float paramFloat2, float paramFloat3)
    {
      RendererManager.nativeModelSetRotationPoint(this.rendererId, this.modelPartName, paramFloat1, paramFloat2, paramFloat3);
      return this;
    }
    
    public NativeModelPart setTextureOffset(int paramInt1, int paramInt2)
    {
      return setTextureOffset(paramInt1, paramInt2, false);
    }
    
    public NativeModelPart setTextureOffset(int paramInt1, int paramInt2, boolean paramBoolean)
    {
      this.textureX = paramInt1;
      this.textureY = paramInt2;
      this.transparent = paramBoolean;
      return this;
    }
    
    public NativeModelPart setTextureSize(float paramFloat1, float paramFloat2)
    {
      this.textureWidth = paramFloat1;
      this.textureHeight = paramFloat2;
      return this;
    }
  }
  
  public static class NativeRenderer
  {
    private String name = null;
    private final int rendererId;
    
    public NativeRenderer(int paramInt)
    {
      this.rendererId = paramInt;
    }
    
    public RendererManager.NativeModel getModel()
    {
      return new RendererManager.NativeModel(this.rendererId, null);
    }
    
    public String getName()
    {
      return this.name;
    }
    
    public int getRenderType()
    {
      return this.rendererId;
    }
    
    public void setName(String paramString)
    {
      if (paramString.indexOf(".") == -1) {
        throw new RuntimeException("Renderer name must be in format of author.modname.name; for example, coolmcpemodder.sz.SwagYolo");
      }
      this.name = paramString;
      RendererManager.NativeRendererApi.register(paramString, this);
    }
  }
  
  public static class NativeRendererApi
    extends ScriptableObject
  {
    public static Map<Integer, RendererManager.NativeRenderer> renderersById = new HashMap();
    public static Map<String, RendererManager.NativeRenderer> renderersByName = new HashMap();
    
    @JSStaticFunction
    public static RendererManager.NativeRenderer createHumanoidRenderer()
    {
      return new RendererManager.NativeRenderer(RendererManager.access$000());
    }
    
    @JSStaticFunction
    public static RendererManager.NativeRenderer get(String paramString)
    {
      try
      {
        RendererManager.NativeRenderer localNativeRenderer = new RendererManager.NativeRenderer(Integer.parseInt(paramString));
        return localNativeRenderer;
      }
      catch (NumberFormatException localNumberFormatException) {}
      return (RendererManager.NativeRenderer)renderersByName.get(paramString);
    }
    
    public static RendererManager.NativeRenderer getById(int paramInt)
    {
      return (RendererManager.NativeRenderer)renderersById.get(Integer.valueOf(paramInt));
    }
    
    public static RendererManager.NativeRenderer getByName(String paramString)
    {
      return (RendererManager.NativeRenderer)renderersByName.get(paramString);
    }
    
    public static void register(String paramString, RendererManager.NativeRenderer paramNativeRenderer)
    {
      renderersByName.put(paramString, paramNativeRenderer);
      renderersById.put(Integer.valueOf(paramNativeRenderer.getRenderType()), paramNativeRenderer);
    }
    
    public String getClassName()
    {
      return "Renderer";
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\api\modpe\RendererManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */