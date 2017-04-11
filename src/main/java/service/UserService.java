package service;

import java.util.List;

import bean.Book;

public interface UserService {
	
	public int getIdOfUser(final String username);
	public List<Book> viewAllAvailableBooks(final int userId);
	public void issueBook(final int userId,final int bookId);
	public List<Book> viewIssuedBookList(final int userId);
	public void returnBook(final int userId,final int bookId);

}
