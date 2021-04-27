package com.dio.springbootapipessoas.service;

import com.dio.springbootapipessoas.dto.MessageResponseDTO;
import com.dio.springbootapipessoas.entity.Pessoa;
import com.dio.springbootapipessoas.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class PessoaService {

    PessoaRepository pessoaRepository;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public MessageResponseDTO inserirPessoa(Pessoa pessoa){

        Pessoa pessoaGravada = pessoaRepository.save(pessoa);

        return MessageResponseDTO.builder()
                .message("Pessoa salva com ID: "+ pessoaGravada.getId())
                .build();

    }
}
