package prime;

import java.awt.GraphicsEnvironment;
import prime.mvp.cliente.presenter.ClientePresenter;
import prime.mvp.cliente.repository.InMemoryClienteRepository;
import prime.mvp.cliente.service.ClienteService;
import prime.mvp.cliente.view.ClienteConsoleView;
import prime.mvp.cliente.view.ClienteSwingView;

public class Main {

    public static void main(String[] args) {
        if (GraphicsEnvironment.isHeadless()) {
            ClienteConsoleView consoleView = new ClienteConsoleView();
            ClientePresenter presenter = new ClientePresenter(consoleView, new ClienteService(new InMemoryClienteRepository()));
            presenter.iniciar();
            presenter.salvarCliente("Cliente de Teste", "123.456.789-00", "teste@exemplo.com");
            return;
        }

        java.awt.EventQueue.invokeLater(() -> {
            ClienteSwingView view = new ClienteSwingView();
            view.setVisible(true);
        });
    }
}