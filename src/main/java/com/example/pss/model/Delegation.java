package com.example.pss.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Delegation")
@Getter
@Setter


public class Delegation {

    @Id
    @GeneratedValue
    @Column(name="iddelegation") //obowiazkowe
    private Integer iddelegation;

    @Column(name="description") //opcjonalne
    private String description;

    @Column(name="dataTimeStart",nullable=false) //obowiazkowe
    private LocalDate dateTimeStart;

    @Column(name="dataTimeStop",nullable=false) //obowiazkowe
    private LocalDate dateTimeStop;

    @Column(name="travelDietAmount")
    private Integer travelDietAmount=30;

    @Column(name="breakfastNumber")
    private Integer breakfastNumber=0;

    @Column(name="dinnerNumber")
    private Integer dinnerNumber=0;

    @Column(name="supperNumber")
    private Integer supperNumber=0;

    @Column(name="transportEnum") //opcjonalne z 3
    @Enumerated(EnumType.STRING)
    private TransportEnum transportEnum;

    @Column(name="ticketPrice")
    private Float ticketPrice;

    @Column(name="autoCapacityEnum")
    @Enumerated(EnumType.STRING)
    private AutoCapacityEnum autoCapacityEnum;

    @Column(name="km")
    private Float km;

    @Column(name="accomodationPrice") //opcjonalne
    private Float accomodationPrice;

    @Column(name="otherticketsPrice") //opcjonalne
    private Float otherTicketsPrice;

    @Column(name="otherOutlayDesc") //opcjonalne
    private String otherOutlayDesc;

    @Column(name="otherOutlayPrice") //opcjonalne
    private Float otherOutlayPrice;

    @ManyToOne //relacja delegacji do uzytkownika
    private User user;

    public Delegation(String description, User user, LocalDate dateTimeStart,
                      LocalDate dateTimeStop, TransportEnum transportEnum, Float ticketPrice,
                      AutoCapacityEnum autoCapacityEnum, Float km, Float accomodationPrice,
                      Float otherTicketsPrice, String otherOutlayDesc, Float otherOutlayPrice) {
        this.description = description;
        this.user = user;
        this.dateTimeStart = dateTimeStart;
        this.dateTimeStop = dateTimeStop;
        this.transportEnum = transportEnum;
        this.ticketPrice = ticketPrice;
        this.autoCapacityEnum = autoCapacityEnum;
        this.km= km;
        this.accomodationPrice = accomodationPrice;
        this.otherTicketsPrice = otherTicketsPrice;
        this.otherOutlayDesc = otherOutlayDesc;
        this.otherOutlayPrice = otherOutlayPrice;
    }

}
