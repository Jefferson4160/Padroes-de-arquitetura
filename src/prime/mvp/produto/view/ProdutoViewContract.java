package prime.mvp.produto.view;

import java.util.List;
import prime.mvp.produto.model.ProdutoModel;

public interface ProdutoViewContract {
    void mostrarProdutos(List<ProdutoModel> produtos);
    void limparFormulario();
    void mostrarMensagem(String mensagem);
    void preencherFormulario(ProdutoModel produto);
    void selecionarLinha(int linha);
}
