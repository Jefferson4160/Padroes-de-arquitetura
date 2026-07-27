package prime.mvp.cliente.view;

import java.util.List;
import prime.mvp.cliente.model.ClienteModel;

public class ClienteConsoleView implements ClienteViewContract {

    @Override
    public void mostrarClientes(List<ClienteModel> clientes) {
        System.out.println("=== Clientes ===");
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        for (ClienteModel cliente : clientes) {
            System.out.println(cliente.getId() + " - " + cliente.getNome() + " | " + cliente.getCpf() + " | " + cliente.getEmail());
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
    public void preencherFormulario(ClienteModel cliente) {
        System.out.println("Carregando cliente: " + cliente.getNome());
    }

    @Override
    public void selecionarLinha(int linha) {
        System.out.println("Linha selecionada: " + linha);
    }
}
