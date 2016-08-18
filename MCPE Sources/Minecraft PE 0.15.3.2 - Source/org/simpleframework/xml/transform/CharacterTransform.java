package org.simpleframework.xml.transform;

class CharacterTransform
  implements Transform<Character>
{
  public Character read(String paramString)
    throws Exception
  {
    if (paramString.length() != 1) {
      throw new InvalidFormatException("Cannot convert '%s' to a character", new Object[] { paramString });
    }
    return Character.valueOf(paramString.charAt(0));
  }
  
  public String write(Character paramCharacter)
    throws Exception
  {
    return paramCharacter.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\transform\CharacterTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */