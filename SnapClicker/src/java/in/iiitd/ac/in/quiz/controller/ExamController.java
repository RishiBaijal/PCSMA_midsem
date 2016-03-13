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
import java.util.logging.Level;
import java.util.logging.Logger;
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
//

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
                //session.setAttribute("currentExam", );
                Exam exam = new Exam(selectedExam);
                session.setAttribute("currentExam", exam);
                
                
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
        
        Exam exam1 = (Exam) request.getSession().getAttribute("currentExam");
        System.out.println("WORKS FINE TILL HERE COCKSUCKER!!");
//        System.out.println("THE CURRENT QUESTION IS: " + exam.currentQuestion);
//        if (exam.currentQuestion == 0)
//        {
//            try {
//                throw new Exception("Sucks a mighty cocksickle.");
//            } catch (Exception ex) {
//                Logger.getLogger(ExamController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        
        System.out.println("The question list: ");
        System.out.println(exam1.dom.toString());
        if (exam1.currentQuestion == 0)
        {
            System.out.println("THE CURRENT QUESTION IS: " + exam1.currentQuestion);
            exam1.setQuestion(exam1.currentQuestion);
            QuizQuestion question = exam1.questionList.get(exam1.currentQuestion);
            session.setAttribute("question", question);
            
            
        }
        String action=request.getParameter("action");
		
        
        System.out.println("Action: " + action + ", " + request.getParameter("minute"));
        int minute = -1;
        int second = -1;
        if (request.getParameter("minute") != null && request.getParameter("second") != null)
        {
            System.out.println("MINUTE" + request.getParameter("minute"));
            System.out.println("SECOND " + request.getParameter("second"));
            
            minute = Integer.parseInt(request.getParameter("minute"));
            second = Integer.parseInt(request.getParameter("second"));
            request.getSession().setAttribute("min", minute);
            request.getSession().setAttribute("sec", second);
            
        }
	String radio=request.getParameter("answer");
	int selectedRadio=-1;
	exam1.selections.put(exam1.currentQuestion, selectedRadio);
	if("1".equals(radio))
	{
		selectedRadio=1;
		exam1.selections.put(exam1.currentQuestion, selectedRadio);
		System.out.println("You selected "+selectedRadio);
	}
	else if("2".equals(radio))
	{
		selectedRadio=2;
		exam1.selections.put(exam1.currentQuestion, selectedRadio);
		System.out.println("You selected "+selectedRadio);
	}
	else if("3".equals(radio))
	{
		selectedRadio=3;
		exam1.selections.put(exam1.currentQuestion, selectedRadio);
		System.out.println("You selected "+selectedRadio);
	}
	else if("4".equals(radio))
	{
		selectedRadio=4;
		exam1.selections.put(exam1.currentQuestion, selectedRadio);
		System.out.println("You selected "+selectedRadio);
	}
	
        if("Next".equals(action)){
		exam1.currentQuestion++;
        	exam1.setQuestion(exam1.currentQuestion);
                QuizQuestion q=exam1.questionList.get(exam1.currentQuestion);
                session.setAttribute("quest",q);
	}
	else if("Previous".equals(action))
	{   
            System.out.println("You clicked Previous Button");
            exam1.currentQuestion--;
            exam1.setQuestion(exam1.currentQuestion);
            QuizQuestion q=exam1.questionList.get(exam1.currentQuestion);	
            session.setAttribute("quest",q);
        }
        else if("Finish Exam".equals(action) || (minute == 0 && second == 0))
        {
            finish=true;
            int result=exam1.calculateResult(exam1);				
            request.setAttribute("result",result);
            request.getSession().setAttribute("currentExam",null);
            request.getRequestDispatcher("/result.jsp").forward(request,response);
        }
						
        if(finish!=true){
            request.getRequestDispatcher("/exam.jsp").forward(request,response);
        }
        //processRequest(request, response);
//           try {
//                throw new Exception("Sucks a mighty cocksickle.");
//            } catch (Exception ex) {
//                Logger.getLogger(ExamController.class.getName()).log(Level.SEVERE, null, ex);
//            }
        //doPost(request, response);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);

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
