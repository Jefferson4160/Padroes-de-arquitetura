package prime.mvp.produto.view;

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
import prime.mvp.produto.model.ProdutoModel;
import prime.mvp.produto.presenter.ProdutoPresenter;
import prime.mvp.produto.repository.InMemoryProdutoRepository;
import prime.mvp.produto.service.ProdutoService;

public class ProdutoSwingView extends JFrame implements ProdutoViewContract {
    private final ProdutoPresenter presenter;
    private final DefaultTableModel tableModel;
    private final JTextField txtCodigo = new JTextField(20);
    private final JTextField txtDescricao = new JTextField(20);
    private final JTextField txtValorCompra = new JTextField(20);
    private final JTextField txtValorVenda = new JTextField(20);
    private final JTextField txtEstoque = new JTextField(20);
    private final JTable tabela = new JTable();
    private String produtoSelecionadoCodigo = "";

    public ProdutoSwingView() {
        super("Produtos - MVP");
        this.presenter = new ProdutoPresenter(this, new ProdutoService(new InMemoryProdutoRepository()));
        this.tableModel = new DefaultTableModel(new Object[]{"Código", "Descrição", "Compra", "Venda", "Estoque"}, 0);
        tabela.setModel(tableModel);

        inicializarInterface();
        presenter.iniciar();
    }

    private void inicializarInterface() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel painelFormulario = new JPanel(new GridLayout(5, 2, 5, 5));
        painelFormulario.add(new JLabel("Código:"));
        painelFormulario.add(txtCodigo);
        painelFormulario.add(new JLabel("Descrição:"));
        painelFormulario.add(txtDescricao);
        painelFormulario.add(new JLabel("Valor Compra:"));
        painelFormulario.add(txtValorCompra);
        painelFormulario.add(new JLabel("Valor Venda:"));
        painelFormulario.add(txtValorVenda);
        painelFormulario.add(new JLabel("Estoque:"));
        painelFormulario.add(txtEstoque);

        JButton btnNovo = new JButton("Novo");
        btnNovo.addActionListener(e -> presenter.novoProduto());

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> presenter.salvarProduto(txtCodigo.getText(), txtDescricao.getText(), txtValorCompra.getText(), txtValorVenda.getText(), txtEstoque.getText()));

        JButton btnRemover = new JButton("Remover selecionado");
        btnRemover.addActionListener(e -> {
            int selectedRow = tabela.getSelectedRow();
            if (selectedRow >= 0) {
                String codigo = tableModel.getValueAt(selectedRow, 0).toString();
                presenter.removerProduto(codigo);
            }
        });

        tabela.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                presenter.editarProdutoSelecionado(tabela.getSelectedRow());
            }
        });

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelBotoes.add(btnNovo);
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnRemover);

        add(painelFormulario, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.CENTER);
        add(new JScrollPane(tabela), BorderLayout.SOUTH);

        setSize(760, 360);
        setLocationRelativeTo(null);
    }

    @Override
    public void mostrarProdutos(List<ProdutoModel> produtos) {
        tableModel.setRowCount(0);
        for (ProdutoModel produto : produtos) {
            tableModel.addRow(new Object[]{produto.getCodigo(), produto.getDescricao(), produto.getValorCompra(), produto.getValorVenda(), produto.getEstoque()});
        }
    }

    @Override
    public void limparFormulario() {
        txtCodigo.setText("");
        txtDescricao.setText("");
        txtValorCompra.setText("");
        txtValorVenda.setText("");
        txtEstoque.setText("");
        produtoSelecionadoCodigo = "";
    }

    @Override
    public void mostrarMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }

    @Override
    public void preencherFormulario(ProdutoModel produto) {
        if (produto == null) {
            limparFormulario();
            return;
        }
        produtoSelecionadoCodigo = produto.getCodigo();
        txtCodigo.setText(produto.getCodigo() != null ? produto.getCodigo() : "");
        txtDescricao.setText(produto.getDescricao() != null ? produto.getDescricao() : "");
        txtValorCompra.setText(String.valueOf(produto.getValorCompra()));
        txtValorVenda.setText(String.valueOf(produto.getValorVenda()));
        txtEstoque.setText(String.valueOf(produto.getEstoque()));
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
