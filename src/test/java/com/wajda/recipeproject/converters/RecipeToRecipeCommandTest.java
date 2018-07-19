package com.wajda.recipeproject.converters;

import com.wajda.recipeproject.commands.RecipeCommand;
import com.wajda.recipeproject.model.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {

    private final static Long ID = 1L;
    private final static Integer PREP_TIME = Integer.valueOf("3");
    private final static String DESCRIPTION = "description";
    private final static Difficulty DIFFICULTY = Difficulty.EASY;
    private final static String DIRECTIONS = "directions";
    private final static Integer COOK_TIME = Integer.valueOf("2");;
    private final static Integer SERVINGS = Integer.valueOf("4");;
    private final static String SOURCE = "www";
    private final static String URL = "http//www.org";
    private final static Long CAT_ID = 11L;
    private final static Long CAT_ID2 = 12L;
    private final static Long ING_ID = 33L;
    private final static Long ING_ID2 = 34L;
    private final static Long NOTES_ID = 444L;

    RecipeToRecipeCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeToRecipeCommand(new CategoryToCategoryCommand()
                                            , new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand())
                                            , new NotesToNotesCommand());
    }

    @Test
    public void nullObjectTest() {
        assertNull(converter.convert(null));
    }

    @Test
    public void notNullObjectTest() {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    public void convert() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(ID);
        recipe.setDescription(DESCRIPTION);
        recipe.setPrepTime(PREP_TIME);
        recipe.setCookTime(COOK_TIME);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setDirections(DIRECTIONS);
        recipe.setUrl(URL);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);

        Category category = new Category();
        Category category2 = new Category();
        category.setId(CAT_ID);
        category2.setId(CAT_ID2);
        recipe.getCategories().add(category);
        recipe.getCategories().add(category2);
        Ingredient ingredient = new Ingredient();
        Ingredient ingredient2 = new Ingredient();
        ingredient.setId(ING_ID);
        ingredient2.setId(ING_ID2);
        recipe.getIngredients().add(ingredient);
        recipe.getIngredients().add(ingredient2);
        Notes notes = new Notes();
        notes.setId(NOTES_ID);
        recipe.setNotes(notes);

        //when
        RecipeCommand recipeCommand = converter.convert(recipe);

        //then
        assertNotNull(recipeCommand);
        assertNotNull(recipeCommand.getCategories());
        assertNotNull(recipeCommand.getIngredients());
        assertNotNull(recipeCommand.getNotes());
        assertEquals(ID, recipeCommand.getId());
        assertEquals(DESCRIPTION, recipeCommand.getDescription());
        assertEquals(PREP_TIME, recipeCommand.getPrepTime());
        assertEquals(COOK_TIME, recipeCommand.getCookTime());
        assertEquals(DIFFICULTY, recipeCommand.getDifficulty());
        assertEquals(DIRECTIONS, recipeCommand.getDirections());
        assertEquals(URL, recipeCommand.getUrl());
        assertEquals(SERVINGS, recipeCommand.getServings());
        assertEquals(SOURCE, recipeCommand.getSource());
        assertEquals(2, recipeCommand.getCategories().size());
        assertEquals(2, recipeCommand.getIngredients().size());
    }
}