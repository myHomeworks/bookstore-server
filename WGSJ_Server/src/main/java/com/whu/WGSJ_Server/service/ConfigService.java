package com.whu.WGSJ_Server.service;

import com.whu.WGSJ_Server.domain.Config;

public interface ConfigService {

    Config getConfigById(String configId);

    Config getConfigByKey(String key);

    Integer updateConfigById(Config config);
}
