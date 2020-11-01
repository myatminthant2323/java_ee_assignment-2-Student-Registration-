package com.mmit.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import com.mmit.entity.Classes;
import com.mmit.entity.Course;

@WebServlet(urlPatterns = {"/class-add", "/classes", "/class-edit", "/class-remove"},loadOnStartup = 1)

public class ClassController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CourseService courseService;
	private ClassService classService;
	
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
		
		courseService = new CourseService(EMF.createEntityManager());
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
		if ("/class-add".equals(path) || "/class-edit".equals(path)) {
			
			// get category list from database
			List<Course> courselist = courseService.findAll();
			
			// add list to request
			req.setAttribute("courses", courselist);
			
			if ("/class-edit".equals(path)) {
				
				// get item id from request 
				String id = req.getParameter("classid");
				
				
				// find item to database
				Classes classobj = classService.findById(Integer.parseInt(id));
				
				// add item to request
				req.setAttribute("classes", classobj);
			}
			
			// forward page
			getServletContext().getRequestDispatcher("/class-add.jsp").forward(req, resp);
		
		} else if ("/classes".equals(path)) {
			getServletContext().setAttribute("page", "");
			getServletContext().setAttribute("page", "class");
			// get items from database
			List<Classes> list = classService.findAll();
			
			// add items to request 
			req.setAttribute("classes", list);
			
			// forward page
			getServletContext().getRequestDispatcher("/class.jsp").forward(req, resp);
			
		} else if ("/class-remove".equals(path)) {
			
			// get id from request
			String id = req.getParameter("classid");
			
			// remove from database
			classService.delete(Integer.parseInt(id));
			
			// redirect page
			resp.sendRedirect(req.getContextPath().concat("/classes"));
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action = req.getServletPath();
		if ("/class-add".equals(action)) {
			
			String id = req.getParameter("classid");
			String courseid = req.getParameter("courseid");
			String name = req.getParameter("name");
			String startDate = req.getParameter("start_date");
			
			Classes classes = (id.equals("") ? new Classes() : classService.findById(Integer.parseInt(id)));
			classes.setName(name);
			
			classes.setStart_date(LocalDate.parse(startDate));
			classes.setCourse(courseService.findById(Integer.parseInt(courseid)));
			
			// insert to database
			classService.save(classes);
			
			// redirect
			resp.sendRedirect(req.getContextPath().concat("/classes"));
			
		}
	}
}
