package com.whu.bookstore_server.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.whu.bookstore_server.domain.Address;
import com.whu.bookstore_server.mapper.AddressMapper;
import com.whu.bookstore_server.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AddressService")
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;


    @Override
    public List<Address> getAddressListByUserId(String userId) {
        return addressMapper.selectList(new EntityWrapper<Address>()
                .eq("user_id", userId));
    }

    @Override
    public Integer addAddress(Address address) {
        return addressMapper.insert(address);
    }

    @Override
    public Integer deleteAddressById(String addressId) {
        return addressMapper.deleteById(addressId);
    }
}
