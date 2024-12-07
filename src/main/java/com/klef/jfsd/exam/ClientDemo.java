package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ClientDemo {
    public static void main(String[] args) {
        // Create a SessionFactory
        SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml") // Ensure hibernate.cfg.xml is properly configured
            .addAnnotatedClass(Vehicle.class) // Add annotated classes
            .addAnnotatedClass(Car.class)
            .addAnnotatedClass(Truck.class)
            .buildSessionFactory();

        // Obtain a Session
        Session session = factory.getCurrentSession();

        try {
            // Create instances of Vehicle, Car, and Truck
            Vehicle vehicle = new Vehicle();
            vehicle.setName("Generic Vehicle");
            vehicle.setType("Vehicle");
            vehicle.setMaxSpeed(150);
            vehicle.setColor("Blue");

            Car car = new Car();
            car.setName("Honda Civic");
            car.setType("Car");
            car.setMaxSpeed(180);
            car.setColor("Red");
            car.setNumberOfDoors(4);

            Truck truck = new Truck();
            truck.setName("Ford F-150");
            truck.setType("Truck");
            truck.setMaxSpeed(120);
            truck.setColor("Black");
            truck.setLoadCapacity(5000);

            // Begin a transaction
            session.beginTransaction();

            // Save the objects into the database
            session.save(vehicle);
            session.save(car);
            session.save(truck);

            // Commit the transaction
            session.getTransaction().commit();

            System.out.println("Records saved successfully!");
        } finally {
            // Ensure the factory is closed
            factory.close();
        }
    }
}
