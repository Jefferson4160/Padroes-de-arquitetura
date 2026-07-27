package prime.mvp.produto.service;

import java.util.List;
import prime.mvp.produto.model.ProdutoModel;
import prime.mvp.produto.repository.ProdutoRepository;

public class ProdutoService {
    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<ProdutoModel> listarProdutos() {
        return repository.buscarTodos();
    }

    public void salvarProduto(String codigo, String descricao, double valorCompra, double valorVenda, int estoque) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição é obrigatória.");
        }

        ProdutoModel produto = new ProdutoModel();
        produto.setCodigo(codigo == null ? "" : codigo.trim());
        produto.setDescricao(descricao.trim());
        produto.setValorCompra(valorCompra);
        produto.setValorVenda(valorVenda);
        produto.setEstoque(estoque);
        repository.salvar(produto);
    }

    public void removerProduto(String codigo) {
        repository.remover(codigo);
    }
}
