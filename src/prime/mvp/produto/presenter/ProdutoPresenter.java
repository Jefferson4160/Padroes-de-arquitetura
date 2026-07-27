package prime.mvp.produto.presenter;

import java.util.List;
import prime.mvp.produto.model.ProdutoModel;
import prime.mvp.produto.service.ProdutoService;
import prime.mvp.produto.view.ProdutoViewContract;

public class ProdutoPresenter {
    private final ProdutoViewContract view;
    private final ProdutoService service;

    public ProdutoPresenter(ProdutoViewContract view, ProdutoService service) {
        this.view = view;
        this.service = service;
    }

    public void iniciar() {
        carregarProdutos();
    }

    public void carregarProdutos() {
        List<ProdutoModel> produtos = service.listarProdutos();
        view.mostrarProdutos(produtos);
    }

    public void salvarProduto(String codigo, String descricao, String valorCompra, String valorVenda, String estoque) {
        try {
            double compra = Double.parseDouble(valorCompra == null || valorCompra.trim().isEmpty() ? "0" : valorCompra.trim());
            double venda = Double.parseDouble(valorVenda == null || valorVenda.trim().isEmpty() ? "0" : valorVenda.trim());
            int qtdEstoque = Integer.parseInt(estoque == null || estoque.trim().isEmpty() ? "0" : estoque.trim());
            service.salvarProduto(codigo, descricao, compra, venda, qtdEstoque);
            view.limparFormulario();
            carregarProdutos();
            view.mostrarMensagem("Produto salvo com sucesso.");
        } catch (NumberFormatException ex) {
            view.mostrarMensagem("Informe valores numéricos válidos.");
        } catch (IllegalArgumentException ex) {
            view.mostrarMensagem(ex.getMessage());
        }
    }

    public void editarProdutoSelecionado(int linha) {
        if (linha < 0) {
            return;
        }

        List<ProdutoModel> produtos = service.listarProdutos();
        if (linha < produtos.size()) {
            view.preencherFormulario(produtos.get(linha));
            view.selecionarLinha(linha);
        }
    }

    public void novoProduto() {
        view.limparFormulario();
        view.selecionarLinha(-1);
    }

    public void removerProduto(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            view.mostrarMensagem("Selecione um produto antes de remover.");
            return;
        }

        service.removerProduto(codigo);
        carregarProdutos();
        view.mostrarMensagem("Produto removido.");
    }
}
