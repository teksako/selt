package com.selt.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private String manufaturer;
}
