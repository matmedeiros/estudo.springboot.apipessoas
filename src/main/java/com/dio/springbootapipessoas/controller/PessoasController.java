package com.dio.springbootapipessoas.controller;

import com.dio.springbootapipessoas.dto.MessageResponseDTO;
import com.dio.springbootapipessoas.entity.Pessoa;
import com.dio.springbootapipessoas.repository.PessoaRepository;
import com.dio.springbootapipessoas.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pessoas")
public class PessoasController {

    private PessoaService pessoaService;

    @Autowired
    public PessoasController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public String getPessoas() {
        return "API pessoas versao 2";
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO inserePessoas(@RequestBody Pessoa pessoa) {

        return pessoaService.inserirPessoa(pessoa);

    }
}
