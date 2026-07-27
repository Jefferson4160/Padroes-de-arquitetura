package prime.mvp.fornecedor.view;

import java.util.List;
import prime.mvp.fornecedor.model.FornecedorModel;

public interface FornecedorViewContract {
    void mostrarFornecedores(List<FornecedorModel> fornecedores);
    void limparFormulario();
    void mostrarMensagem(String mensagem);
    void preencherFormulario(FornecedorModel fornecedor);
    void selecionarLinha(int linha);
}
