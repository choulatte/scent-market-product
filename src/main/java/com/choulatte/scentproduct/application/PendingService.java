package com.choulatte.scentproduct.application;

public interface PendingService {

    Boolean makeUserPending(Long userID);
    Boolean clearPending(Long userId);
}
