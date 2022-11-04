package board_ex.model;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class BoardControl extends HttpServlet {
	
	private HashMap commandMap;
	private String	jspDir = "/04_board_ex";
	private String  error = "error.jsp";
  
    public BoardControl() {
        super();
        initCommand();
    }
    private void initCommand() {
    	commandMap = new HashMap();
    }
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		process(request, response);
	}

	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String nextpage;
		String ekey = request.getParameter("cmd");
		
		
	}

}
