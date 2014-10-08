package com.funcon.domain;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("UnusedDeclaration")
public class Warehouse {

    final Set<Item> items;

    public Warehouse() {
        items = new HashSet<>();
    }

    public Warehouse add(Product product, int quantity) {
        items.add(new Item(product, quantity));
        return this;
    }

    public void products(final String name) {
        items.stream()
                .map(Item::product)
                .filter(product -> product.name.equals(name))
                .forEach(System.out::println);
    }

    public void inventory() {
        items.stream().filter(Item::isInStock)
                .map(Item::toString)
                .forEach(System.out::println);
    }

    public void inventory(final Category category) {
        items.stream()
                .filter(item -> item.isInStock() && item.isOf(category))
                .map(Item::name)
                .forEach(System.out::println);
    }

    public int numberOfItems() {
        return items.stream()
                .mapToInt(Item::quantity)
                .sum();
    }

}