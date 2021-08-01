package com.selt.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "MOBILEPHONE")
@NoArgsConstructor
public class MobilePhone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MONILEPHONE_ID")
    private Long id;

    @Column
    private String mark;

    @Column
    private String model;

    @Column
    private String IMEI;

    @Column
    private String serialNumber;


}
