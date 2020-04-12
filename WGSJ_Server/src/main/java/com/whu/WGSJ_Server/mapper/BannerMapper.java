package com.whu.WGSJ_Server.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.whu.WGSJ_Server.domain.Banner;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "BannerMapper")
public interface BannerMapper extends BaseMapper<Banner> {
}
