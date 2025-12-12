package ru.netology;

import org.junit.jupiter.api.Test;
import ru.netology.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ShopRepositoryTest {

    @Test
    void shouldRemoveExistingProduct() {
        // Arrange
        ShopRepository repository = new ShopRepository();
        Product product1 = new Product(1, "Product 1", 100);
        Product product2 = new Product(2, "Product 2", 200);
        Product product3 = new Product(3, "Product 3", 300);

        repository.add(product1);
        repository.add(product2);
        repository.add(product3);

        // Act
        repository.removeById(2);

        // Assert
        Product[] products = repository.findAll();
        assertEquals(2, products.length);
        assertArrayEquals(new Product[]{product1, product3}, products);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenRemovingNonExistingProduct() {
        // Arrange
        ShopRepository repository = new ShopRepository();
        Product product1 = new Product(1, "Product 1", 100);
        Product product2 = new Product(2, "Product 2", 200);

        repository.add(product1);
        repository.add(product2);

        // Act & Assert
        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> repository.removeById(3)
        );

        assertEquals("Element with id: 3 not found", exception.getMessage());
    }

    @Test
    void shouldFindProductById() {
        // Arrange
        ShopRepository repository = new ShopRepository();
        Product product1 = new Product(1, "Product 1", 100);
        Product product2 = new Product(2, "Product 2", 200);

        repository.add(product1);
        repository.add(product2);

        // Act
        Product foundProduct = repository.findById(2);

        // Assert
        assertNotNull(foundProduct);
        assertEquals(product2, foundProduct);
    }

    @Test
    void shouldReturnNullWhenProductNotFound() {
        // Arrange
        ShopRepository repository = new ShopRepository();
        Product product1 = new Product(1, "Product 1", 100);

        repository.add(product1);

        // Act
        Product foundProduct = repository.findById(999);

        // Assert
        assertNull(foundProduct);
    }

    @Test
    void shouldAddProduct() {
        // Arrange
        ShopRepository repository = new ShopRepository();
        Product product = new Product(1, "Product 1", 100);

        // Act
        repository.add(product);

        // Assert
        Product[] products = repository.findAll();
        assertEquals(1, products.length);
        assertEquals(product, products[0]);
    }
}