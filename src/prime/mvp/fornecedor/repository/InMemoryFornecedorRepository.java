package prime.mvp.fornecedor.repository;

import java.util.ArrayList;
import java.util.List;
import prime.mvp.fornecedor.model.FornecedorModel;

public class InMemoryFornecedorRepository implements FornecedorRepository {
    private final List<FornecedorModel> fornecedores = new ArrayList<>();
    private int proximoId = 1;

    public InMemoryFornecedorRepository() {
        salvar(new FornecedorModel(0, "ACME Ltda", "12.345.678/0001-99", "contato@acme.com", "(71) 3333-0000"));
        salvar(new FornecedorModel(0, "Beta Distribuidora", "98.765.432/0001-88", "vendas@beta.com", "(71) 3333-1111"));
    }

    @Override
    public List<FornecedorModel> buscarTodos() {
        return new ArrayList<>(fornecedores);
    }

    @Override
    public void salvar(FornecedorModel fornecedor) {
        if (fornecedor.getId() == 0) {
            fornecedor.setId(proximoId++);
            fornecedores.add(fornecedor);
            return;
        }

        for (int i = 0; i < fornecedores.size(); i++) {
            if (fornecedores.get(i).getId() == fornecedor.getId()) {
                fornecedores.set(i, fornecedor);
                return;
            }
        }
    }

    @Override
    public void remover(int id) {
        fornecedores.removeIf(fornecedor -> fornecedor.getId() == id);
    }
}
