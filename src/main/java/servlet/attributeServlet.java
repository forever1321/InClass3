// From "Professional Java Server Programming", Patzer et al.,

// Import Servlet Libraries
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;
import java.util.Scanner;
import javax.servlet.annotation.WebServlet;
// Import Java Libraries
import java.io.*;
import java.util.Enumeration;

@WebServlet(name = "AttributeServlet", urlPatterns = {"/sbhushaAttributeServlet"})
public class attributeServlet extends HttpServlet
{
public void doGet (HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException
{
     
   // Get session object  
   HttpSession session = request.getSession();

   String name   = request.getParameter("attrib_name");
   String value  = request.getParameter("attrib_value");
   String nickName  = request.getParameter("attrib_nickName");
   String hobby = request.getParameter("attrib_hobby");
   String remove = request.getParameter("attrib_remove");

   if (remove != null && remove.equals("on"))
   {
      session.removeAttribute(name);
      session.removeAttribute(nickName);
   }
   else
   {
      if ((name != null && name.length() > 0) && (value != null && value.length() > 0))
      {
         session.setAttribute(name, value);
      }
      if ((nickName != null && nickName.length() > 0) && (hobby != null && hobby.length() > 0)){
         session.setAttribute(nickName, hobby);
      }

   }

   response.setContentType("text/html");
   PrintWriter out = response.getWriter();

   out.println("<html>");
   // no-cache lets the page reload by clicking on the reload link
   out.println("<meta http-equiv=\"Pragma\" content=\"no-cache\">");
   out.println("<head>");
   out.println(" <title>Session lifecycle</title>");
   out.println("</head>");
   out.println("");

   out.println("<body>");
   out.println("<h1><center>Session attributes</center></h1>");

   out.println("Enter name and value of an attribute");

   // String url = response.encodeURL ("offutt/servlet/attributeServlet");
   String url = response.encodeURL("sbhushaAttributeServlet");
   out.println("<form action=\"" + url + "\" method=\"GET\">");
   out.println(" Name: ");
   out.println(" <input type=\"text\" size=\"10\" name=\"attrib_name\">");

   out.println(" Value: ");
   out.println(" <input type=\"text\" size=\"10\" name=\"attrib_value\">");
   
   out.println(" NickName: ");
   out.println(" <input type=\"text\" size=\"10\" name=\"attrib_nickName\">");
   
   out.println(" Hobby: ");
   out.println(" <input type=\"text\" size=\"10\" name=\"attrib_hobby\">");
   
   out.println(" <br><input type=\"checkbox\" name=\"attrib_remove\">Remove");
   out.println(" <input type=\"submit\" name=\"update\" value=\"Update\">");
   out.println(" <br><br><a href=\"/sbhushaAttributeServlet?action=invalidate\">Invalidate the session</a>");
   out.println("</form>");
   out.println("<hr>");

   out.println("Attributes in this session:");
   Enumeration e = session.getAttributeNames();
   while (e.hasMoreElements())
   {
      String att_name  = (String) e.nextElement();
      String att_value = (String) session.getAttribute(att_name);
      String att_nickName = (String) e.nextElement();
      String att_hobby = (String) session.getAttribute(att_nickName);

      out.print  ("<br><b>Name:</b> ");
      out.println(att_name);
      out.print  ("<br><b>Value:</b> ");
      out.println(att_value);
      out.print  ("<br><b>NickName:</b> ");
      out.println(att_nickName);
      out.print  ("<br><b>Hobby:</b> ");
      out.println(att_hobby);
   } //end while
   
   out.println("</body>");
   out.println("</html>");
   out.close();
} // End doGet
} //End  SessionLifeCycle