package org.simpleframework.xml.stream;

final class ProviderFactory
{
  public static Provider getInstance()
  {
    try
    {
      StreamProvider localStreamProvider = new StreamProvider();
      return localStreamProvider;
    }
    catch (Throwable localThrowable1)
    {
      try
      {
        PullProvider localPullProvider = new PullProvider();
        return localPullProvider;
      }
      catch (Throwable localThrowable2) {}
    }
    return new DocumentProvider();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\stream\ProviderFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */