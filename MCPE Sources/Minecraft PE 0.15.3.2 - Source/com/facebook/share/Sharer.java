package com.facebook.share;

public abstract interface Sharer
{
  public abstract boolean getShouldFailOnDataError();
  
  public abstract void setShouldFailOnDataError(boolean paramBoolean);
  
  public static class Result
  {
    final String postId;
    
    public Result(String paramString)
    {
      this.postId = paramString;
    }
    
    public String getPostId()
    {
      return this.postId;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\share\Sharer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */