package com.whu.bookstore_server.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.whu.bookstore_server.domain.Ad;
import com.whu.bookstore_server.mapper.AdMapper;
import com.whu.bookstore_server.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BannerService")
public class AdServiceImpl implements AdService {
    @Autowired
    private AdMapper adMapper;


    @Override
    public List<Ad> getMenus() {
        return adMapper.selectList(new EntityWrapper<Ad>()
                .eq("type", "menu")
                .orderBy("queue", true));
    }

    @Override
    public List<Ad> getBannerByDate(String date) {
        return adMapper.selectList(new EntityWrapper<Ad>()
                .eq("date", date)
                .eq("type", "banner")
                .orderBy("queue", true));
    }

    @Override
    public Integer updateBannersByDate(String date, List<Ad> banners) {
        List<Ad> bannersOld = adMapper.selectList(new EntityWrapper<Ad>()
                .eq("date", date)
                .eq("type", "banner"));
        for (Ad banner : bannersOld) {
            banner.setIsAbandon(1);
            if (adMapper.deleteById(banner.getAdId()) == 0) return 0; // update fair
        }
        for (Ad banner : banners) {
            if (adMapper.insert(banner) == 0) return -1; // insert fair
        }
        return 1;
    }

    @Override
    public Integer addAdsByDate(String date, List<Ad> ads) {
        if (adMapper.selectCount(new EntityWrapper<Ad>().eq("date", date)) > 0)
            return 0; // not permit
        for (Ad banner : ads) {
            if (adMapper.insert(banner) == 0) return -1; // insert fair
        }
        return 1;
    }

    @Override
    public Integer deleteAdById(Ad banner) {
        banner.setIsAbandon(1);
        return adMapper.updateById(banner);
    }
}
