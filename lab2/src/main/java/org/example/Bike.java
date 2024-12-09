package org.example;

/**
 * Клас, який представняє мотоцикл з такими параметрами: id, бренд, потужність, максимальна швидкість, об'єм двигуна
 */
@Table(name = "bikes")
public class Bike {
    @Column(name = "id", primaryKey = true)
    private int id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "power")
    private int power;

    @Column(name = "topSpeed")
    private int topSpeed;

    @Column(name = "engineDisplacement")
    private int engineDisplacement;

    /**
     * Конструктор класу Bike
     *
     * @param id                 id
     * @param brand              бренд
     * @param power              потужність
     * @param topSpeed           максимальна швидкість
     * @param engineDisplacement об'єм двигуна
     */
    public Bike(int id, String brand, int power, int topSpeed, int engineDisplacement) {
        this.id = id;
        this.brand = brand;
        this.power = power;
        this.topSpeed = topSpeed;
        this.engineDisplacement = engineDisplacement;
    }

    /**
     * Повертає id
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Повертає бренд
     *
     * @return бренд
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Повертає потужність
     *
     * @return потужність
     */
    public int getPower() {
        return power;
    }

    /**
     * Повертає максимальну швидкість
     *
     * @return максимальна швидкість
     */
    public int getTopSpeed() {
        return topSpeed;
    }

    /**
     * Повертає об'єм двигуна
     *
     * @return об'єм двигуна
     */
    public int getEngineDisplacement() {
        return engineDisplacement;
    }
}
