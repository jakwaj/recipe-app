package com.wajda.recipeproject.converters;

import com.wajda.recipeproject.commands.CategoryCommand;
import com.wajda.recipeproject.model.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {

    private static final Long LONG_NUMBER = new Long(1L);
    private static final String DESCRIPTION = "description";

    CategoryCommandToCategory converter;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryCommandToCategory();
    }

    @Test
    public void nullObjectTest() {
        assertNull(converter.convert(null));
    }

    @Test
    public void notNullObjectTest() {
        assertNotNull(converter.convert(new com.wajda.recipeproject.commands.CategoryCommand()));
    }

    @Test
    public void convert() {
        //given
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setDescription(DESCRIPTION);
        categoryCommand.setId(LONG_NUMBER);

        //when
        Category category = converter.convert(categoryCommand);

        //then
        assertNotNull(category);
        assertEquals(LONG_NUMBER, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }
}