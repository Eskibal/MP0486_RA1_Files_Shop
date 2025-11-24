package dao;

import model.Employee;
import model.Product;

import java.util.ArrayList;

public interface Dao {
	
	public void connect();
	
	public ArrayList<Product> getInventory();
	
	public boolean writeInventory(ArrayList<Product> list);

	public void addProduct(Product product);
	
	public void updateProduct(Product product);
	
	public void deleteProduct(int productId);
	
	public Employee getEmployee(int employeeId, String password);
	
	public void disconnect();

}
