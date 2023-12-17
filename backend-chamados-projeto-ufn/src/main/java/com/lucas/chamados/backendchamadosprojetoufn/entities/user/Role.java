package com.lucas.chamados.backendchamadosprojetoufn.entities.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    @Column
    private String name;
}
