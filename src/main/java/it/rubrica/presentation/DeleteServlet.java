package it.rubrica.presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import it.rubrica.business.RubricaEjb;
import it.rubrica.data.Contatto;


@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public DeleteServlet() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Integer id = Integer.valueOf(request.getParameter("id"));
	Contatto c = new Contatto();
	c.setId(id);
	RubricaEjb rubricaEjb = new RubricaEjb();
	rubricaEjb.deleteContatto(c);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
