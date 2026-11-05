package org.main.model;

public record Transaction(double grossAmount, double vat, double totalPrice, String modelName, int subItems) {
}
