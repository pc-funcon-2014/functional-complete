package com.funcon.domain;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("UnusedDeclaration")
public class Warehouse {

    public static final SearchCriteria<Item> IN_STOCK = new SearchCriteria<Item>() {
        @Override
        public boolean test(Item item) {
            return item.isInStock();
        }
    };
    final Set<Item> items;

    public Warehouse() {
        items = new HashSet<>();
    }

    public Warehouse add(Product product, int quantity) {
        items.add(new Item(product, quantity));
        return this;
    }

    public void products(final SearchCriteria<Product> criteria) {
        for (Item item : items) {
            final Product product = item.product;
            if (criteria.test(product)) {
                System.out.println(product.name);
            }
        }
    }

    public void products(final String name) {
        products(matching(name));
    }

    private SearchCriteria<Product> matching(final String name) {
        return product -> product.name.equals(name);
    }

    public void inventory() {
        inventory(IN_STOCK);
    }

    public void inventory(final Category category) {
        inventory(ofCategory(category));
    }

    private SearchCriteria<Item> ofCategory(final Category category) {
        return item -> item.isInStock() && item.isOf(category);
    }

    public void inventory(SearchCriteria<Item> criteria) {
        for (Item item : items) {
            if (criteria.test(item)) {
                System.out.println(item.name());
            }
        }
    }

}