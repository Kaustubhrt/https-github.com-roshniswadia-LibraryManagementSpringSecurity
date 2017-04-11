package controller;

import java.security.Principal;
import java.util.List;

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
import service.AdminService;
import service.AdminServiceImpl;

@Controller
public class AdminController {

	private static final String NULL_DETAILS = "Please enter Details";
	private static final String WRONG_DETAILS = "Invalid Details";
	private AdminService adminService;

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}


	@RequestMapping(value = "/admin/adminhomepage", method = RequestMethod.GET)
	public ModelAndView showHomePage() {
		
		System.out.println("############# IN SHOW PAGE");
		return new ModelAndView("adminhomepage");
	}


	/**
	 * Returns the Admin's HomePage if login credential are correct. If the
	 * credentials are wrong or if user enters null values it will redirect to
	 * the login Page.
	 * 
	 * @model The model.
	 * @param admin
	 *            An object of bean Admin which contain the username and
	 *            password.
	 * 
	 * @return ModelAndView
	 */

	@RequestMapping(value = "/admin/adminlogin", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

			System.out.println("In Admin Login");
		  ModelAndView model = new ModelAndView();
		  if (error != null) {
			model.addObject("error", "Invalid username and password!");
		  }

		  if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		  }
		  model.setViewName("adminlogin");

		  return model;
	}

	/**
	 * It is used to view all the Books present in the library.
	 * 
	 * @return the complete list of all books
	 */
	@RequestMapping(value = "/admin/viewAllBooks", method = RequestMethod.GET)
	public ModelAndView viewAllBooks() {

		final List<Book> bookList = adminService.viewAllBooks();
		return new ModelAndView("booklist", "bookList", bookList);
	}

	@RequestMapping(value = "/admin/edit", method = RequestMethod.GET)
	public ModelAndView editBook(@RequestParam(value = "bookId", required = true) final String bookId
			) {
		final Book book = adminService.getBook(Integer.parseInt(bookId));
		return new ModelAndView("modifybook", "book", book);
	}

	@RequestMapping(value = "/admin/update", method = RequestMethod.POST)
	public ModelAndView editOrSaveBook(final Model model, @ModelAttribute("book") final Book book) {
		adminService.updateBook(book);
		final List<Book> bookList = adminService.viewAllBooks();
		return new ModelAndView("booklist", "bookList", bookList);
	}

	@RequestMapping(value = "/admin/delete", method = RequestMethod.GET)
	public ModelAndView deleteBook(final Model model,
			@RequestParam(value = "bookId", required = true) final String bookId) {
		//verifyLoginAttributes(req, userName, res);
		final boolean isIssued = adminService.deleteBook(Integer.parseInt(bookId));

		if (isIssued == true) {
			model.addAttribute("error", "Issued book cannot be deleted");
		}
		final List<Book> bookList = adminService.viewAllBooks();
		return new ModelAndView("booklist", "bookList", bookList);
	}

	@RequestMapping(value = "/admin/insert", method = RequestMethod.POST)
	public ModelAndView addNewBook(Model model, @ModelAttribute("book") final Book book) {
		//verifyLoginAttributes(req, userName, res);
		adminService.addNewBook(book);
		final List<Book> bookList = adminService.viewAllBooks();
		return new ModelAndView("booklist", "bookList", bookList);
	}

	@RequestMapping("/admin/addBook")
	public ModelAndView showAddEditBookPage(Model model) {
		//verifyLoginAttributes(req, userName, res);
		return new ModelAndView("modifybook");
	}

	@RequestMapping(value = "/admin/viewAllUser", method = RequestMethod.GET)
	public ModelAndView viewAllUsers() {
		final List<User> userList = adminService.viewAllUsers();
		return new ModelAndView("userlist", "userList", userList);
	}

	@ExceptionHandler(RuntimeException.class)
	public ModelAndView handle(final RuntimeException ex) {
		return new ModelAndView("error", "exception", ex);
	}

	@RequestMapping(value = "/admin/accessDeniedRedirect", method = RequestMethod.GET)
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
