package prime.mvp.cliente.view;

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
import prime.mvp.cliente.model.ClienteModel;
import prime.mvp.cliente.presenter.ClientePresenter;
import prime.mvp.cliente.repository.InMemoryClienteRepository;
import prime.mvp.cliente.service.ClienteService;

public class ClienteSwingView extends JFrame implements ClienteViewContract {
    private final ClientePresenter presenter;
    private final DefaultTableModel tableModel;
    private final JTextField txtNome = new JTextField(20);
    private final JTextField txtCpf = new JTextField(20);
    private final JTextField txtEmail = new JTextField(20);
    private final JTable tabela = new JTable();
    private int clienteSelecionadoId = 0;

    public ClienteSwingView() {
        super("Clientes - MVP");
        this.presenter = new ClientePresenter(this, new ClienteService(new InMemoryClienteRepository()));
        this.tableModel = new DefaultTableModel(new Object[]{"ID", "Nome", "CPF", "Email"}, 0);
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
        painelFormulario.add(new JLabel("CPF:"));
        painelFormulario.add(txtCpf);
        painelFormulario.add(new JLabel("Email:"));
        painelFormulario.add(txtEmail);

        JButton btnNovo = new JButton("Novo");
        btnNovo.addActionListener(e -> presenter.novoCliente());

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> presenter.salvarCliente(clienteSelecionadoId, txtNome.getText(), txtCpf.getText(), txtEmail.getText()));

        JButton btnRemover = new JButton("Remover selecionado");
        btnRemover.addActionListener(e -> {
            int selectedRow = tabela.getSelectedRow();
            if (selectedRow >= 0) {
                int id = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
                presenter.removerCliente(id);
            }
        });

        tabela.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                presenter.editarClienteSelecionado(tabela.getSelectedRow());
            }
        });

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelBotoes.add(btnNovo);
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnRemover);

        add(painelFormulario, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.CENTER);
        add(new JScrollPane(tabela), BorderLayout.SOUTH);

        setSize(600, 300);
        setLocationRelativeTo(null);
    }

    @Override
    public void mostrarClientes(List<ClienteModel> clientes) {
        tableModel.setRowCount(0);
        for (ClienteModel cliente : clientes) {
            tableModel.addRow(new Object[]{cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getEmail()});
        }
    }

    @Override
    public void limparFormulario() {
        txtNome.setText("");
        txtCpf.setText("");
        txtEmail.setText("");
        clienteSelecionadoId = 0;
    }

    @Override
    public void mostrarMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }

    @Override
    public void preencherFormulario(ClienteModel cliente) {
        if (cliente == null) {
            limparFormulario();
            return;
        }
        clienteSelecionadoId = cliente.getId();
        txtNome.setText(cliente.getNome() != null ? cliente.getNome() : "");
        txtCpf.setText(cliente.getCpf() != null ? cliente.getCpf() : "");
        txtEmail.setText(cliente.getEmail() != null ? cliente.getEmail() : "");
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
