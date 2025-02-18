package gui;

import dao.ProductDAO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import models.EletronicProduct;
import models.Product;

public class MainFrame extends JFrame {
  private final ProductDAO productDAO;
  private JTable productTable;
  private JTextField txtName, txtPrice, txtQuantity;
  private JTextField txtBrand, txtModel, txtGuarantee;
  private JComboBox<String> cbCategory;

  public MainFrame() {
    productDAO = new ProductDAO();
    configWindow();
    initializeComponents();
  }

  private void configWindow() {
    setTitle("Sistema de E-commerce");
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout(10, 10));
  }

  private void initializeComponents() {
    // Painel de formulário
    JPanel formPanel = new JPanel(new GridLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.anchor = GridBagConstraints.WEST;

    // Campos básicos
    cbCategory = new JComboBox<>(new String[]{"Produto Comum", "Produto Eletrônico"});
    txtName = new JTextField(20);
    txtPrice = new JTextField(10);
    txtQuantity = new JTextField(10);

    // Campos específicos para produtos eletrônicos
    txtBrand = new JTextField(20);
    txtModel = new JTextField(20);
    txtGuarantee = new JTextField(10);

    // Adiciona os componentes ao painel
    gbc.gridx = 0; gbc.gridy = 0;
    formPanel.add(new JLabel("Tipo de Produto: "), gbc);
    gbc.gridx = 1;
    formPanel.add(cbCategory, gbc);

    gbc.gridx = 0; gbc.gridy++;
    formPanel.add(new JLabel("Nome: "), gbc);
    gbc.gridx = 1;
    formPanel.add(txtName, gbc);

    gbc.gridx = 0; gbc.gridy++;
    formPanel.add(new JLabel("Preço: "), gbc);
    gbc.gridx = 1;
    formPanel.add(txtPrice, gbc);

    gbc.gridx = 0; gbc.gridy++;
    formPanel.add(new JLabel("Estoque: "), gbc);
    gbc.gridx = 1;
    formPanel.add(txtQuantity, gbc);

    // Campos específicos para eletrônicos
    gbc.gridx = 0; gbc.gridy++;
    formPanel.add(new JLabel("Marca: "), gbc);
    gbc.gridx = 1;
    formPanel.add(txtBrand, gbc);

    gbc.gridx = 0; gbc.gridy++;
    formPanel.add(new JLabel("Modelo: "), gbc);
    gbc.gridx = 1;
    formPanel.add(txtModel, gbc);

    gbc.gridx = 0; gbc.gridy++;
    formPanel.add(new JLabel("Garantia (meses): "), gbc);
    gbc.gridx = 1;
    formPanel.add(txtGuarantee, gbc);

    // Botões
    JPanel buttonPanel = new JPanel();
    JButton btnAdd = new JButton("Adicionar");
    JButton btnUpdate = new JButton("Atualizar");
    JButton btnDelete = new JButton("Excluir");
    JButton btnClear = new JButton("Limpar");

    buttonPanel.add(btnAdd);
    buttonPanel.add(btnUpdate);
    buttonPanel.add(btnDelete);
    buttonPanel.add(btnClear);

    // Tabela de produtos
    String[] columns = {"ID", "Nome", "Preço", "Estoque", "Categoria"};
    productTable = new JTable(new Object[0][6], columns);
    JScrollPane scrollPane = new JScrollPane(productTable);

    // Layout
    add(formPanel, BorderLayout.NORTH);
    add(scrollPane, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

    // Eventos
    btnAdd.addActionListener(e -> addProduct());
    btnUpdate.addActionListener(e -> updateProduct());
    btnDelete.addActionListener(e -> deleteProduct());
    btnClear.addActionListener(e -> clearFields());

    cbCategory.addActionListener(e -> updateVisibilityInputs());
    productTable.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        productSelected();
      }
    });

    // Atualiza a visibilidade dos campos
    updateVisibilityInputs();
    updateTable();
  }

  private void updateVisibilityInputs() {
    boolean isElectronic = cbCategory.getSelectedIndex() == 1;
    txtBrand.setVisible(isElectronic);
    txtModel.setVisible(isElectronic);
    txtGuarantee.setVisible(isElectronic);
  }

  private void addProduct() {
    try {
      String name = txtName.getText();
      double price = Double.parseDouble(txtPrice.getText());
      int quantity = Integer.parseInt(txtQuantity.getText());

      Product product;
      if (cbCategory.getSelectedIndex() == 1) {
        String brand = txtBrand.getText();
        String model = txtModel.getText();
        int guarantee = Integer.parseInt(txtGuarantee.getText());
        product = new EletronicProduct(0, name, price, quantity, brand, model, guarantee);
      } else {
        product = new Product(0, name, price, quantity);
      }

      productDAO.addProduct(product);
      updateTable();
      clearFields();
      JOptionPane.showMessageDialog(this, "Produto adicionado com sucesso!");
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Por favor, verifique os valores inseridos!");
    }
  }

  private void updateProduct() {
    try {
      int row = productTable.getSelectedRow();
      if (row == -1) {
        JOptionPane.showMessageDialog(this, "Selecione um produto para atualizar!");
        return;
      }

      int id = (int) productTable.getValueAt(row, 0);
      String name = txtName.getText();
      double price = Double.parseDouble(txtPrice.getText());
      int quantity = Integer.parseInt(txtQuantity.getText());

      Product product;
      if (cbCategory.getSelectedIndex() == 1) {
        String brand = txtBrand.getText();
        String model = txtModel.getText();
        int guarantee = Integer.parseInt(txtGuarantee.getText());
        product = new EletronicProduct(id, name, price, quantity, brand, model, guarantee);
      } else {
        product = new Product(id, name, price, quantity);
      }

      productDAO.updateProduct(product);
      updateTable();
      clearFields();
      JOptionPane.showMessageDialog(this, "Produto atualizado com sucesso!");
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Por favor, verifique os valores inseridos!");
    }
  }

  private void deleteProduct() {
    int row = productTable.getSelectedRow();
    if (row == -1) {
      JOptionPane.showMessageDialog(this, "Selecione um produto para excluir!");
      return;
    }

    int id = (int) productTable.getValueAt(row, 0);
    productDAO.deleteProduct(id);
    updateTable();
    clearFields();
    JOptionPane.showMessageDialog(this, "Produto excluído com sucesso!");
  }

  private void productSelected() {
    int row = productTable.getSelectedRow();
    if (row == -1) return;

    int id = (int) productTable.getValueAt(row, 0);
    Product product = productDAO.getProductById(id);

    txtName.setText(product.getName());
    txtPrice.setText(String.valueOf(product.getPrice()));
    txtQuantity.setText(String.valueOf(product.getQuantity()));

    if (product instanceof EletronicProduct ep) {
      cbCategory.setSelectedIndex(1);
      txtBrand.setText(ep.getBrand());
      txtModel.setText(ep.getModel());
      txtGuarantee.setText(String.valueOf(ep.getGuaranteedMonths()));
    } else {
      cbCategory.setSelectedIndex(0);
      txtBrand.setText("");
      txtModel.setText("");
      txtGuarantee.setText("");
    }

    updateVisibilityInputs();
  }

  private void clearFields() {
    txtName.setText("");
    txtPrice.setText("");
    txtQuantity.setText("");
    txtBrand.setText("");
    txtModel.setText("");
    txtGuarantee.setText("");
    cbCategory.setSelectedIndex(0);
    productTable.clearSelection();
    updateVisibilityInputs();
  }

  private void updateTable() {
    List<Product> products = productDAO.listProducts();
    Object[][] data = new Object[products.size()][6];

    for (int i = 0; i < products.size(); i++) {
        Product product = products.get(i);
        data[i][0] = product.getId();
        data[i][1] = product.getName();
        data[i][2] = product.getPrice();
        data[i][3] = product.getQuantity();

        if (product instanceof EletronicProduct ep) {
            data[i][4] = "Eletrônico";
            data[i][5] = ep.getBrand() + " " + ep.getModel() + " (" + ep.getGuaranteedMonths() + " meses)";
        } else {
            data[i][4] = "Comum";
            data[i][5] = "-";
        }
    }

    productTable.setModel(new javax.swing.table.DefaultTableModel(data, new String[]{"ID", "Nome", "Preço", "Estoque", "Categoria", "Detalhes"}));
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      MainFrame frame = new MainFrame();
      frame.setVisible(true);
    });
  }
}