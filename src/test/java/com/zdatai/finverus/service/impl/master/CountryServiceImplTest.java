package com.zdatai.finverus.service.impl.master;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CountryServiceImplTest {

    /*@Mock
    private CountryDAO countryDAO;

    @InjectMocks
    private CountryServiceImpl countryService;

    private Country country;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        country = new Country("CC1", "Country1");
    }

    @Test
    void testGetAllCountries() {
        Country country1 = new Country("CC1", "Country1");
        Country country2 = new Country("CC2", "Country2");
        when(countryDAO.findAll()).thenReturn(Arrays.asList(country1, country2));

        var result = countryService.getAllCountries();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(countryDAO, times(1)).findAll();
    }

    //@Test
    void testGetCountryById() {

        when(countryDAO.findById(1L)).thenReturn(Optional.of(country));
        var result = countryService.getCountryById(1L);

        assertTrue(result.isPresent());
        assertEquals("Country1", result.get().getName());
        verify(countryDAO, times(1)).findById(1L);
    }

    @Test
    void testGetCountryByIdNotFound() {

        when(countryDAO.findById(1L)).thenReturn(Optional.empty());

        var result = countryService.getCountryById(1L);
        assertFalse(result.isPresent());
        verify(countryDAO, times(1)).findById(1L);
    }

   // @Test
    void testCreateCountry() {

        when(countryDAO.save(country)).thenReturn(country);
        var result = countryService.createCountry(country);

        assertNotNull(result);
        assertEquals("Country1", result.getName());
        verify(countryDAO, times(1)).save(country);
    }

   // @Test
    void testUpdateCountry() {

        Country updatedCountry = new Country("CC1", "UpdatedCountry");
        when(countryDAO.findById(1L)).thenReturn(Optional.of(country));
        when(countryDAO.save(any(Country.class))).thenReturn(updatedCountry);

        var result = countryService.updateCountry(1L, updatedCountry);

        assertNotNull(result);
        assertEquals("UpdatedCountry", result.getName());
        verify(countryDAO, times(1)).findById(1L);
        verify(countryDAO, times(1)).save(any(Country.class));
    }

    @Test
    void testUpdateCountryNotFound() {

        Country updatedCountry = new Country("CC1", "UpdatedCountry");
        when(countryDAO.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> countryService.updateCountry(1L, updatedCountry));
        verify(countryDAO, times(1)).findById(1L);
        verify(countryDAO, never()).save(any(Country.class));
    }

    @Test
    void testDeleteCountry() {

        doNothing().when(countryDAO).deleteById(1L);
        countryService.deleteCountry(1L);
        verify(countryDAO, times(1)).deleteById(1L);
    }*/
}

