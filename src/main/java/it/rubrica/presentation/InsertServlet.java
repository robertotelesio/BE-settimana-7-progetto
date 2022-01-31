package it.rubrica.presentation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import it.rubrica.business.RubricaEjb;
import it.rubrica.data.Contatto;
import it.rubrica.data.NumTelefono;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/insert")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
@EJB
RubricaEjb rubricaEjb;
    
    public InsertServlet() {
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Contatto c = new Contatto();

		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String email = request.getParameter("email");

		c.setNome(nome);
		c.setCognome(cognome);
		c.setEmail(email);

		NumTelefono numero1 = new NumTelefono();
		NumTelefono numero2 = new NumTelefono();

		ArrayList<NumTelefono> numeriTelefono = new ArrayList<NumTelefono>();

		if (!request.getParameter("numero1").isBlank()) {
			numero1.setNumTelefono(request.getParameter("numero1"));
			numero1.setContatto(c);
			numeriTelefono.add(numero1);

		}

		 if (!request.getParameter("numero2").isBlank()) {
			numero2.setNumTelefono(request.getParameter("numero2"));
			numero2.setContatto(c);
			numeriTelefono.add(numero2);
		}

		  if (request.getParameter("numero1").isBlank() && request.getParameter("numero2").isBlank()) {
			HttpSession session = request.getSession();
			session.setAttribute("messaggio", "Attenzione! Inserire almeno un numero di telefono!");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/insert.jsp");
			dispatcher.forward(request, response);

		}
		c.setNumTelefoni(numeriTelefono);
		rubricaEjb.insertContatto(c);
		
		out.println("Il nome inserito è : "+request.getParameter("nome")+" | ");
		out.println("Il cognome inserito è : "+request.getParameter("cognome")+" | ");
		out.println("L' email inserito è : "+request.getParameter("email")+" | ");
		out.println("Il numero1 inserito è : "+request.getParameter("numero1")+" | ");
		out.println("Il numero2 inserito è : "+request.getParameter("numero2")+" | ");
	}

}