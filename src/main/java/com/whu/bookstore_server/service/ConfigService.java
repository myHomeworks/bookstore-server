package com.whu.bookstore_server.service;

import com.whu.bookstore_server.domain.Config;

public interface ConfigService {

    Config getConfigById(String configId);

    Config getConfigByKey(String key);

    Integer updateConfigById(Config config);
}
