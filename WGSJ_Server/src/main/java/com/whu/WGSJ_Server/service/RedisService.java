package com.whu.WGSJ_Server.service;

public interface RedisService {

    String checkSessionId(String sessionId);

    void saveSessionId(String sessionId, String username);

    void delSessionId(String sessionId);

    void saveUserOrAdminBySessionId(String sessionId, Object obj);

    Object getUserOrAdminBySessionId(String sessionId);

    void updateExpireTime(String key);
}
