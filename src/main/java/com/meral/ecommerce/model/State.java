package com.meral.ecommerce.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "State")
public class State  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name="";

    @ManyToOne
    @JoinColumn(name = "country_id",nullable = false)
    private Country country;

    public State() {
    }

    public State(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
