package prime.mvp.cliente.service;

import java.util.List;
import prime.mvp.cliente.model.ClienteModel;
import prime.mvp.cliente.repository.ClienteRepository;

public class ClienteService {
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<ClienteModel> listarClientes() {
        return repository.buscarTodos();
    }

    public void salvarCliente(int id, String nome, String cpf, String email) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }

        ClienteModel cliente = new ClienteModel();
        cliente.setId(id);
        cliente.setNome(nome.trim());
        cliente.setCpf(cpf == null ? "" : cpf.trim());
        cliente.setEmail(email == null ? "" : email.trim());
        cliente.setAtivo(true);
        repository.salvar(cliente);
    }

    public void removerCliente(int id) {
        repository.remover(id);
    }
}
