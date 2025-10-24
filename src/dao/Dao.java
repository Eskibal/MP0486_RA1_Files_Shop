package dao;

import model.Employee;
import model.Product;

import java.util.ArrayList;

public interface Dao {
	
	public void connect();
	
	public ArrayList<Product> getInventory();
	
	public boolean writeInventory(ArrayList<Product> list);
	

	public Employee getEmployee(int employeeId, String password);
	
	public void disconnect();

}
