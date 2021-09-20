import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
//import com.oreilly.servlet.MultipartRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.tomcat.util.buf.Base64;

public class SignUpSelvlet extends HttpServlet {
public void doPost(HttpServletRequest request, HttpServletResponse response) 
throws ServletException, IOException {
    PrintWriter out = response.getWriter();
     response.setContentType("text/html");
     String name = request.getParameter("name");
     String password = request.getParameter("password");
     DataManager dm = new DataManager();
     UserManager um = new UserManager(dm);
     if(name != "" && password != "") {
        um.addUser(name, password);
        SecurityManager.authenticate(request, name, password);
        response.sendRedirect("./main.html");
        return;
     }
        SecurityManager.redirect(response);

   }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
    }
}