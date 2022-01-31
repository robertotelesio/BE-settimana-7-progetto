package it.rubrica.presentation;

import java.io.IOException;
import java.io.PrintWriter;



import it.rubrica.business.RubricaEjb;


import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
    public UpdateServlet() {
        // TODO Auto-generated constructor stub
    }
    @EJB
    RubricaEjb rubricaEjb;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		Integer idCont = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String email = request.getParameter("email");
		String numero1 = request.getParameter("numero1");
		String numero2 = request.getParameter("numero2");
		rubricaEjb.aggiornaContattoEsistente(idCont, numero1, numero2, nome, cognome, email);
		out.println("<h1> Contatto aggiornato con successo </h1>");

	}

	
		
	
	

}
