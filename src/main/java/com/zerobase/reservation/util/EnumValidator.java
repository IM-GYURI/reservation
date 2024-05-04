package com.zerobase.reservation.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Enum 타입의 파라미터 검증
 */
public class EnumValidator implements ConstraintValidator<ValidEnum, Enum<?>> {
    private ValidEnum annotation;

    @Override
    public void initialize(ValidEnum constraintAnnotation) {
        annotation = constraintAnnotation;
    }

    /**
     * Enum 값 검증
     * 값이 없거나 해당 Enum 클래스에 정의되어있는 값이 아니라면 false 리턴
     */
    @Override
    public boolean isValid(Enum value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        Enum<?>[] enumValues = annotation.enumClass().getEnumConstants();
        if (enumValues != null) {
            for (Enum<?> enumValue : enumValues) {
                if (value == enumValue) {
                    return true;
                }
            }
        }

        return false;
    }
}
