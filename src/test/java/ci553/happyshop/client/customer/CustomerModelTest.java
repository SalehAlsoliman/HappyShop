package ci553.happyshop.client.customer;

import ci553.happyshop.catalogue.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CustomerModelTest {
// Organized Trolley

    @Test
    void makeOrganizedTrolley() {
        CustomerModel cm = new CustomerModel();
        Product p = new Product("0001", "TV", "0001.jpg", 12.01, 100);
        cm.setTheProduct(p);
        cm.makeOrganizedTrolley();
        cm.makeOrganizedTrolley();
        cm.makeOrganizedTrolley();
        ArrayList<Product> tro = cm.getTrolley();
        assertEquals(1, tro.size());
        assertEquals(3, tro.get(0).getOrderedQuantity());
    }


    @Test
    void TrolleySortingTest() {
        CustomerModel cm = new CustomerModel();

        Product p1 = new Product("0004", "Watch", "0004.jpg", 29.99, 100);
        cm.setTheProduct(p1);
        cm.makeOrganizedTrolley();

        Product p2 = new Product("0001", "TV", "0001.jpg", 12.01, 100);
        cm.setTheProduct(p2);
        cm.makeOrganizedTrolley();

        ArrayList<Product> tro = cm.getTrolley();
        assertEquals(2, tro.size());
        assertEquals("0001", tro.get(0).getProductId());
        assertEquals("0004", tro.get(1).getProductId());
    }


    // Stock shortage
    @Test
    void testStockShortageAtCheckout() {
        CustomerModel cm = new CustomerModel();
        Product p = new Product("0003", "Toaster", "0003.jpg", 19.99, 1);
        cm.setTheProduct(p);

        p.setOrderedQuantity(2);
        p.setOrderedQuantity(2);

        try {
            cm.checkOut();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<Product> trolley = cm.getTrolley();
        assertEquals(0, trolley.size(), "Trolley should be empty");
    }

//sound click

    @Test
    void testsound() {
        CustomerModel cm = new CustomerModel();
        Product p = new Product("0001", "40 inch TV", "0001.jpg", 269.00, 10);
        cm.setTheProduct(p);
        cm.addToTrolley();
        ArrayList<Product> trolley = cm.getTrolley();
        assertEquals(1, trolley.size(), "Trolley should contain 1 item after addition");
    }

    @Test
    void testSearchByName() {
        // 1. Setup
        CustomerModel model = new CustomerModel();
        //Search for TV
        //this triggers the new logic which loads the dummy data and finds the tv
        model.searchByName("TV");
        //Verification
        Product selected = model.getSelectedProduct(); // Make sure you have a getter for 'theProduct'
        //did it find a product?
        assertNotNull(selected, "The product should not be null after a successful search");
        //is it the Right product?
        assertTrue(selected.getProductDescription().contains("TV"), "it should be 'TV'");
        //is the price correct?
        assertEquals(150.00, selected.getUnitPrice(), "The price should be 150.00");
        }
    }
