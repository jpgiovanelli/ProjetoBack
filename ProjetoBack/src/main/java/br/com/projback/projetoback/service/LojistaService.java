package br.com.projback.projetoback.service;

import br.com.projback.projetoback.exception.LojistaException;
import br.com.projback.projetoback.model.DadoBancario;
import br.com.projback.projetoback.model.Endereco;
import br.com.projback.projetoback.model.Loja;
import br.com.projback.projetoback.model.Lojista;
import br.com.projback.projetoback.repository.DadoBancario_Repository;
import br.com.projback.projetoback.repository.Endereco_Repository;
import br.com.projback.projetoback.repository.Loja_Repository;
import br.com.projback.projetoback.repository.Lojista_Repository;
import br.com.projback.projetoback.request.CadastroLojistaRequest;
import br.com.projback.projetoback.response.LojistaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LojistaService {

    @Autowired
    private DadoBancario_Repository dadoBancarioRepository;
    @Autowired
    private Endereco_Repository enderecoRepository;
    @Autowired
    private Loja_Repository lojaRepository;
    @Autowired
    private Lojista_Repository lojistaRepository;

    public LojistaResponse createLojista(CadastroLojistaRequest request) throws Exception {
        Lojista lojista = Lojista.fromRequest(request);

        if (!this.lojaRepository.findByCnpj(lojista.getLojas().getFirst().getCnpj()).isEmpty()) {
            throw new LojistaException("cpnj", "CNPJ da loja já cadastrado.");
        }

        if (!this.lojistaRepository.findByCpf(lojista.getCpf()).isEmpty()) {
            throw new LojistaException("cpf", "CPF do lojista já cadastrado.");
        }

        Loja loja = lojista.getLojas().getFirst();
        Endereco endereco = loja.getEndereco().getFirst();
        DadoBancario dadoBancario = lojista.getDado_bancario().getFirst();

        dadoBancarioRepository.save(dadoBancario);
        enderecoRepository.save(endereco);
        lojaRepository.save(loja);
        lojistaRepository.save(lojista);

        return Lojista.toResponse(lojista);
    }

    public LojistaResponse getLojistaById(int id) {
        Optional<Lojista> optLojista = lojistaRepository.findById(id);

        if (optLojista.isEmpty())
            return null;

        return Lojista.toResponse(optLojista.get());
    }

    public LojistaResponse getLojistaByCpf(String cpf) throws LojistaException {
        Optional<Lojista> optLojista = lojistaRepository.findByCpf(cpf);

        if (optLojista.isEmpty())
            throw new LojistaException("cpf", "Lojista não encontrado.");

        return Lojista.toResponse(optLojista.get());
    }

    public LojistaResponse updateLojista(int id, CadastroLojistaRequest request) throws LojistaException {
        Optional<Lojista> optLojista = lojistaRepository.findById(id);

        if (optLojista.isEmpty())
            throw new LojistaException("id", "Lojista não encontrado.");

        Lojista lojista = Lojista.fromRequest(optLojista.get(), request);

        Loja loja = lojista.getLojas().getFirst();
        Endereco endereco = loja.getEndereco().getFirst();
        DadoBancario dadoBancario = lojista.getDado_bancario().getFirst();

        dadoBancarioRepository.save(dadoBancario);
        enderecoRepository.save(endereco);
        lojaRepository.save(loja);
        lojistaRepository.save(lojista);

        return Lojista.toResponse(lojista);
    }

}
