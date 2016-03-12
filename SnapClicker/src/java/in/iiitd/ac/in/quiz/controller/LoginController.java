/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.iiitd.ac.in.quiz.controller;
import javax.servlet.RequestDispatcher;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import in.iiitd.ac.in.quiz.*;
import java.sql.*;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Apple
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController", "/checkLogin"})
public class LoginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public LoginController()
    {
        super();
    }
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet LoginController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet LoginController at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Connection connection = DatabaseConnectionFactory.createConnection();
        ResultSet resultSet = null;
        int i = 0;
        try 
        {
            Statement statement = connection.createStatement();
            String sqlQuery = "Select * from users where username = '" + username + "' and password = '" + password + "';"; 
            System.out.println(sqlQuery);
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next())
            {
                i = 1;
            }
            if (i!=0)
            {
                HttpSession session = request.getSession();
                session.setAttribute("user", username);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
                requestDispatcher.forward(request, response);
            }
            else
            {
                request.setAttribute("error","Invalid username or password.");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
                requestDispatcher.forward(request, response);
            
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        try 
        {
            connection.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
