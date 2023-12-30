package com.lcwd.electronic_store.electronic_store.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class ImageNameValidation implements ConstraintValidator<ImageNameValid,String> {
    Logger logger= LoggerFactory.getLogger(ImageNameValidation.class);
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        logger.info("this your valid image name{}",s);
        if(s.equalsIgnoreCase("")){
            return false;
        }else {
            return true;
        }
    }
}
