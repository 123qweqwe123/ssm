package com.lmbx.ssm.service;

import java.util.List;

import com.lmbx.ssm.domain.PipCommLcc;

public interface LccService {
	public void getDict();
	public List<PipCommLcc>  getDictbyfilter();
	public void insert(PipCommLcc pipCommLcc);
}
