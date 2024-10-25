package com.revature.util;

/**
 * The AdminMiddleware class is responsible for enforcing access control 
 * within the application by protecting specific routes from non-admin users. 
 * This class utilizes a list of protected methods to determine which HTTP 
 * methods require admin access and leverages the ChefService to validate 
 * user permissions. The middleware intercepts requests and ensures that 
 * only users with admin privileges can access protected resources.
 */

public class AdminMiddleware {

    /**
     * An array of protected HTTP methods that require admin access.
     */

    private String[] protectedMethods;

    /**
     * The ChefService instance used for handling chef-related operations 
     * and validation.
     */

    private ChefService chefService;

    /**
     * Constructs an AdminMiddleware instance with the specified ChefService 
     * and an array of protected methods.
     *
     * @param chefService the ChefService instance for handling operations
     * @param protectedMethods the array of protected HTTP methods
     */

    public AdminMiddleware(ChefService chefService, String... protectedMethods) {
        this.chefService = chefService;
        this.protectedMethods = protectedMethods;
    }

    /**
     * Handles the incoming request by checking if the method is protected 
     * and whether the user has admin privileges.
     *
     * @param ctx the Javalin context representing the HTTP request and response
     */

    public void handle(Context ctx) {
        if (isProtectedMethod(ctx.method()) && !isAdmin(ctx)) {
            ctx.status(403).result("Access Denied: Admins only");
        }
    }

    /**
     * Checks if the specified HTTP method is in the list of protected methods.
     *
     * @param method the HTTP method to check
     * @return true if the method is protected; false otherwise
     */

    public boolean isProtectedMethod(String method) {
        for (String protectedMethod : protectedMethods) {
            if (protectedMethod.equalsIgnoreCase(method)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines whether the current user has admin privileges.
     *
     * @param ctx the Javalin context representing the HTTP request and response
     * @return true if the user is an admin; false otherwise
     */
    
    public boolean isAdmin(Context ctx) {
        // Logic to determine if the user is an admin
        // This may involve checking the session or user roles
        return false; // Placeholder logic
    }
}

