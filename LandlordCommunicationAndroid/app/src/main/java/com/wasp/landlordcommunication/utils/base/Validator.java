package com.wasp.landlordcommunication.utils.base;

import com.wasp.landlordcommunication.utils.ValidationException;

public interface Validator<T> {

    void validateEntity(T entity) throws ValidationException;
}
