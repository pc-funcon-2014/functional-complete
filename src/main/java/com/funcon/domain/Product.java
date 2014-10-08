package com.funcon.domain;

import static com.funcon.domain.Category.BOOKS;
import static com.funcon.domain.Category.COMPUTERS;
import static com.funcon.domain.Category.TOYS;

public class Product {

    public final Category category;
    public final String name;
    public final int price;

    private Product(String name,
                    Category category,
                    int price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product: " + "category=" + category
                + ", name='" + name + "', price=" + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product item = (Product) o;
        return name.equals(item.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public static class Builder {
        private final Category category;
        private final String name;

        public Builder(String name, Category category) {
            this.name = name;
            this.category = category;
        }

        public static Builder computerNamed(final String name) {
            return new Builder(name, COMPUTERS);
        }

        public static Builder toyNamed(final String name) {
            return new Builder(name, TOYS);
        }

        public static Builder bookNamed(final String name) {
            return new Builder(name, BOOKS);
        }

        public Product pricedAt(int price) {
            return new Product(name, category, price);
        }
    }
}