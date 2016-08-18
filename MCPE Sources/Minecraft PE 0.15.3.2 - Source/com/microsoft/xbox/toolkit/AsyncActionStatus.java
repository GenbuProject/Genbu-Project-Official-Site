package com.microsoft.xbox.toolkit;

public enum AsyncActionStatus
{
  private static final AsyncActionStatus[][] MERGE_MATRIX;
  
  static
  {
    FAIL = new AsyncActionStatus("FAIL", 1);
    NO_CHANGE = new AsyncActionStatus("NO_CHANGE", 2);
    NO_OP_SUCCESS = new AsyncActionStatus("NO_OP_SUCCESS", 3);
    NO_OP_FAIL = new AsyncActionStatus("NO_OP_FAIL", 4);
    $VALUES = new AsyncActionStatus[] { SUCCESS, FAIL, NO_CHANGE, NO_OP_SUCCESS, NO_OP_FAIL };
    AsyncActionStatus localAsyncActionStatus1 = SUCCESS;
    AsyncActionStatus localAsyncActionStatus2 = FAIL;
    AsyncActionStatus localAsyncActionStatus3 = SUCCESS;
    AsyncActionStatus localAsyncActionStatus4 = SUCCESS;
    AsyncActionStatus localAsyncActionStatus5 = FAIL;
    AsyncActionStatus localAsyncActionStatus6 = FAIL;
    AsyncActionStatus localAsyncActionStatus7 = FAIL;
    AsyncActionStatus localAsyncActionStatus8 = FAIL;
    AsyncActionStatus localAsyncActionStatus9 = FAIL;
    AsyncActionStatus localAsyncActionStatus10 = FAIL;
    AsyncActionStatus localAsyncActionStatus11 = SUCCESS;
    AsyncActionStatus localAsyncActionStatus12 = FAIL;
    AsyncActionStatus localAsyncActionStatus13 = NO_CHANGE;
    AsyncActionStatus localAsyncActionStatus14 = NO_OP_SUCCESS;
    AsyncActionStatus localAsyncActionStatus15 = NO_OP_FAIL;
    AsyncActionStatus localAsyncActionStatus16 = SUCCESS;
    AsyncActionStatus localAsyncActionStatus17 = FAIL;
    AsyncActionStatus localAsyncActionStatus18 = NO_OP_SUCCESS;
    AsyncActionStatus localAsyncActionStatus19 = NO_OP_SUCCESS;
    AsyncActionStatus localAsyncActionStatus20 = NO_OP_FAIL;
    AsyncActionStatus localAsyncActionStatus21 = FAIL;
    AsyncActionStatus localAsyncActionStatus22 = FAIL;
    AsyncActionStatus localAsyncActionStatus23 = NO_OP_FAIL;
    AsyncActionStatus localAsyncActionStatus24 = NO_OP_FAIL;
    AsyncActionStatus localAsyncActionStatus25 = NO_OP_FAIL;
    MERGE_MATRIX = new AsyncActionStatus[][] { { localAsyncActionStatus1, localAsyncActionStatus2, localAsyncActionStatus3, localAsyncActionStatus4, localAsyncActionStatus5 }, { localAsyncActionStatus6, localAsyncActionStatus7, localAsyncActionStatus8, localAsyncActionStatus9, localAsyncActionStatus10 }, { localAsyncActionStatus11, localAsyncActionStatus12, localAsyncActionStatus13, localAsyncActionStatus14, localAsyncActionStatus15 }, { localAsyncActionStatus16, localAsyncActionStatus17, localAsyncActionStatus18, localAsyncActionStatus19, localAsyncActionStatus20 }, { localAsyncActionStatus21, localAsyncActionStatus22, localAsyncActionStatus23, localAsyncActionStatus24, localAsyncActionStatus25 } };
  }
  
  private AsyncActionStatus() {}
  
  public static boolean getIsFail(AsyncActionStatus paramAsyncActionStatus)
  {
    return (paramAsyncActionStatus == FAIL) || (paramAsyncActionStatus == NO_OP_FAIL);
  }
  
  public static AsyncActionStatus merge(AsyncActionStatus paramAsyncActionStatus, AsyncActionStatus... paramVarArgs)
  {
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      AsyncActionStatus localAsyncActionStatus = paramVarArgs[i];
      paramAsyncActionStatus = MERGE_MATRIX[paramAsyncActionStatus.ordinal()][localAsyncActionStatus.ordinal()];
      i += 1;
    }
    return paramAsyncActionStatus;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\AsyncActionStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */