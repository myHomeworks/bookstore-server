package com.whu.WGSJ_Server.service;

import com.whu.WGSJ_Server.domain.Address;

import java.util.List;

public interface AddressService {

    List<Address> getAddressListByUserId(String userId);

    Integer addAddress(Address address);

    Integer deleteAddressById(String addressId);
}
