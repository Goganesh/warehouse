package com.goganesh.warehouse.repository.dictionary;

import com.goganesh.warehouse.domain.Country;
import com.goganesh.warehouse.repository.CountryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Repository для работы с справочников - Страны")
@DataJpaTest
class CountryRepositoryTest {

    @Autowired
    private CountryRepository repository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("сохранять новую страну")
    @Test
    void shouldSaveNewCountry() {
        Country expectedCountry = new Country();
        expectedCountry.setName("USA");
        Country actualCountry = repository.save(expectedCountry);

        assertEquals(expectedCountry.getName(), actualCountry.getName());
    }

    @DisplayName("возвращать все страны")
    @Test
    void shouldReturnAllCounties() {
        long expectedSize = 3;
        long actualSize = repository.findAll().size();

        assertEquals(expectedSize, actualSize);
    }

}