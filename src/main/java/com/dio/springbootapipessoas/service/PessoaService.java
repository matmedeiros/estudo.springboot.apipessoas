package com.dio.springbootapipessoas.service;

import com.dio.springbootapipessoas.dto.MessageResponseDTO;
import com.dio.springbootapipessoas.dto.PessoaDTO;
import com.dio.springbootapipessoas.entity.Pessoa;
import com.dio.springbootapipessoas.exception.PessoaNotFoundException;
import com.dio.springbootapipessoas.mapper.PessoaMapper;
import com.dio.springbootapipessoas.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    PessoaRepository pessoaRepository;

    PessoaMapper pessoaMapper = PessoaMapper.INSTANCE;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public MessageResponseDTO inserirPessoa(PessoaDTO pessoaDTO){

        Pessoa pessoa = pessoaMapper.toModel(pessoaDTO);

        Pessoa pessoaGravada = pessoaRepository.save(pessoa);

        return MessageResponseDTO.builder()
                .message("Pessoa salva com ID: "+ pessoaGravada.getId())
                .build();

    }

    public List<PessoaDTO> findAll() {

        List<PessoaDTO> result = new ArrayList();

        List<Pessoa> pessoas = pessoaRepository.findAll();

        for (Pessoa pessoa:pessoas){
            result.add(pessoaMapper.toDTO(pessoa));
        }

        return result;
    }

    public PessoaDTO findById(Long id) {

        Pessoa pessoa = pessoaRepository.findById(id)
                            .orElseThrow(() -> new PessoaNotFoundException("Id nao encontrado" + id));

        return pessoaMapper.toDTO(pessoa);
    }
}
