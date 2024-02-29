package com.picpaychallenger.dto;

import java.math.BigInteger;

public record ExceptionDTO (
        String message,
        String status
) {
}
