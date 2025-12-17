package com.calife.financeiro.service;

import com.calife.financeiro.domain.LancamentoReceita;
import com.calife.financeiro.domain.User;
import com.calife.financeiro.repository.LancamentoReceitaRepository;
import com.calife.financeiro.web.controller.GlobalControllerAdvice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class LancamentoReceitaServiceImpl implements LancamentoReceitaService {

    private final LancamentoReceitaRepository repository;

    private final GlobalControllerAdvice gca;

    public LancamentoReceitaServiceImpl(LancamentoReceitaRepository repository, GlobalControllerAdvice gca) {
        this.repository = repository;
        this.gca = gca;
    }

    @Override
    public void salvar(LancamentoReceita lancamentoReceita) {
        User user = gca.getUserLogado();
        lancamentoReceita.setUser(user);

        repository.save(lancamentoReceita);
    }

    @Override
    public void editar(LancamentoReceita lancamentoReceita) {
        User user = gca.getUserLogado();
        lancamentoReceita.setUser(user);

        repository.save(lancamentoReceita);
    }

    @Override
    public void excluir(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public LancamentoReceita buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LancamentoReceita> buscarTodos() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LancamentoReceita> buscarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LancamentoReceita> buscarPorMesAno(int mes, int ano, Pageable pageable) {
        return repository.findByMesAndAno(mes, ano, pageable);
    }
}
