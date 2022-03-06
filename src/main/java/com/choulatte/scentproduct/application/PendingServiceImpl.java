package com.choulatte.scentproduct.application;

import com.choulatte.scentproduct.domain.PendingUser;
import com.choulatte.scentproduct.dto.PendingUserDTO;
import com.choulatte.scentproduct.exception.PendingUserException;
import com.choulatte.scentproduct.repository.PendingUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PendingServiceImpl implements PendingService {

    private final PendingUserRepository pendingUserRepository;

    @Override
    public void makeUserPending(Long userID) {
        pendingUserRepository.save(new PendingUserDTO(userID, true).toEntity());
    }

    @Override
    public void makeUserInvalid(Long userId) {
        pendingUserRepository.save(getPendingUser(userId).makeUserInvalid());
    }

    @Override
    public void releasePending(Long userId){
        pendingUserRepository.delete(getPendingUser(userId));
    }

    @Override
    public boolean isPresent(Long userId) {
        return pendingUserRepository.findByUserId(userId).isPresent();
    }

    private PendingUser getPendingUser(Long userId) {
        return pendingUserRepository.findByUserId(userId).orElseThrow(PendingUserException::new);
    }
}
