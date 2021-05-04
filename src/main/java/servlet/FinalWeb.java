import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "FinalShashwatBhushan", urlPatterns = {"/FinalsShashwatBhushan"})
public class FinalWeb extends HttpServlet{
   
   ArrayList <Integer> list = new ArrayList<Integer>();
   
   public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
      int result = 0;
      String resultString = 0;
      String warning = "";
      String operation = request.getParameter("Operation");
      String input = request.getParameter("Input");
      
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      
      if(operation.equals("Submit")){
         this.list = new ArrayList<Integer>();
         for(String s : input.split(" ")){
            int addingIn = new Integer(s);
            this.list.add(addingIn);       
         }
      }
      
      if(this.list.size() == 0){
         warning = "You must add in numbers!";
      }else{
         if(removeDuplicates != null){
            replacementList = new ArrayList<Integer>();
            replacementList = this.list;
            for(int i = 0; i < this.list.size(); i++){
               for(int j = 0; j < replacementList.size(); j++){
                  if(this.list.get(i) == replacementList.get(j) && i != j){
                     this.list.remove(i);
                  }
               }
            }
         }
         switch(operation){
            case "Mean":
               int sum = 0;
               for(int i = 0; i < this.list.size(); i++){
                  sum += this.list.get(i);
               }
               result = sum / this.list.size();
               break;
            case "Median":
               if(this.list.size() % 2 == 1){
                  result = this.list.get((this.list.size() + 1) / 2 - 1);
               }else{
                  int lower = this.list.get(this.list.size() / 2 - 1);
                  int upper = this.list.get(this.list.size() / 2);
                  result = (lower + upper) / 2;
               }
               break;
            case "Mode":
               int maxCount = 0;
               for(int i = 0; i < this.list.size(); i++){
                  int count = 0;
                  for(int j = 0; j < this.list.size(); j++){
                     if(this.list.get(i) == this.list.get(j)){
                        count++;
                     }
                  }
                  if(count > maxCount){
                     maxCount = count;
                     result = this.list.get(i);
                  }
               }   
         }
         resultString = result.toString();
      }
      
      PrintHead(out);
      PrintBody(out, resultString, warning);
      PrintTail(out);
   }
   
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      PrintHead(out);
      PrintBody(out, "", "");
      PrintTail(out);
   }
   
   private void PrintHead(PrintWriter out){
      out.println("<html>");
      out.println("");
      out.println("<head>");
      out.println("<title> Average of Integers </title>");
      out.println("</head>");
   }
   
   private void PrintBody(PrintWriter out, String result, String warning){
      out.println("<body>");
      out.println("<h>Shashwat Bhushan G-Number: G01134256</h>");
      out.println("<p>Input a list of integers into the space provided, and choose which average you want to see.</p>");
   
   if (warning != null) {
      out.println("<p>" + warning + "</p>");
   }
   
      out.println("<fieldset>");
      out.println("  <form name = \"Form\"> ");
      out.println("  Input:");
      out.println("  <input type = \"text\" name = \"Input\">");
      out.println("  <table>");
      out.println("  <tr>");
      out.println("     <td>");
      out.println("     Average Options:");
      out.println("        <input type = \"submit\" value = \"Mean\" formmethod = \"post\" name=\"Operation\">");
      out.println("     </td>");
      out.println("     <td>");
      out.println("        <input type = \"submit\" value = \"Median\" formmethod = \"post\" name=\"Operation\">");
      out.println("     </td>");
      out.println("     <td>");
      out.println("        <input type = \"submit\" value = \"Mode\" formmethod = \"post\" name=\"Operation\">");
      out.println("     </td>"); 
      
      out.println("  </tr>");
      out.println("  <tr>");
      out.println("     <td>");
      out.println("        <input type = \"checkbox\" name = \"removeDuplicates\" value = \"true\"> Remove Duplicates");
      out.println("     </td>");
      out.println("  </tr>");
      out.println("  </table>");
      out.println("  Results: <form name = \"ResultField\">");
      out.println("  <input type = \"text\" name = \"Entry\" value = \"" +result + "\">");
      out.println("  </form>");
      out.println("</fieldset>");
      
      out.println("</body>");
   }  
   
   private void PrintBody(PrintWriter out){
      PrintBody(out, "", "");
   }
   
   private void PrintTail(PrintWriter out){
      out.println("");
      out.println("</html>");
   } 
}
