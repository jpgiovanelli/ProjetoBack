package br.com.projback.projetoback.service;

import br.com.projback.projetoback.exception.LojistaException;
import br.com.projback.projetoback.model.Loja;
import br.com.projback.projetoback.repository.Loja_Repository;
import br.com.projback.projetoback.request.EnableLojaRequest;
import br.com.projback.projetoback.response.LojaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LojaService {

    @Autowired
    private Loja_Repository lojaRepository;

    public LojaResponse getLojaByCnpj(String cnpj) throws LojistaException {
        Optional<Loja> optLoja = lojaRepository.findByCnpj(cnpj);

        if (optLoja.isEmpty())
            throw new LojistaException("cnpj", "Lojista não encontrado.");

        return Loja.toResponse(optLoja.get());
    }

    public LojaResponse changeStatusLoja(int id, EnableLojaRequest request) throws LojistaException {
        Optional<Loja> optLoja = lojaRepository.findById(id);

        if (optLoja.isEmpty())
            throw new LojistaException("id", "Lojista não encontrado.");

        Loja loja = optLoja.get();

        loja.setEnabled(request.getEnabled());
        loja.setDtAtivacao(LocalDateTime.now());
        loja.setUserNameAtivacao(request.getUserNameAtivacao());

        lojaRepository.save(loja);
        return Loja.toResponse(loja);
    }
}
