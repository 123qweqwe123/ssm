package com.lmbx.ssm.vo;


public class BaseFilter
{
  private String sidx;
  private String sord;
  private Integer page = Integer.valueOf(1);
  private Integer rows = Integer.valueOf(15);
  private Integer records;
  private Integer total;
  
  public String getSidx()
  {
    return this.sidx;
  }
  
  public void setSidx(String sidx)
  {
    this.sidx = sidx;
  }
  
  public String getSord()
  {
    return this.sord;
  }
  
  public void setSord(String sord)
  {
    this.sord = sord;
  }
  
  public Integer getPage()
  {
    return this.page;
  }
  
  public void setPage(Integer page)
  {
    this.page = page;
  }
  
  public Integer getRows()
  {
    return this.rows;
  }
  
  public void setRows(Integer rows)
  {
    this.rows = rows;
  }
  
  public Integer getTotal()
  {
    return this.total;
  }
  
  public void setTotal(Integer total)
  {
    this.total = total;
  }
  
  public Integer getRecords()
  {
    return this.records;
  }
  
  public void setRecords(Integer records)
  {
    this.records = records;
  }
  
  public String toString()
  {
    return "BaseFilter [sidx=" + this.sidx + ", sord=" + this.sord + ", page=" + this.page + ", rows=" + this.rows + "]";
  }
  
  public static final void setLocal(BaseFilter baseFilter)
  {
    JqgridResponse jqgridResponse = (JqgridResponse)JqgridResponseContext.localResponse.get();
    if (jqgridResponse == null) {
      jqgridResponse = new JqgridResponse();
    }
    jqgridResponse.setPage(baseFilter.getPage());
    jqgridResponse.setTotal(baseFilter.getTotal());
    jqgridResponse.setRecords(baseFilter.getRecords());
    JqgridResponseContext.localResponse.set(jqgridResponse);
  }
}
