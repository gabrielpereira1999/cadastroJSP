package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ClientBean;
import dao.ClientDao;

@WebServlet("/")
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ClientDao clientDao;

    public void init() {
        clientDao = new ClientDao();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertClient(request, response);
				break;
			case "/delete":
				deleteClient(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateUser(request, response);
				break;
			case "/show":
				showClient(request, response);
				break;
			default:
				listClients(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("client-registration.jsp");
		dispatcher.forward(request, response);
	}
    
    private void insertClient(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
    	String cpf = request.getParameter("cpf");
    	cpf = cpf.replaceAll(Pattern.quote("."), "").replaceAll(Pattern.quote("-"), "");
		String name = request.getParameter("name");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String address = request.getParameter("address");
		System.out.println("Passou no insert");
		String telephonesConcatenated = request.getParameter("telephoneParameter");
		ArrayList<String> telephones = new ArrayList<String>(Arrays.asList(telephonesConcatenated.replaceAll(Pattern.quote("("), "").replaceAll(Pattern.quote(")"), "").replaceAll(Pattern.quote("-"), "").replaceAll(Pattern.quote(" "), "").split(Pattern.quote(","))));
		System.out.println(telephones.toString());
		ClientBean client = new ClientBean(name, address, state, city, cpf, telephones);
		clientDao.registerClient(client);
		response.sendRedirect("listClients");
	}
    
    private void deleteClient(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String cpf = request.getParameter("cpf");
		cpf = cpf.replaceAll(Pattern.quote("."), "").replaceAll(Pattern.quote("-"), "");
		clientDao.deleteClient(cpf);
		response.sendRedirect("listClients");
	}
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String cpf = request.getParameter("cpf");
		cpf = cpf.replaceAll(Pattern.quote("."), "").replaceAll(Pattern.quote("-"), "");
		ClientBean client = clientDao.getClient(cpf);
		RequestDispatcher dispatcher = request.getRequestDispatcher("client-registration.jsp");
		request.setAttribute("client", client);
		dispatcher.forward(request, response);

	}
    
    private void showClient(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String cpf = request.getParameter("cpf");
		cpf = cpf.replaceAll(Pattern.quote("."), "").replaceAll(Pattern.quote("-"), "");
		ClientBean client = clientDao.getClient(cpf);
		RequestDispatcher dispatcher = request.getRequestDispatcher("client-registration.jsp");
		request.setAttribute("client", client);
		request.setAttribute("disabled", true);
		dispatcher.forward(request, response);

	}
    
    private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String cpf = request.getParameter("cpf");
		cpf = cpf.replaceAll(Pattern.quote("."), "").replaceAll(Pattern.quote("-"), "");
		String name = request.getParameter("name");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String address = request.getParameter("address");
		String telephonesConcatenated = request.getParameter("telephoneParameter");
		ArrayList<String> telephones = new ArrayList<String>(Arrays.asList(telephonesConcatenated.replaceAll(Pattern.quote("("), "").replaceAll(Pattern.quote(")"), "").replaceAll(Pattern.quote("-"), "").replaceAll(Pattern.quote(" "), "").split(Pattern.quote(","))));
		ClientBean client = new ClientBean(name, address, state, city, cpf, telephones);
		clientDao.updateClient(client);
		response.sendRedirect("listClients");
	}
    
    private void listClients(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		ArrayList<ClientBean> clientList = clientDao.selectClients();
		clientList.forEach(client -> {
			String cpf = client.getCpfClient();
			StringBuilder strBd = new StringBuilder();
			strBd.append(cpf.substring(0,3));
			strBd.append(".").append(cpf.substring(3,6));
			strBd.append(".").append(cpf.substring(6,9));
			strBd.append("-").append(cpf.substring(9));
			client.setCpfClient(strBd.toString());
		});
		request.setAttribute("clientList", clientList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("client-list.jsp");
		dispatcher.forward(request, response);
	}

}
