package com.wajda.recipeproject.converters;

import com.wajda.recipeproject.commands.CategoryCommand;
import com.wajda.recipeproject.model.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category source) {
        if (source == null) {
            return null;
        }

        final com.wajda.recipeproject.commands.CategoryCommand category = new com.wajda.recipeproject.commands.CategoryCommand();
        category.setId(source.getId());
        category.setDescription(source.getDescription());
        return category;
    }
}
