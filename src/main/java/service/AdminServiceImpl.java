package service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import bean.Book;
import bean.User;
import dao.AdminDao;
import dao.AdminDaoImpl;

public class AdminServiceImpl implements AdminService {

	private AdminDao adminDao;

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	
	@Transactional(readOnly = true)
	public List<Book> viewAllBooks() {
		final List<Book> books = adminDao.viewAllBooks();
		return books;
	}

	@Transactional(readOnly = true)
	public Book getBook(final int bookId) {
		final Book book = adminDao.getBook(bookId);
		return book;
	}

	@Transactional
	public void updateBook(final Book book) {
		adminDao.updateBook(book);
	}

	@Transactional
	public void deleteBook(final int bookId) {
		adminDao.deleteBook(bookId);
	}

	@Transactional
	public void addNewBook(final Book book) {
		adminDao.addNewBook(book);
	}
	
	@Transactional(readOnly = true)
	public List<User> viewAllUsers() {
		final List<User> users = adminDao.viewAllUsers();
		return users;
	}
	
	@Transactional(readOnly = true)
	public String checkBookIssueStatus(final int bookId){
		String issueStatus = adminDao.checkBookIssueStatus(bookId);
		return issueStatus;
	}

}
