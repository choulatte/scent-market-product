package com.choulatte.scentproduct.dto;

import com.choulatte.scentproduct.domain.PendingUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PendingUserDTO {
    private Long userIdx;
    private Boolean userValidation;

    public PendingUser toEntity(){
        return PendingUser.builder()
                .userIdx(this.userIdx)
                .userValidation(this.userValidation)
                .build();
    }
}
