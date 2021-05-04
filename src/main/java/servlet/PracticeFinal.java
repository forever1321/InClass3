import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.lang.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.annotation.WebServlet;


@WebServlet(name = "AssignmentServlet", urlPatterns = {"/PracticeFinal"})
public class PracticeFinal extends HttpServlet{

   static String ABC = "Concat String: ABC";
   static String ACB = "Concat String: ACB";
   static String BAC = "Concat String: BAC";
   static String BCA = "Concat String: BCA";
   static String CAB = "Concat String: CAB";
   static String CBA = "Concat String: CBA";
   static String reverse = "Reverse Result";
   

   public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
      
      HttpSession session = request.getSession();
      
      String result = "";
      String operation = request.getParameter("Operation");
      String stringAPara = request.getParameter("StringA");
      String stringBPara = request.getParameter("StringB");
      String stringCPara = request.getParameter("StringC");
      String seperatorPara = request.getParameter("Seperator");
            
      if(operation.equals(ABC)){
         result = stringAPara + stringBPara + stringCPara;
      }else if(operation.equals(ACB)){
         result = stringAPara + stringCPara + stringBPara;
      }else if(operation.equals(BAC)){
         result = stringBPara + stringAPara + stringCPara;
      }else if(operation.equals(BCA)){
         result = stringBPara + stringCPara + stringAPara;
      }else if(operation.equals(CAB)){
         result = stringCPara + stringAPara + stringBPara;
      }else if(operation.equals(CBA)){
         result = stringCPara + stringBPara + stringAPara;
      }else if(operation.equals(reverse)){
         StringBuilder temp = new StringBuilder();
         temp.append(result);
         result = temp.reverse().toString();
      }
      
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      PrintHead(out);
      PrintBody(out, result);
      PrintTail(out);
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
      response.setContentType("text/html");
   
      PrintWriter out = response.getWriter();
   
      PrintHead(out);
      PrintBody(out, "");
      PrintTail(out);
   }

   private void PrintHead(PrintWriter out){
      out.println("<html>");
      out.println("");
      out.println("<head>");
      out.println("<title> Practice Final Shashwat Bhushan </title>");
      out.println("</head>");
   }

   private void PrintBody(PrintWriter out, String result){
      out.println("<body>");
      if(!result.isEmpty()){
         out.println("<p>Concat String: <b>" + result + "</b></p>");
      }
      out.println(" <table>");
      out.println("  <tr>");
      out.println("   <td>String A:");
      out.println("   <td><input type=\"text\" name=\"StringA\" size=15>");
      out.println("  </tr>");
      out.println("  <tr>");
      out.println("   <td>String B:");
      out.println("   <td><input type=\"text\" name=\"StringB\" size=15>");
      out.println("  </tr>");
      out.println("   <td>String C:");
      out.println("   <td><input type=\"text\" name=\"StringC\" value=\" size=15>");
      out.println("  </tr>");
      out.println("  <tr>");
      out.println("   <td>Sepearator:");
      out.println("   <td><input type=\"text\" name=\"Seperator\" value=\" size=15>");
      out.println("  </tr>");
      out.println(" </table>");
      out.println(" <br>");
      out.println(" <br>");
      out.println(" <input type=\"submit\" value=\"" + ABC + "\" name=\"Operation\">");
      out.println(" <input type=\"submit\" value=\"" + ACB + "\" name=\"Operation\">");
      out.println(" <input type=\"submit\" value=\"" + BAC + "\" name=\"Operation\">");
      out.println(" <input type=\"submit\" value=\"" + BCA + "\" name=\"Operation\">");
      out.println(" <input type=\"submit\" value=\"" + CAB + "\" name=\"Operation\">");
      out.println(" <input type=\"submit\" value=\"" + CBA + "\" name=\"Operation\">"); 
      out.println(" <input type=\"submit\" value=\"" + reverse + "\" name=\"Operation\">");     
      out.println("</body>");
   }

   //Override of the PrintBody method to print out a form without any information
   private void PrintBody(PrintWriter out){
      PrintBody(out, "");
   }

   private void PrintTail(PrintWriter out){
      out.println("");
      out.println("</html>");
   }
}
