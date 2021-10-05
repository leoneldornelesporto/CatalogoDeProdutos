package com.example.catalogodeprodutos.controller.form;

import com.example.catalogodeprodutos.domain.Product;
import com.example.catalogodeprodutos.repository.ProductRespository;
import org.yaml.snakeyaml.events.Event;

public class UpdateProductForm {
    private String name;
    private String description;
    private Float price;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Product update(Long id, ProductRespository productRespository) {
        Product product = new Product(id,name,description,price);
        return productRespository.save(product);
    }
}
