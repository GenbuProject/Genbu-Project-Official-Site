package org.simpleframework.xml.transform;

import java.io.File;

class FileTransform
  implements Transform<File>
{
  public File read(String paramString)
  {
    return new File(paramString);
  }
  
  public String write(File paramFile)
  {
    return paramFile.getPath();
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\transform\FileTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */