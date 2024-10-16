package org.persona.dao;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.persona.modelo.Persona;

public class PersonaDao {
	private ObjectMapper mapper = new ObjectMapper();
	public void addPersona(Persona persona) {
		AnnotationConfiguration cfg = new AnnotationConfiguration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(persona);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	public void updatePersona(Persona persona) {
		AnnotationConfiguration cfg = new AnnotationConfiguration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			session.update(persona);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	public void deletePersona(int id) {
		AnnotationConfiguration cfg = new AnnotationConfiguration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.getCurrentSession();
		Persona persona = null;
		try {
			session.beginTransaction();
			persona = (Persona) session.get(Persona.class, id);
			session.delete(persona);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	public Persona getPersonaById(int id) {
		AnnotationConfiguration cfg = new AnnotationConfiguration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.getCurrentSession();
		Persona persona = null;
		try {
			session.beginTransaction();
			persona = (Persona) session.get(Persona.class, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
		return persona;
	}
	
	@SuppressWarnings("unchecked")
	public List<Persona> listPersonas() {
		AnnotationConfiguration cfg = new AnnotationConfiguration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.getCurrentSession();
		List<Persona> personas = null;
		try {
			session.beginTransaction();
			personas = (List<Persona>) session.createQuery("from Persona").list();
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
		return personas;
	}
	
	public <T> T convertJsonToJavaObject(String jsonString, Class<T> cls) {
		T result = null;
		try {
			result = mapper.readValue(jsonString, cls);
		} catch (JsonParseException e) {
			System.out.println("EXCEPTION OCURRED WHILE CONVERT JSON TO JAVA OBJECT");
			e.printStackTrace();
		} catch (JsonMappingException e) {
			System.out.println("EXCEPTION OCURRED WHILE CONVERT JSON TO JAVA OBJECT");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("EXCEPTION OCURRED WHILE CONVERT JSON TO JAVA OBJECT");
			e.printStackTrace();
		}
		return result;
	}
}
