package com.zerobase.reservation.util;

import com.zerobase.reservation.type.Role;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * 유저 권한 converter
 * Role 타입 -> String
 * String -> Role 타입
 */
@Converter
public class RoleConverter implements AttributeConverter<Role, String> {
    @Override
    public String convertToDatabaseColumn(Role role) {
        if (role == null) {
            return null;
        }

        return role.getKey();
    }

    @Override
    public Role convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }

        return Role.fromKey(s);
    }
}