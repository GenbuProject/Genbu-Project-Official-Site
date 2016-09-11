package com.google.android.gms.vision.face;

import android.graphics.PointF;
import java.util.Arrays;
import java.util.List;

public class Face
{
  public static final float UNCOMPUTED_PROBABILITY = -1.0F;
  private int mId;
  private float zzaTm;
  private float zzaTn;
  private float zzbnA;
  private float zzbnB;
  private List<Landmark> zzbnC;
  private float zzbnD;
  private float zzbnE;
  private float zzbnF;
  private PointF zzbnz;
  
  public Face(int paramInt, PointF paramPointF, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, Landmark[] paramArrayOfLandmark, float paramFloat5, float paramFloat6, float paramFloat7)
  {
    this.mId = paramInt;
    this.zzbnz = paramPointF;
    this.zzaTm = paramFloat1;
    this.zzaTn = paramFloat2;
    this.zzbnA = paramFloat3;
    this.zzbnB = paramFloat4;
    this.zzbnC = Arrays.asList(paramArrayOfLandmark);
    if ((paramFloat5 >= 0.0F) && (paramFloat5 <= 1.0F))
    {
      this.zzbnD = paramFloat5;
      if ((paramFloat6 < 0.0F) || (paramFloat6 > 1.0F)) {
        break label120;
      }
    }
    label120:
    for (this.zzbnE = paramFloat6;; this.zzbnE = -1.0F)
    {
      if ((this.zzbnF < 0.0F) || (this.zzbnF > 1.0F)) {
        break label129;
      }
      this.zzbnF = paramFloat7;
      return;
      this.zzbnD = -1.0F;
      break;
    }
    label129:
    this.zzbnF = -1.0F;
  }
  
  public float getEulerY()
  {
    return this.zzbnA;
  }
  
  public float getEulerZ()
  {
    return this.zzbnB;
  }
  
  public float getHeight()
  {
    return this.zzaTn;
  }
  
  public int getId()
  {
    return this.mId;
  }
  
  public float getIsLeftEyeOpenProbability()
  {
    return this.zzbnD;
  }
  
  public float getIsRightEyeOpenProbability()
  {
    return this.zzbnE;
  }
  
  public float getIsSmilingProbability()
  {
    return this.zzbnF;
  }
  
  public List<Landmark> getLandmarks()
  {
    return this.zzbnC;
  }
  
  public PointF getPosition()
  {
    return new PointF(this.zzbnz.x - this.zzaTm / 2.0F, this.zzbnz.y - this.zzaTn / 2.0F);
  }
  
  public float getWidth()
  {
    return this.zzaTm;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\vision\face\Face.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */