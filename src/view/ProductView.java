package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.Shop;
import model.Product;
import model.Amount;
import utils.Constants;

public class ProductView extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Shop shop;
	private int option;
	private JButton okButton;
	private JButton cancelButton;
	private JSpinner spinnerId;
	private JTextField textFieldName;
	private JTextField textFieldStock;
	private JTextField textFieldPrice;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			ProductView dialog = new ProductView();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public ProductView(Shop shop, int option) {
		this.shop = shop;
		this.option = option;

		// main configuration dialog
		switch (option) {
		case Constants.OPTION_ADD_PRODUCT:
			setTitle("Añadir Producto");
			break;
		case Constants.OPTION_ADD_STOCK:
			setTitle("Añadir Stock");
			break;
		case Constants.OPTION_REMOVE_PRODUCT:
			setTitle("Eliminar Producto");
			break;

		default:
			break;
		}
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		// name section
		JLabel lblName = new JLabel("Nombre producto:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(33, 10, 119, 19);
		contentPanel.add(lblName);
		textFieldName = new JTextField();
		textFieldName.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldName.setBounds(169, 10, 136, 25);
		contentPanel.add(textFieldName);
		textFieldName.setColumns(10);
		if (option == Constants.OPTION_ADD_PRODUCT || option == Constants.OPTION_ADD_STOCK) {
			lblName.setVisible(true);
			textFieldName.setVisible(true);
		} else {
			lblName.setVisible(false);
			textFieldName.setVisible(false);
		}


		// stock section
		JLabel lblStock = new JLabel("Stock producto:");
		lblStock.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStock.setBounds(33, 50, 119, 19);
		contentPanel.add(lblStock);
		textFieldStock = new JTextField();
		textFieldStock.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldStock.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldStock.setBounds(169, 50, 136, 25);
		contentPanel.add(textFieldStock);
		textFieldStock.setColumns(10);
		if (option == Constants.OPTION_ADD_PRODUCT || option == Constants.OPTION_ADD_STOCK) {
			lblStock.setVisible(true);
			textFieldStock.setVisible(true);
		} else {
			lblStock.setVisible(false);
			textFieldStock.setVisible(false);
		}

		// price section
		JLabel lblPrice = new JLabel("Precio producto:");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrice.setBounds(33, 90, 119, 19);
		contentPanel.add(lblPrice);
		textFieldPrice = new JTextField();
		textFieldPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldPrice.setBounds(169, 90, 136, 25);
		contentPanel.add(textFieldPrice);
		textFieldPrice.setColumns(10);
		if (option == Constants.OPTION_ADD_PRODUCT) {
			lblPrice.setVisible(true);
			textFieldPrice.setVisible(true);
		} else {
			lblPrice.setVisible(false);
			textFieldPrice.setVisible(false);
		}

		// id section
		JLabel lblId = new JLabel("Id producto:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblId.setBounds(33, 50, 119, 19);
		contentPanel.add(lblId);
		spinnerId = new JSpinner();
		spinnerId.setAlignmentX(SwingConstants.RIGHT);
		spinnerId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinnerId.setBounds(169, 50, 136, 25);
		contentPanel.add(spinnerId);
		if (option == Constants.OPTION_REMOVE_PRODUCT) {
			lblId.setVisible(true);
			spinnerId.setVisible(true);
		} else {
			lblId.setVisible(false);
			spinnerId.setVisible(false);
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(this);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(this);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == okButton) {
			Product product;
			switch (this.option) {
			case Constants.OPTION_ADD_PRODUCT:
				// check product does not exist
				product = shop.findProduct(textFieldName.getText());

				if (product != null) {
					JOptionPane.showMessageDialog(null, "Producto ya existe ", "Error", JOptionPane.ERROR_MESSAGE);

				} else {
					product = new Product(textFieldName.getText(),
							new Amount(Double.parseDouble(textFieldPrice.getText())), true,
							Integer.parseInt(textFieldStock.getText()));
					shop.addProduct(product);
					JOptionPane.showMessageDialog(null, "Producto añadido ", "Information",
							JOptionPane.INFORMATION_MESSAGE);
					// release current screen
					dispose();
				}

				break;

			case Constants.OPTION_ADD_STOCK:
				// check product exists
				product = shop.findProduct(textFieldName.getText());

				if (product == null) {
					JOptionPane.showMessageDialog(null, "Producto no existe ", "Error", JOptionPane.ERROR_MESSAGE);

				} else {
					product.setStock(product.getStock() + Integer.parseInt(textFieldStock.getText()));
					shop.updateProduct(product);
					JOptionPane.showMessageDialog(null, "Stock actualizado ", "Information",
							JOptionPane.INFORMATION_MESSAGE);
					// release current screen
					dispose();
				}

				break;

			case Constants.OPTION_REMOVE_PRODUCT:
				// check product exists
				product = shop.findProduct(textFieldName.getText());
				int productId = (int) spinnerId.getModel().getValue();
				
				if (product == null && productId == 0) {
					JOptionPane.showMessageDialog(null, "Producto no existe ", "Error", JOptionPane.ERROR_MESSAGE);

				} else {
					shop.getInventory().remove(product);
					
					shop.deleteProduct(productId);
					JOptionPane.showMessageDialog(null, "Producto eliminado", "Information",
							JOptionPane.INFORMATION_MESSAGE);
					// release current screen
					dispose();
				}

				break;

			default:
				break;
			}

		}

		if (e.getSource() == cancelButton) {
			// release current screen
			dispose();
		}
	}

}
