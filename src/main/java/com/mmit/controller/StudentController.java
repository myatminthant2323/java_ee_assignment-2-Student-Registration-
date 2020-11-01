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
import com.mmit.controller.service.StudentService;
import com.mmit.entity.Course;
import com.mmit.entity.Student;

@WebServlet(urlPatterns = { "/students", "/student-add", "/student-edit", "/student-remove" }, loadOnStartup = 1)
public class StudentController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private StudentService studentService;

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
		studentService = new StudentService(EMF.createEntityManager());
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
		if ("/student-add".equals(action)) {
			// get data from req

			String id = req.getParameter("studentid");
			String name = req.getParameter("studentname");
			String email = req.getParameter("email");
			String phone = req.getParameter("phone");
			// create entity obj
			Student s = (id.equals("") ? new Student() : studentService.findById(Integer.parseInt(id)));
			s.setName(name);
			s.setEmail(email);
			s.setPhno(phone);
			// insert to db
			studentService.save(s);
			// redirect page
			resp.sendRedirect(req.getContextPath().concat("/students"));
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if ("/students".equals(path)) {
			getServletContext().setAttribute("page", "");
			getServletContext().setAttribute("page", "student");
			List<Student> students =studentService.findAll();
			req.setAttribute("students", students);
			getServletContext().getRequestDispatcher("/student.jsp").forward(req, resp);
		}else if("/student-edit".equals(path)) {
			String studentid = req.getParameter("studentid");
			System.out.println("studentid: "+studentid);
			Student student = studentService.findById(Integer.parseInt(studentid));
			req.setAttribute("student", student);
			// forward page
			getServletContext().getRequestDispatcher("/student-add.jsp").forward(req , resp);
		}
		else if("/student-remove".equals(path)) {
			String studentid = req.getParameter("studentid");
			studentService.delete(Integer.parseInt(studentid));
			resp.sendRedirect(req.getContextPath().concat("/students"));
		}
	}

}
