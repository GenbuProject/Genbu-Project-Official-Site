package com.microsoft.xbox.service.model.sls;

import com.microsoft.xbox.toolkit.GsonUtil;

public class SubmitFeedbackRequest
{
  public String evidenceId;
  public FeedbackType feedbackType;
  public String sessionRef;
  public String textReason;
  public String voiceReasonId;
  public long xuid;
  
  public SubmitFeedbackRequest(long paramLong, String paramString1, FeedbackType paramFeedbackType, String paramString2, String paramString3, String paramString4)
  {
    this.xuid = paramLong;
    this.sessionRef = paramString1;
    this.feedbackType = paramFeedbackType;
    this.textReason = paramString2;
    this.voiceReasonId = paramString3;
    this.evidenceId = paramString4;
  }
  
  public static String getSubmitFeedbackRequestBody(SubmitFeedbackRequest paramSubmitFeedbackRequest)
  {
    return GsonUtil.toJsonString(paramSubmitFeedbackRequest);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\service\model\sls\SubmitFeedbackRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */