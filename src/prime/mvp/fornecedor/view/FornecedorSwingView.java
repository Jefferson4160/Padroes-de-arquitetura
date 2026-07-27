package prime.mvp.fornecedor.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import prime.mvp.fornecedor.model.FornecedorModel;
import prime.mvp.fornecedor.presenter.FornecedorPresenter;
import prime.mvp.fornecedor.repository.InMemoryFornecedorRepository;
import prime.mvp.fornecedor.service.FornecedorService;

public class FornecedorSwingView extends JFrame implements FornecedorViewContract {
    private final FornecedorPresenter presenter;
    private final DefaultTableModel tableModel;
    private final JTextField txtNome = new JTextField(20);
    private final JTextField txtCnpj = new JTextField(20);
    private final JTextField txtEmail = new JTextField(20);
    private final JTextField txtTelefone = new JTextField(20);
    private final JTable tabela = new JTable();
    private int fornecedorSelecionadoId = 0;

    public FornecedorSwingView() {
        super("Fornecedores - MVP");
        this.presenter = new FornecedorPresenter(this, new FornecedorService(new InMemoryFornecedorRepository()));
        this.tableModel = new DefaultTableModel(new Object[]{"ID", "Nome", "CNPJ", "Email", "Telefone"}, 0);
        tabela.setModel(tableModel);

        inicializarInterface();
        presenter.iniciar();
    }

    private void inicializarInterface() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel painelFormulario = new JPanel(new GridLayout(4, 2, 5, 5));
        painelFormulario.add(new JLabel("Nome:"));
        painelFormulario.add(txtNome);
        painelFormulario.add(new JLabel("CNPJ:"));
        painelFormulario.add(txtCnpj);
        painelFormulario.add(new JLabel("Email:"));
        painelFormulario.add(txtEmail);
        painelFormulario.add(new JLabel("Telefone:"));
        painelFormulario.add(txtTelefone);

        JButton btnNovo = new JButton("Novo");
        btnNovo.addActionListener(e -> presenter.novoFornecedor());

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> presenter.salvarFornecedor(fornecedorSelecionadoId, txtNome.getText(), txtCnpj.getText(), txtEmail.getText(), txtTelefone.getText()));

        JButton btnRemover = new JButton("Remover selecionado");
        btnRemover.addActionListener(e -> {
            int selectedRow = tabela.getSelectedRow();
            if (selectedRow >= 0) {
                int id = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
                presenter.removerFornecedor(id);
            }
        });

        tabela.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                presenter.editarFornecedorSelecionado(tabela.getSelectedRow());
            }
        });

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelBotoes.add(btnNovo);
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnRemover);

        add(painelFormulario, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.CENTER);
        add(new JScrollPane(tabela), BorderLayout.SOUTH);

        setSize(700, 320);
        setLocationRelativeTo(null);
    }

    @Override
    public void mostrarFornecedores(List<FornecedorModel> fornecedores) {
        tableModel.setRowCount(0);
        for (FornecedorModel fornecedor : fornecedores) {
            tableModel.addRow(new Object[]{fornecedor.getId(), fornecedor.getNome(), fornecedor.getCnpj(), fornecedor.getEmail(), fornecedor.getTelefone()});
        }
    }

    @Override
    public void limparFormulario() {
        txtNome.setText("");
        txtCnpj.setText("");
        txtEmail.setText("");
        txtTelefone.setText("");
        fornecedorSelecionadoId = 0;
    }

    @Override
    public void mostrarMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }

    @Override
    public void preencherFormulario(FornecedorModel fornecedor) {
        if (fornecedor == null) {
            limparFormulario();
            return;
        }
        fornecedorSelecionadoId = fornecedor.getId();
        txtNome.setText(fornecedor.getNome() != null ? fornecedor.getNome() : "");
        txtCnpj.setText(fornecedor.getCnpj() != null ? fornecedor.getCnpj() : "");
        txtEmail.setText(fornecedor.getEmail() != null ? fornecedor.getEmail() : "");
        txtTelefone.setText(fornecedor.getTelefone() != null ? fornecedor.getTelefone() : "");
    }

    @Override
    public void selecionarLinha(int linha) {
        if (linha >= 0 && linha < tabela.getRowCount()) {
            tabela.setRowSelectionInterval(linha, linha);
        } else {
            tabela.clearSelection();
        }
    }
}
