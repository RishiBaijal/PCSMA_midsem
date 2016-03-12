/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.iiitd.ac.in.quiz.controller;

import in.iiitd.ac.in.quiz.Exam;
import in.iiitd.ac.in.quiz.QuizQuestion;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Apple
 */
@WebServlet(name = "ExamController", urlPatterns = {"/ExamController", "/exam"})
public class ExamController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ExamController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ExamController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        //processRequest(request, response);
        doPost(request, response);
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
        boolean finish = false;
        HttpSession session = request.getSession();
        try
        {
            if (session.getAttribute("currentExam") == null)
            {
                session = request.getSession();
                String selectedExam = (String) request.getSession().getAttribute("exam");
                System.out.println("Exam: " + selectedExam);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/DD HH:mm:ss a");
                Date date = new Date();
                String started = simpleDateFormat.format(date);
                session.setAttribute("started", started);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        Exam exam = (Exam) request.getSession().getAttribute("currentExam");
        if (exam.currentQuestion == 0)
        {
            exam.setQuestion(exam.currentQuestion);
            QuizQuestion question = exam.questionList.get(exam.currentQuestion);
            session.setAttribute("question", question);
            
            
        }
        String action=request.getParameter("action");
			
	String radio=request.getParameter("answer");
	int selectedRadio=-1;
	exam.selections.put(exam.currentQuestion, selectedRadio);
	if("1".equals(radio))
	{
		selectedRadio=1;
		exam.selections.put(exam.currentQuestion, selectedRadio);
		System.out.println("You selected "+selectedRadio);
	}
	else if("2".equals(radio))
	{
		selectedRadio=2;
		exam.selections.put(exam.currentQuestion, selectedRadio);
		System.out.println("You selected "+selectedRadio);
	}
	else if("3".equals(radio))
	{
		selectedRadio=3;
		exam.selections.put(exam.currentQuestion, selectedRadio);
		System.out.println("You selected "+selectedRadio);
	}
	else if("4".equals(radio))
	{
		selectedRadio=4;
		exam.selections.put(exam.currentQuestion, selectedRadio);
		System.out.println("You selected "+selectedRadio);
	}
	
        if("Next".equals(action)){
		exam.currentQuestion++;
        	exam.setQuestion(exam.currentQuestion);
                QuizQuestion q=exam.questionList.get(exam.currentQuestion);
                session.setAttribute("quest",q);
	}
	else if("Previous".equals(action))
	{   System.out.println("You clicked Previous Button");
		exam.currentQuestion--;
				exam.setQuestion(exam.currentQuestion);
			    QuizQuestion q=exam.questionList.get(exam.currentQuestion);	
				session.setAttribute("quest",q);
			}
			else if("Finish Exam".equals(action))
			{   finish=true;
				int result=exam.calculateResult(exam);				
				request.setAttribute("result",result);
				request.getSession().setAttribute("currentExam",null);
				request.getRequestDispatcher("/WEB-INF/jsps/result.jsp").forward(request,response);
				
			}
						
		if(finish!=true){
		request.getRequestDispatcher("/WEB-INF/jsps/exam.jsp").forward(request,response);
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
