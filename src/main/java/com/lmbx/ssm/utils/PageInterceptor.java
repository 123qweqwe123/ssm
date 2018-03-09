package com.lmbx.ssm.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.springframework.stereotype.Component;

import com.lmbx.ssm.vo.BaseFilter;
import com.lmbx.ssm.vo.Reflections;

@Component
@Intercepts({@org.apache.ibatis.plugin.Signature(type=StatementHandler.class, method="prepare", args={Connection.class})})
public class PageInterceptor
  implements Interceptor
{
  public Object intercept(Invocation invocation)
    throws Throwable
  {
    RoutingStatementHandler handler = (RoutingStatementHandler)invocation.getTarget();
    StatementHandler delegate = (StatementHandler)Reflections.getFieldValue(handler, "delegate");
    BoundSql boundSql = delegate.getBoundSql();
    Object obj = boundSql.getParameterObject();
    String sqls = boundSql.getSql();
    if ((obj instanceof BaseFilter))
    {
      BaseFilter baseFilter = (BaseFilter)obj;
      MappedStatement mappedStatement = (MappedStatement)Reflections.getFieldValue(delegate, "mappedStatement");
      

      Connection connection = (Connection)invocation.getArgs()[0];
      String sql = boundSql.getSql();
      StringBuffer sb = new StringBuffer();
      sb.append("select * from (").append(sql).append(" ) t where ");
      setTotalRecord(baseFilter, mappedStatement, connection);
      String pageSql = getPageSql(baseFilter, sql);
      
      Reflections.setFieldValue(boundSql, "sql", pageSql);
      BaseFilter.setLocal(baseFilter);
    }
    return invocation.proceed();
  }
  
  public Object plugin(Object target)
  {
    return Plugin.wrap(target, this);
  }
  
  public void setProperties(Properties properties) {}
  
  private void setTotalRecord(BaseFilter filter, MappedStatement mappedStatement, Connection connection)
  {
    BoundSql boundSql = mappedStatement.getBoundSql(filter);
    String sql = boundSql.getSql();
    String countSql = getCountSql(sql);
    List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
    
    BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql, parameterMappings, boundSql.getParameterObject());
    MetaObject metaParameters = (MetaObject)Reflections.getFieldValue(boundSql, "metaParameters");
    Reflections.setFieldValue(countBoundSql, "metaParameters", metaParameters);
    ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, filter, countBoundSql);
    
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try
    {
      pstmt = connection.prepareStatement(countSql);
      parameterHandler.setParameters(pstmt);
      rs = pstmt.executeQuery();
      if (rs.next())
      {
        int totalRecord = rs.getInt(1);
        filter.setRecords(Integer.valueOf(totalRecord));
        int rows = filter.getRows().intValue();
        
        int total = totalRecord / rows;
        total = totalRecord % rows == 0 ? total : total + 1;
        filter.setTotal(Integer.valueOf(total));
      }
      return;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    finally
    {
      try
      {
        if (rs != null) {
          rs.close();
        }
        if (pstmt != null) {
          pstmt.close();
        }
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
    }
  }
  
  private String getCountSql(String sql)
  {
    return "select count (1) from (" + sql + ")";
  }
  
  private String getPageSql(BaseFilter filter, String sql)
  {
    Integer page = Integer.valueOf(filter.getPage().intValue() < 1 ? 1 : filter.getPage().intValue());
    Integer rows = filter.getRows();
    StringBuffer sqlBuffer = new StringBuffer(sql);
    if ((page.intValue() == 1) && (rows.intValue() == 1))
    {
      sqlBuffer.insert(0, "select * from (").append(") where rownum=1 ");
    }
    else
    {
      int offset = (page.intValue() - 1) * rows.intValue() + 1;
      sqlBuffer.insert(0, "select result_temp_table.*, rownum row_now from (")
        .append(") result_temp_table where rownum<").append(offset + rows.intValue());
      sqlBuffer.insert(0, "select * from (").append(") where row_now >= ").append(offset);
    }
    return sqlBuffer.toString();
  }
}
