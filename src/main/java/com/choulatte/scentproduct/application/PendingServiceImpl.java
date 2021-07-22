package com.choulatte.scentproduct.application;

import com.choulatte.scentproduct.domain.PendingUser;
import com.choulatte.scentproduct.dto.PendingUserDTO;
import com.choulatte.scentproduct.exception.PendingIllegalStateException;
import com.choulatte.scentproduct.exception.PendingUserException;
import com.choulatte.scentproduct.repository.PendingUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PendingServiceImpl implements PendingService {

    private final PendingUserRepository pendingUserRepository;

    @Override
    public Long makeUserPending(Long userID) throws PendingIllegalStateException {
        return pendingUserRepository.save(new PendingUserDTO(userID, true).toEntity()).getUserId();
    }

    @Override
    public void clearPending(Long userId) throws PendingIllegalStateException {
        pendingUserRepository.delete(getPendingUser(userId));
    }

    private PendingUser getPendingUser(Long userId) {
        return pendingUserRepository.findByUserId(userId).orElseThrow(PendingUserException::new);
    }
}
