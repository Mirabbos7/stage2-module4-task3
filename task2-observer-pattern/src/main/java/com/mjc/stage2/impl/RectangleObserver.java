package com.mjc.stage2.impl;

import com.mjc.stage2.Observer;
import com.mjc.stage2.entity.Rectangle;
import com.mjc.stage2.entity.RectangleValues;
import com.mjc.stage2.event.RectangleEvent;
import com.mjc.stage2.repository.RectangleRepository;
import com.mjc.stage2.warehouse.RectangleWarehouse;

public class RectangleObserver implements Observer {

    @Override
    public void handleEvent(RectangleEvent event) {
        Rectangle rectangle = event.getSource();
        double sideA = rectangle.getSideA();
        double sideB = rectangle.getSideB();
        double square = sideA * sideB;
        double perimeter = 2 * (sideA + sideB);

        RectangleValues rectangleValues = new RectangleValues(square, perimeter);
        RectangleWarehouse.getInstance().put(rectangle.getId(), rectangleValues);
    }

    public static void main(String[] args) {
        RectangleRepository repository = RectangleRepository.getInstance();
        RectangleWarehouse warehouse = RectangleWarehouse.getInstance();

        Rectangle rectangle = new Rectangle(1, 2.0, 3.0);
        RectangleObserver observer = new RectangleObserver();

        rectangle.addObserver(observer);
        repository.add(rectangle);

        rectangle.setSideA(4.0); // This should trigger the observer and update the warehouse
        rectangle.setSideB(5.0); // This should also trigger the observer and update the warehouse

        RectangleValues values = warehouse.get(rectangle.getId());
        System.out.println("Square: " + values.getSquare());
        System.out.println("Perimeter: " + values.getPerimeter());
    }
}
