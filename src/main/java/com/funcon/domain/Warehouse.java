package com.funcon.domain;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

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

    public static int numberOfItems(Set<Warehouse> warehouses) {
        return warehouses.stream()
                .flatMap(Warehouse::items)
                .mapToInt(Item::quantity)
                .sum();
    }

    public static Optional<Item> findItemWithLeastQuantity(Set<Warehouse> warehouses) {
        return warehouses.stream()
                .flatMap(Warehouse::items)
                .min(Comparator.<Item>naturalOrder());
    }

    private Stream<Item> items() {
        return items.stream();
    }
}