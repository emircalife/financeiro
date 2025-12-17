package com.calife.financeiro.service;

import com.calife.financeiro.domain.CategoriaReceita;
import com.calife.financeiro.repository.CategoriaReceitaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class CategoriaReceitaServiceImpl implements CategoriaReceitaService {

    private final CategoriaReceitaRepository repository;

    public CategoriaReceitaServiceImpl(CategoriaReceitaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void salvar(CategoriaReceita categoriaReceita) {
        repository.save(categoriaReceita);
    }

    @Override
    public void editar(CategoriaReceita categoriaReceita) {
        repository.save(categoriaReceita);
    }

    @Override
    public void excluir(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoriaReceita buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaReceita> buscarTodos() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CategoriaReceita> buscarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
