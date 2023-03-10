package br.com.cwi.api.domain;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PasswordToken {
    private String email;
    private Long criadoEm;
}
