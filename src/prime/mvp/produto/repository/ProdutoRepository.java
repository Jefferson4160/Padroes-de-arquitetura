package prime.mvp.produto.repository;

import java.util.List;
import prime.mvp.produto.model.ProdutoModel;

public interface ProdutoRepository {
    List<ProdutoModel> buscarTodos();
    void salvar(ProdutoModel produto);
    void remover(String codigo);
}
