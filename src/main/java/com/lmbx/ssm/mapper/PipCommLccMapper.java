package com.lmbx.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lmbx.ssm.domain.PipCommLcc;
import com.lmbx.ssm.domain.PipCommLccExample;
import com.lmbx.ssm.domain.PipCommLccKey;
import com.lmbx.ssm.utils.MyBatisRepository;
import com.lmbx.ssm.vo.PipCommLccFiter;
@MyBatisRepository
public interface PipCommLccMapper {
    long countByExample(PipCommLccExample example);

    int deleteByExample(PipCommLccExample example);

    int deleteByPrimaryKey(PipCommLccKey key);

    int insert(PipCommLcc record);

    int insertSelective(PipCommLcc record);

    List<PipCommLcc> selectByExample(PipCommLccExample example);

    PipCommLcc selectByPrimaryKey(PipCommLccKey key);

    int updateByExampleSelective(@Param("record") PipCommLcc record, @Param("example") PipCommLccExample example);

    int updateByExample(@Param("record") PipCommLcc record, @Param("example") PipCommLccExample example);

    int updateByPrimaryKeySelective(PipCommLcc record);

    int updateByPrimaryKey(PipCommLcc record);
    
    List<PipCommLcc> selectByFilter(PipCommLccFiter pipCommLccFiter);
}