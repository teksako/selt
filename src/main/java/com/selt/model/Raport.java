package com.selt.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "RAPORT")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Raport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RAPORT_ID")
    private Long id;

    @Column
    private Date date;

    @Column
    private Long count;


    @ManyToMany
    @JoinTable
            (
                    name = "toner_raport",
                    joinColumns = {@JoinColumn(name = "RAPORT_ID")},
                    inverseJoinColumns = {@JoinColumn(name = "PRINTER_ID")}
            )
    private List<Printer> printers;
}
