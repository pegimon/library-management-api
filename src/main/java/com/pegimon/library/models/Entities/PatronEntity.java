package com.pegimon.library.models.Entities;

import jakarta.persistence.*;
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
    private String name;
    private String email;
    private String phoneNumber;
}
