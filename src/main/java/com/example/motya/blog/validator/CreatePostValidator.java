package com.example.motya.blog.validator;

import com.example.motya.blog.dto.CreatePostDto;

public class CreatePostValidator implements Validator<CreatePostDto> {

    private static final CreatePostValidator INSTANCE = new CreatePostValidator();

    @Override
    public ValidationResult isValid(CreatePostDto object) {
        var validationResult = new ValidationResult();
        if (object.getTitle() == null){
            validationResult.add(Error.of("invalid.title","Title is invalid"));
        }
        return validationResult;
    }

    public static CreatePostValidator getInstance() {
        return INSTANCE;
    }

}
