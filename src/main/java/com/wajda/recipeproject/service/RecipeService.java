package com.wajda.recipeproject.service;

import com.wajda.recipeproject.commands.RecipeCommand;
import com.wajda.recipeproject.model.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

}
