package prime;

import java.awt.GraphicsEnvironment;
import prime.mvp.fornecedor.presenter.FornecedorPresenter;
import prime.mvp.fornecedor.repository.InMemoryFornecedorRepository;
import prime.mvp.fornecedor.service.FornecedorService;
import prime.mvp.fornecedor.view.FornecedorConsoleView;
import prime.mvp.fornecedor.view.FornecedorSwingView;

public class Main {

    public static void main(String[] args) {
        if (GraphicsEnvironment.isHeadless()) {
            FornecedorConsoleView consoleView = new FornecedorConsoleView();
            FornecedorPresenter presenter = new FornecedorPresenter(consoleView, new FornecedorService(new InMemoryFornecedorRepository()));
            presenter.iniciar();
            presenter.salvarFornecedor(0, "Fornecedor de Teste", "12.345.678/0001-99", "teste@fornecedor.com", "(71) 99999-0000");
            return;
        }

        java.awt.EventQueue.invokeLater(() -> {
            FornecedorSwingView view = new FornecedorSwingView();
            view.setVisible(true);
        });
    }
}