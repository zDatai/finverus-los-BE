//package com.zdatai.finverus.api.master;
//
//import com.zdatai.finverus.repository.entity.master.Country;
//import com.zdatai.finverus.service.master.CountryService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//class CountryRestControllerTest {
//
//    @Mock
//    private CountryService countryService;
//
//    @InjectMocks
//    private CountryRestController countryRestController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testGetAllCountries() {
//        List<Country> mockCountries = Arrays.asList(
//                new Country("CC2", "Country1"),
//                new Country("CC2", "Country2")
//        );
//
//        when(countryService.getAllCountries()).thenReturn(mockCountries);
//
////        ResponseEntity<List<Country>> response = countryRestController.getAllCountries();
//
//        assertEquals(200, response.getStatusCode().value());
//        assertEquals(mockCountries, response.getBody());
//        verify(countryService, times(1)).getAllCountries();
//    }
//
//    @Test
//    void testGetCountryById_Found() {
//        Country mockCountry = new Country("CC1", "Country1");
//        when(countryService.getCountryById(1L)).thenReturn(Optional.of(mockCountry));
//
//        ResponseEntity<Country> response = countryRestController.getCountryById(1L);
//
//        assertEquals(200, response.getStatusCode().value());
//        assertEquals(mockCountry, response.getBody());
//        verify(countryService, times(1)).getCountryById(1L);
//    }
//
//    @Test
//    void testGetCountryById_NotFound() {
//        when(countryService.getCountryById(1L)).thenReturn(Optional.empty());
//
//        ResponseEntity<Country> response = countryRestController.getCountryById(1L);
//
//        assertEquals(404, response.getStatusCode().value());
//        verify(countryService, times(1)).getCountryById(1L);
//    }
//
//    @Test
//    void testCreateCountry() {
//        Country mockCountry = new Country(null, "NewCountry");
//        Country savedCountry = new Country("CC1", "NewCountry");
//
//        when(countryService.createCountry(mockCountry)).thenReturn(savedCountry);
//
//        ResponseEntity<Country> response = countryRestController.createCountry(mockCountry);
//
//        assertEquals(200, response.getStatusCode().value());
//        assertEquals(savedCountry, response.getBody());
//        verify(countryService, times(1)).createCountry(mockCountry);
//    }
//
//    @Test
//    void testUpdateCountry() {
//        Country mockCountry = new Country("CC1", "UpdatedCountry");
//        when(countryService.updateCountry(1L, mockCountry)).thenReturn(mockCountry);
//
//        ResponseEntity<Country> response = countryRestController.updateCountry(1L, mockCountry);
//
//        assertEquals(200, response.getStatusCode().value());
//        assertEquals(mockCountry, response.getBody());
//        verify(countryService, times(1)).updateCountry(1L, mockCountry);
//    }
//
//    @Test
//    void testDeleteCountry() {
//        doNothing().when(countryService).deleteCountry(1L);
//
//        ResponseEntity<Void> response = countryRestController.deleteCountry(1L);
//
//        assertEquals(204, response.getStatusCode().value());
//        verify(countryService, times(1)).deleteCountry(1L);
//    }
//}
