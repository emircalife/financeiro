package com.calife.financeiro.service;

import com.calife.financeiro.dao.*;
import com.calife.financeiro.domain.CategoriaDespesa;
import com.calife.financeiro.domain.CategoriaReceita;
import com.calife.financeiro.domain.DTO.MesAnoDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional(readOnly = true)
public class HomeServiceImpl implements HomeService {
    private final UserDao userDao;

    private final LancamentoDespesaDao lancamentoDespesaDao;
    private final LancamentoReceitaDao lancamentoReceitaDao;
    private final CategoriaDespesaDao categoriaDespesaDao;
    private final CategoriaReceitaDao categoriaReceitaDao;

    public HomeServiceImpl(LancamentoDespesaDao lancamentoDespesaDao, LancamentoReceitaDao lancamentoReceitaDao,
                           CategoriaDespesaDao categoriaDespesaDao, CategoriaReceitaDao categoriaReceitaDao,
                           UserDao userDao) {
        this.lancamentoDespesaDao = lancamentoDespesaDao;
        this.lancamentoReceitaDao = lancamentoReceitaDao;
        this.categoriaDespesaDao = categoriaDespesaDao;
        this.categoriaReceitaDao = categoriaReceitaDao;
        this.userDao = userDao;
    }

    @Override
    public List<MesAnoDTO> getLancamentoDespesaMesAno() {
        return lancamentoDespesaDao.getLancamentoDespesaMesAno();
    }

    @Override
    public List<MesAnoDTO> getLancamentoReceitaMesAno() {
        return lancamentoReceitaDao.getLancamentoReceitaMesAno();
    }

    @Override
    public List<String> getMesAno() {

        List<MesAnoDTO> despesas = lancamentoDespesaDao.getLancamentoDespesaMesAno();
        List<MesAnoDTO> receitas = lancamentoReceitaDao.getLancamentoReceitaMesAno();

        Set<String> setMesAno = Stream.concat(despesas.stream(), receitas.stream())
                .map(dto -> String.format("%02d/%04d", dto.getMes(), dto.getAno()))
                .collect(Collectors.toSet());

        LocalDate dataAtual = LocalDate.now();
        for (int i = 0; i < 12; i++) {
            LocalDate proximoMes = dataAtual.plusMonths(i);
            setMesAno.add(String.format("%02d/%04d", proximoMes.getMonthValue(), proximoMes.getYear()));
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        List<String> sortedList = setMesAno.stream()
                .map(s -> YearMonth.parse(s, formatter))
                .sorted(Comparator.reverseOrder())
                .map(ym -> ym.format(formatter))
                .collect(Collectors.toList());

        sortedList.add(0, "<<<TODOS>>>");

        return sortedList;
    }

    @Override
    public CategoriaDespesa buscarPorCategoriaDespesaId(Long id) {
        return categoriaDespesaDao.findById(id);
    }

    @Override
    public CategoriaReceita buscarPorCategoriaReceitaId(Long id) {
        return categoriaReceitaDao.findById(id);
    }

    @Override
    public List<CategoriaDespesa> buscarCategoriaDespesaTodos() {
        return categoriaDespesaDao.findAll();
    }

    @Override
    public List<CategoriaReceita> buscarCategoriaReceitaTodos() {
        return categoriaReceitaDao.findAll();
    }
}
