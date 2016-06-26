package org.mozilla.javascript;

public class DefiningClassLoader
  extends ClassLoader
  implements GeneratedClassLoader
{
  private final ClassLoader parentLoader;
  
  public DefiningClassLoader()
  {
    this.parentLoader = getClass().getClassLoader();
  }
  
  public DefiningClassLoader(ClassLoader paramClassLoader)
  {
    this.parentLoader = paramClassLoader;
  }
  
  public Class<?> defineClass(String paramString, byte[] paramArrayOfByte)
  {
    return super.defineClass(paramString, paramArrayOfByte, 0, paramArrayOfByte.length, SecurityUtilities.getProtectionDomain(getClass()));
  }
  
  public void linkClass(Class<?> paramClass)
  {
    resolveClass(paramClass);
  }
  
  public Class<?> loadClass(String paramString, boolean paramBoolean)
    throws ClassNotFoundException
  {
    Class localClass2 = findLoadedClass(paramString);
    Class localClass1 = localClass2;
    if (localClass2 == null) {
      if (this.parentLoader == null) {
        break label42;
      }
    }
    label42:
    for (localClass1 = this.parentLoader.loadClass(paramString);; localClass1 = findSystemClass(paramString))
    {
      if (paramBoolean) {
        resolveClass(localClass1);
      }
      return localClass1;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\DefiningClassLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */