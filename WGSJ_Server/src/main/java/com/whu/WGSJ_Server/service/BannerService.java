package com.whu.WGSJ_Server.service;

import com.whu.WGSJ_Server.domain.Banner;
import java.util.List;

public interface BannerService {
    List<Banner> getBannerByDate(String date);

    Integer updateBannersByDate(String date, List<Banner> banners);

    Integer addBannersByDate(String date, List<Banner> banners);

    Integer deleteBannerById(Banner banner); // 假删除
}
