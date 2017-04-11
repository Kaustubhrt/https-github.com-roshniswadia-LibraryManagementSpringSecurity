package dao;

import java.util.List;

import bean.Book;
import bean.User;

public interface AdminDao {

	public List<Book> viewAllBooks();
	public Book getBook(final int bookId);
	public void updateBook(final Book book);
	public void deleteBook(final int bookId);
	public void addNewBook(final Book book);
	public List<User> viewAllUsers();
	public String checkBookIssueStatus(final int bookId);
}
