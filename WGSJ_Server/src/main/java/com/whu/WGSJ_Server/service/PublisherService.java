package com.whu.WGSJ_Server.service;

import com.whu.WGSJ_Server.domain.Publisher;

public interface PublisherService {

    Integer addPublisher(Publisher publisher);

    Integer deletePublisher(String publisherId);

    Integer updatePublisher(Publisher publisher);

    Publisher getPublisherById(String publisherId);

    Publisher getPublisherByName(String name);
}
