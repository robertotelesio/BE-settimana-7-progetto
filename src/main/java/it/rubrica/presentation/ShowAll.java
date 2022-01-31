package it.rubrica.presentation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import it.rubrica.business.RubricaEjb;
import it.rubrica.data.Contatto;
import it.rubrica.data.NumTelefono;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/showall")
public class ShowAll extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @EJB
    RubricaEjb rubricaEjb;
    public ShowAll() {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		List<Object[]> lista = rubricaEjb.getAllContattiEnumeri();
		for(Object[] obj : lista){
			out.println("Nome:" + obj[0] + " | " + "cognome: " + obj[1]
                    + " | " + "Email: " + obj[2] + " | " + "Numero telefonico: " +  obj[3] + " | ");
			
		}
		
	}

}
