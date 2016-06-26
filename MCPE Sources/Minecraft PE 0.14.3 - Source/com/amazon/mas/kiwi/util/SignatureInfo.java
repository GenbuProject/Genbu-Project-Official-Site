package com.amazon.mas.kiwi.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SignatureInfo
{
  private static final String VALIDITY_REQUIREMENTS_FOR_CREATING_KEYSTORE = String.format("A valid instance of %s is required to have at least one non-blank ID, either developerId or certificateId, and at least one non-blank name, either developerName or commonName.", new Object[] { SignatureInfo.class.getSimpleName() });
  private static final String VALIDITY_REQUIREMENTS_FOR_RETRIEVING_KEYSTORE = String.format("A valid instance of %s is required to have a certificate ID.", new Object[] { SignatureInfo.class.getSimpleName() });
  private String certificateId;
  private String commonName;
  private String developerId;
  private String developerName;
  
  public static String getValidityRequirementsForCreatingKeystore()
  {
    return VALIDITY_REQUIREMENTS_FOR_CREATING_KEYSTORE;
  }
  
  public static String getValidityRequirementsForRetrievingKeystore()
  {
    return VALIDITY_REQUIREMENTS_FOR_RETRIEVING_KEYSTORE;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {
      return false;
    }
    if (paramObject == this) {
      return true;
    }
    if (paramObject.getClass() != getClass()) {
      return false;
    }
    paramObject = (SignatureInfo)paramObject;
    return new EqualsBuilder().append(this.certificateId, ((SignatureInfo)paramObject).certificateId).append(this.commonName, ((SignatureInfo)paramObject).commonName).append(this.developerId, ((SignatureInfo)paramObject).developerId).append(this.developerName, ((SignatureInfo)paramObject).developerName).isEquals();
  }
  
  public String getCertificateId()
  {
    return this.certificateId;
  }
  
  public String getCommonName()
  {
    return this.commonName;
  }
  
  public String getDeveloperId()
  {
    return this.developerId;
  }
  
  public String getDeveloperName()
  {
    return this.developerName;
  }
  
  public String getId()
  {
    if (StringUtils.isBlank(this.certificateId)) {
      return this.developerId;
    }
    return this.certificateId;
  }
  
  public String getName()
  {
    if (StringUtils.isBlank(this.commonName)) {
      return this.developerName;
    }
    return this.commonName;
  }
  
  public int hashCode()
  {
    return new HashCodeBuilder().append(this.certificateId).append(this.commonName).append(this.developerId).append(this.developerName).toHashCode();
  }
  
  public boolean isValidForCreatingKeystore()
  {
    return (!StringUtils.isBlank(getId())) && (!StringUtils.isBlank(getName()));
  }
  
  public boolean isValidForRetrievingKeyStore()
  {
    return !StringUtils.isBlank(getId());
  }
  
  public void setCertificateId(String paramString)
  {
    this.certificateId = paramString;
  }
  
  public void setCommonName(String paramString)
  {
    this.commonName = paramString;
  }
  
  public void setDeveloperId(String paramString)
  {
    this.developerId = paramString;
  }
  
  public void setDeveloperName(String paramString)
  {
    this.developerName = paramString;
  }
  
  public String toString()
  {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
  
  public SignatureInfo withCertificateId(String paramString)
  {
    this.certificateId = paramString;
    return this;
  }
  
  public SignatureInfo withCommonName(String paramString)
  {
    this.commonName = paramString;
    return this;
  }
  
  public SignatureInfo withDeveloperId(String paramString)
  {
    this.developerId = paramString;
    return this;
  }
  
  public SignatureInfo withDeveloperName(String paramString)
  {
    this.developerName = paramString;
    return this;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\mas\kiwi\util\SignatureInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */