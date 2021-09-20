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

public class UploadServlet extends HttpServlet {
public void doPost(HttpServletRequest request, HttpServletResponse response) 
throws ServletException, IOException {
     response.setContentType("text/html");
     PrintWriter out = response.getWriter();
     //MultipartRequest m = new MultipartRequest(request,"c:/tomcat/webapps/upload/images");
     out.print("successfully uploaded");
   }

   private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(SecurityManager.isAuthenticated(request) == false) {
			SecurityManager.redirect(response);
			return;
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("This is the Test Servlet");

		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = (String) headerNames.nextElement();
			out.print("<br/>Header Name: <em>" + headerName);
			String headerValue = request.getHeader(headerName);
			out.print("</em>, Header Value: <em>" + headerValue);
			out.println("</em>");
		}

		out.println("<hr/>");
		String authHeader = request.getHeader("authorization");
		String encodedValue = authHeader.split(" ")[1];
		out.println("Base64-encoded Authorization Value: <em>" + encodedValue);
		// String decodedValue = Base64.base64Decode(encodedValue);
		// out.println("</em><br/>Base64-decoded Authorization Value: <em>" + decodedValue);
		out.println("</em>");
	}
}