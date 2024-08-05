package CST8132A2.system.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import CST8132A2.system.book.Book;
import CST8132A2.system.user.UserPlan;
import CST8132A2.system.user.User;

class TestCaseUser {


	@Test
	void testUser() {
		Book book;
		UserPlan plan;
		User user;
		System.out.println("Testing users...................");
		plan = UserPlan.createPlan("invalid", "invalid");
		assertNull(plan);
		System.out.println("User test1 - Invalid plan checked");
		plan = UserPlan.createPlan("trial", "true");
		assertNotNull(plan);
		System.out.println("User test2 - Valid plan created");
		user = User.createUser("", "invalid");
		assertNull(user);
		System.out.println("User test3 - Invalid user checked");
		user = User.createUser("Paulo", "paulo@mail.com");
		assertNotNull(user);
		System.out.println("User test4 - Valid user created");
		user.setPlan(plan);
		plan = user.getPlan();
		assertNotNull(plan);
		System.out.println("User test5 - Valid plan from user");
		book = Book.createBook("1000", "MyBook", "Paulo Sousa", "English", "2000", "0", "Fiction");
		assertNotNull(book);
		System.out.println("User test6 - Valid book2 created");
		try {
			user.addToBooklist(book);
		} catch (Exception e) {
			fail("Error creating books");
		}
		int size = user.getBookListSize();
		assertEquals(1, size);
		System.out.println("User test7 - Valid book inclusion in list");
		boolean canRead = book.read(user);
		assertTrue(canRead);
		System.out.println("User test8 - Valid book read checked");
		boolean canDownload = book.download(user);
		assertFalse(canDownload);
		System.out.println("User test9 - Invalid book download checked");
	}
}
