package prime.mvp.shell.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import prime.mvp.cliente.view.ClienteSwingView;
import prime.mvp.fornecedor.view.FornecedorSwingView;
import prime.mvp.produto.view.ProdutoSwingView;
import prime.mvp.shell.model.ModuloModel;
import prime.mvp.shell.presenter.ShellPresenter;

public class ShellSwingView extends JFrame implements ShellViewContract {
    private final ShellPresenter presenter;
    private final DefaultTableModel tableModel;
    private final JTable tabela = new JTable();

    public ShellSwingView() {
        super("Shell MVP - Módulos");
        this.presenter = new ShellPresenter(this);
        this.tableModel = new DefaultTableModel(new Object[]{"Módulo", "Descrição"}, 0);
        tabela.setModel(tableModel);
        inicializarInterface();
        presenter.iniciar();
    }

    private void inicializarInterface() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JButton btnAbrir = new JButton("Abrir módulo selecionado");
        btnAbrir.addActionListener(e -> {
            int selectedRow = tabela.getSelectedRow();
            if (selectedRow >= 0) {
                String nome = tableModel.getValueAt(selectedRow, 0).toString();
                presenter.abrirModulo(nome);
            }
        });

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelBotoes.add(btnAbrir);

        add(painelBotoes, BorderLayout.NORTH);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        setSize(500, 300);
        setLocationRelativeTo(null);
    }

    @Override
    public void mostrarModulos(List<ModuloModel> modulos) {
        tableModel.setRowCount(0);
        for (ModuloModel modulo : modulos) {
            tableModel.addRow(new Object[]{modulo.getNome(), modulo.getDescricao()});
        }
    }

    @Override
    public void mostrarMensagem(String mensagem) {
        javax.swing.JOptionPane.showMessageDialog(this, mensagem);
    }

    @Override
    public void abrirCliente() {
        ClienteSwingView view = new ClienteSwingView();
        view.setVisible(true);
    }

    @Override
    public void abrirFornecedor() {
        FornecedorSwingView view = new FornecedorSwingView();
        view.setVisible(true);
    }

    @Override
    public void abrirProduto() {
        ProdutoSwingView view = new ProdutoSwingView();
        view.setVisible(true);
    }
}
