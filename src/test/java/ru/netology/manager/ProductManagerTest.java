package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);

    private Product product1 = new Product(1, "Евгений Онегин", 150);
    private Product product2 = new Product(2, "Война и мир", 200);
    private Product product3 = new Product(3, "Идиот", 130);
    private Product product4 = new Product(4, "Отцы и дети", 170);
    private Product product5 = new Product(5, "Мертвые души", 250);

    private Product product6 = new Smartphone(6, "Galaxy", 60000, "Samsung");
    private Product product7 = new Smartphone(7, "Iphone", 80000, "Apple");
    private Product product8 = new Smartphone(8, "Nova5T", 25000, "Huawey");
    private Product product9 = new Smartphone(9, "Black Shark", 50000, "Xiaomi");
    private Product product10 = new Smartphone(10, "20", 20000, "Honor");

    @BeforeEach
    public void addProd() {
        manager.add(product1);
        manager.add(product2);
        manager.add(product3);
        manager.add(product4);
        manager.add(product5);
        manager.add(product6);
        manager.add(product7);
        manager.add(product8);
        manager.add(product9);
        manager.add(product10);
    }

    @Test
    void saveProduct() {

        Product[] expected = {product1, product2, product3, product4, product5, product6, product7, product8, product9, product10};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchBy() {
        Product[] expected = {product1};
        Product[] actual = manager.searchBy("Евгений Онегин");
        assertArrayEquals(expected, actual);
    }
    @Test
    void searchByBrand(){
        Product[]actual = manager.searchBy("Black Shark");
        Product[]expected = {new Smartphone(9,"Black Shark", 50000,"Xiaomi")};
        assertArrayEquals(expected,actual);
    }
    @Test
    void searchBySmartphoneName(){
        Product[]actual = manager.searchBy("Galaxy");
        Product[]expected = {new Smartphone(6, "Galaxy", 60000,"Samsung")};
        assertArrayEquals(expected,actual);
    }

    @Test
    void findById() {
        Product expected = product2;
        Product actual = repository.findById(2);
        assertEquals(expected, actual);
    }

    @Test
    void removeById() {
        repository.removeById(3);
        Product[] expected = {product1, product2, product4, product5, product6, product7, product8, product9, product10};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }
    @Test
    void searchByTenProducts() {
        Product[] expected = {};
        Product[] actual = manager.searchBy("1");
        assertArrayEquals(expected, actual);
    }
    @Test
    void searchIncorrectBrand() {
        Product[] actual = manager.searchBy("Lenovo");
        Product[] expected = {};
        assertArrayEquals(expected, actual);
    }
    @Test
    void searchIncorrectBook() {
        Product[] actual = manager.searchBy("Мцыри");
        Product[] expected = {};
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByNotCorrectProduct() {
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("Ни один товар не подходит");
        assertArrayEquals(expected, actual);
    }
}