package org.simpleframework.xml;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

public abstract interface Serializer
{
  public abstract <T> T read(Class<? extends T> paramClass, File paramFile)
    throws Exception;
  
  public abstract <T> T read(Class<? extends T> paramClass, File paramFile, boolean paramBoolean)
    throws Exception;
  
  public abstract <T> T read(Class<? extends T> paramClass, InputStream paramInputStream)
    throws Exception;
  
  public abstract <T> T read(Class<? extends T> paramClass, InputStream paramInputStream, boolean paramBoolean)
    throws Exception;
  
  public abstract <T> T read(Class<? extends T> paramClass, Reader paramReader)
    throws Exception;
  
  public abstract <T> T read(Class<? extends T> paramClass, Reader paramReader, boolean paramBoolean)
    throws Exception;
  
  public abstract <T> T read(Class<? extends T> paramClass, String paramString)
    throws Exception;
  
  public abstract <T> T read(Class<? extends T> paramClass, String paramString, boolean paramBoolean)
    throws Exception;
  
  public abstract <T> T read(Class<? extends T> paramClass, InputNode paramInputNode)
    throws Exception;
  
  public abstract <T> T read(Class<? extends T> paramClass, InputNode paramInputNode, boolean paramBoolean)
    throws Exception;
  
  public abstract <T> T read(T paramT, File paramFile)
    throws Exception;
  
  public abstract <T> T read(T paramT, File paramFile, boolean paramBoolean)
    throws Exception;
  
  public abstract <T> T read(T paramT, InputStream paramInputStream)
    throws Exception;
  
  public abstract <T> T read(T paramT, InputStream paramInputStream, boolean paramBoolean)
    throws Exception;
  
  public abstract <T> T read(T paramT, Reader paramReader)
    throws Exception;
  
  public abstract <T> T read(T paramT, Reader paramReader, boolean paramBoolean)
    throws Exception;
  
  public abstract <T> T read(T paramT, String paramString)
    throws Exception;
  
  public abstract <T> T read(T paramT, String paramString, boolean paramBoolean)
    throws Exception;
  
  public abstract <T> T read(T paramT, InputNode paramInputNode)
    throws Exception;
  
  public abstract <T> T read(T paramT, InputNode paramInputNode, boolean paramBoolean)
    throws Exception;
  
  public abstract boolean validate(Class paramClass, File paramFile)
    throws Exception;
  
  public abstract boolean validate(Class paramClass, File paramFile, boolean paramBoolean)
    throws Exception;
  
  public abstract boolean validate(Class paramClass, InputStream paramInputStream)
    throws Exception;
  
  public abstract boolean validate(Class paramClass, InputStream paramInputStream, boolean paramBoolean)
    throws Exception;
  
  public abstract boolean validate(Class paramClass, Reader paramReader)
    throws Exception;
  
  public abstract boolean validate(Class paramClass, Reader paramReader, boolean paramBoolean)
    throws Exception;
  
  public abstract boolean validate(Class paramClass, String paramString)
    throws Exception;
  
  public abstract boolean validate(Class paramClass, String paramString, boolean paramBoolean)
    throws Exception;
  
  public abstract boolean validate(Class paramClass, InputNode paramInputNode)
    throws Exception;
  
  public abstract boolean validate(Class paramClass, InputNode paramInputNode, boolean paramBoolean)
    throws Exception;
  
  public abstract void write(Object paramObject, File paramFile)
    throws Exception;
  
  public abstract void write(Object paramObject, OutputStream paramOutputStream)
    throws Exception;
  
  public abstract void write(Object paramObject, Writer paramWriter)
    throws Exception;
  
  public abstract void write(Object paramObject, OutputNode paramOutputNode)
    throws Exception;
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\Serializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */