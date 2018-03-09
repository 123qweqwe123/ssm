package com.lmbx.ssm.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmbx.ssm.domain.PipCommLcc;
import com.lmbx.ssm.domain.PipCommLccExample;
import com.lmbx.ssm.mapper.PipCommLccMapper;
import com.lmbx.ssm.service.LccService;
import com.lmbx.ssm.vo.JqgridResponse;
import com.lmbx.ssm.vo.JqgridResponseContext;
import com.lmbx.ssm.vo.PipCommLccFiter;
@Service
public class LccServicesImpl implements LccService{
	
		
		@Autowired
		private PipCommLccMapper pipCommLccMapper;

		@Override
		public void getDict() {
			PipCommLccExample example = new PipCommLccExample();
			List<PipCommLcc> list = pipCommLccMapper.selectByExample(example);
			System.out.println(list.size());
		}

		@Override
		public List<PipCommLcc>  getDictbyfilter() {
			PipCommLccFiter pipCommLccFiter = new PipCommLccFiter();
			//pipCommLccFiter.setPage(2);
			List<PipCommLcc> list = pipCommLccMapper.selectByFilter(pipCommLccFiter);
			JqgridResponse<PipCommLcc> response = JqgridResponseContext.getJqgridResponse();
		    response.setRows(list);
			return list;
		}

		@Transactional
		@Override
		public void insert(PipCommLcc pipCommLcc) {
			// TODO Auto-generated method stub
			pipCommLcc.setAddress("aaaa");
			pipCommLcc.setLccCode("ssss");
			pipCommLcc.setLccName("ssssssss");
			pipCommLcc.setProjectId("sssssssss");
			Short ss = 2;
			pipCommLcc.setStatus(ss);
			pipCommLccMapper.insert(pipCommLcc);
			System.out.println(10/0);
		}
	
}
