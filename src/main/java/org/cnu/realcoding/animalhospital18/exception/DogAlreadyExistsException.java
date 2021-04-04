package org.cnu.realcoding.animalhospital18.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DogAlreadyExistsException extends RuntimeException{
}
