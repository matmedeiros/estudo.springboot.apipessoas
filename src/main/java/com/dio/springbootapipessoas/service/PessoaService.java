package com.dio.springbootapipessoas.service;

import com.dio.springbootapipessoas.dto.MessageResponseDTO;
import com.dio.springbootapipessoas.dto.PessoaDTO;
import com.dio.springbootapipessoas.dto.TelefoneDTO;
import com.dio.springbootapipessoas.entity.Pessoa;
import com.dio.springbootapipessoas.entity.Telefone;
import com.dio.springbootapipessoas.exception.PessoaNotFoundException;
import com.dio.springbootapipessoas.mapper.PessoaMapper;
import com.dio.springbootapipessoas.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PessoaService {

    PessoaRepository pessoaRepository;

    PessoaMapper pessoaMapper = PessoaMapper.INSTANCE;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public MessageResponseDTO inserirPessoa(PessoaDTO pessoaDTO){

        var pessoa = pessoaMapper.toModel(pessoaDTO);

        var pessoaGravada = pessoaRepository.save(pessoa);

        return MessageResponseDTO.builder()
                .message("Pessoa salva com ID: "+ pessoaGravada.getId())
                .build();

    }

    public List<PessoaDTO> findAll() {

        List<PessoaDTO> result = new ArrayList<>();

        List<Pessoa> pessoas = pessoaRepository.findAll();

        for (Pessoa pessoa:pessoas){
            result.add(pessoaMapper.toDTO(pessoa));
        }

        return result;
    }

    public PessoaDTO findById(Long id) {

        var pessoa = getPessoa(id);

        return pessoaMapper.toDTO(pessoa);
    }

    public void remove(Long id) {

        var pessoa = getPessoa(id);

        pessoaRepository.delete(pessoa);

    }

    public MessageResponseDTO update(Long id, PessoaDTO pessoaDTO) {

        var pessoa = getPessoa(id);

        merge(pessoaDTO, pessoa);

        var pessoaGravada = pessoaRepository.save(pessoa);

        return MessageResponseDTO.builder()
                .message("Pessoa atualizada com ID: "+ pessoaGravada.getId())
                .build();

    }

    private Pessoa getPessoa(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new PessoaNotFoundException("Id nao encontrado" + id));
        return pessoa;
    }

    private void merge(PessoaDTO pessoaDTO, Pessoa pessoa) {
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setSobreNome(pessoaDTO.getSobreNome());

        var novosTelefones = new ArrayList<Telefone>();
        for (TelefoneDTO telefoneDto: pessoaDTO.getTelefones()) {
            novosTelefones.add(new Telefone(telefoneDto.getId(), telefoneDto.getTipo(), telefoneDto.getNumero()));
        }
        pessoa.setTelefones(novosTelefones);
    }

}
