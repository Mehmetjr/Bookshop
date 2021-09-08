package net.codejava.javaee.bookstore;
 
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @author www.codejava.net
 */
public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookDAO bookDAO;
    private String GenreFilter, DateFilter;
    private String target;
    private User user;
    private UserDAO userDAO;
    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
        GenreFilter="all";
        DateFilter="";
        bookDAO = new BookDAO(jdbcURL, jdbcUsername, jdbcPassword);
        userDAO = new UserDAO(jdbcURL, jdbcUsername, jdbcPassword);
        target="BookListUser.jsp";
 
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

		HttpSession session = request.getSession(false);
        user=(User) session.getAttribute("user");
    	if(user==null) {
    		action="/login";
    	}
    	else {
    		if(user.getRole().equalsIgnoreCase("admin"))
    		{
    			target="BookList.jsp";
    		}
    	}
        try {
            switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertBook(request, response);
                break;
            case "/delete":
                deleteBook(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateBook(request, response);
                break;
            case "/login":
                login(request, response);
                break;
            case "/Info":
                Information(request, response);
                break;
            case "/add":
                addToCart(request, response);
                break;
            case "/cart":
                goToCart(request, response);
                break;
            case "/deletefromcart":
                deletefromCart(request, response);
                break;
            case "/users":
                ShowUsers(request, response);
                break;
            case "/userUpdate":
                UserUpdate(request, response);
                break;
            case "/deletefromusers":
                DeleteUser(request, response);
                break;
            case "/addUser":
                addUser(request, response);
                break;
            case "/newUser":
                ShownewUser(request, response);
                break;
            case "/updateUser":
            	updateUser(request, response);
                break;
            default:
                listBook(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
        dispatcher.forward(request, response);
    }
    
    public void ShowUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<User> listUser = userDAO.listAllUsers();
        request.setAttribute("listUser", listUser);
        request.setAttribute("user",user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");
        dispatcher.forward(request, response);
    }
    private void UserUpdate(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existinguser=userDAO.getUser(id);
        request.setAttribute("selecteduser", existinguser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("UserForm.jsp");
        dispatcher.forward(request, response);
 
    }   
    private void ShownewUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("UserForm.jsp");
        dispatcher.forward(request, response);
    }
    private void DeleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        User user =new User(id);
        userDAO.deleteUser(user);
        response.sendRedirect("users");
 
    }
    private void addUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String fullname =request.getParameter("fullname");
        String role =request.getParameter("role");
        String cart="";
        
        
        User newuser = new User(fullname, email, password, role, cart);
        userDAO.insertUser(newuser);
        response.sendRedirect("users");
    }
    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String fullname =request.getParameter("fullname");
        String role =request.getParameter("role");
        String cart="";
        
        
        User newuser = new User(id,fullname, email, password, role, cart);
        userDAO.updateUser(newuser);
        response.sendRedirect("users");
    }
    
    public void listBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	GenreFilter = request.getParameter("GenreFilter");

        DateFilter = request.getParameter("DateFilter");
        if(GenreFilter==null) {
        	GenreFilter="all";
        }

        List<Book> listBook = bookDAO.listAllBooks(GenreFilter,DateFilter);
        request.setAttribute("listBook", listBook);
        request.setAttribute("user",user);
        RequestDispatcher dispatcher = request.getRequestDispatcher(target);
        dispatcher.forward(request, response);
    }
    private void Information(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	int id = Integer.parseInt(request.getParameter("id"));
        Book book = bookDAO.getBook(id);
        request.setAttribute("book", book);
        RequestDispatcher dispatcher = request.getRequestDispatcher("BookInformation.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("BookForm.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Book existingBook = bookDAO.getBook(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("BookForm.jsp");
        request.setAttribute("book", existingBook);
        dispatcher.forward(request, response);
 
    }
 
    private void insertBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        
        java.util.Date selectedDate = null;
        
        try {
        	selectedDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date")); 
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
        java.sql.Date date =  new java.sql.Date(selectedDate.getTime());
        String info=request.getParameter("info");
        String genre=request.getParameter("genre");
        String img=request.getParameter("img");
        
        
        
        Book newBook = new Book(title, author, info, date, genre, img);
        bookDAO.insertBook(newBook);
        response.sendRedirect("list");
    }
 
    private void updateBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        java.util.Date selectedDate = null;
        
        try {
        	selectedDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date")); 
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
        java.sql.Date date =  new java.sql.Date(selectedDate.getTime());
        String info=request.getParameter("info");
        String genre=request.getParameter("genre");
        String img=request.getParameter("img");
 
        Book book = new Book(id, title, author, info, date, genre, img);
        bookDAO.updateBook(book);
        response.sendRedirect("list");
    }
 
    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Book book = new Book(id);
        bookDAO.deleteBook(book);
        response.sendRedirect("list");
 
    }
    private void addToCart(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        user.addCart(request.getParameter("id"));
        response.sendRedirect("list");
    }
    private void deletefromCart(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        user.deleteCart(request.getParameter("id"));
        response.sendRedirect("cart");
    }
    public void goToCart(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Book> listBook = new ArrayList<Book>();
        String[] cart = user.getCart().split("[/]", 0);
        for(int i=0;i<cart.length;i++) {
        	listBook.add(bookDAO.getBook(Integer.parseInt(cart[i])));
        }
        request.setAttribute("listBook", listBook);
        request.setAttribute("user",user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Cart.jsp");
        dispatcher.forward(request, response);
    }
}