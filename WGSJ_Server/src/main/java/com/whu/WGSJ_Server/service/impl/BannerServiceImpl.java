package com.whu.WGSJ_Server.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.whu.WGSJ_Server.domain.Banner;
import com.whu.WGSJ_Server.mapper.BannerMapper;
import com.whu.WGSJ_Server.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BannerService")
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public List<Banner> getBannerByDate(String date) {
        return bannerMapper.selectList(new EntityWrapper<Banner>()
                .eq("date", date)
                .orderBy("queue", true));
    }

    @Override
    public Integer updateBannersByDate(String date, List<Banner> banners) {
        List<Banner> bannersOld = bannerMapper.selectList(new EntityWrapper<Banner>()
                .eq("date", date));
        for (Banner banner : bannersOld) {
            banner.setIsAbandon(1);
            if (bannerMapper.updateById(banner) == 0) return 0; // update fair
        }
        for (Banner banner : banners) {
            if (bannerMapper.insert(banner) == 0) return -1; // insert fair
        }
        return 1;
    }

    @Override
    public Integer addBannersByDate(String date, List<Banner> banners) {
        if (bannerMapper.selectCount(new EntityWrapper<Banner>().eq("date", date)) > 0)
            return 0; // not permit
        for (Banner banner : banners) {
            if (bannerMapper.insert(banner) == 0) return -1; // insert fair
        }
        return 1;
    }

    @Override
    public Integer deleteBannerById(Banner banner) {
        banner.setIsAbandon(1);
        return bannerMapper.updateById(banner);
    }
}
