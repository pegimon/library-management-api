package com.pegimon.library.models.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name = "patrons")
public class PatronEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patron_id_seq")
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    @Email(message = "please provide a valid email address")
    private String email;
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;
}
