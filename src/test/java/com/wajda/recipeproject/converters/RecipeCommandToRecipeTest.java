package com.wajda.recipeproject.converters;

import com.wajda.recipeproject.commands.CategoryCommand;
import com.wajda.recipeproject.commands.IngredientCommand;
import com.wajda.recipeproject.commands.NotesCommand;
import com.wajda.recipeproject.commands.RecipeCommand;
import com.wajda.recipeproject.model.Difficulty;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class RecipeCommandToRecipeTest {

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

    RecipeCommandToRecipe converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(new NotesCommandToNotes()
                                            , new CategoryCommandToCategory()
                                            , new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()));
    }

    @Test
    public void notNullObjectTest() {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    public void nullObjectTest() {
        assertNull(converter.convert(null));
    }

    @Test
    public void convert() {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(ID);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setDifficulty(DIFFICULTY);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setUrl(URL);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);

        CategoryCommand categoryCommand = new CategoryCommand();
        CategoryCommand categoryCommand2 = new CategoryCommand();
        categoryCommand.setId(CAT_ID);
        categoryCommand2.setId(CAT_ID2);
        recipeCommand.getCategories().add(categoryCommand);
        recipeCommand.getCategories().add(categoryCommand2);
        IngredientCommand ingredientCommand = new IngredientCommand();
        IngredientCommand ingredientCommand2 = new IngredientCommand();
        ingredientCommand.setId(ING_ID);
        ingredientCommand2.setId(ING_ID2);
        recipeCommand.getIngredients().add(ingredientCommand);
        recipeCommand.getIngredients().add(ingredientCommand2);
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID);
        recipeCommand.setNotes(notesCommand);
    }
}