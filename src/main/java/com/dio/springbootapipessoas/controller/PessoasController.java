package com.dio.springbootapipessoas.controller;

import com.dio.springbootapipessoas.dto.MessageResponseDTO;
import com.dio.springbootapipessoas.dto.PessoaDTO;
import com.dio.springbootapipessoas.entity.Pessoa;
import com.dio.springbootapipessoas.repository.PessoaRepository;
import com.dio.springbootapipessoas.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pessoas")
public class PessoasController {

    private PessoaService pessoaService;

    @Autowired
    public PessoasController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO inserePessoas(@RequestBody @Valid PessoaDTO pessoa) {

        return pessoaService.inserirPessoa(pessoa);

    }

    @GetMapping
    public List<PessoaDTO> listarPessoas() {
        return pessoaService.findAll();
    }

    @GetMapping("/{id}")
    public PessoaDTO getPessoa(@PathVariable  Long id) {
        return pessoaService.findById(id);
    }
}
