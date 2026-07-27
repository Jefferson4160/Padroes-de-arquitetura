package prime.mvp.cliente.presenter;

import java.util.List;
import prime.mvp.cliente.model.ClienteModel;
import prime.mvp.cliente.service.ClienteService;
import prime.mvp.cliente.view.ClienteViewContract;

public class ClientePresenter {
    private final ClienteViewContract view;
    private final ClienteService service;

    public ClientePresenter(ClienteViewContract view, ClienteService service) {
        this.view = view;
        this.service = service;
    }

    public void iniciar() {
        carregarClientes();
    }

    public void carregarClientes() {
        List<ClienteModel> clientes = service.listarClientes();
        view.mostrarClientes(clientes);
    }

    public void salvarCliente(int id, String nome, String cpf, String email) {
        try {
            service.salvarCliente(id, nome, cpf, email);
            view.limparFormulario();
            carregarClientes();
            view.mostrarMensagem("Cliente salvo com sucesso.");
        } catch (IllegalArgumentException ex) {
            view.mostrarMensagem(ex.getMessage());
        }
    }

    public void editarClienteSelecionado(int linha) {
        if (linha < 0) {
            return;
        }

        List<ClienteModel> clientes = service.listarClientes();
        if (linha < clientes.size()) {
            view.preencherFormulario(clientes.get(linha));
            view.selecionarLinha(linha);
        }
    }

    public void novoCliente() {
        view.limparFormulario();
        view.selecionarLinha(-1);
    }

    public void removerCliente(int id) {
        if (id <= 0) {
            view.mostrarMensagem("Selecione um cliente antes de remover.");
            return;
        }

        service.removerCliente(id);
        carregarClientes();
        view.mostrarMensagem("Cliente removido.");
    }
}
