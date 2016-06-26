package org.mozilla.javascript;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;

public class SecurityUtilities
{
  public static ProtectionDomain getProtectionDomain(Class<?> paramClass)
  {
    (ProtectionDomain)AccessController.doPrivileged(new PrivilegedAction()
    {
      public ProtectionDomain run()
      {
        return this.val$clazz.getProtectionDomain();
      }
    });
  }
  
  public static ProtectionDomain getScriptProtectionDomain()
  {
    SecurityManager localSecurityManager = System.getSecurityManager();
    if ((localSecurityManager instanceof RhinoSecurityManager)) {
      (ProtectionDomain)AccessController.doPrivileged(new PrivilegedAction()
      {
        public ProtectionDomain run()
        {
          Class localClass = ((RhinoSecurityManager)this.val$securityManager).getCurrentScriptClass();
          if (localClass == null) {
            return null;
          }
          return localClass.getProtectionDomain();
        }
      });
    }
    return null;
  }
  
  public static String getSystemProperty(String paramString)
  {
    (String)AccessController.doPrivileged(new PrivilegedAction()
    {
      public String run()
      {
        return System.getProperty(this.val$name);
      }
    });
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\SecurityUtilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */