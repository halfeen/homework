package oma_projekti.homework.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import oma_projekti.homework.domain.Course;
import oma_projekti.homework.domain.CourseRepository;
import oma_projekti.homework.domain.Homework;
import oma_projekti.homework.domain.HomeworkRepository;
import oma_projekti.homework.domain.StudentRepository;

@Controller
public class HomeworkController {
	
	@Autowired
	HomeworkRepository homeworkRepo;
	@Autowired
	CourseRepository courseRepo;
	@Autowired
	StudentRepository studentRepo;

	//Welcome index page
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String Welcome() {
		return "welcome";
	}
	
	// Login
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	
    
	// User welcome page
    @RequestMapping(value="/welcome")
    public String UserWelcome() {	
        return "userWelcome";
    }	
	
	//Listing Homework
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/homework", method = RequestMethod.GET)
	public String getHomework(Model model) {
		model.addAttribute("homeworks", homeworkRepo.findAll());
		return "/homeworklist";
	}
	
    //Check username
    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Principal principal) {
       return principal.getName();
    }
    
	//User only sees their own homework
	@RequestMapping(value="/userhomework", method = RequestMethod.GET)
	public String getStudentHomework(Model model, Principal principal) {
		String username = principal.getName(); //get logged in username, eli esim student1
		model.addAttribute("homeworks", homeworkRepo.findByOwner(username));
		return "/userhomeworklist";
	}
	
	//Listing Courses
	@RequestMapping(value = "/courses", method = RequestMethod.GET)
	public String getCourses(Model model) {
		model.addAttribute("courses", courseRepo.findAll());
		return "/courselist";
	}
	
	//Add new homework
	/*@RequestMapping(value="/savehomework", method = RequestMethod.GET)
	public String getNewHomeworkForm(Model model) {
		//String username = principal.getName();
		model.addAttribute("homework", new Homework()); 
		model.addAttribute("courses", courseRepo.findAll());
		return "savehomework";
		
	}*/
	
	//Add new homework
	@RequestMapping(value="/savehomework", method = RequestMethod.GET)
	public String getNewHomeworkForm(Model model, Principal principal) {
		String username = principal.getName();
		model.addAttribute("owner", username);
		model.addAttribute("homework", new Homework());
		model.addAttribute("courses", courseRepo.findAll());
		return "/savehomework";
	}
	
	//Save the homework
	@RequestMapping(value="/savehomework", method = RequestMethod.POST)
	public String saveNewHomework(Homework homework) {
		homeworkRepo.save(homework);
		return "redirect:/userhomework";
	}
	
	//Add new course
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/savecourse", method = RequestMethod.GET)
	public String getNewCourseForm(Model model) {
		model.addAttribute("course", new Course());
		return "/savecourse";
	}
	
	//Save the course
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/savecourse", method = RequestMethod.POST)
	public String saveNewCourse(Course course) {
		courseRepo.save(course);
		return "redirect:/courses";
	}
	
	//Deleting homework
	@RequestMapping(value="/deletehomework/{homeId}", method = RequestMethod.GET)
	public String deleteHomework(@PathVariable("homeId") Long homeId) {
		homeworkRepo.deleteById(homeId);
		return "redirect:../userhomework";
	}
	
	//Deleting a course
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/deletecourse/{courseId}", method = RequestMethod.GET)
	public String deleteCourse(@PathVariable("courseId") Long courseId) {
		courseRepo.deleteById(courseId);
		return "redirect:../courses";
	}
	
	//Editing homework
	@RequestMapping(value= "/savehomework/{homeId}")
	public String saveHomework(@PathVariable("homeId") Long homeId, Model model) {
		model.addAttribute("homework", homeworkRepo.findById(homeId));
		model.addAttribute("courses", courseRepo.findAll());
		return "/savehomework";
	}
	
	//Editing course
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value= "/savecourse/{courseId}")
	public String saveCourse(@PathVariable("courseId") Long courseId, Model model) {
		model.addAttribute("course", courseRepo.findById(courseId));
		return "/savecourse";
	}
	

	
	


}









