package com.jimmodel.services.service;

import java.time.Duration;
import java.util.UUID;

public interface BlackListUserService {

    Boolean isBlackListed(UUID userId);

    void blackList(UUID userId, Duration expiration);

    void unblackList(UUID userId);

}
