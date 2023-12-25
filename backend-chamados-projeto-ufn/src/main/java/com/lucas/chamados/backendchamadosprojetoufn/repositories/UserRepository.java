package com.lucas.chamados.backendchamadosprojetoufn.repositories;

import com.lucas.chamados.backendchamadosprojetoufn.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByLogin(String login);
}
