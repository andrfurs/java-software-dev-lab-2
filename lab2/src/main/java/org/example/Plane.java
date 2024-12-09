package org.example;

/**
 * Клас, який представняє літак з такими параметрами: id, бренд, тип, дальність польоту
 */
@Table(name = "planes")
public class Plane {
    @Column(name = "id", primaryKey = true)
    private int id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "type")
    private String type;

    @Column(name = "flightDistance")
    private int flightDistance;

    /**
     * Конструктор класу Plane
     *
     * @param id             id
     * @param brand          бренд
     * @param type           тип
     * @param flightDistance дальність польоту
     */
    public Plane(int id, String brand, String type, int flightDistance) {
        this.id = id;
        this.brand = brand;
        this.type = type;
        this.flightDistance = flightDistance;
    }
}
