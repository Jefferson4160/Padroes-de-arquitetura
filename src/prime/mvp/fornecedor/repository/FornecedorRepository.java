package prime.mvp.fornecedor.repository;

import java.util.List;
import prime.mvp.fornecedor.model.FornecedorModel;

public interface FornecedorRepository {
    List<FornecedorModel> buscarTodos();
    void salvar(FornecedorModel fornecedor);
    void remover(int id);
}
