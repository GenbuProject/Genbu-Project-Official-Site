package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zzb;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.fitness.HistoryApi;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DailyTotalRequest;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.fitness.request.DataInsertRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.DataUpdateRequest;
import com.google.android.gms.fitness.result.DailyTotalResult;
import com.google.android.gms.fitness.result.DataReadResult;
import java.util.List;

public class zzpc
  implements HistoryApi
{
  private PendingResult<Status> zza(GoogleApiClient paramGoogleApiClient, final DataSet paramDataSet, final boolean paramBoolean)
  {
    zzx.zzb(paramDataSet, "Must set the data set");
    if (!paramDataSet.getDataPoints().isEmpty()) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zza(bool, "Cannot use an empty data set");
      zzx.zzb(paramDataSet.getDataSource().zzum(), "Must set the app package name for the data source");
      paramGoogleApiClient.zza(new zzob.zzc(paramGoogleApiClient)
      {
        protected void zza(zzob paramAnonymouszzob)
          throws RemoteException
        {
          zzph localzzph = new zzph(this);
          ((zzom)paramAnonymouszzob.zzqJ()).zza(new DataInsertRequest(paramDataSet, localzzph, paramBoolean));
        }
      });
    }
  }
  
  public PendingResult<Status> deleteData(GoogleApiClient paramGoogleApiClient, final DataDeleteRequest paramDataDeleteRequest)
  {
    paramGoogleApiClient.zza(new zzob.zzc(paramGoogleApiClient)
    {
      protected void zza(zzob paramAnonymouszzob)
        throws RemoteException
      {
        zzph localzzph = new zzph(this);
        ((zzom)paramAnonymouszzob.zzqJ()).zza(new DataDeleteRequest(paramDataDeleteRequest, localzzph));
      }
    });
  }
  
  public PendingResult<Status> insertData(GoogleApiClient paramGoogleApiClient, DataSet paramDataSet)
  {
    return zza(paramGoogleApiClient, paramDataSet, false);
  }
  
  public PendingResult<DailyTotalResult> readDailyTotal(GoogleApiClient paramGoogleApiClient, final DataType paramDataType)
  {
    paramGoogleApiClient.zza(new zzob.zza(paramGoogleApiClient)
    {
      protected DailyTotalResult zzL(Status paramAnonymousStatus)
      {
        return DailyTotalResult.zza(paramAnonymousStatus, paramDataType);
      }
      
      protected void zza(zzob paramAnonymouszzob)
        throws RemoteException
      {
        DailyTotalRequest localDailyTotalRequest = new DailyTotalRequest(new zzog.zza()
        {
          public void zza(DailyTotalResult paramAnonymous2DailyTotalResult)
            throws RemoteException
          {
            zzpc.5.this.zza(paramAnonymous2DailyTotalResult);
          }
        }, paramDataType);
        ((zzom)paramAnonymouszzob.zzqJ()).zza(localDailyTotalRequest);
      }
    });
  }
  
  public PendingResult<DataReadResult> readData(GoogleApiClient paramGoogleApiClient, final DataReadRequest paramDataReadRequest)
  {
    paramGoogleApiClient.zza(new zzob.zza(paramGoogleApiClient)
    {
      protected DataReadResult zzK(Status paramAnonymousStatus)
      {
        return DataReadResult.zza(paramAnonymousStatus, paramDataReadRequest);
      }
      
      protected void zza(zzob paramAnonymouszzob)
        throws RemoteException
      {
        zzpc.zza localzza = new zzpc.zza(this, null);
        ((zzom)paramAnonymouszzob.zzqJ()).zza(new DataReadRequest(paramDataReadRequest, localzza));
      }
    });
  }
  
  public PendingResult<Status> updateData(GoogleApiClient paramGoogleApiClient, final DataUpdateRequest paramDataUpdateRequest)
  {
    zzx.zzb(paramDataUpdateRequest.getDataSet(), "Must set the data set");
    zzx.zza(paramDataUpdateRequest.zzlO(), "Must set a non-zero value for startTimeMillis/startTime");
    zzx.zza(paramDataUpdateRequest.zzud(), "Must set a non-zero value for endTimeMillis/endTime");
    paramGoogleApiClient.zza(new zzob.zzc(paramGoogleApiClient)
    {
      protected void zza(zzob paramAnonymouszzob)
        throws RemoteException
      {
        zzph localzzph = new zzph(this);
        ((zzom)paramAnonymouszzob.zzqJ()).zza(new DataUpdateRequest(paramDataUpdateRequest, localzzph));
      }
    });
  }
  
  private static class zza
    extends zzoh.zza
  {
    private int zzaAc = 0;
    private DataReadResult zzaAd = null;
    private final zza.zzb<DataReadResult> zzamC;
    
    private zza(zza.zzb<DataReadResult> paramzzb)
    {
      this.zzamC = paramzzb;
    }
    
    /* Error */
    public void zza(DataReadResult paramDataReadResult)
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: ldc 34
      //   4: iconst_2
      //   5: invokestatic 40	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
      //   8: ifeq +31 -> 39
      //   11: ldc 34
      //   13: new 42	java/lang/StringBuilder
      //   16: dup
      //   17: invokespecial 43	java/lang/StringBuilder:<init>	()V
      //   20: ldc 45
      //   22: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   25: aload_0
      //   26: getfield 21	com/google/android/gms/internal/zzpc$zza:zzaAc	I
      //   29: invokevirtual 52	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   32: invokevirtual 56	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   35: invokestatic 60	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
      //   38: pop
      //   39: aload_0
      //   40: getfield 23	com/google/android/gms/internal/zzpc$zza:zzaAd	Lcom/google/android/gms/fitness/result/DataReadResult;
      //   43: ifnonnull +48 -> 91
      //   46: aload_0
      //   47: aload_1
      //   48: putfield 23	com/google/android/gms/internal/zzpc$zza:zzaAd	Lcom/google/android/gms/fitness/result/DataReadResult;
      //   51: aload_0
      //   52: aload_0
      //   53: getfield 21	com/google/android/gms/internal/zzpc$zza:zzaAc	I
      //   56: iconst_1
      //   57: iadd
      //   58: putfield 21	com/google/android/gms/internal/zzpc$zza:zzaAc	I
      //   61: aload_0
      //   62: getfield 21	com/google/android/gms/internal/zzpc$zza:zzaAc	I
      //   65: aload_0
      //   66: getfield 23	com/google/android/gms/internal/zzpc$zza:zzaAd	Lcom/google/android/gms/fitness/result/DataReadResult;
      //   69: invokevirtual 66	com/google/android/gms/fitness/result/DataReadResult:zzvj	()I
      //   72: if_icmpne +16 -> 88
      //   75: aload_0
      //   76: getfield 25	com/google/android/gms/internal/zzpc$zza:zzamC	Lcom/google/android/gms/common/api/internal/zza$zzb;
      //   79: aload_0
      //   80: getfield 23	com/google/android/gms/internal/zzpc$zza:zzaAd	Lcom/google/android/gms/fitness/result/DataReadResult;
      //   83: invokeinterface 72 2 0
      //   88: aload_0
      //   89: monitorexit
      //   90: return
      //   91: aload_0
      //   92: getfield 23	com/google/android/gms/internal/zzpc$zza:zzaAd	Lcom/google/android/gms/fitness/result/DataReadResult;
      //   95: aload_1
      //   96: invokevirtual 75	com/google/android/gms/fitness/result/DataReadResult:zzb	(Lcom/google/android/gms/fitness/result/DataReadResult;)V
      //   99: goto -48 -> 51
      //   102: astore_1
      //   103: aload_0
      //   104: monitorexit
      //   105: aload_1
      //   106: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	107	0	this	zza
      //   0	107	1	paramDataReadResult	DataReadResult
      // Exception table:
      //   from	to	target	type
      //   2	39	102	finally
      //   39	51	102	finally
      //   51	88	102	finally
      //   88	90	102	finally
      //   91	99	102	finally
      //   103	105	102	finally
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzpc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */