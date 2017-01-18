package se.plushogskolan.restcaseservice.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.springframework.beans.factory.annotation.Autowired;

import se.plushogskolan.restcaseservice.exception.UnathorizedException;
import se.plushogskolan.restcaseservice.service.AdminService;

@Provider
public final class RequestFilter implements ContainerRequestFilter {

	public static final String AUTH_TOKEN = "auth";

	@Autowired
	AdminService adminService;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		String authHeader = requestContext.getHeaderString("Authorization");

		if ("/login".equals(requestContext.getUriInfo().getRequestUri().getRawPath())) {
		} else if (adminService.authenticateToken(requestContext.getHeaderString("Authorization"))) {
		} else if (authHeader == null || !authHeader.equalsIgnoreCase(AUTH_TOKEN)) {
			throw new UnathorizedException("Unathorized");
		}

	}

}
