package prime.mvp.cliente.view;

import java.util.List;
import prime.mvp.cliente.model.ClienteModel;

public interface ClienteViewContract {
    void mostrarClientes(List<ClienteModel> clientes);
    void limparFormulario();
    void mostrarMensagem(String mensagem);
    void preencherFormulario(ClienteModel cliente);
    void selecionarLinha(int linha);
}
