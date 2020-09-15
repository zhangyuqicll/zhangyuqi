package com.leyou.service.leyouservice.service;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.service.leyouservice.mapper.SpecGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecificationService {
    @Autowired
    private SpecGroupMapper specGroupMapper;
    public List<SpecGroup> queryGroupsByCid(Long cid) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        return this.specGroupMapper.select(specGroup);
    }

    public void addGroup(SpecGroup specGroup) {
        this.specGroupMapper.insert(specGroup);
    }

    public void deleteGroup(Long id) {
        specGroupMapper.deleteByPrimaryKey(id);
    }

    public void updateGroup(SpecGroup specGroup) {
        specGroupMapper.updateByPrimaryKeySelective(specGroup);
    }
}
