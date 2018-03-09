package com.lmbx.ssm.vo;


import java.util.List;

public class JqgridResponse<T>
{
  private Integer page;
  private Integer total;
  private Integer records;
  private List<T> rows;
  
  protected JqgridResponse() {}
  
  public JqgridResponse(Integer page, Integer total, Integer records, List<T> rows)
  {
    this.page = page;
    this.total = total;
    this.records = records;
    this.rows = rows;
  }
  
  public Integer getPage()
  {
    return this.page;
  }
  
  public void setPage(Integer page)
  {
    this.page = page;
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
  
  public List<T> getRows()
  {
    return this.rows;
  }
  
  public JqgridResponse<T> setRows(List<T> rows)
  {
    this.rows = rows;
    return this;
  }
  
  public String toString()
  {
    return "JqgridResponse [page=" + this.page + ", total=" + this.total + ", records=" + this.records + "]";
  }
}
