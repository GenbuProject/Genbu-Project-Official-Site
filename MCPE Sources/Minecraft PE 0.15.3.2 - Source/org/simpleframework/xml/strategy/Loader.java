package org.simpleframework.xml.strategy;

class Loader
{
  private static ClassLoader getCallerClassLoader()
    throws Exception
  {
    return Loader.class.getClassLoader();
  }
  
  private static ClassLoader getClassLoader()
    throws Exception
  {
    return Thread.currentThread().getContextClassLoader();
  }
  
  public Class load(String paramString)
    throws Exception
  {
    ClassLoader localClassLoader2 = getClassLoader();
    ClassLoader localClassLoader1 = localClassLoader2;
    if (localClassLoader2 == null) {
      localClassLoader1 = getCallerClassLoader();
    }
    return localClassLoader1.loadClass(paramString);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\strategy\Loader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */