package com.talos.hospital.CustomUtils.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchIdFound extends RuntimeException {

    public NoSuchIdFound(String modelType, UUID id) {
        super("No such " + modelType + " was found with id: " + id);
    }

}
