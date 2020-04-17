package com.whu.bookstore_server.service;

import com.whu.bookstore_server.domain.Address;

import java.util.List;

public interface AddressService {

    List<Address> getAddressListByUserId(String userId);

    Integer addAddress(Address address);

    Integer deleteAddressById(String addressId);
}
