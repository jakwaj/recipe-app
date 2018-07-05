package com.wajda.recipeproject.repository;

import com.wajda.recipeproject.model.UnitOfMeasure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Test
    public void findByDescription() {
        String description = "Teaspoon";
        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription(description);

        assertEquals(description, uomOptional.get().getDescription());
    }
}