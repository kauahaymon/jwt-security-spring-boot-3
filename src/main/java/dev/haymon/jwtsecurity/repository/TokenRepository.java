package dev.haymon.jwtsecurity.repository;

import dev.haymon.jwtsecurity.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Integer> {
}
