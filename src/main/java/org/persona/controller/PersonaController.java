package org.persona.controller;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.persona.dao.PersonaDao;
import org.persona.modelo.Persona;

@Path("/service")
public class PersonaController {
	private PersonaDao dao = new PersonaDao();
	@GET
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Persona> listPersonas() {
		List<Persona> personas = (List<Persona>) dao.listPersonas();
		return personas;
	}
	
	@GET
	@Path("/persona/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Persona getPersonaById(@PathParam("id") int id) {
		return dao.getPersonaById(id);
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public String addPersona(String datos) throws IOException {
		Persona persona = dao.convertJsonToJavaObject(datos, Persona.class);
		persona.setNombre(persona.getNombre());
		persona.setTelefono(persona.getTelefono());
		dao.addPersona(persona);
		return "SUCCESS_RESULT: INSERT INTO...";
	}
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public String updatePersona(String datos) throws IOException {
		Persona persona = dao.convertJsonToJavaObject(datos, Persona.class);
		persona.setId(persona.getId());
		persona.setNombre(persona.getNombre());
		persona.setTelefono(persona.getTelefono());
		dao.updatePersona(persona);
		return "SUCCESS_RESULT: UPDATE RECORD DATABASE...";
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deletePersona(@PathParam("id") int id) {
		dao.deletePersona(id);
		return "SUCCESS_RESULT: DELETE RECORD DATABASE...";
	}
}
