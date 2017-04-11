package dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import bean.Book;
import bean.User;

public class AdminDaoImpl implements AdminDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}


	public List<Book> viewAllBooks() {

		final Session session = this.sessionFactory.getCurrentSession();
		final List<Book> empList = session.createQuery("from Book").list();
		return empList;

	}

	public Book getBook(final int bookId) {

		final Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Book b where b.bookId =:bookId");
		query.setInteger("bookId", bookId);

		final Book emp = (Book) query.uniqueResult();

		return emp;
	}

	public void updateBook(final Book book) {

		final Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("update Book b set b.bookName = :bookName ,b.authorName =:authorName , "
				+ "b.issueStatus =:issueStatus,b.amount =:amount where b.bookId =:bookId");
		query.setString("bookName", book.getBookName());
		query.setString("authorName", book.getAuthorName());
		query.setString("issueStatus", book.getIssueStatus());
		query.setInteger("amount", book.getAmount());

		query.setInteger("bookId", book.getBookId());
		query.executeUpdate();
	}

	public void deleteBook(final int bookId) {

		final Session session = this.sessionFactory.getCurrentSession();
			Query query2 = session.createQuery("delete from Book e where e.bookId =:bookId");
			query2.setInteger("bookId", bookId);
			query2.executeUpdate();
	}
	
	public String checkBookIssueStatus(final int bookId){
		final Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("select issueStatus from Book b where b.bookId =:bookId");
		query.setInteger("bookId", bookId);
		final String issueStatus = (String) query.uniqueResult();
		return issueStatus;
		
	}

	public void addNewBook(final Book book) {

		final Session session = this.sessionFactory.getCurrentSession();
		session.persist(book);
	}

	public List<User> viewAllUsers() {

		final Session session = this.sessionFactory.getCurrentSession();
		final List<User> userList = session.createQuery("from User").list();
		return userList;
	}

}
