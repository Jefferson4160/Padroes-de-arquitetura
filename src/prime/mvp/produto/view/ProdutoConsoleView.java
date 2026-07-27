package prime.mvp.produto.view;

import java.util.List;
import prime.mvp.produto.model.ProdutoModel;

public class ProdutoConsoleView implements ProdutoViewContract {

    @Override
    public void mostrarProdutos(List<ProdutoModel> produtos) {
        System.out.println("=== Produtos ===");
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        for (ProdutoModel produto : produtos) {
            System.out.println(produto.getCodigo() + " - " + produto.getDescricao() + " | Compra: " + produto.getValorCompra() + " | Venda: " + produto.getValorVenda() + " | Estoque: " + produto.getEstoque());
        }
    }

    @Override
    public void limparFormulario() {
        System.out.println("Formulário limpo.");
    }

    @Override
    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    @Override
    public void preencherFormulario(ProdutoModel produto) {
        System.out.println("Carregando produto: " + produto.getDescricao());
    }

    @Override
    public void selecionarLinha(int linha) {
        System.out.println("Linha selecionada: " + linha);
    }
}
