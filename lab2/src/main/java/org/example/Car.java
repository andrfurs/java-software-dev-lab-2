package org.example;

/**
 * Клас, який представняє автомобіль з такими параметрами: id, бренд, потужність, вага, максимальна швидкість
 */
@Table(name = "cars")
public class Car {
    @Column(name = "id", primaryKey = true)
    private int id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "horsepower")
    private int horsepower;

    @Column(name = "weight")
    private int weight;

    @Column(name = "topSpeed")
    private int topSpeed;

    /**
     * Конструктор класу Car
     *
     * @param id         id
     * @param brand      бренд
     * @param horsepower потужність
     * @param weight     вага
     * @param topSpeed   максимальна швидкість
     */
    public Car(int id, String brand, int horsepower, int weight, int topSpeed) {
        this.id = id;
        this.brand = brand;
        this.horsepower = horsepower;
        this.weight = weight;
        this.topSpeed = topSpeed;
    }
}
