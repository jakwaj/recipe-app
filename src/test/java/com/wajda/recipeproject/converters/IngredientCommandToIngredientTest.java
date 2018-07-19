package com.wajda.recipeproject.converters;

import com.wajda.recipeproject.commands.UnitOfMeasureCommand;
import com.wajda.recipeproject.model.Ingredient;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {

    private final static Long LONG_VALUE = new Long(1L);
    private final static String DESCRIPTION = "description";
    private final static BigDecimal BIG_DECIMAL = new BigDecimal(3);
    private final static Long UOM_ID = new Long(2L);

    IngredientCommandToIngredient converter;

    @Before
    public void setUp() throws Exception {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void nullObjectTest() {
        assertNull(converter.convert(null));
    }

    @Test
    public void notNullObjectTest() {
        assertNotNull(converter.convert(new com.wajda.recipeproject.commands.IngredientCommand()));
    }

    @Test
    public void convert() {
        //given
        com.wajda.recipeproject.commands.IngredientCommand ingredientCommand = new com.wajda.recipeproject.commands.IngredientCommand();
        ingredientCommand.setId(LONG_VALUE);
        ingredientCommand.setAmount(BIG_DECIMAL);
        ingredientCommand.setDescription(DESCRIPTION);
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(UOM_ID);
        ingredientCommand.setUnitOfMeasureCommand(unitOfMeasureCommand);

        //when
        Ingredient ingredient = converter.convert(ingredientCommand);

        //then
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUom());
        assertEquals(LONG_VALUE, ingredient.getId());
        assertEquals(BIG_DECIMAL, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(UOM_ID, ingredient.getUom().getId());

    }
}