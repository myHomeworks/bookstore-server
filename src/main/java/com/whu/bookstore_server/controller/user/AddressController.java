package com.whu.bookstore_server.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.whu.bookstore_server.domain.Address;
import com.whu.bookstore_server.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user/address")
@Component("AddressController")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PostMapping("/add")
    public HashMap<String, Object> add(@RequestBody String body) {
        HashMap<String, Object> ret = new HashMap<>();
        JSONObject object = JSONObject.parseObject(body);
        Address address = new Address();
        address.setAddressId(UUID.randomUUID().toString());
        address.setMobile(object.getString("mobile"));
        address.setUserId(object.getString("userId"));
        address.setAddress(object.getString("address"));
        address.setConsignee(object.getString("consignee"));
        address.setTransportDay(object.getString("transportDay"));
        if (addressService.addAddress(address) == 1)
            ret.put("state", "ok");
        else
            ret.put("state", "failAdd");
        return ret;
    }

    @PostMapping("/delete")
    public HashMap<String, Object> delete(@RequestBody String body) {
        HashMap<String, Object> ret = new HashMap<>();
        JSONObject object = JSONObject.parseObject(body);
        String addressId = object.getString("addressId");
        if (addressService.deleteAddressById(addressId) == 1)
            ret.put("state", "ok");
        else
            ret.put("state", "failDelete");
        return ret;
    }

    @PostMapping("/get")
    public HashMap<String, Object> get(@RequestBody String body) {
        HashMap<String, Object> ret = new HashMap<>();
        JSONObject object = JSONObject.parseObject(body);
        String userId = object.getString("userId");
        List<Address> addresses = addressService.getAddressListByUserId(userId);
        ret.put("addresses", addresses);
        ret.put("state", "ok");
        return ret;
    }
}
