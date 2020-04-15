package com.whu.WGSJ_Server.service;

import com.whu.WGSJ_Server.domain.Classification;

import java.util.List;

public interface ClassificationService {

    List<Classification> getAllFathers();

    List<Classification> getAllChildrenByFatherId(String id);

    Integer addClass(Classification classification);

    Integer deleteClassById(String id);

    Integer updateClass(Classification classification);

    Classification getFatherClassByName(String name);

}
