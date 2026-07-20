package prime.mvp.cliente.repository;

import java.util.List;
import prime.mvp.cliente.model.ClienteModel;

public interface ClienteRepository {
    List<ClienteModel> buscarTodos();
    void salvar(ClienteModel cliente);
    void remover(int id);
}
