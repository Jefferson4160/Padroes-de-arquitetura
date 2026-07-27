package prime.mvp.fornecedor.service;

import java.util.List;
import prime.mvp.fornecedor.model.FornecedorModel;
import prime.mvp.fornecedor.repository.FornecedorRepository;

public class FornecedorService {
    private final FornecedorRepository repository;

    public FornecedorService(FornecedorRepository repository) {
        this.repository = repository;
    }

    public List<FornecedorModel> listarFornecedores() {
        return repository.buscarTodos();
    }

    public void salvarFornecedor(int id, String nome, String cnpj, String email, String telefone) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }

        FornecedorModel fornecedor = new FornecedorModel();
        fornecedor.setId(id);
        fornecedor.setNome(nome.trim());
        fornecedor.setCnpj(cnpj == null ? "" : cnpj.trim());
        fornecedor.setEmail(email == null ? "" : email.trim());
        fornecedor.setTelefone(telefone == null ? "" : telefone.trim());
        repository.salvar(fornecedor);
    }

    public void removerFornecedor(int id) {
        repository.remover(id);
    }
}
