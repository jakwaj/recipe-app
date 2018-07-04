package com.wajda.recipeproject.controller;

import com.wajda.recipeproject.model.Recipe;
import com.wajda.recipeproject.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping({"/", "/index"})
    public String getIndexPage(Model model) {

        log.debug("Getting index page");

        Set<Recipe> recipes = recipeService.getRecipes();

        model.addAttribute("recipes", recipes);

        return "index";
    }

}
