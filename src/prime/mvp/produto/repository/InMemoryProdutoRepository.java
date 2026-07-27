package prime.mvp.produto.repository;

import java.util.ArrayList;
import java.util.List;
import prime.mvp.produto.model.ProdutoModel;

public class InMemoryProdutoRepository implements ProdutoRepository {
    private final List<ProdutoModel> produtos = new ArrayList<>();

    public InMemoryProdutoRepository() {
        salvar(new ProdutoModel("P001", "Caneta Azul", 1.50, 2.50, 100));
        salvar(new ProdutoModel("P002", "Caderno 100 folhas", 8.00, 12.00, 50));
    }

    @Override
    public List<ProdutoModel> buscarTodos() {
        return new ArrayList<>(produtos);
    }

    @Override
    public void salvar(ProdutoModel produto) {
        if (produto.getCodigo() == null || produto.getCodigo().trim().isEmpty()) {
            return;
        }

        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getCodigo().equalsIgnoreCase(produto.getCodigo())) {
                produtos.set(i, produto);
                return;
            }
        }

        produtos.add(produto);
    }

    @Override
    public void remover(String codigo) {
        produtos.removeIf(produto -> produto.getCodigo().equalsIgnoreCase(codigo));
    }
}
