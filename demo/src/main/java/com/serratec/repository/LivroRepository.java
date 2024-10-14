package com.serratec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serratec.domain.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

}
