package com.calife.financeiro.service;

import com.calife.financeiro.dao.LancamentoDespesaDao;
import com.calife.financeiro.domain.LancamentoDespesa;
import com.calife.financeiro.domain.User;
import com.calife.financeiro.repository.LancamentoDespesaRepository;
import com.calife.financeiro.web.controller.GlobalControllerAdvice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class LancamentoDespesaServiceImpl implements LancamentoDespesaService {

    private final LancamentoDespesaRepository repository;

    private final LancamentoDespesaDao dao;

    private final GlobalControllerAdvice gca;

    public LancamentoDespesaServiceImpl(LancamentoDespesaRepository repository,
                                        LancamentoDespesaDao dao, GlobalControllerAdvice gca) {
        this.repository = repository;
        this.dao = dao;
        this.gca = gca;
    }

    @Override
    public void salvar(LancamentoDespesa lancamentoDespesa) {
        User user = gca.getUserLogado();
        lancamentoDespesa.setUser(user);

        repository.save(lancamentoDespesa);
    }

    @Override
    public void editar(LancamentoDespesa lancamentoDespesa) {
        User user = gca.getUserLogado();
        lancamentoDespesa.setUser(user);

        repository.save(lancamentoDespesa);
    }

    @Override
    public void excluir(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public LancamentoDespesa buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LancamentoDespesa> buscarTodos() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LancamentoDespesa> buscarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LancamentoDespesa> buscarPorMesAno(int mes, int ano, Pageable pageable) {
        return repository.findByMesAndAno(mes, ano, pageable);
    }
}
