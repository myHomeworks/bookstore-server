package com.whu.WGSJ_Server.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.whu.WGSJ_Server.domain.Config;
import com.whu.WGSJ_Server.mapper.ConfigMapper;
import com.whu.WGSJ_Server.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ConfigService")
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    private ConfigMapper configMapper;

    @Override
    public Config getConfigById(String configId) {
        return configMapper.selectById(configId);
    }

    @Override
    public Config getConfigByKey(String key) {
        List<Config> configs = configMapper.selectList(new EntityWrapper<Config>()
                .eq("`key`", key));
        return configs.size() > 0 ? configs.get(0) : null;
    }

    @Override
    public Integer updateConfigById(Config config) {
        return configMapper.updateById(config);
    }
}
