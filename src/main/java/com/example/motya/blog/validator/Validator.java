package com.example.motya.blog.validator;

public interface Validator<T> {

    ValidationResult isValid(T object);
}
