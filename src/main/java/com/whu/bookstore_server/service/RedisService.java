package com.whu.bookstore_server.service;

public interface RedisService {

    String checkSessionId(String sessionId);

    void saveSessionId(String sessionId, String username);

    void delSessionId(String sessionId);

    void saveObjectInstanceBySessionId(String sessionId, Object obj);

    Object getObjectInstanceBySessionId(String sessionId);

    void updateExpireTime(String key);
}
