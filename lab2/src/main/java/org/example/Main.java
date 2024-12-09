package org.example;

/**
 * Головний клас для демонстрації генерації запитів
 */
public class Main {
    /**
     * Основний метод для киконання. Демонструє: генерацію INSERT, SELECT, UPDATE, DELETE запитів
     * Демонструє час виконання запитів при використанні рефлексії і без
     *
     * @param args аргументи командного рядка
     */
    public static void main(String[] args) throws IllegalAccessException {
        Bike bike = new Bike(1, "BMW", 45, 210, 450);
        Car car = new Car(1, "Mercedes", 350, 1800, 250);
        Plane plane = new Plane(1, "Boeing", "passenger", 3500);

        System.out.println(SQLGenerator.generateInsertQuery(bike));
        System.out.println(SQLGenerator.generateSelectQuery(Bike.class));
        System.out.println(SQLGenerator.generateDeleteQuery(car));
        System.out.println(SQLGenerator.generateUpdateQuery(plane));

        System.out.println(BikeSQLGenerator.generateInsertQuery(bike));
        System.out.println(BikeSQLGenerator.generateSelectQuery());
        System.out.println(BikeSQLGenerator.generateDeleteQuery(bike));
        System.out.println(BikeSQLGenerator.generateUpdateQuery(bike));

        long startReflection = System.currentTimeMillis();
        for (int i = 0; i < 10000; i ++) {
            SQLGenerator.generateInsertQuery(bike);
            SQLGenerator.generateSelectQuery(Bike.class);
            SQLGenerator.generateDeleteQuery(bike);
            SQLGenerator.generateUpdateQuery(bike);
        }
        long finishReflection = System.currentTimeMillis();

        long startNoReflection = System.currentTimeMillis();
        for (int i = 0; i < 10000; i ++) {
            BikeSQLGenerator.generateInsertQuery(bike);
            BikeSQLGenerator.generateSelectQuery();
            BikeSQLGenerator.generateDeleteQuery(bike);
            BikeSQLGenerator.generateUpdateQuery(bike);
        }
        long finishNoReflection = System.currentTimeMillis();
        System.out.println("Час з рефлексією: " + (finishReflection - startReflection));
        System.out.println("Час без рефлексії: " + (finishNoReflection - startNoReflection));
    }
}

