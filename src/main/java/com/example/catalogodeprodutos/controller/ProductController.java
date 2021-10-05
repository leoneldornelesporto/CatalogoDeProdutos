package com.example.catalogodeprodutos.controller;

import com.example.catalogodeprodutos.controller.form.ProductForm;
import com.example.catalogodeprodutos.controller.form.UpdateProductForm;
import com.example.catalogodeprodutos.domain.Product;
import com.example.catalogodeprodutos.repository.ProductRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequestMapping("/products")
@RestController
public class ProductController {
    private final ProductRespository productRespository;

    @Autowired
    public ProductController(ProductRespository productRespository) {
        this.productRespository = productRespository;
    }

    @GetMapping
    public ResponseEntity<? extends List<Product>> findAllProducts(){
        List<Product> productList = productRespository.findAll();

        if (!productList.isEmpty()){
            return ResponseEntity.ok(productList);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<? extends Product> findProductById(@PathVariable Long id){
        Optional<Product> product = productRespository.findById(id);

        if (product.isPresent()){
            return ResponseEntity.ok(product.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<? extends List<Product>> findProductMinPriceAndMaxPrice(@RequestParam Float min_price, @RequestParam Float max_price){
        List<Product> productList = productRespository.findByPriceBetween(min_price, max_price);

        if (!productList.isEmpty()){
            return ResponseEntity.ok(productList);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<? extends List<Product>> findProductByName(@PathVariable String name){
        List<Product> productList = productRespository.findByNameContainingIgnoreCase(name);

        if (!productList.isEmpty()){
            return ResponseEntity.ok(productList);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<? extends Product> saveProduct(@RequestBody ProductForm productForm, UriComponentsBuilder uriComponentsBuilder){
        Product product = productForm.save(productRespository);
        URI uri = uriComponentsBuilder.path("/product/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<? extends Product> updateProduct(@PathVariable Long id, @RequestBody UpdateProductForm updateProduct, UriComponentsBuilder uriComponentsBuilder){
        Product product = updateProduct.update(id,productRespository);
        URI uri = uriComponentsBuilder.path("/product/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<? extends Product> deleteProduct(@PathVariable Long id){
        Optional<Product> optional = productRespository.findById(id);
        if (optional.isPresent()) {
            productRespository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
