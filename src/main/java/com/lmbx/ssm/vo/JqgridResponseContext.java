package com.lmbx.ssm.vo;


import java.util.List;

public class JqgridResponseContext
{
  protected static ThreadLocal<JqgridResponse> localResponse = new ThreadLocal();
  
  public static <T> JqgridResponse<T> getJqgridResponse()
  {
    JqgridResponse<T> jqgridResponse = (JqgridResponse)localResponse.get();
    if (jqgridResponse == null) {
      jqgridResponse = new JqgridResponse();
    }
    localResponse.remove();
    return jqgridResponse;
  }
  
  public static <T> JqgridResponse<T> getJqgridResponse(List<T> rows)
  {
    JqgridResponse<T> jqgridResponse = getJqgridResponse();
    return jqgridResponse.setRows(rows);
  }
}
