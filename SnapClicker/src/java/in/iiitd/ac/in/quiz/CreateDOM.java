/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.iiitd.ac.in.quiz;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Apple
 */
public class CreateDOM {
    public static String directory = "/Users/Apple/NetBeansProjects";
    public static Document getDOM(String test) throws Exception
    {
        Document dom = null;
        File quizFile = null;
        System.out.println("TEST: " + test);
        quizFile = new File(directory + "/Quizzes/" + test + "_quiz.xml");    
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        System.out.println("The quiz file is: " + quizFile);
        DocumentBuilder db = documentBuilderFactory.newDocumentBuilder();
        try
        {
            dom = db.parse(quizFile);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        dom.getDocumentElement().normalize();
        return dom;
    }
}
