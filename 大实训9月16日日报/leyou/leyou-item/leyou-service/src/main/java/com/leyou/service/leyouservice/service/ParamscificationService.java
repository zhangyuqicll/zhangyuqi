package com.leyou.service.leyouservice.service;

import com.leyou.item.pojo.SpecParam;
import com.leyou.service.leyouservice.mapper.SpecParamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ParamscificationService {
    @Autowired
    private SpecParamMapper specParamMapper;
    public List<SpecParam> queryParams(Long gid, Long cid, Boolean generic, Boolean searching) {
        SpecParam record = new SpecParam();
        record.setGroupId(gid);
        record.setCid(cid);
        record.setGeneric(generic);
        record.setSearching(searching);
        return this.specParamMapper.select(record);
    }

    public void addParam(SpecParam specParam) {
        this.specParamMapper.insert(specParam);
    }

    public void deleteParam(Long id) {
        specParamMapper.deleteByPrimaryKey(id);
    }

    public void update(SpecParam specParam) {
        specParamMapper.updateByPrimaryKeySelective(specParam);
    }
}
