//package com.zdatai.finverus.api.master;
//
//import com.zdatai.finverus.repository.entity.master.Citizenship;
//import com.zdatai.finverus.repository.master.CitizenshipDAO;
//import jakarta.persistence.OptimisticLockException;
//import jakarta.transaction.Transactional;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@SpringBootTest
//public class OptimisticLockingTest {
//    @Autowired
//    private CitizenshipDAO productDAO;
//
//    private Citizenship savedProduct;
//
//    @BeforeEach
//    public void setUp() {
//        // Create and save a product for testing
//        Citizenship product = new Citizenship();
//        product.setCitizenshipName("Test Product");
//        savedProduct = productDAO.save(product);
//    }
//
//    @Test
//    @Transactional
//    public void testOptimisticLocking() {
//        // Fetch the product twice to simulate concurrent updates
//        Citizenship product1 = productDAO.findById(savedProduct.getCitizenshipId()).orElseThrow();
//        Citizenship product2 = productDAO.findById(savedProduct.getCitizenshipId()).orElseThrow();
//
//        // Update the product name in two separate "transactions"
//        product1.setCitizenshipName("Updated by Transaction 1");
//        productDAO.saveAndFlush(product1); // This should update the version
//
//        // Now, product2 still has the old version
//        product2.setCitizenshipName("Updated by Transaction 2");
//
//        // Saving product2 should now throw OptimisticLockException
//        assertThrows(OptimisticLockException.class, () -> productDAO.saveAndFlush(product2));
//
//    }
//
//}
