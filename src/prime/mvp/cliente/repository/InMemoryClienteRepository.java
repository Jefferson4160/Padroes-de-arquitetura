package prime.mvp.cliente.repository;

import java.util.ArrayList;
import java.util.List;
import prime.mvp.cliente.model.ClienteModel;

public class InMemoryClienteRepository implements ClienteRepository {
    private final List<ClienteModel> clientes = new ArrayList<>();
    private int proximoId = 1;

    public InMemoryClienteRepository() {
        salvar(new ClienteModel(0, "Maria Souza", "111.222.333-44", "maria@teste.com", true));
        salvar(new ClienteModel(0, "João Pereira", "555.666.777-88", "joao@teste.com", true));
    }

    @Override
    public List<ClienteModel> buscarTodos() {
        return new ArrayList<>(clientes);
    }

    @Override
    public void salvar(ClienteModel cliente) {
        if (cliente.getId() == 0) {
            cliente.setId(proximoId++);
            clientes.add(cliente);
            return;
        }

        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == cliente.getId()) {
                clientes.set(i, cliente);
                return;
            }
        }
    }

    @Override
    public void remover(int id) {
        clientes.removeIf(cliente -> cliente.getId() == id);
    }
}
