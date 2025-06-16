package view;

import controller.ControllerFuncionario;
import model.Funcionario;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViewFuncionario implements ActionListener, MouseListener {

	private JFrame frame;
	private JTable tabela;
	private JTextField txtCodigo, txtNome, txtContacto, txtDiasTrabalhados, txtSalarioDiario;
	private JComboBox<String> cbDepartamento, cbEstadoCivil;
	private JRadioButton rbMasculino, rbFeminino;
	private ButtonGroup grupoGenero;
	private JButton btnAdicionar, btnListar, btnEditar, btnRemover, btnLimpar, btnNovo, btnFiltrar;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				ViewFuncionario window = new ViewFuncionario();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public ViewFuncionario() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Gestão de Funcionários");
		frame.setBounds(100, 100, 1050, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Dados do Funcionário", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(15, 15, 680, 270);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblCodigo = new JLabel("Código:");
		lblCodigo.setBounds(15, 30, 80, 20);
		panel.add(lblCodigo);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(15, 65, 80, 20);
		panel.add(lblNome);

		JLabel lblContacto = new JLabel("Contacto:");
		lblContacto.setBounds(15, 100, 80, 20);
		panel.add(lblContacto);

		JLabel lblDepartamento = new JLabel("Departamento:");
		lblDepartamento.setBounds(15, 135, 100, 20);
		panel.add(lblDepartamento);

		JLabel lblGenero = new JLabel("Gênero:");
		lblGenero.setBounds(15, 170, 80, 20);
		panel.add(lblGenero);

		JLabel lblEstadoCivil = new JLabel("Estado Civil:");
		lblEstadoCivil.setBounds(15, 205, 80, 20);
		panel.add(lblEstadoCivil);

		JLabel lblDias = new JLabel("Dias Trabalhados:");
		lblDias.setBounds(15, 240, 120, 20);
		panel.add(lblDias);

		JLabel lblSalario = new JLabel("Salário Diário:");
		lblSalario.setBounds(350, 240, 100, 20);
		panel.add(lblSalario);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(120, 25, 180, 30);
		panel.add(txtCodigo);

		txtNome = new JTextField();
		txtNome.setBounds(120, 60, 400, 30);
		panel.add(txtNome);

		txtContacto = new JTextField();
		txtContacto.setBounds(120, 95, 250, 30);
		panel.add(txtContacto);

		cbDepartamento = new JComboBox<>(new String[] { "RH", "Financeiro", "TI", "Marketing", "Operacoes" });
		cbDepartamento.setBounds(120, 130, 250, 30);
		panel.add(cbDepartamento);

		rbMasculino = new JRadioButton("Masculino");
		rbMasculino.setBounds(120, 165, 120, 30);
		panel.add(rbMasculino);

		rbFeminino = new JRadioButton("Feminino");
		rbFeminino.setBounds(250, 165, 120, 30);
		panel.add(rbFeminino);

		grupoGenero = new ButtonGroup();
		grupoGenero.add(rbMasculino);
		grupoGenero.add(rbFeminino);

		cbEstadoCivil = new JComboBox<>(new String[] { "Solteiro", "Casado", "Divorciado", "Viúvo" });
		cbEstadoCivil.setBounds(120, 200, 250, 30);
		panel.add(cbEstadoCivil);

		txtDiasTrabalhados = new JTextField();
		txtDiasTrabalhados.setBounds(140, 235, 180, 30);
		panel.add(txtDiasTrabalhados);

		txtSalarioDiario = new JTextField();
		txtSalarioDiario.setBounds(440, 235, 180, 30);
		panel.add(txtSalarioDiario);

		JPanel panelOperacoes = new JPanel();
		panelOperacoes.setBorder(new TitledBorder(null, "Operações", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelOperacoes.setBounds(710, 15, 300, 270);
		frame.getContentPane().add(panelOperacoes);
		panelOperacoes.setLayout(null);

		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(50, 15, 200, 35);
		btnAdicionar.addActionListener(this);
		panelOperacoes.add(btnAdicionar);

		btnListar = new JButton("Listar Todos");
		btnListar.setBounds(50, 55, 200, 35);
		btnListar.addActionListener(this);
		panelOperacoes.add(btnListar);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(50, 215, 200, 35);
		btnFiltrar.addActionListener(this);
		panelOperacoes.add(btnFiltrar);
		
		
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(50, 95, 200, 35);
		btnEditar.addActionListener(this);
		panelOperacoes.add(btnEditar);

		btnRemover = new JButton("Remover");
		btnRemover.setBounds(50, 135, 200, 35);
		btnRemover.addActionListener(this);
		panelOperacoes.add(btnRemover);

		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(50, 175, 200, 35);
		btnLimpar.addActionListener(this);
		panelOperacoes.add(btnLimpar);

//		btnNovo = new JButton("Novo Código");
//		btnNovo.setBounds(50, 215, 200, 35);
//		btnNovo.addActionListener(this);
//		panelOperacoes.add(btnNovo);

		JPanel panelTabela = new JPanel();
		panelTabela.setBorder(new TitledBorder(null, "Listagem de Funcionários", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTabela.setBounds(15, 300, 1000, 250);
		frame.getContentPane().add(panelTabela);
		panelTabela.setLayout(new BorderLayout());

		JScrollPane scrollPane = new JScrollPane();
		panelTabela.add(scrollPane, BorderLayout.CENTER);

		tabela = new JTable();
		tabela.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"Código", "Nome", "Contacto", "Departamento", "Gênero", "Estado Civil", "Dias Trabalhados", "Salário Diário"
		}));
		tabela.addMouseListener(this);
		scrollPane.setViewportView(tabela);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == btnAdicionar) {
				Funcionario funcionario = criarFuncionarioDoFormulario();
				ControllerFuncionario.adicionarFuncionario(funcionario);
				listar();
				limparCampos();
				JOptionPane.showMessageDialog(null, "Funcionário adicionado com sucesso.");
			} else if (e.getSource() == btnEditar) {
				Funcionario funcionario = criarFuncionarioDoFormulario();
				ControllerFuncionario.actualizarFuncionario(funcionario);
				listar();
				limparCampos();
				JOptionPane.showMessageDialog(null, "Funcionário atualizado com sucesso.");
			} else if (e.getSource() == btnRemover) {
				int codigo = Integer.parseInt(txtCodigo.getText());
				ControllerFuncionario.removerFuncionario(codigo);
				listar();
				limparCampos();
				JOptionPane.showMessageDialog(null, "Funcionário removido com sucesso.");
			} else if (e.getSource() == btnListar) {
				listar();
			}else if (e.getSource() == btnFiltrar) {
				mostrarFuncionariosFiltrados();
			} else if (e.getSource() == btnLimpar) {
				limparCampos();
			} else if (e.getSource() == btnNovo) {
				gerarNovoCodigo();
			}
		} catch (SQLException | NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
		}
	}
	
	private void mostrarFuncionariosFiltrados() {
	    try {
	        ArrayList<Funcionario> lista = ControllerFuncionario.listarFuncionariosFiltrados();
	        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
	        modelo.setRowCount(0);

	        for (Funcionario f : lista) {
	            modelo.addRow(new Object[]{
	                f.getCodigo(),
	                f.getNome(),
	                f.getContacto(),
	                f.getDepartamento(),
	                f.getGenero(),
	                f.getEstadoCivil(),
	                f.getDiasTrabalhados(),
	                f.getSalarioDiario(),
	                f.calcularSalario()
	            });
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(frame, "Erro ao carregar funcionários filtrados: " + e.getMessage());
	    }
	}


	private Funcionario criarFuncionarioDoFormulario() {
		String genero = rbMasculino.isSelected() ? "Masculino" : rbFeminino.isSelected() ? "Feminino" : "";
		return new Funcionario(
				Integer.parseInt(txtCodigo.getText()),
				txtNome.getText(),
				txtContacto.getText(),
				(String) cbDepartamento.getSelectedItem(),
				genero,
				(String) cbEstadoCivil.getSelectedItem(),
				Integer.parseInt(txtDiasTrabalhados.getText()),
				Double.parseDouble(txtSalarioDiario.getText()));
	}

	private void listar() throws SQLException {
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		modelo.setRowCount(0);
		ArrayList<Funcionario> lista = ControllerFuncionario.listarFuncionarios();
		for (Funcionario f : lista) {
			modelo.addRow(new Object[] {
					f.getCodigo(),
					f.getNome(),
					f.getContacto(),
					f.getDepartamento(),
					f.getGenero(),
					f.getEstadoCivil(),
					f.getDiasTrabalhados(),
					f.getSalarioDiario()
			});
		}
	}

	private void limparCampos() {
		txtCodigo.setText("");
		txtNome.setText("");
		txtContacto.setText("");
		cbDepartamento.setSelectedIndex(0);
		grupoGenero.clearSelection();
		cbEstadoCivil.setSelectedIndex(0);
		txtDiasTrabalhados.setText("");
		txtSalarioDiario.setText("");
		txtCodigo.setEnabled(true);
	}

	private void gerarNovoCodigo() throws SQLException {
		ArrayList<Funcionario> lista = ControllerFuncionario.listarFuncionarios();
		int novoCodigo = lista.isEmpty() ? 1 : lista.get(lista.size() - 1).getCodigo() + 1;
		txtCodigo.setText(String.valueOf(novoCodigo));
		txtCodigo.setEnabled(false);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (tabela.getSelectedRow() != -1) {
			int row = tabela.getSelectedRow();
			txtCodigo.setText(tabela.getValueAt(row, 0).toString());
			txtNome.setText(tabela.getValueAt(row, 1).toString());
			txtContacto.setText(tabela.getValueAt(row, 2).toString());
			cbDepartamento.setSelectedItem(tabela.getValueAt(row, 3).toString());
			String genero = tabela.getValueAt(row, 4).toString();
			if (genero.equals("Masculino")) {
				rbMasculino.setSelected(true);
			} else if (genero.equals("Feminino")) {
				rbFeminino.setSelected(true);
			} else {
				grupoGenero.clearSelection();
			}
			cbEstadoCivil.setSelectedItem(tabela.getValueAt(row, 5).toString());
			txtDiasTrabalhados.setText(tabela.getValueAt(row, 6).toString());
			txtSalarioDiario.setText(tabela.getValueAt(row, 7).toString());
			txtCodigo.setEnabled(false);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
