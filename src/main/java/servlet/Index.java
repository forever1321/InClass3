import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.annotation.WebServlet;


@WebServlet(name = "AssignmentServlet", urlPatterns = {"/Assignment4"})
public class Index extends HttpServlet{

   static String stringReplace = "One Random String With Replacement";
   static String stringNoReplace = "One Random String Without Replacement";
   static String sortStrings = "Sorted Array";
   static String sortStringsNoDuplicates = "Sorted Array without Duplicates";
   static String reverseArray = "Reverse Sorted Array";
   static String reverseArrayNoDuplicates = "Reverse Sorted Array without Duplicates";

   //This is so we can make stringNoReplace work
   static String prevResult = "";
   String[] arr;
   ArrayList <String> list = new ArrayList<String>();

   public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
      String inputUse = "";
      String result = "";
      String warning = "";
      String operation = request.getParameter("Operation");
      String input = request.getParameter("Input");

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();

      if(operation.equals("Submit")){
         this.list = new ArrayList<String>();

         for (String s : input.split(" ")) {
            if(s.indexOf('<') != -1 && s.indexOf('>') != -1){
               s = s.substring(1, s.length() - 1);
            }
            this.list.add(s);
         }

         PrintHead(out);
         PrintBody(out, result, warning);
         PrintTail(out);

         return;
      }

      if (this.list.size() == 0) {
         warning = "You must first insert values!";
      } else {
         if (operation.equals(stringReplace)) {
            Random r = new Random();

            result = list.get(r.nextInt(list.size()));
            prevResult = result;

         } else if (operation.equals(stringNoReplace)) {
            Random r = new Random();

            int index = r.nextInt(list.size());
            result = list.get(index);
            while(prevResult.equals(result)){
               int newIndex = r.nextInt(list.size());
               result = list.get(newIndex);
            }
            prevResult = result;

         }else if (operation.equals(sortStrings)) {
            this.list.sort(null);
            
            result = String.join(" ", this.list); 
         }else if (operation.equals(sortStringsNoDuplicates)) {
            ArrayList <String> fixedList = new ArrayList<String>();
            this.list.sort(null);
            for(int i = 0; i < this.list.size(); i++){
               if(!fixedList.contains(this.list.get(i))){
                  fixedList.add(this.list.get(i));
               }
            }
            result = String.join(" ", fixedList);

         }else if (operation.equals(reverseArray)) {
            this.list.sort(null);
            
            Collections.reverse(this.list);

            result = String.join(" ", this.list);
 
         }else if (operation.equals(reverseArrayNoDuplicates)) {
            ArrayList <String> fixedList = new ArrayList<String>();
            this.list.sort(null);
            for(int i = 0; i < this.list.size(); i++){
               if(!fixedList.contains(this.list.get(i))){
                  fixedList.add(this.list.get(i));
               }
            }
            Collections.reverse(fixedList);

            result = String.join(" ", fixedList);
         }
      }

      PrintHead(out);
      PrintBody(out, result, warning);
      PrintTail(out);
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
      response.setContentType("text/html");

      PrintWriter out = response.getWriter();

      PrintHead(out);
      PrintBody(out);
      PrintTail(out);
   }

   private void PrintHead(PrintWriter out){
      out.println("<html>");
      out.println("");
      out.println("<head>");
      out.println("<title> Assignment 4 </title>");
      out.println("</head>");
   }

   private void PrintBody(PrintWriter out, String rsltString, String warning){
      out.println("<body>");
      out.println("<h>Collaborators: Arman Jumani, Shashwat Bhushan, Julien Kmec</h>");
      out.println("<p>Format strings so they are separated by spaces</p>");

      if (warning != null) {
         out.println("<p>" + warning + "</p>");
      }

      out.println("<fieldset>");
      out.println("  <form name = \"Form\"> ");
      out.println("     List of strings: <input type = \"text\" name = \"Input\">");
      out.println("     <input type = \"submit\" value = \"Submit\" formmethod = \"post\" name = \"Operation\">");
      out.println("  <table>");
      out.println("  <tr>");
      out.println("     <td>");
      out.println("     Random String:");
      out.println("        <input type = \"submit\" value = \"" + stringReplace +"\" formmethod = \"post\" name=\"Operation\">");
      out.println("     </td>");
      out.println("     <td>");
      out.println("        <input type = \"submit\" value = \"" + stringNoReplace + "\" formmethod = \"post\" name=\"Operation\">");
      out.println("     </td>");
      out.println("  </tr>");
      out.println("  <tr>");
      out.println("     <td>");
      out.println("     Sort Array:");
      out.println("        <input type = \"submit\" value = \"" + sortStrings +"\" formmethod = \"post\" name=\"Operation\">");
      out.println("     </td>");
      out.println("     <td>");
      out.println("        <input type = \"submit\" value = \"" + reverseArray +"\" formmethod = \"post\" name=\"Operation\">");
      out.println("     </td>");
      out.println("  </tr>");
      out.println("  <tr>");
      out.println("     <td>");
      out.println("     Sort Array:");
      out.println("        <input type = \"submit\" value = \"" + sortStringsNoDuplicates +"\" formmethod = \"post\" name=\"Operation\">");
      out.println("     </td>");
      out.println("     <td>");
      out.println("        <input type = \"submit\" value = \"" + reverseArrayNoDuplicates +"\" formmethod = \"post\" name=\"Operation\">");
      out.println("     </td>");
      out.println("  </tr>");
      out.println("  </table>");
      out.println("  Results: <form name = \"ResultField\">");
      out.println("  <input type = \"text\" name = \"Entry\" value = \"" +rsltString + "\">");
      out.println("  </form>");
      out.println("</fieldset>");
      out.println("<p>Collaboration Summary:");
      out.println("<p>Shashwat worked on part of doPost and all of doGet, PrintHead, and PrintTail</p>");
      out.println("</p>Arman worked on PrintBody method and ensuring that servlet displayed</p>");
      out.println("<p>Julien refactored doPost to use an ArrayList and coded all 4 of the main buttons. Also added the warning label to the HTML.</p>");
      out.println("</p>");
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
}
