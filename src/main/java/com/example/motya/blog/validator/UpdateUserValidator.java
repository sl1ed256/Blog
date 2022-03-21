package com.example.motya.blog.validator;

import com.example.motya.blog.dto.UpdateUserDto;

public class UpdateUserValidator implements Validator<UpdateUserDto> {

    private static final UpdateUserValidator INSTANCE = new UpdateUserValidator();

    @Override
    public ValidationResult isValid(UpdateUserDto object) {
        var validationResult = new ValidationResult();
        if (object.getNickname() == null) {
            validationResult.add(Error.of("invalid.nickname", "nickname is invalid"));
        }
        return validationResult;
    }

    public static UpdateUserValidator getInstance() {
        return INSTANCE;
    }
}
