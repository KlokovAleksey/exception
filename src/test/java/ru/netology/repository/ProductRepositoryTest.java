package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();
    private Book coreJava = new Book();
    private ProductManager manager = new ProductManager(repository);
    private Product product1 = new Book(1, "первая", 100, "Пушкин", 10, 2001);
    private Product product2 = new Book(2, "пятый", 200, "Лермонтов", 20, 2002);
    private Product product3 = new Book(3, "третья", 300, "Гоголь", 30, 2003);
    private Product product4 = new Smartphone(4, "четвёртый", 400, "нокия");
    private Product product5 = new Smartphone(5, "пятый", 500, "самсунг");
    private Product product6 = new Smartphone(6, "шестой", 600, "сони");


    @Test
    public void shouldSaveOneItem() {
        repository.save(coreJava);

        Product[] expected = new Product[]{coreJava};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }
    @BeforeEach
    public void addProduct(){
        manager.add(product1);
        manager.add(product2);
        manager.add(product3);
        manager.add(product4);
        manager.add(product5);
        manager.add(product6);
    }

    @Test
    public void shouldRemoveProductById() {
        int deleteId = 3;
        repository.removeById(deleteId);
        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{product1,product2,product4,product5,product6};
        assertArrayEquals(actual,expected);
    }

    @Test
    public void shouldRemoveProductByNotExistId(){
        int idRemove = 8;
        assertThrows(NotFoundException.class,()-> repository.removeById(idRemove));
    }
}
