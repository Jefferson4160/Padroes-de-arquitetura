package prime.mvp.fornecedor.view;

import java.util.List;
import prime.mvp.fornecedor.model.FornecedorModel;

public class FornecedorConsoleView implements FornecedorViewContract {

    @Override
    public void mostrarFornecedores(List<FornecedorModel> fornecedores) {
        System.out.println("=== Fornecedores ===");
        if (fornecedores.isEmpty()) {
            System.out.println("Nenhum fornecedor cadastrado.");
            return;
        }

        for (FornecedorModel fornecedor : fornecedores) {
            System.out.println(fornecedor.getId() + " - " + fornecedor.getNome() + " | " + fornecedor.getCnpj() + " | " + fornecedor.getEmail());
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
    public void preencherFormulario(FornecedorModel fornecedor) {
        System.out.println("Carregando fornecedor: " + fornecedor.getNome());
    }

    @Override
    public void selecionarLinha(int linha) {
        System.out.println("Linha selecionada: " + linha);
    }
}
