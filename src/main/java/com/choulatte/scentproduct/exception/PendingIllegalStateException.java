package com.choulatte.scentproduct.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PendingIllegalStateException extends IllegalStateException{
}
