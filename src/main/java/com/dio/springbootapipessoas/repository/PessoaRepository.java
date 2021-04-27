package com.dio.springbootapipessoas.repository;

import com.dio.springbootapipessoas.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa,Long> {

}
