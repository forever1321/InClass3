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
      Double stand = 0.0;
      String resultString = "";
      String warning = "";
      String operation = request.getParameter("Operation");
      String input = request.getParameter("Input");
      String[] removeDuplicates = request.getParameterValues("removeDuplicates");
      
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      
      
      this.list = new ArrayList<Integer>();
      for(String s : input.split(" ")){
         int addingIn = new Integer(s);
         this.list.add(addingIn);       
      }
      
      if(this.list.size() == 0){
         warning = "You must add in numbers!";
      }else{
         if(removeDuplicates != null){
            this.list = removeDuplicates(this.list);
         }
         switch(operation){
            case "Mean":
               stand = mean(this.list);
               resultString = Double.toString(stand);
               break;
            case "Median":
               result = median(this.list);
               resultString = Integer.toString(result);
               break;
            case "Mode":
               ArrayList<Integer> modding = new ArrayList<Integer>();
               modding = mode(this.list);
               for(int i = 0; i < modding.size(); i++){
                  String insertHere = Integer.toString(modding.get(i));
                  resultString += insertHere + " ";
               } 
               break;
            case "Standard Deviation":
               stand = standardDeviation(this.list);
               resultString = Double.toString(stand);
               break;
             
         }
         //resultString = Integer.toString(result);
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
   
   private Double mean(ArrayList<Integer> list){
      int sum = 0;
      Double result = 0.0;
      for(int i = 0; i < list.size(); i++){
         sum += list.get(i);
      }
      Double summa = new Double(sum);
      Double sizeList = new Double(list.size());
      result = summa / sizeList;
      return result;
   }
   
   private int median(ArrayList<Integer> list){
      int result = 0;
      Collections.sort(list);
      if(this.list.size() % 2 == 1){
         result = list.get((list.size() + 1) / 2 - 1);
      }else{
         int lower = list.get(list.size() / 2 - 1);
         int upper = list.get(list.size() / 2);
         result = (lower + upper) / 2;
      }
      return result;
   }
   
   private ArrayList<Integer> mode(ArrayList<Integer> list){
      int maxCount = 0;
      ArrayList<Integer> result = new ArrayList<Integer>();
      for(int i = 0; i < list.size(); i++){
         int count = 0;
         for(int j = 0; j < list.size(); j++){
            if(this.list.get(i) == list.get(j)){
               count++;
            }
         }
         if(count > maxCount){
            result.clear();
            maxCount = count;
            result.add(list.get(i));
         }else if(count == maxCount){
            if(result.contains(list.get(i)) == false){
               result.add(list.get(i));
            }
         }
      }  
      return result;
   }
   
   private Double standardDeviation(ArrayList<Integer> list){
      Double result = 0.0;
      Double numerator = 0.0;
      Double meanList = mean(list);
      int sizeList = list.size() - 1;
      for(int i = 0; i < list.size(); i++){
         Double diff = list.get(i) - meanList;
         diff *= diff;
         numerator += diff;
      }
      result = numerator / sizeList;
      result = Math.sqrt(result);
      return result;
   }
   
   private ArrayList<Integer> removeDuplicates(ArrayList<Integer> list){
      ArrayList<Integer> result = new ArrayList<Integer>();
      for(int i = 0; i < list.size(); i++){
         if(result.contains(list.get(i)) == false){
            result.add(list.get(i));
         }
      }
      return result;
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
      out.println("     <td>");
      out.println("        <input type = \"submit\" value = \"Standard Deviation\" formmethod = \"post\" name=\"Operation\">");
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
