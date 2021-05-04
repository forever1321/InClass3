import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "FinalPractice", urlPatterns = {"/FinalPracticeCheck"})
public class PracticeFinalCheck extends HttpServlet{
   
   public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
      String warning = "";
      String result = "";
      String operation = request.getParameter("Operation");
      String input1 = request.getParameter("Input1");
      String input2 = request.getParameter("Input2");
      String input3 = request.getParameter("Input3");
      String separator = request.getParameter("Separator");
      String[] reverseCheck = request.getParameterValues("reverseCheck");
      
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      
      if(input1 == "" && input2 == "" && input3 == "")
      {
         warning = "Input values within the text boxes!";
      }
      else
      {
         if(reverseCheck != null)
         {
            input1 = reverseString(input1);
            input2 = reverseString(input2);
            input3 = reverseString(input3);
         }
         switch(operation)
         {
            case "123":
               result = input1 + separator + input2 + separator + input3;
               break;
            case "132":
               result = input1 + separator + input3 + separator + input2;
               break;
            case "213":
               result = input2 + separator + input1 + separator + input3;
               break;
            case "231":
               result = input2 + separator + input3 + separator + input1;
               break;
            case "312":
               result = input3 + separator + input1 + separator + input2;
               break;
            case "321":
               result = input3 + separator + input2 + separator + input1;
               break;
         }//end of switch
      }//end of else
      
      PrintHead(out);
      PrintBody(out, result, warning);
      PrintTail(out);
   
   }//end of doPost
   
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
   
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      PrintHead(out);
      PrintBody(out, "", "");
      PrintTail(out);
   }//end of doGet
   
   private String reverseString(String input)
   {
      char [] reverseArray = input.toCharArray();
      ArrayList<Character> reverseString = new ArrayList<Character>();
      
      for(char c : reverseArray)
      {
         reverseString.add(c);
      }
      
      Collections.reverse(reverseString);
      
      String result = "";
      for(char c : reverseString)
      {
         result += c;
      }
      return result;
   }
    
   private void PrintHead(PrintWriter out){
      out.println("<html>");
      out.println("");
      out.println("<head>");
      out.println("<title> String Concatenation </title>");
      out.println("</head>");
   }//end of printHead
   
   private void PrintBody(PrintWriter out, String rsltString, String warning){
      out.println("<body>");
      out.println("<h>Created by: Arman Jumani</h>");
      out.println("<p>Input strings into the textboxes and select how you want to concatenate using the buttons below</p>");
   
   if (warning != null) {
      out.println("<p>" + warning + "</p>");
   }
   
      out.println("<fieldset>");
      out.println("  <form name = \"Form\"> ");
      out.println("  String 1:");
      out.println("  <input type = \"text\" name = \"Input1\">");
      out.println("  String 2:");
      out.println("  <input type = \"text\" name = \"Input2\">");
      out.println("  String 3:");
      out.println("  <input type = \"text\" name = \"Input3\">");
      out.println("  Separator:");
      out.println("  <input type = \"text\" name = \"Separator\">");
      out.println("  <table>");
      out.println("  <tr>");
      out.println("     <td>");
      out.println("     Combination:");
      out.println("        <input type = \"submit\" value = \"123\" formmethod = \"post\" name=\"Operation\">");
      out.println("     </td>");
      out.println("     <td>");
      out.println("        <input type = \"submit\" value = \"132\" formmethod = \"post\" name=\"Operation\">");
      out.println("     </td>");
      out.println("     <td>");
      out.println("        <input type = \"submit\" value = \"213\" formmethod = \"post\" name=\"Operation\">");
      out.println("     </td>"); 
      out.println("     <td>");
      out.println("        <input type = \"submit\" value = \"231\" formmethod = \"post\" name=\"Operation\">");
      out.println("     </td>");
      out.println("     <td>");
      out.println("        <input type = \"submit\" value = \"312\" formmethod = \"post\" name=\"Operation\">");
      out.println("     </td>");
      out.println("     <td>");
      out.println("        <input type = \"submit\" value = \"321\" formmethod = \"post\" name=\"Operation\">");
      out.println("     </td>");
      out.println("  </tr>");
      out.println("  <tr>");
      out.println("     <td>");
      out.println("        <input type = \"checkbox\" name = \"reverseCheck\" value = \"true\"> Reverse Strings");
      out.println("     </td>");
      out.println("  </tr>");
      out.println("  </table>");
      out.println("  Results: <form name = \"ResultField\">");
      out.println("  <input type = \"text\" name = \"Entry\" value = \"" +rsltString + "\">");
      out.println("  </form>");
      out.println("</fieldset>");
      
      out.println("</body>");
   }   
   //Override of the PrintBody method to print out a form without any information
   private void PrintBody(PrintWriter out){
      PrintBody(out, "", "");
   }
   
   private void PrintTail(PrintWriter out){
      out.println("");
      out.println("</html>");
   }
}//end of class
