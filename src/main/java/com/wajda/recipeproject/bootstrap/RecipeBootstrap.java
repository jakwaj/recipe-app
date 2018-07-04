package com.wajda.recipeproject.bootstrap;

import com.wajda.recipeproject.model.*;
import com.wajda.recipeproject.repository.CategoryRepository;
import com.wajda.recipeproject.repository.RecipeRepository;
import com.wajda.recipeproject.repository.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository
            , RecipeRepository recipeRepository
            , UnitOfMeasureRepository unitOfMeasureRepository
    ) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    private List<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>();

        Optional<UnitOfMeasure> teaspoonOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        Optional<UnitOfMeasure> tablespoonOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        Optional<UnitOfMeasure> cupOptional = unitOfMeasureRepository.findByDescription("Cup");
        Optional<UnitOfMeasure> pinchOptional = unitOfMeasureRepository.findByDescription("Pinch");
        Optional<UnitOfMeasure> ounceOptional = unitOfMeasureRepository.findByDescription("Ounce");
        Optional<UnitOfMeasure> eachOptional = unitOfMeasureRepository.findByDescription("Each");
        Optional<UnitOfMeasure> dashOptional = unitOfMeasureRepository.findByDescription("Dash");
        Optional<UnitOfMeasure> pintOptional = unitOfMeasureRepository.findByDescription("Pint");

        UnitOfMeasure teaspoon = teaspoonOptional.get();
        UnitOfMeasure tablespoon = tablespoonOptional.get();
        UnitOfMeasure cup = cupOptional.get();
        UnitOfMeasure pinch = pinchOptional.get();
        UnitOfMeasure ounce = ounceOptional.get();
        UnitOfMeasure each = eachOptional.get();
        UnitOfMeasure dash = dashOptional.get();
        UnitOfMeasure pint = pintOptional.get();

        List<Category> categories = new ArrayList<>();

        Optional<Category> americanOptional = categoryRepository.findByDescription("American");
        Optional<Category> italianOptional = categoryRepository.findByDescription("Italian");
        Optional<Category> mexicanOptional = categoryRepository.findByDescription("Mexican");
        Optional<Category> fastfoodOptional = categoryRepository.findByDescription("Fast-Food");

        Category american = americanOptional.get();
        Category italian = italianOptional.get();
        Category mexican = mexicanOptional.get();
        Category fastfood = fastfoodOptional.get();

        Recipe guacamole = new Recipe();
        guacamole.setDescription("Perfect guacamole");
        guacamole.setPrepTime(10);
        guacamole.setCookTime(0);
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl." +
        "\n" +
        "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
        "\n" +
        "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
        "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
        "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste." +
        "\n" +
        "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
        "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.");

        Notes guacamoleNote = new Notes();
        guacamoleNote.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!");
        guacamoleNote.setRecipe(guacamole);

        guacamole.setNotes(guacamoleNote);

        guacamole.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), each, guacamole));
        guacamole.addIngredient(new Ingredient("kosher salt", new BigDecimal("0.5"), teaspoon, guacamole));
        guacamole.addIngredient(new Ingredient("fresh lime juice", new BigDecimal(2), tablespoon, guacamole));
        guacamole.addIngredient(new Ingredient("minced red onion", new BigDecimal(2), tablespoon, guacamole));
        guacamole.addIngredient(new Ingredient("serano chiles", new BigDecimal(2), each, guacamole));
        guacamole.addIngredient(new Ingredient("cilantro", new BigDecimal(2), tablespoon, guacamole));
        guacamole.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(2), dash, guacamole));
        guacamole.addIngredient(new Ingredient("ripe tomato", new BigDecimal("0.5"), each, guacamole));

        guacamole.getCategories().add(american);
        guacamole.getCategories().add(mexican);

        recipes.add(guacamole);

        return recipes;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.debug("Loading bootstrap data");
        recipeRepository.saveAll(getRecipes());
    }
}
