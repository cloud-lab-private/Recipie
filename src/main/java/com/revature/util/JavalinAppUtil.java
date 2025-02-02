package com.revature.util;
import com.revature.controller.RecipeController;

import io.javalin.Javalin;

import com.revature.controller.AuthenticationController;
import com.revature.controller.IngredientController;


/**
 * The JavalinAppUtil class is responsible for setting up and configuring 
 * the Javalin application instance. This utility class encapsulates the 
 * necessary controllers for handling different aspects of the application, 
 * such as recipes, authentication, and ingredients. It provides a method 
 * to create and configure the Javalin app instance, including defining 
 * the routes for each controller and applying any necessary middleware, 
 * such as admin middleware.
 */

public class JavalinAppUtil {

    /**
     * The RecipeController for handling recipe-related routes.
     */

    private RecipeController recipeController;

    /**
     * The AuthenticationController for handling authentication-related routes.
     */

    private AuthenticationController authenticationController;

    /**
     * The IngredientController for handling ingredient-related routes.
     */

    private IngredientController ingredientController;

    /**
     * An AdminMiddlware instance to ensure routes are protected.
     */
    private AdminMiddleware	adminMiddleware;

    /**
     * Constructs a JavalinAppUtil with the specified controllers.
     *
     * @param recipeController the controller for handling recipe operations
     * @param authController the controller for handling authentication operations
     * @param ingredientController the controller for handling ingredient operations
     */

    public JavalinAppUtil(RecipeController recipeController, AuthenticationController authController, IngredientController ingredientController, AdminMiddleware adminMiddleware) {
        this.recipeController = recipeController;
        this.authenticationController = authController;
        this.ingredientController = ingredientController;
        this.adminMiddleware = adminMiddleware;
    }

    /**
     * Creates a Javalin instance, configures the routes for all controllers, 
     * and applies any necessary middleware, including admin middleware.
     *
     * @return the configured Javalin instance
     */
	
    public Javalin getApp() {
        Javalin app = Javalin.create();

        // Configure routes for each controller
        recipeController.configureRoutes(app);
        authenticationController.configureRoutes(app);
        ingredientController.configureRoutes(app);

        app.before("/recipe", adminMiddleware);

        return app;
    }
}
