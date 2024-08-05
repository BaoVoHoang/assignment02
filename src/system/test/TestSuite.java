package CST8132A2.system.test;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ TestCaseBook.class, TestCaseUser.class })
public class TestSuite {

	static void printInitialMsg() {
		System.out.println("================================");
		System.out.println("||    Test Suite Validation   ||");
		System.out.println("================================");
	}

	@BeforeAll
	static void initAll() {
		printInitialMsg();
	}

	@BeforeEach
	void init() {
	}

	@Test
	void succeedingTest() {
	}

	@Test
	void failingTest() {
		//fail("a failing test");
	}

	@Test
	@Disabled("for demonstration purposes")
	void skippedTest() {
		// not executed
	}

	@Test
	void abortedTest() {
		assumeTrue("abc".contains("Z"));
		fail("test should have been aborted");
	}

	@AfterEach
	void tearDown() {
	}


	@AfterAll
	static void tearDownAll() {
	}
	
	; // Empty
}
