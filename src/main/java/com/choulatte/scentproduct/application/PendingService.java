package com.choulatte.scentproduct.application;

import java.util.List;

public interface PendingService {

    Long makeUserPending(Long userID);
    void clearPending(Long userId);
}
