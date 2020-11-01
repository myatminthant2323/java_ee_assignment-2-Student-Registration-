package com.mmit.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mmit.controller.service.ClassService;
import com.mmit.controller.service.CourseService;
import com.mmit.controller.service.RegistrationService;
import com.mmit.controller.service.StudentService;
import com.mmit.entity.Classes;
import com.mmit.entity.Course;
import com.mmit.entity.Registration;
import com.mmit.entity.Student;

@WebServlet(urlPatterns = {"/registration-add", "/registrations", "/registration-edit", "/registration-remove"},loadOnStartup = 1)

public class RegistrationController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private StudentService studentService;
	private ClassService classService;
	private RegistrationService registrationService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
		EntityManagerFactory EMF = null;
		
		Object obj = getServletContext().getAttribute("emf"); // application scope
		if (obj == null) {
			
			EMF = Persistence.createEntityManagerFactory("student-registration");
			getServletContext().setAttribute("emf", EMF);
		} else {
			
			EMF = (EntityManagerFactory) obj;
		}
		registrationService = new RegistrationService(EMF.createEntityManager());
		studentService = new StudentService(EMF.createEntityManager());
		classService = new ClassService(EMF.createEntityManager());
		
	}
	
	
	@Override
	public void destroy() {
		
		EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
		if (emf != null && emf.isOpen()) {
			emf.close();
		}
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = req.getServletPath();
		if ("/registration-add".equals(path) || "/registration-edit".equals(path)) {
			
			List<Student> studentlist = studentService.findAll();
			List<Classes> classlist = classService.findAll();
			
			req.setAttribute("students", studentlist);
			req.setAttribute("classes", classlist);
			
			if ("/registration-edit".equals(path)) {
				
				// get item id from request 
				String id = req.getParameter("regid");
				
				
				// find item to database
				Registration registrationobj = registrationService.findById(Integer.parseInt(id));
				
				// add item to request
				req.setAttribute("registration", registrationobj);
			}
			
			// forward page
			getServletContext().getRequestDispatcher("/registration-add.jsp").forward(req, resp);
		
		} else if ("/registrations".equals(path)) {
			getServletContext().setAttribute("page", "");
			getServletContext().setAttribute("page", "registration");
			// get items from database
			List<Registration> list = registrationService.findAll();
			
			// add items to request 
			req.setAttribute("registrations", list);
			
			// forward page
			getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);
			
		} else if ("/registration-remove".equals(path)) {
			
			// get id from request
			String id = req.getParameter("regid");
			
			// remove from database
			registrationService.delete(Integer.parseInt(id));
			
			// redirect page
			resp.sendRedirect(req.getContextPath().concat("/registrations"));
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action = req.getServletPath();
		if ("/registration-add".equals(action)) {
			
			// get data from request
			String id = req.getParameter("regid");
			String stdid = req.getParameter("stdid");
			String classid = req.getParameter("classid");
			String register_date = req.getParameter("register_date");
			String paid_amount = req.getParameter("paid_amount");
			
			// create item object
			Registration registration = (id.equals("") ? new Registration() : registrationService.findById(Integer.parseInt(id)));
			registration.setStudent(studentService.findById(Integer.parseInt(stdid)));
			registration.setClasses(classService.findById(Integer.parseInt(classid)));
			registration.setRegistrationDate(LocalDate.now());
			registration.setPaidAmount(Integer.parseInt(paid_amount));
			
			// insert to database
			registrationService.save(registration);
			
			// redirect
			resp.sendRedirect(req.getContextPath().concat("/registrations"));
			
		}
	}
}
