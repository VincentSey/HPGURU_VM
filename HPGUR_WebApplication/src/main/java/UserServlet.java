import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private String jdbcURL = "jdbc:mysql://localhost:3306/user_details";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";
	private static final long serialVersionUID = 1L;
	private static final String SELECT_USER_BY_ID = "select name,password,email,language, Login_userid, address, reset from User_Details where name =?";
	private static final String SELECT_ALL_USERS = "select * from user_details";
	private static final String DELETE_USERS_SQL = "delete from User_Details where name = ?;";
	private static final String UPDATE_USERS_SQL = "update User_Details set name = ?,password= ?, email =?,language =? where name = ?;";
	
	protected Connection getConnection() {
				Connection connection = null;
				try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(jdbcURL, jdbcUsername,
				jdbcPassword);
				} catch (SQLException e) {
				e.printStackTrace();
				} catch (ClassNotFoundException e) {
				e.printStackTrace();
				}
				return connection;
				}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    //Method
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/UserServlet/delete":
				deleteUser(request, response);
				break;
			case "/UserServlet/edit":
				showEditForm(request, response);
				break;
			case "/UserServlet/update":
				updateUser(request, response);
				break;
			case "/UserServlet/dashboard":
				listUsers(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//Method
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	// Method - Refer to Lab (Maven Dstabase part 2 - retrieve) part 4.5
	private void listUsers(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException
			{
			List <User> users = new ArrayList <>();
			try (Connection connection = getConnection();
					PreparedStatement preparedStatement =
							connection.prepareStatement(SELECT_ALL_USERS);) {
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					String name = rs.getString("name");
					String password = rs.getString("password");
					String email = rs.getString("email");
					String language = rs.getString("language");
					String Login_userid = rs.getString("Login_userid");
					String address = rs.getString("address");
					String reset = rs.getString("reset");
					users.add(new User(name, password, email, language, Login_userid, address, reset));
				    //users.add(new User(name,password, email, language));
					System.out.println("Creating list user");
				}
					} catch (SQLException e) {
					System.out.println(e.getMessage());
					}
					System.out.println("Starting list user");
			request.setAttribute("listUsers", users);
					System.out.println("Complete list user");
			request.getRequestDispatcher("/userManagement.jsp").forward(request, response);
			}
	// Method 3.2.1
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String name = request.getParameter("name");
		User existingUser = new User("", "", "", "", "", "", "");
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setString(1, name);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				name = rs.getString("name");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String language = rs.getString("language");
				String Login_userid = rs.getString("Login_userid");
				String address = rs.getString("address");
				String reset = rs.getString("reset");
				existingUser = new User(name, password, email, language, Login_userid, address, reset);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		request.setAttribute("user", existingUser);
		request.getRequestDispatcher("/userEdit.jsp").forward(request, response);
	}
	
	// Method 3.2.2
		private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
			//String oriName = request.getParameter("oriName");
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String language = request.getParameter("language");
			String Login_userid = request.getParameter("Login_userid");
			String address = request.getParameter("address");
			String reset = request.getParameter("reset");
			try (Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
				statement.setString(1, name);
				statement.setString(2, password);
				statement.setString(3, email);
				statement.setString(4, language);
				//statement.setString(5, oriName);
				statement.setString(6, Login_userid);
				statement.setString(7, address);
				statement.setString(8, reset);
				statement.executeUpdate();
			}
			response.sendRedirect("http://localhost:8091/HPGUR_WebApplication/UserServlet/dashboard");
		}
		
		// Method 3.2.3
		private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
			String name = request.getParameter("name");
			try (Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
				statement.setString(1, name);
				statement.executeUpdate();
			}
			response.sendRedirect("http://localhost:8091/HPGUR_WebApplication/UserServlet/dashboard");
		}
	}
			

