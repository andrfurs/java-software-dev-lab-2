package org.example;

/**
 * Утиліта для створення SQL-запитів (INSERT, SELECT, UPDATE, DELETE) для об'єктів Bike без рефлексії
 */
public class BikeSQLGenerator {

    /**
     * Генерує INSERT запит для заданого мотоцикла
     *
     * @param bike мотоцикл, для якого потрібно згенерувати запит
     * @return рядок String, що містить згенерований INSERT запит
     */
    public static String generateInsertQuery(Bike bike) {
        return String.format(
                "INSERT INTO bikes (id, brand, power, topSpeed, engineDisplacement) VALUES (%d, '%s', %d, %d, %d);",
                bike.getId(), bike.getBrand(), bike.getPower(), bike.getTopSpeed(), bike.getEngineDisplacement()
        );
    }

    /**
     * Генерує SELECT запит для заданого мотоцикла
     *
     * @return рядок String, що містить згенерований SELECT запит
     */
    public static String generateSelectQuery() {
        return "SELECT * FROM bikes;";
    }

    /**
     * Генерує UPDATE запит для заданого мотоцикла
     *
     * @param bike мотоцикл, для якого потрібно згенерувати запит
     * @return рядок String, що містить згенерований UPDATE запит
     */
    public static String generateUpdateQuery(Bike bike) {
        return String.format(
                "UPDATE bikes SET brand = '%s', power = %d, topSpeed = %d, engineDisplacement = %d WHERE id = %d;",
                bike.getBrand(), bike.getPower(), bike.getTopSpeed(), bike.getEngineDisplacement(), bike.getId()
        );
    }

    /**
     * Генерує DELETE запит для заданого мотоцикла
     *
     * @param bike мотоцикл, для якого потрібно згенерувати запит
     * @return рядок String, що містить згенерований DELETE запит
     */
    public static String generateDeleteQuery(Bike bike) {
        return String.format("DELETE FROM bikes WHERE id = %d;", bike.getId());
    }
}
