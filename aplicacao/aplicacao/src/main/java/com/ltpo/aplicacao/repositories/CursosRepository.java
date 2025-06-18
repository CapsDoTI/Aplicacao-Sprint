package com.ltpo.aplicacao.repositories;

import com.ltpo.aplicacao.models.Cursos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursosRepository extends JpaRepository<Cursos, Long> {
    boolean existsByNome(String nome);
}