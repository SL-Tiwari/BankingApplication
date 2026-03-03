package util;

import exception.ValidationException;

@FunctionalInterface
public interface Validation<T> {

    void validate(T valid) throws ValidationException;

}
