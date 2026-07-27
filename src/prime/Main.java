package prime;

import java.awt.GraphicsEnvironment;
import prime.mvp.produto.presenter.ProdutoPresenter;
import prime.mvp.produto.repository.InMemoryProdutoRepository;
import prime.mvp.produto.service.ProdutoService;
import prime.mvp.produto.view.ProdutoConsoleView;
import prime.mvp.produto.view.ProdutoSwingView;

public class Main {

    public static void main(String[] args) {
        if (GraphicsEnvironment.isHeadless()) {
            ProdutoConsoleView consoleView = new ProdutoConsoleView();
            ProdutoPresenter presenter = new ProdutoPresenter(consoleView, new ProdutoService(new InMemoryProdutoRepository()));
            presenter.iniciar();
            presenter.salvarProduto("P003", "Produto de Teste", "5.00", "10.00", "20");
            return;
        }

        java.awt.EventQueue.invokeLater(() -> {
            ProdutoSwingView view = new ProdutoSwingView();
            view.setVisible(true);
        });
    }
}