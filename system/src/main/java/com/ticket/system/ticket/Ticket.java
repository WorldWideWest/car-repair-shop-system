package com.ticket.system.ticket;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "ticket")
public class Ticket {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "vehicle_name")
    private String vehicleName;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "repair_type")
    private String repairType;

    public Ticket(){}

    public Ticket(String firstName, String lastName, String vehicleName, String registrationNumber, String repairType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.vehicleName = vehicleName;
        this.registrationNumber = registrationNumber;
        this.repairType = repairType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRepairType() {
        return repairType;
    }

    public void setRepairType(String repairType) {
        this.repairType = repairType;
    }

    @Override
    public String toString() {
        return "Ticket [firstName=" + firstName + ", id=" + id + ", lastName=" + lastName + ", repairType=" + repairType
                + ", vehicleType=" + vehicleName + "]";
    }

}
