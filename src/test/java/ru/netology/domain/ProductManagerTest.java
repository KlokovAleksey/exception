package ru.netology.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ProductManagerTest {

    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);
    private Product product1 = new Book(1, "первая", 100, "Пушкин", 10, 2001);
    private Product product2 = new Book(2, "пятый", 200, "Лермонтов", 20, 2002);
    private Product product3 = new Book(3, "третья", 300, "Гоголь", 30, 2003);
    private Product product4 = new Smartphone(4, "четвёртый", 400, "нокия");
    private Product product5 = new Smartphone(5, "пятый", 500, "самсунг");
    private Product product6 = new Smartphone(6, "шестой", 600, "сони");

    @BeforeEach
    public void addManger() {
        manager.add(product1);
        manager.add(product2);
        manager.add(product3);
        manager.add(product4);
        manager.add(product5);
        manager.add(product6);
    }

    @Test
    public void shouldSearchBookByName() {
        Product[] actual = manager.searchBy("первая");
        Product[] expected = new Product[]{product1};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldSearchBookByAuthor() {
        Product[] actual = manager.searchBy("Гоголь");
        Product[] expected = new Product[]{product3};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldSearchSmartphoneByName() {
        Product[] actual = manager.searchBy("четвёртый");
        Product[] expected = new Product[]{product4};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldSearchSmartphoneByProduce() {
        Product[] actual = manager.searchBy("сони");
        Product[] expected = new Product[]{product6};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldSearchNotProduct() {
        Product[] actual = manager.searchBy("стопятьсот");
        Product[] expected = new Product[]{};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldSearchBookAndSmartphoneByName() {
        Product[] actual = manager.searchBy("пятый");
        Product[] expected = new Product[]{product2, product5};
        assertArrayEquals(actual, expected);
    }
}