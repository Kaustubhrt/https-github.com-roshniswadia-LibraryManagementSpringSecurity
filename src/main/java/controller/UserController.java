package controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bean.Book;
import bean.User;
import service.UserService;

@Controller
public class UserController {

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/loginRedirectUser")
	public ModelAndView redirectform() {
		return new ModelAndView("userlogin");
	}

	@RequestMapping("/user/userhomepage")
	public ModelAndView userhomePageform(final Model model) {
		System.out.println("############ In User Home Page Form");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String username = auth.getName(); //get logged in username
	      
	      int userId = userService.getIdOfUser(username);
	      
		return new ModelAndView("userhomepage","userId",userId);
	}

	@RequestMapping(value = "/user/userlogin", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

			System.out.println("In User Login");
		  ModelAndView model = new ModelAndView();
		  if (error != null) {
			model.addObject("error", "Invalid username and password!");
		  }

		  if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		  }
		  model.setViewName("userlogin");

		  return model;

		}

	@RequestMapping(value = "/user/viewAvailableBooks", method = RequestMethod.GET)
	public ModelAndView viewAvailableBooks(final Model model,
			@RequestParam(value = "userId", required = true) final String userId) {

		final List<Book> bookList = userService.viewAllAvailableBooks(Integer.parseInt(userId));
		model.addAttribute("userId", userId);
		return new ModelAndView("userbooklist", "bookList", bookList);
	}

	@RequestMapping(value = "/user/issue", method = RequestMethod.GET)
	public ModelAndView issueBook(final Model model,
			@RequestParam(value = "userId", required = true) final String userId,
			@RequestParam(value = "bookId", required = true) final String bookId) {
		userService.issueBook(Integer.parseInt(userId), Integer.parseInt(bookId));
		model.addAttribute("userId", userId);
		final List<Book> bookList = userService.viewIssuedBookList(Integer.parseInt(userId));
		return new ModelAndView("userissuedbook", "bookList", bookList);
	}

	@RequestMapping(value = "/user/return", method = RequestMethod.GET)
	public ModelAndView returnBook(final Model model,
			@RequestParam(value = "userId", required = true) final String userId,
			@RequestParam(value = "bookId", required = true) String bookId) {
		userService.returnBook(Integer.parseInt(userId), Integer.parseInt(bookId));
		model.addAttribute("userId", userId);
		final List<Book> bookList = userService.viewIssuedBookList(Integer.parseInt(userId));
		return new ModelAndView("userissuedbook", "bookList", bookList);
	}

	@RequestMapping(value = "/user/viewAllIssuedBooks", method = RequestMethod.GET)
	public ModelAndView viewIssuedBookList(final Model model,
			@RequestParam(value = "userId", required = true) final String userId) {
		final List<Book> bookList = userService.viewIssuedBookList(Integer.parseInt(userId));
		model.addAttribute("userId", userId);
		return new ModelAndView("userissuedbook", "bookList", bookList);

	}

	@ExceptionHandler(RuntimeException.class)
	public ModelAndView handle(final RuntimeException ex) {
		return new ModelAndView("error", "exception", ex);
	}

	@RequestMapping(value = "/user/accessDeniedRedirect", method = RequestMethod.GET)
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName()
			+ ", you do not have permission to access this page!");
		} else {
			model.addObject("msg",
			"You do not have permission to access this page!");
		}

		model.setViewName("accessdenied");
		return model;

	}
	
}
