package it.rubrica.presentation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import it.rubrica.business.RubricaEjb;
import it.rubrica.data.Contatto;
import it.rubrica.data.NumTelefono;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cognomeservlet")
public class CognomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

   @EJB
   RubricaEjb rubricajb;
    public CognomeServlet() {
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String cognome = request.getParameter("cognome");
		if 
		(cognome== null)
		out.println("<p style='text-align:center; front-weight: bold'>Cognome selezionato:" + cognome + " " + "NON PRESENTE!"+ "</p>");
		
		List<Contatto> lista = rubricajb.getContattoByCognome(cognome);
		
		for (Contatto c : lista) {
			ArrayList<NumTelefono> numTelefoni = c.getNumTelefoni();
			
			out.println(" Nome : " + c.getNome()+ " | " + "Cognome :" + c.getCognome() + " | " );
			
			for(NumTelefono n : numTelefoni) {
				out.println(" | " + n.getNumTelefono()+ " | ");
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
