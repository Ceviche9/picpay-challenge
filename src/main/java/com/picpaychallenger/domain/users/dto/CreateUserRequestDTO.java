package com.picpaychallenger.domain.users.dto;

import com.picpaychallenger.domain.users.entity.UserType;

import java.math.BigDecimal;

public record CreateUserRequestDTO(
        String firstName,
        String lastName,
        String document,
        BigDecimal balance,
        String email,
        String password,
        UserType userType
) {
}
