package com.mmit.controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mmit.controller.service.CourseService;
import com.mmit.entity.Course;

@WebServlet(urlPatterns = { "/courses", "/course-add", "/course-edit", "/course-remove" }, loadOnStartup = 1)
public class CourseController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CourseService courseService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		EntityManagerFactory EMF = null;
		Object obj = getServletContext().getAttribute("emf");
		if (obj == null) {
			EMF = Persistence.createEntityManagerFactory("student-registration");
			getServletContext().setAttribute("emf", EMF);
		} else {
			EMF = (EntityManagerFactory) obj;
		}
		courseService = new CourseService(EMF.createEntityManager());
	}

	@Override
	public void destroy() {
		EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
		if (emf != null && emf.isOpen()) {
			emf.close();
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		if ("/course-add".equals(action)) {
			// get data from req

			String id = req.getParameter("courseid");
			String name = req.getParameter("coursename");
			String level = req.getParameter("level");
			String fees = req.getParameter("fees");
			String duration = req.getParameter("duration");
			// create entity obj
			Course c = (id.equals("") ? new Course() : courseService.findById(Integer.parseInt(id)));
			c.setName(name);
			c.setLevel(level);
			c.setFees(Integer.parseInt(fees));
			c.setDuration(Integer.parseInt(duration));
			// insert to db
			courseService.save(c);
			// redirect page
			resp.sendRedirect(req.getContextPath().concat("/courses"));
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if ("/courses".equals(path)) {
			getServletContext().setAttribute("page", "");
			getServletContext().setAttribute("page", "course");
			List<Course> courses = courseService.findAll();
			req.setAttribute("courses", courses);
			getServletContext().getRequestDispatcher("/course.jsp").forward(req, resp);
		}else if("/course-edit".equals(path)) {
			String courseid = req.getParameter("courseid");
			System.out.println("courseid: "+courseid);
			Course course = courseService.findById(Integer.parseInt(courseid));
			req.setAttribute("course", course);
			// forward page
			getServletContext().getRequestDispatcher("/course-add.jsp").forward(req , resp);
		}
		else if("/course-remove".equals(path)) {
			String courseid = req.getParameter("courseid");
			courseService.delete(Integer.parseInt(courseid));
			resp.sendRedirect(req.getContextPath().concat("/courses"));
		}
	}

}
