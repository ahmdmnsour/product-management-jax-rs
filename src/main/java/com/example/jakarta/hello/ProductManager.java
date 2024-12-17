package com.example.jakarta.hello;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProductManager {
    private List<Product> products = new ArrayList<>();

    public Optional<Product> getProductById(int id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst();
    }

    public List<Product> searchProducts(String nameQuery) {
        return products.stream()
                .filter(product -> product.getName().contains(nameQuery))
                .toList();
    }

    public Optional<Product> getProductByName(String name) {
        return products.stream()
                .filter(product -> Objects.equals(product.getName(), name))
                .findFirst();
    }

    public int addProduct(Product product) {
        Optional<Product> existingProduct = getProductByName(product.getName());
        if (existingProduct.isPresent()) {
            updateProduct(existingProduct.get().getId(), product);
            return existingProduct.get().getId();
        }

        int productId = products.size() + 1;
        product.setId(productId);

        products.add(product);

        return productId;
    }

    public Optional<Product> updateProduct(int id, Product newProduct) {
        Optional<Product> optionalProduct = getProductById(id);
        if (optionalProduct.isPresent()) {
            optionalProduct.get().setName(newProduct.getName());
            optionalProduct.get().setPrice(newProduct.getPrice());
        }
        return optionalProduct;
    }

    public boolean deleteProduct(int id) {
        return products.removeIf(product -> product.getId() == id);
    }

    public List<Product> getAllProducts() {
        return products;
    }
}
