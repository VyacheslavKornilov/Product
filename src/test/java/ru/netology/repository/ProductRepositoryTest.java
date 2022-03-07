package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Product;
import ru.netology.manager.ProductManager;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);

    private Product product1 = new Product(1, "Евгений Онегин", 150);
    private Product product2 = new Product(2, "Война и мир", 200);
    private Product product3 = new Product(3, "Идиот", 130);
    private Product product4 = new Product(4, "Отцы и дети", 170);
    private Product product5 = new Product(5, "Мертвые души", 250);

    @BeforeEach
    public void addProd() {
        manager.add(product1);
        manager.add(product2);
        manager.add(product3);
        manager.add(product4);
        manager.add(product5);

    }

    @Test
    void searchByName() {

        Product[] expected = new Product[]{product3};
        Product[] actual = manager.searchBy("Идиот");
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchAll() {

        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("null");
        assertArrayEquals(expected, actual);

    }


}