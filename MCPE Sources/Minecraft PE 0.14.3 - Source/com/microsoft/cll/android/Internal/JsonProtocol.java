package com.microsoft.cll.android.Internal;

import android.util.Base64;
import com.microsoft.bond.BondBlob;
import com.microsoft.bond.BondDataType;
import com.microsoft.bond.BondSerializable;
import com.microsoft.bond.Metadata;
import com.microsoft.bond.ProtocolCapability;
import com.microsoft.bond.ProtocolWriter;
import java.io.IOException;
import java.util.Stack;

public class JsonProtocol
  extends ProtocolWriter
{
  private static final char ESCAPE_CHAR = '\\';
  private static final char[] HEX_CHARACTERS = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
  private static final String NUMERIC_ESCAPE_STRING = "\\u";
  private static final Stack<Boolean> inContainerStack;
  private static final Stack<BondDataType> keyTypes = new Stack();
  private static final Stack<BondDataType> valueTypes = new Stack();
  private final Stack<Boolean> containerIsTyped = new Stack();
  private boolean inContainer;
  private boolean isKey;
  private final StringBuilder stringBuilder;
  
  static
  {
    inContainerStack = new Stack();
  }
  
  public JsonProtocol(StringBuilder paramStringBuilder)
  {
    this.stringBuilder = paramStringBuilder;
  }
  
  private void actuallyWriteString(String paramString)
  {
    if (paramString == null)
    {
      appendEscaped("null");
      appendInContainer();
      return;
    }
    this.stringBuilder.append('"');
    appendEscaped(paramString);
    this.stringBuilder.append('"');
    appendInContainer();
  }
  
  private void appendComma()
  {
    if ((this.stringBuilder.length() > 0) && (this.stringBuilder.charAt(this.stringBuilder.length() - 1) != ',')) {
      this.stringBuilder.append(',');
    }
  }
  
  private void appendEscaped(String paramString)
  {
    int i = this.stringBuilder.length();
    this.stringBuilder.append(paramString);
    int j = this.stringBuilder.length();
    if (i < j)
    {
      char c = this.stringBuilder.charAt(i);
      int k;
      switch (c)
      {
      default: 
        if (Character.isISOControl(c))
        {
          paramString = this.stringBuilder;
          k = i + 1;
          paramString.insert(i, "\\u");
          paramString = this.stringBuilder;
          i = k + 1;
          paramString.setCharAt(k, HEX_CHARACTERS[(c >> '\f' & 0xF)]);
          paramString = this.stringBuilder;
          k = i + 1;
          paramString.insert(i, HEX_CHARACTERS[(c >> '\b' & 0xF)]);
          paramString = this.stringBuilder;
          int m = k + 1;
          paramString.insert(k, HEX_CHARACTERS[(c >> '\004' & 0xF)]);
          paramString = this.stringBuilder;
          i = m + 1;
          paramString.insert(m, HEX_CHARACTERS[(c & 0xF)]);
          j += 5;
        }
        break;
      }
      for (;;)
      {
        break;
        this.stringBuilder.insert(i, '\\');
        i += 2;
        j += 1;
        continue;
        paramString = this.stringBuilder;
        k = i + 1;
        paramString.insert(i, '\\');
        this.stringBuilder.setCharAt(k, 'n');
        j += 1;
        i = k + 1;
        continue;
        paramString = this.stringBuilder;
        k = i + 1;
        paramString.insert(i, '\\');
        this.stringBuilder.setCharAt(k, 'r');
        j += 1;
        i = k + 1;
        continue;
        paramString = this.stringBuilder;
        k = i + 1;
        paramString.insert(i, '\\');
        this.stringBuilder.setCharAt(k, 't');
        j += 1;
        i = k + 1;
        continue;
        paramString = this.stringBuilder;
        k = i + 1;
        paramString.insert(i, '\\');
        this.stringBuilder.setCharAt(k, '"');
        j += 1;
        i = k + 1;
        continue;
        i += 1;
      }
    }
  }
  
  private void appendInContainer()
  {
    if (this.inContainer) {
      appendComma();
    }
  }
  
  private void removeLastComma()
  {
    if ((this.stringBuilder.length() > 0) && (this.stringBuilder.charAt(this.stringBuilder.length() - 1) == ',')) {
      this.stringBuilder.deleteCharAt(this.stringBuilder.length() - 1);
    }
  }
  
  private void writeJsonFieldName(String paramString)
  {
    this.stringBuilder.append("\"");
    appendEscaped(paramString);
    this.stringBuilder.append("\":");
  }
  
  public boolean hasCapability(ProtocolCapability paramProtocolCapability)
  {
    if (paramProtocolCapability == ProtocolCapability.CAN_OMIT_FIELDS) {
      return true;
    }
    return super.hasCapability(paramProtocolCapability);
  }
  
  public String toString()
  {
    return this.stringBuilder.toString();
  }
  
  public void writeBegin() {}
  
  public void writeBlob(BondBlob paramBondBlob)
    throws IOException
  {
    this.stringBuilder.append(Base64.encode(paramBondBlob.getBuffer(), 0));
    appendInContainer();
  }
  
  public void writeBool(boolean paramBoolean)
    throws IOException
  {
    this.stringBuilder.append(paramBoolean);
    appendInContainer();
  }
  
  public void writeContainerBegin(int paramInt, BondDataType paramBondDataType)
    throws IOException
  {
    this.stringBuilder.append('[');
    this.containerIsTyped.push(Boolean.TRUE);
    inContainerStack.push(Boolean.valueOf(true));
  }
  
  public void writeContainerBegin(int paramInt, BondDataType paramBondDataType1, BondDataType paramBondDataType2)
    throws IOException
  {
    this.stringBuilder.append('{');
    this.containerIsTyped.push(Boolean.FALSE);
    this.inContainer = true;
    this.isKey = true;
    keyTypes.push(paramBondDataType1);
    valueTypes.push(paramBondDataType2);
    inContainerStack.push(Boolean.valueOf(true));
  }
  
  public void writeContainerEnd()
    throws IOException
  {
    removeLastComma();
    StringBuilder localStringBuilder = this.stringBuilder;
    if (((Boolean)this.containerIsTyped.pop()).booleanValue()) {}
    for (char c = ']';; c = '}')
    {
      localStringBuilder.append(c);
      this.inContainer = false;
      this.isKey = false;
      keyTypes.pop();
      inContainerStack.pop();
      return;
    }
  }
  
  public void writeDouble(double paramDouble)
    throws IOException
  {
    this.stringBuilder.append(paramDouble);
    appendInContainer();
  }
  
  public void writeEnd() {}
  
  public void writeFieldBegin(BondDataType paramBondDataType, int paramInt, BondSerializable paramBondSerializable)
    throws IOException
  {
    if ((paramBondSerializable instanceof Metadata)) {}
    for (paramBondDataType = (Metadata)paramBondSerializable;; paramBondDataType = null)
    {
      if (paramBondDataType != null) {
        writeJsonFieldName(paramBondDataType.getName());
      }
      return;
    }
  }
  
  public void writeFieldEnd()
  {
    appendComma();
  }
  
  public void writeFieldOmitted(BondDataType paramBondDataType, int paramInt, BondSerializable paramBondSerializable)
    throws IOException
  {}
  
  public void writeFloat(float paramFloat)
    throws IOException
  {
    this.stringBuilder.append(paramFloat);
    appendInContainer();
  }
  
  public void writeInt16(short paramShort)
    throws IOException
  {
    this.stringBuilder.append(paramShort);
    appendInContainer();
  }
  
  public void writeInt32(int paramInt)
    throws IOException
  {
    this.stringBuilder.append(paramInt);
    appendInContainer();
  }
  
  public void writeInt64(long paramLong)
    throws IOException
  {
    this.stringBuilder.append(paramLong);
    appendInContainer();
  }
  
  public void writeInt8(byte paramByte)
    throws IOException
  {
    this.stringBuilder.append(paramByte);
    appendInContainer();
  }
  
  public void writeString(String paramString)
    throws IOException
  {
    if ((((Boolean)inContainerStack.peek()).booleanValue()) && (!keyTypes.empty()) && (keyTypes.peek() == BondDataType.BT_STRING))
    {
      if (this.isKey)
      {
        writeJsonFieldName(paramString);
        if (valueTypes.peek() == BondDataType.BT_STRING) {
          if (this.isKey) {
            break label90;
          }
        }
      }
      label90:
      for (boolean bool = true;; bool = false)
      {
        this.isKey = bool;
        return;
        if (this.isKey) {
          break;
        }
        actuallyWriteString(paramString);
        break;
      }
    }
    actuallyWriteString(paramString);
  }
  
  public void writeStructBegin(BondSerializable paramBondSerializable, boolean paramBoolean)
  {
    if (!paramBoolean) {
      this.stringBuilder.append('{');
    }
    inContainerStack.push(Boolean.valueOf(false));
  }
  
  public void writeStructEnd(boolean paramBoolean)
  {
    if (!paramBoolean)
    {
      removeLastComma();
      this.stringBuilder.append('}');
      if (inContainerStack.size() > 1) {
        appendComma();
      }
    }
    inContainerStack.pop();
  }
  
  public void writeUInt16(short paramShort)
    throws IOException
  {
    this.stringBuilder.append(paramShort);
    appendInContainer();
  }
  
  public void writeUInt32(int paramInt)
    throws IOException
  {
    this.stringBuilder.append(paramInt);
    appendInContainer();
  }
  
  public void writeUInt64(long paramLong)
    throws IOException
  {
    this.stringBuilder.append(paramLong);
    appendInContainer();
  }
  
  public void writeUInt8(byte paramByte)
    throws IOException
  {
    this.stringBuilder.append(paramByte);
    appendInContainer();
  }
  
  public void writeVersion()
    throws IOException
  {}
  
  public void writeWString(String paramString)
    throws IOException
  {
    writeString(paramString);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\cll\android\Internal\JsonProtocol.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */