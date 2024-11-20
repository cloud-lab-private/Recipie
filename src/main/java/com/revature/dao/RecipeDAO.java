package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.revature.model.Chef;
import com.revature.model.Recipe;
import com.revature.model.RecipeIngredient;
import com.revature.util.ConnectionUtil;
import com.revature.util.Page;
import com.revature.util.PageOptions;

/**
 * Data Access Object (DAO) for managing Recipe entities in the database.
 * This class provides methods for creating, reading, updating, and deleting
 * recipes,
 * as well as searching for recipes by various criteria.
 */
public class RecipeDAO {

	/**
	 * DAO for managing Chef entities, used for retrieving chef details associated
	 * with recipes.
	 */
	private ChefDAO chefDAO;
	/**
	 * DAO for managing Ingredient entities, used for retrieving ingredient details
	 * for recipes.
	 */
	private IngredientDAO ingredientDAO;
	/**
	 * Utility class for managing database connections, providing methods to obtain
	 * database connections.
	 */
	private ConnectionUtil connectionUtil;

	/**
	 * TODO: Constructs a RecipeDAO instance with specified ChefDAO and IngredientDAO.
	 *
	 * @param chefDAO       the ChefDAO used for retrieving chef details.
	 * @param ingredientDAO the IngredientDAO used for retrieving ingredient
	 *                      details.
	 */
	public RecipeDAO(ChefDAO chefDAO, IngredientDAO ingredientDAO) {
		
	}

	/**
	 * Retrieves all recipes from the database.
	 *
	 * @return a list of all recipes, or null if an error occurs.
	 */
	public List<Recipe> getAllRecipes() {
return null;
	}

	/**
	 * Retrieves a paginated list of all recipes from the database based on the
	 * specified page options.
	 *
	 * @param pageOptions options for pagination including sorting.
	 * @return a Page object containing the paginated recipes, or null if an error
	 *         occurs.
	 */
	public Page<Recipe> getAllRecipes(PageOptions pageOptions) {
		return null;
	}

	/**
	 * Searches for recipes by a specified search term.
	 *
	 * @param term the search term to filter recipes by name.
	 * @return a list of recipes that match the search term, or null if an error
	 *         occurs.
	 */
	public List<Recipe> searchRecipesByTerm(String term) {
		return null;
	}

	/**
	 * Searches for recipes by a specified search term and returns a paginated
	 * result.
	 *
	 * @param term the search term to filter recipes by name.
	 * @param pageOptions options for pagination including sorting.
	 * @return a Page object containing the paginated recipes that match the search
	 *         term, or null if an error occurs.
	 */
	public Page<Recipe> searchRecipesByTerm(String term, PageOptions pageOptions) {
		return null;
	}

	/**
	 * Retrieves a recipe by its unique identifier.
	 *
	 * @param id the unique identifier of the recipe.
	 * @return the Recipe object if found.
	 */
	public Recipe getRecipeById(int id) {
		return null;
	}

	/**
	 * Creates a new recipe in the database and returns its generated unique
	 * identifier.
	 *
	 * @param recipe the Recipe object to be created.
	 * @return the generated unique identifier of the created recipe.
	 */
	public int createRecipe(Recipe recipe) {
		return 0;
	}

	/**
	 * Updates an existing recipe in the database.
	 *
	 * @param recipe the Recipe object containing updated information.
	 */
	public void updateRecipe(Recipe recipe) {
		

	}

	/**
	 * Deletes a recipe from the database, along with its associated ingredients.
	 *
	 * @param recipe the Recipe object to be deleted.
	 */
	public void deleteRecipe(Recipe recipe) {
		
	}

	// below are helper methods for your convenience
	
	/**
	 * Maps a single row from the ResultSet to a Recipe object.
	 * This method extracts the recipe details such as ID, name, instructions,
	 * and associated chef from the ResultSet and constructs a Recipe instance.
	 *
	 * @param set the ResultSet containing the recipe data
	 * @return a Recipe object representing the mapped row
	 * @throws SQLException if there is an error accessing the ResultSet
	 */
	private Recipe mapSingleRow(ResultSet set) throws SQLException {
		int id = set.getInt("id");
		String name = set.getString("name");
		String instructions = set.getString("instructions");
		Chef author = chefDAO.getChefById(set.getInt("chef_id"));
		return new Recipe(id, name, instructions, author);
	}

	/**
	 * Maps multiple rows from a ResultSet to a list of Recipe objects.
	 * This method iterates through the ResultSet and calls mapSingleRow
	 * for each row, adding the resulting Recipe objects to a list.
	 *
	 * @param set the ResultSet containing multiple recipe rows
	 * @return a list of Recipe objects representing the mapped rows
	 * @throws SQLException if there is an error accessing the ResultSet
	 */
	private List<Recipe> mapRows(ResultSet set) throws SQLException {
		List<Recipe> recipes = new ArrayList<>();
		while (set.next()) {
			recipes.add(mapSingleRow(set));
		}
		return recipes;
	}

	/**
	 * Pages the results from a ResultSet into a Page object for the Recipe entity.
	 * This method processes the ResultSet to retrieve recipes, then slices the list
	 * based on the provided pagination options, and returns a Page object
	 * containing
	 * the paginated results.
	 *
	 * @param set         the ResultSet containing recipe data
	 * @param pageOptions the PageOptions object containing pagination details
	 * @return a Page object containing the paginated list of Recipe objects
	 * @throws SQLException if there is an error accessing the ResultSet
	 */
	private Page<Recipe> pageResults(ResultSet set, PageOptions pageOptions) throws SQLException {
		List<Recipe> recipes = mapRows(set);
		int offset = (pageOptions.getPageNumber() - 1) * pageOptions.getPageSize();
		int limit = offset + pageOptions.getPageSize();
		List<Recipe> slicedList = sliceList(recipes, offset, limit);
		return new Page<>(pageOptions.getPageNumber(), pageOptions.getPageSize(),
				recipes.size() / pageOptions.getPageSize(), recipes.size(), slicedList);
	}

	/**
	 * Slices a list of Recipe objects from a specified start index to an end index.
	 * This method creates a sublist of the provided list, which can be used for
	 * pagination.
	 *
	 * @param list  the original list of Recipe objects
	 * @param start the starting index (inclusive) for the slice
	 * @param end   the ending index (exclusive) for the slice
	 * @return a list of Recipe objects representing the sliced portion
	 */
	private List<Recipe> sliceList(List<Recipe> list, int start, int end) {
		List<Recipe> sliced = new ArrayList<>();
		for (int i = start; i < end; i++) {
			sliced.add(list.get(i));
		}
		return sliced;
	}
}