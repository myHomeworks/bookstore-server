package com.whu.WGSJ_Server.service.impl;

import com.whu.WGSJ_Server.domain.Publisher;
import com.whu.WGSJ_Server.mapper.PublisherMapper;
import com.whu.WGSJ_Server.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PublisherService")
public class PublisherServiceImpl implements PublisherService {
    @Autowired
    private PublisherMapper publisherMapper;

    @Override
    public Integer addPublisher(Publisher publisher) {
        return publisherMapper.insert(publisher);
    }

    @Override
    public Integer deletePublisher(String publisherId) {
        return publisherMapper.deleteById(publisherId);
    }

    @Override
    public Integer updatePublisher(Publisher publisher) {
        return publisherMapper.updateById(publisher);
    }

    @Override
    public Publisher getPublisherById(String publisherId) {
        return publisherMapper.selectById(publisherId);
    }
}
