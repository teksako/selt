package com.selt.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "PRINTER")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Printer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRINTER_ID")
    private Long id;

    @Column
    private String model;

    @Column
    private String manufacturer;

    @Column
    private String serialNumber;

    @Column
    private String IPAdress;

    @Column
    private String MACAdress;

    @Column
    private String user;

    @Column
    private String inventoryNumber;

    @OneToOne
    @JoinTable(
            name="PRINTER_LOCATION",
            joinColumns  ={@JoinColumn(name ="PRINTER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "DEPARTMENT_ID")}
    )

    private Department department;

    @ManyToOne
    @JoinTable(
            name = "PRINTER_TONER",
            joinColumns ={@JoinColumn(name ="PRINTER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "TONER_ID")}
    )

    private Toner toner;

    @OneToMany
    @JoinTable(
            name="PRINTER_COUNTER",
            joinColumns = {@JoinColumn(name="PRINTER_ID")},
            inverseJoinColumns = {@JoinColumn(name="COUNTER_ID")}
    )

    private List<Counter> counters;

    @ManyToMany
    @JoinTable(
            name="PRINTER_OID",
            joinColumns = {@JoinColumn(name="PRINTER_ID")},
            inverseJoinColumns = {@JoinColumn(name="OID_ID")}
    )

    private List<OID> oid;

}
