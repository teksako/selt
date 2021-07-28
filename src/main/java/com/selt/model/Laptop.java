package com.selt.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "LAPTOP")
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LAPTOP_ID")
    private Long id;

    @Column
    private String manufacturer;

    @Column
    private String model;

    @Column
    private String serialNumber;

    @Column
    private String hostname;

    @Column
    private String MACAdress;

    @Column
    private String IPAdress;

//    @Column
//    private List<Employee> oldOwners;

    @Column
    private String note;

    @OneToOne
    @JoinTable(
            name = "laptop_license",
            joinColumns = {@JoinColumn(name = "LAPTOP_ID")},
            inverseJoinColumns = {@JoinColumn(name = "WINDOWS_ID")}
    )
    private Windows windowsKey;


}
