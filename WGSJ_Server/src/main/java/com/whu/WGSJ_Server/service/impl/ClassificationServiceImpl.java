package com.whu.WGSJ_Server.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.whu.WGSJ_Server.domain.Classification;
import com.whu.WGSJ_Server.mapper.ClassificationMapper;
import com.whu.WGSJ_Server.service.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ClassificationService")
public class ClassificationServiceImpl implements ClassificationService {
    @Autowired
    private ClassificationMapper classificationMapper;

    @Override
    public List<Classification> getAllFathers() {
        return classificationMapper.selectList(new EntityWrapper<Classification>()
                .eq("if_father", 1)
                .orderBy("time", false));
    }

    @Override
    public List<Classification> getAllChildrenByFatherId(String id) {
        return classificationMapper.selectList(new EntityWrapper<Classification>()
                .eq("father_id", id)
                .orderBy("time", false));
    }

    @Override
    public Integer addClass(Classification classification) {
        return classificationMapper.insert(classification);
    }

    @Override
    public Integer deleteClassById(String id) {
        return classificationMapper.deleteById(id);
    }

    @Override
    public Integer updateClass(Classification classification) {
        return classificationMapper.updateById(classification);
    }

    @Override
    public Classification getFatherClassByName(String name) {
        List<Classification> classifications = classificationMapper.selectList(new EntityWrapper<Classification>()
                .eq("name", name)
                .eq("if_father", 1));
        if(classifications.size() > 0){
            return classifications.get(0);
        }else{
            return null;
        }
    }
}
