/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.iiitd.ac.in.quiz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;


/**
 *
 * @author Apple
 */
@WebServlet(urlPatterns = { "/login", "/register", "/takeExam", "/logout"})
public class MainController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet MainController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet MainController at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String applicationContextPath = request.getContextPath();
        System.out.println("APPLICATION CONTEXT PATH: " + applicationContextPath);
        if (request.getRequestURI().equals(applicationContextPath + "/"))
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        }
        else if (request.getRequestURI().equals(applicationContextPath + "/login"))
        {
            System.out.println("It comes here for login.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
            
        }
        else if (request.getRequestURI().equals(applicationContextPath + "/register"))
        {
            System.out.println("It comes here for registration.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
            dispatcher.forward(request, response);  
        }
        else if (request.getRequestURI().equals(applicationContextPath + "/takeExam"))
        {
            request.getSession().setAttribute("currentExam", null);
            
            String exam = request.getParameter("test");
            request.getSession().setAttribute("exam", exam);
            
            System.out.println(request.getSession().getAttribute("user"));
            if (request.getSession().getAttribute("user") == null)
            {
                System.out.println("IT COMES HERE BITCH!!!");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
                requestDispatcher.forward(request, response);
            }
            else
            {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/quizDetails.jsp");
                requestDispatcher.forward(request, response);
            }
            
        }
        else if (request.getRequestURI().equals(applicationContextPath + "/logout"))
        {
            request.getSession().invalidate();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
            
        }
       // processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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