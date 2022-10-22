package com.utn.dabd.tpi.blackjack.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDTO {
    private String timestamp;
    private Integer status;
    private String error;
    private String message;
}

