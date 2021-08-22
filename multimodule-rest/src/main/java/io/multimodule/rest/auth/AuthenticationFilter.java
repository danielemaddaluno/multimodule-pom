package io.multimodule.rest.auth;
// XXX ripristinare
//package io.multimodule.rest.auth;
//
//import java.io.IOException;
//import java.lang.reflect.AnnotatedElement;
//import java.lang.reflect.Method;
//import java.math.BigInteger;
//import java.util.List;
//import java.util.NoSuchElementException;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import javax.annotation.Priority;
//import javax.ejb.EJB;
//import javax.ws.rs.Priorities;
//import javax.ws.rs.container.ContainerRequestContext;
//import javax.ws.rs.container.ContainerRequestFilter;
//import javax.ws.rs.container.ResourceInfo;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.SecurityContext;
//import javax.ws.rs.ext.Provider;
//
///**
// * There are 4 level of authorization:
// * <ul>
// * <li><b>PUBLIC</b>: it's ok for all request, even non authenticated
// * requests</li>
// * <li><b>USER</b>: request must be authenticated (es throught firebase)</li>
// * <li><b>EDIT</b>: request must be authenticated (es throught firebase) and
// * user should be in the user_provider list as <i>Editor</i></li>
// * <li><b>ADMIN</b>: request must be authenticated (es throught firebase) and
// * user should be in the user_provider list as <i>Administrator</i></li>
// * </ul>
// * 
// * @author madx
// *
// */
//@Secured(value = Role.PUBLIC)
//@Provider
//@Priority(Priorities.AUTHENTICATION)
//public class AuthenticationFilter implements ContainerRequestFilter {
//
//	@Context
//	private ResourceInfo resourceInfo;
//
//	@EJB
//	private AuthBean authService;
//
//	@EJB
//	private UserProviderBean userProviderBean;
//
//	@Override
//	public void filter(ContainerRequestContext requestContext) throws IOException {
//		Role minRoleAuthorized = getMinRoleAuthorized();
//		// Get the HTTP Authorization headers from the request
//		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
//
//		// Check if the HTTP Authorization header is present and formatted correctly
//		if (authorizationHeader == null || authorizationHeader.isEmpty()) {
//			if (minRoleAuthorized == Role.PUBLIC) {
//				final SecurityContext securityContext = requestContext.getSecurityContext();
//				requestContext
//						.setSecurityContext(new NightsSecurityContext(securityContext, UserProvider.buildEmpty()));
//				return;
//			} else {
//				abortWithUnauthorized(requestContext, "No JWT token");
//				return;
//			}
//		}
//
//		User user = getUserFromAuthHeader(authorizationHeader);
//
//		if (user == null || minRoleAuthorized == null) {
//			abortWithUnauthorized(requestContext, "No principal for the user");
//		} else {
//			final SecurityContext securityContext = requestContext.getSecurityContext();
//			BigInteger providerId = getAuthorizationProviderIdHeader(requestContext);
//			UserProvider authorizedPrincipal = getAuthorizedPrincipal(user, providerId, minRoleAuthorized);
//			if (authorizedPrincipal != null) {
//				requestContext.setSecurityContext(new NightsSecurityContext(securityContext, authorizedPrincipal));
//			} else {
//				abortWithUnauthorized(requestContext, "User not autorized for his roles");
//			}
//		}
//	}
//
//	private void abortWithUnauthorized(ContainerRequestContext requestContext, String unauthorizedMessage) {
//		requestContext.setProperty("auth-failed", true);
//		requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
//				.header(HttpHeaders.AUTHORIZATION, unauthorizedMessage).build());
//	}
//
//	private User getUserFromAuthHeader(String authorizationHeader) {
//		if (authorizationHeader.startsWith("Bearer ")) {
//			// Extract the token from the HTTP Authorization header
//			String java_web_token = authorizationHeader.substring("Bearer".length()).trim();
//			return authService.validateTokenJwt(java_web_token);
//		} else {
//			return authService.validateTokenFirebase(authorizationHeader);
//		}
//	}
//
//	/**
//	 * Extract which roles are admitted for the considered method
//	 * 
//	 * @return
//	 * @throws Exception
//	 */
//	private Role getMinRoleAuthorized() throws IOException {
//		// Get the resource class which matches with the requested URL
//		// Extract the roles declared by it
//		Class<?> resourceClass = resourceInfo.getResourceClass();
//		Role classRole = extractRole(resourceClass);
//
//		// Get the resource method which matches with the requested URL
//		// Extract the roles declared by it
//		Method resourceMethod = resourceInfo.getResourceMethod();
//		Role methodRole = extractRole(resourceMethod);
//
//		// Check if the user is allowed to execute the method
//		// The method annotations override the class annotations
//		if (methodRole != null) {
//			return methodRole;
//		} else if (classRole != null) {
//			return classRole;
//		} else {
//			throw new IOException("Neither the class and the method contains a Role to refer to");
//		}
//	}
//
//	/**
//	 * Extract the role from the annotated element
//	 * 
//	 * @param annotatedElement
//	 * @return
//	 */
//	private Role extractRole(AnnotatedElement annotatedElement) {
//		if (annotatedElement == null) {
//			return null;
//		} else {
//			Secured secured = annotatedElement.getAnnotation(Secured.class);
//			if (secured == null) {
//				return null;
//			} else {
//				return secured.value();
//			}
//		}
//	}
//
//	private BigInteger getAuthorizationProviderIdHeader(ContainerRequestContext requestContext) {
//		try {
//			String authorizationProviderHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION_PROVIDER);
//			return new BigInteger(authorizationProviderHeader);
//		} catch (Exception e) {
//			return null;
//		}
//	}
//
//	/**
//	 * 
//	 * @param user
//	 * @param providerId
//	 * @param minRoleAuthorized
//	 * @return null if it is not authorized
//	 */
//	private UserProvider getAuthorizedPrincipal(User user, BigInteger providerId, Role minRoleAuthorized) {
//		UserProvider userProvider = null;
//		Role authorizedRoleLevel;
//
//		// Obtain the user authorization level checking on the db on the user_provider
//		// table
//		if (providerId != null && user != null) {
//			List<UserProvider> userProviders = userProviderBean.selectUserProvidersByUser(user);
//			try {
//				Optional<UserProvider> userProviderOpt = userProviders.stream()
//						.filter(up -> up.getProvider().getId() == providerId)
//						.collect(Collectors.reducing((a, b) -> null));
//				userProvider = userProviderOpt.get();
//				authorizedRoleLevel = userProvider.getRole();
//			} catch (NoSuchElementException e) {
//				authorizedRoleLevel = Role.USER;
//			}
//		} else {
//			authorizedRoleLevel = user != null ? Role.USER : Role.PUBLIC;
//		}
//
//		// Check if authorizedLevel is ok for the minimum role admitted
//		boolean isAuthorized = minRoleAuthorized.isRoleAuthorized(authorizedRoleLevel);
//		if (!isAuthorized)
//			return null;
//
//		switch (authorizedRoleLevel) {
//		case PUBLIC:
//			return UserProvider.buildEmpty();
//		case USER:
//			return UserProvider.buildByUser(user);
//		case EDIT:
//		case ADMIN:
//			return userProvider;
//		default:
//			return null;
//		}
//	}
//
//}