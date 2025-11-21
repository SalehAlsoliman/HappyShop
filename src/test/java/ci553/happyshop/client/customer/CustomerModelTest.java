package ci553.happyshop.client.customer;

import ci553.happyshop.catalogue.Product;
import org.junit.jupiter.api.Test;

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
}