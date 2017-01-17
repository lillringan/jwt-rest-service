package se.plushogskolan.restcaseservice.service;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import se.plushogskolan.restcaseservice.model.Admin;

public class AdminServiceTest {

	private static AdminService service;
	private static final String username = "Username123";
	private static final String password = "pAssW0rD";
	private static Admin me;
	
	@BeforeClass
	public static void setUp() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		service = context.getBean(AdminService.class);
		me = service.save(username, password);
	}
	
	@Test
	public void testLogin() {
		String token = service.login(username, password);
		assertEquals(me.getToken(), token);
	}
}
