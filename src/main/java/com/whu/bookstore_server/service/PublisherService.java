package com.whu.bookstore_server.service;

import com.whu.bookstore_server.domain.Publisher;

public interface PublisherService {

    Integer addPublisher(Publisher publisher);

    Integer deletePublisher(String publisherId);

    Integer updatePublisher(Publisher publisher);

    Publisher getPublisherById(String publisherId);

    Publisher getPublisherByName(String name);
}
