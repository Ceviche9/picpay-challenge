package com.picpaychallenger.dto;

import com.picpaychallenger.domain.user.UserType;

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
