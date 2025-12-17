package com.calife.financeiro.service;

import com.calife.financeiro.domain.CategoriaDespesa;
import com.calife.financeiro.repository.CategoriaDespesaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class CategoriaDespesaServiceImpl implements CategoriaDespesaService {

    private final CategoriaDespesaRepository repository;

    public CategoriaDespesaServiceImpl(CategoriaDespesaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void salvar(CategoriaDespesa categoriaDespesa) {
        repository.save(categoriaDespesa);
    }

    @Override
    public void editar(CategoriaDespesa categoriaDespesa) {
        repository.save(categoriaDespesa);
    }

    @Override
    public void excluir(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoriaDespesa buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaDespesa> buscarTodos() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CategoriaDespesa> buscarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
