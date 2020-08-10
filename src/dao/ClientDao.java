package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Pattern;

import bean.ClientBean;
import dbconnection.DBConnection;

public class ClientDao {

	
	public boolean registerClient(ClientBean client) {
		boolean success = false;
		Connection dbconnection = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "INSERT INTO clientinfo (nameClient, addressClient, stateClient, cityClient, cpfClient) VALUES (?, ?, ?, ?, ?)";
			
			DBConnection connector = new DBConnection();
			dbconnection = connector.createConnection();
			System.out.println("Cliente: ");
			System.out.print(client.toString());
			
			pstmt = dbconnection.prepareStatement(sql);
			pstmt.setString(1, client.getNameClient());
			pstmt.setString(2, client.getAddressClient());
			pstmt.setString(3, client.getStateClient());
			pstmt.setString(4, client.getCityClient());
			pstmt.setString(5, client.getCpfClient());
			pstmt.execute();
			
			if(client.getTelephonesClient() != null) {
				pstmt.close();
				
				sql = "INSERT INTO telephone (cpfClient, number) VALUES (?, ?)";
				pstmt = dbconnection.prepareStatement(sql);
				pstmt.setString(1, client.getCpfClient());
				for(String number : client.getTelephonesClient()) {
					pstmt.setString(2, number);
					System.out.println(number);
					pstmt.addBatch();
				}
				pstmt.executeBatch();
			}
			
		} catch (Exception sqlError) {
			System.out.println("Falha ao cadastrar o cliente: " + sqlError.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (dbconnection != null) {
				try {
					dbconnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return success;
	}
	
	public ArrayList<ClientBean> selectClients() {

		ArrayList<ClientBean> clients = new ArrayList<ClientBean>();
		Connection dbconnection = null;
		Statement stmt = null;

		try {
			String sql = "SELECT * FROM clientinfo";
			
			DBConnection connector = new DBConnection();
			dbconnection = connector.createConnection();

			stmt = dbconnection.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				ClientBean client = new ClientBean(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				clients.add(client);
			}

		} catch (Exception sqlError) {
			System.out.println("Falha ao obter os clientes: " + sqlError.getMessage());
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (dbconnection != null) {
				try {
					dbconnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return clients;

	}
	
	public ClientBean getClient(String cpf) {
		ClientBean client = null;
		Connection dbconnection = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "SELECT * FROM clientinfo WHERE cpfClient = ?";
			
			DBConnection connector = new DBConnection();
			dbconnection = connector.createConnection();

			pstmt = dbconnection.prepareStatement(sql);
			pstmt.setString(1, cpf);
			System.out.println(cpf);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				client = new ClientBean(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
			
			pstmt.close();
			rs.close();
			
			sql = "SELECT number FROM telephone WHERE cpfClient = ?";
			pstmt = dbconnection.prepareStatement(sql);
			pstmt.setString(1, cpf);
			
			rs = pstmt.executeQuery();
			
			ArrayList<String> numbers = new ArrayList<String>();
			while (rs.next()) {
				numbers.add(rs.getString(1));
			}
			
			client.setTelephonesClient(numbers);

		} catch (Exception sqlError) {
			System.out.println("Falha ao obter o cliente: " + sqlError.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (dbconnection != null) {
				try {
					dbconnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return client;
	}
	
	public boolean updateClient(ClientBean client) {

		boolean success = false;
		Connection dbconnection = null;
		PreparedStatement pstmt = null;

		try {
			String sql = "UPDATE clientinfo SET nameClient = ?, addressClient = ?, stateClient = ?, cityClient = ? WHERE cpfClient = ?";
			
			DBConnection connector = new DBConnection();
			dbconnection = connector.createConnection();

			pstmt = dbconnection.prepareStatement(sql);
			pstmt.setString(1, client.getNameClient());
			pstmt.setString(2, client.getAddressClient());
			pstmt.setString(3, client.getStateClient());
			pstmt.setString(4, client.getCityClient());
			pstmt.setString(5, client.getCpfClient());
			pstmt.execute();
			pstmt.close();
			
			sql = "DELETE FROM telephone WHERE cpfClient = ?";
			
			pstmt = dbconnection.prepareStatement(sql);
			pstmt.setString(1, client.getCpfClient());
			pstmt.execute();
			pstmt.close();
			
			sql = "INSERT INTO telephone (cpfClient, number) VALUES (?, ?)";
			pstmt = dbconnection.prepareStatement(sql);
			pstmt.setString(1, client.getCpfClient());
			for(String number : client.getTelephonesClient()) {
				pstmt.setString(2, number);
				System.out.println(number);
				pstmt.addBatch();
			}
			pstmt.executeBatch();

			success = true;

		} catch (Exception sqlError) {
			System.out.println("Falha ao atualizar cliente: " + sqlError.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (dbconnection != null) {
				try {
					dbconnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return success;

	}
	
	public boolean deleteClient(String cpf) {

		boolean success = false;
		Connection dbconnection = null;
		PreparedStatement pstmt = null;

		try {
			String sql = "DELETE FROM clientinfo WHERE cpfClient = ?";

			DBConnection connector = new DBConnection();
			dbconnection = connector.createConnection();

			pstmt = dbconnection.prepareStatement(sql);
			pstmt.setString(1, cpf);
			pstmt.execute();

			success = true;

		} catch (Exception sqlError) {
			System.out.println("Falha ao excluir cliente: " + sqlError.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (dbconnection != null) {
				try {
					dbconnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return success;

	}
	
	
}
