package com.example.User.DB.Twilio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtpResponseDto {
    private OtpStatus status;
    private String message;
}
