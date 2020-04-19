package com.whu.bookstore_server.service;

import com.whu.bookstore_server.domain.Ad;
import java.util.List;

public interface AdService {
    List<Ad> getMenus();

    List<Ad> getBannerByDate(String date);

    Integer updateBannersByDate(String date, List<Ad> ads);

    Integer addAdsByDate(String date, List<Ad> ads);

    Integer deleteAdById(Ad banner); // 假删除
}
