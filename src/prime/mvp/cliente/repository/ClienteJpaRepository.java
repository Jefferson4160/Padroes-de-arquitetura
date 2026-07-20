package prime.mvp.cliente.repository;

import java.util.ArrayList;
import java.util.List;
import prime.mvp.cliente.model.ClienteModel;

public class ClienteJpaRepository implements ClienteRepository {
    private final List<ClienteModel> clientes = new ArrayList<>();

    public ClienteJpaRepository() {
        clientes.add(new ClienteModel(1, "Cliente Exemplo", "123.456.789-00", "teste@exemplo.com", true));
    }

    @Override
    public List<ClienteModel> buscarTodos() {
        return new ArrayList<>(clientes);
    }

    @Override
    public void salvar(ClienteModel clienteModel) {
        if (clienteModel.getId() == 0) {
            clienteModel.setId(clientes.size() + 1);
        }
        clientes.removeIf(cliente -> cliente.getId() == clienteModel.getId());
        clientes.add(clienteModel);
    }

    @Override
    public void remover(int id) {
        clientes.removeIf(cliente -> cliente.getId() == id);
    }
}
