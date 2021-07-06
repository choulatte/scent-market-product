package com.choulatte.scentproduct.controller;

import com.choulatte.scentproduct.application.InterestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/interest")
public class InterestController {

    private final InterestService interestService;

    @PostMapping(value = "/{id}")
    void setInterest(@PathVariable("id") Long id, @RequestHeader("userId") String userId) {
        interestService.setInterest(Long.parseLong(userId), id);
    }

    @DeleteMapping(value = "/{id}")
    void deleteInterest(@PathVariable("id") Long id, @RequestHeader("userId") String userId) {
        interestService.deleteInterest(Long.parseLong(userId), id);
    }

}
