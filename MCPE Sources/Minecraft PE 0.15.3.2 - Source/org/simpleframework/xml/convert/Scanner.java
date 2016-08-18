package org.simpleframework.xml.convert;

import java.lang.annotation.Annotation;

abstract interface Scanner
{
  public abstract <T extends Annotation> T scan(Class<T> paramClass);
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\convert\Scanner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */