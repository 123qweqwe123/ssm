package com.lmbx.ssm.web.service.Impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmbx.ssm.web.daomain.CustomFunction;
import com.lmbx.ssm.web.mapper.CustomFunctionDao;
import com.lmbx.ssm.web.service.CustomFunctionService;


@Service
@Transactional
public class CustomFunctionServiceImpl implements CustomFunctionService{

    private static Logger logger = LoggerFactory.getLogger(CustomFunctionServiceImpl.class);

    @Autowired
    private CustomFunctionDao customFunctionDao;

	@Override
	public CustomFunction findById(String id) {
		return customFunctionDao.findById(id);
	}

	@Override
	public List<CustomFunction> findByUerId(String userId) {
		return customFunctionDao.findByUerId(userId);
	}

	@Override
	public void saveOne(CustomFunction vo) {
		if(StringUtils.isNotEmpty(vo.getId())){
			vo.setUpdateDatetime(new Date());
			Integer con = customFunctionDao.updateOne(vo);	
		}else{			
			//vo.setId(Identities.uuid());
			//vo.setId(GenerateKey.getKey(GenerateKey.PREFIX_FUNCTION));
			CustomFunction temp = customFunctionDao.findByCode(vo.getCode());
			vo.setFunctionId(temp.getFunctionId());
			vo.setCreateDatetime(new Date());
			customFunctionDao.insertOne(vo);			
		}
		
		
	}

	@Override
	public CustomFunction findByUserAndCode(String userId, String code) {
		return customFunctionDao.findByUserAndCode(userId, code);
	}

	@Override
	public Map findKeyMapByUerId(String userId) {
		List<CustomFunction> list =  customFunctionDao.findByUerId(userId);
		Map result = new HashMap();
		for(CustomFunction vo : list ){
			result.put(vo.getCode(), vo.getFvalue());
		}
		return result;
	} 
    
	@Override
	public Map findVoMapByUerId(String userId) {
		List<CustomFunction> list =  customFunctionDao.findByUerId(userId);
		Map result = new HashMap();
		for(CustomFunction vo : list ){
			result.put(vo.getCode(), vo);
		}
		return result;
	} 
	
	 
	public static void main(String[] args){
		CustomFunction aa= new CustomFunction();
		aa.setCode("aa");
		aa.setCreateDatetime(new Date());
		/*String str  = JaxbMapper.toXml(aa);
		System.out.println(str);*/
	}

}
