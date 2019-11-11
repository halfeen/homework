package oma_projekti.homework.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	//Listing complete list of Homework (every users homework)
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/homeworklist", method = RequestMethod.GET)
	public String getHomework(Model model) {
		model.addAttribute("homeworks", homeworkRepo.findAll());
		return "homeworklist";
	}
	
    //Check username --> EI KÄYTÖSSÄ MIHINKÄÄN TÄLLÄ HETKELLÄ
    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Principal principal) {
       return principal.getName();
    }
    
	//User specific list of homework
	@RequestMapping(value="/userhomeworklist", method = RequestMethod.GET)
	public String getUsersHomework(Model model, Principal principal) {
		String username = principal.getName(); //get logged in username, eli esim student1
		model.addAttribute("homeworks", homeworkRepo.findByOwner(username));
		return "userhomeworklist";
	}
	
	//Listing Courses
	@RequestMapping(value = "/courselist", method = RequestMethod.GET)
	public String getCourselist(Model model) {
		model.addAttribute("courses", courseRepo.findAll());
		return "courselist";
	}
	
	//Add new homework --> yksi monesta yrityksestä saada owner automaattisesti lisättyä homeworkkiin
	// Lisää löytyy Homework-luokasta
	
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
		model.addAttribute("owner", username); //ei vissiin tee mitään(?)
		model.addAttribute("homework", new Homework());
		model.addAttribute("courses", courseRepo.findAll());
		return "savehomework";
	}
	
	//Save the homework
	@RequestMapping(value="/savehomework", method = RequestMethod.POST)
	public String saveNewHomework(@ModelAttribute Homework homework) {
		homeworkRepo.save(homework);
		return "redirect:userhomeworklist";
	}
	
	//Add new course
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/savecourse", method = RequestMethod.GET)
	public String getNewCourseForm(Model model) {
		model.addAttribute("course", new Course());
		return "savecourse";
	}
	
	//Save the course
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/savecourse", method = RequestMethod.POST)
	public String saveNewCourse(@ModelAttribute Course course) {
		courseRepo.save(course);
		return "redirect:courselist";
	}
	
	//Deleting user homework
	@RequestMapping(value="/deleteuserhomework/{homeId}", method = RequestMethod.GET)
	public String deleteUserHomework(@PathVariable("homeId") Long homeId) {
		homeworkRepo.deleteById(homeId);
		return "redirect:../userhomeworklist";
	}
	
	//Delete homework from complete list
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/deletehomework/{homeId}", method = RequestMethod.GET)
	public String deleteHomework(@PathVariable("homeId") Long homeId) {
		homeworkRepo.deleteById(homeId);
		return "redirect:../homeworklist";
	}
	
	
	//Deleting a course
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/deletecourse/{courseId}", method = RequestMethod.GET)
	public String deleteCourse(@PathVariable("courseId") Long courseId) {
		courseRepo.deleteById(courseId);
		return "redirect:../courselist";
	}
	
	//Editing homework
	@RequestMapping(value= "/savehomework/{homeId}")
	public String editHomework(@PathVariable("homeId") Long homeId, Model model) {
		model.addAttribute("homework", homeworkRepo.findById(homeId));
		model.addAttribute("courses", courseRepo.findAll());
		return "savehomework";
	}
	
	//Editing course
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value= "/savecourse/{courseId}")
	public String editCourse(@PathVariable("courseId") Long courseId, Model model) {
		model.addAttribute("course", courseRepo.findById(courseId));
		return "savecourse";
	}
    
    
    
    
 
    
    
    // ***REST-services***
    
	//Home page of REST services
	@RequestMapping(value="/resthomepage", method = RequestMethod.GET)
	public String getRestHome() {
		return "resthomepage";
	}
    
    //RESTful service to get all homework
	@RequestMapping(value = "/homeworks", method = RequestMethod.GET)
	public @ResponseBody List<Homework> getHomeworkRest() {
		return (List<Homework>) homeworkRepo.findAll();
	}
	
    //RESTful service to get all courses
	@RequestMapping(value = "/courses", method = RequestMethod.GET)
	public @ResponseBody List<Course> getCourseRest() {
		return (List<Course>) courseRepo.findAll();
	}
	
	//RESTful service to get homework by owner
	@RequestMapping(value="/homeworks/{owner}", method = RequestMethod.GET)
	public @ResponseBody List<Homework> getHomeworkRest(@PathVariable("owner") String owner) {
		return homeworkRepo.findByOwner(owner);
	}

	
	


}









