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

public class TempServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SecurityManager.setAuthenticated(request, "Molly");
	}
}