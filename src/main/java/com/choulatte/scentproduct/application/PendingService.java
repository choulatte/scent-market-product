package com.choulatte.scentproduct.application;

public interface PendingService {

    void makeUserPending(Long userID);
    void makeUserInvalid(Long userId);
    void clearPending(Long userId);
}
