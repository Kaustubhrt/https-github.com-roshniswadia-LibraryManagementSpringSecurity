package service;

import java.util.List;

import bean.Book;
import bean.User;

public interface AdminService {
	
	public List<Book> viewAllBooks();
	public Book getBook(final int bookId);
	public void updateBook(final Book book);
	public boolean deleteBook(final int bookId);
	public void addNewBook(final Book book);
	public List<User> viewAllUsers();

}