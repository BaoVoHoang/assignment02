package CST8132A2.system.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;

import CST8132A2.system.book.Book;

class TestCaseBook {
	

	@Test
	void testAccessBook() {
		Book book;
		System.out.println("Testing books...................");
		book = Book.createBook("", "null", null, "error", "invalid", "a", null);
		assertNull(book);
		System.out.println("Book test1 - Invalid book checked");
		book = Book.createBook("1000", "MyBook", "Paulo Sousa", "English", "2000", "0", "Fiction");
		assertNotNull(book);
		System.out.println("Book test2 - Valid book created");
	}

}
