package com.pegimon.library.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatronDto {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
}
