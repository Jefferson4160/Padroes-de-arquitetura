package prime.mvp.fornecedor.presenter;

import java.util.List;
import prime.mvp.fornecedor.model.FornecedorModel;
import prime.mvp.fornecedor.service.FornecedorService;
import prime.mvp.fornecedor.view.FornecedorViewContract;

public class FornecedorPresenter {
    private final FornecedorViewContract view;
    private final FornecedorService service;

    public FornecedorPresenter(FornecedorViewContract view, FornecedorService service) {
        this.view = view;
        this.service = service;
    }

    public void iniciar() {
        carregarFornecedores();
    }

    public void carregarFornecedores() {
        List<FornecedorModel> fornecedores = service.listarFornecedores();
        view.mostrarFornecedores(fornecedores);
    }

    public void salvarFornecedor(int id, String nome, String cnpj, String email, String telefone) {
        try {
            service.salvarFornecedor(id, nome, cnpj, email, telefone);
            view.limparFormulario();
            carregarFornecedores();
            view.mostrarMensagem("Fornecedor salvo com sucesso.");
        } catch (IllegalArgumentException ex) {
            view.mostrarMensagem(ex.getMessage());
        }
    }

    public void editarFornecedorSelecionado(int linha) {
        if (linha < 0) {
            return;
        }

        List<FornecedorModel> fornecedores = service.listarFornecedores();
        if (linha < fornecedores.size()) {
            view.preencherFormulario(fornecedores.get(linha));
            view.selecionarLinha(linha);
        }
    }

    public void novoFornecedor() {
        view.limparFormulario();
        view.selecionarLinha(-1);
    }

    public void removerFornecedor(int id) {
        if (id <= 0) {
            view.mostrarMensagem("Selecione um fornecedor antes de remover.");
            return;
        }

        service.removerFornecedor(id);
        carregarFornecedores();
        view.mostrarMensagem("Fornecedor removido.");
    }
}
