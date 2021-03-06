package com.example.catalogodeprodutos.domain;

import javax.persistence.Entity;

@Entity
public class Product extends AbstractEntity{
    private String name;
    private String description;
    private Float price;

    public Product() {
    }

    public Product(String name, String description, Float price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(Long id, String name, String description, Float price) {
        this.setId(id);
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
