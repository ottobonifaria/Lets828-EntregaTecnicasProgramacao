package com.letscode.modeloentrega.repository;

import com.letscode.modeloentrega.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
