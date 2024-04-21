package com.example.MinusServer.Controllers;

import com.example.MinusServer.Classes.*;
import com.example.MinusServer.Models.*;
import com.example.MinusServer.Repositories.CategoryRepository;
import com.example.MinusServer.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository ProductRepos;
    @Autowired
    private CategoryRepository CategoryRepos;
    @GetMapping("/getProduct")
    public ResponseEntity<Product> getProduct(@RequestBody int id){
        Optional<Product> product = ProductRepos.findById((long) id);

        if(product.isEmpty()){
            return  new ResponseEntity<Product>(product.get(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Product>(product.get(), HttpStatus.OK);

    }
    @PostMapping("/postProduct")
    public ResponseEntity<Product> postProduct(@RequestBody Product product){
        try {
            ProductRepos.save(product);
        }catch (IndexOutOfBoundsException e){
            return new ResponseEntity<Product>(product, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }
    @PostMapping("/postItem")
    public ResponseEntity<Product> postItem(@RequestBody Item item){
        Optional<Category> category = CategoryRepos.findById(Long.valueOf(item.getCategoryId()));
        if(category.isEmpty()){
            Product o = new Product();
            return new ResponseEntity<Product>(o, HttpStatus.BAD_REQUEST);
        }
        Product product = new Product();
        product.setName(item.getName());
        product.setCompany(item.getCompany());
        product.setPrice(item.getFullPrice());
        product.setPartlyPrice((double) 0);
        product.setCategory(category.get());
        ProductRepos.save(product);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }
    @PatchMapping("/patchProduct")
    public ResponseEntity<String> patchProduct(@RequestBody PatchClass changer){
        try {
            Optional<Product> TestProduct = ProductRepos.findById((long) changer.getId());
            if(TestProduct.isEmpty()){
                return new ResponseEntity<String>("Bad", HttpStatus.BAD_REQUEST);
            }
            Product product = TestProduct.get();
            changer.setType(changer.getType().toLowerCase());
            if(changer.getType().equals("name")){
                product.setName(changer.getPatch());
            }
            else if(changer.getType().equals("company")){
                product.setCompany(changer.getPatch());
            }
            else if(changer.getType().equals("price")){
                product.setPrice(Double.valueOf(changer.getPatch()));
            }
            ProductRepos.save(product);
        }catch (IndexOutOfBoundsException e){
            return new ResponseEntity<String>("Bad", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>("Good", HttpStatus.OK);
    }
    @PutMapping("/putProduct")
    public ResponseEntity<String> putProduct(@RequestBody PutProductClass ProductChanger){
        try {
            Optional<Product> TestProduct = ProductRepos.findById((long) ProductChanger.getId());
            if(TestProduct.isEmpty()){
                return new ResponseEntity<String>("Bad", HttpStatus.BAD_REQUEST);
            }
            Product ChangeProduct = TestProduct.get();
            ChangeProduct.setPrice(ProductChanger.getProduct().getFullPrice());
            ChangeProduct.setName(ProductChanger.getProduct().getName());
            ChangeProduct.setCompany(ProductChanger.getProduct().getCompany());
            ProductRepos.save(ChangeProduct);
        }catch (IndexOutOfBoundsException e){
            return new ResponseEntity<String>("Bad", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>("Good", HttpStatus.OK);
    }
    //!!!!!!!!!
    @PutMapping("/updateProduct")
    public ResponseEntity<String> updateProduct(@RequestBody UniqueProduct product){
        Optional<Product> TestProduct = ProductRepos.findById((long) product.getId());
        Optional<Category> TestCategory = CategoryRepos.findByName(product.getCategoryName());
        if(TestProduct.isEmpty() || TestCategory.isEmpty()){
            return new ResponseEntity<String>("Bad", HttpStatus.BAD_REQUEST);
        }
        Product UpdateProduct = TestProduct.get();
        UpdateProduct.setPrice(product.getFullPrice());
        UpdateProduct.setName(product.getName());
        UpdateProduct.setPartlyPrice(TestProduct.get().getPartlyPrice());
        UpdateProduct.setCategory(TestCategory.get());
        UpdateProduct.setCompany(product.getCompany());
        ProductRepos.save(UpdateProduct);
        return new ResponseEntity<String>("Good", HttpStatus.OK);
    }

    @DeleteMapping("/deleteProduct")
    public ResponseEntity<String> deleteProduct(@RequestParam int id){
        Optional<Product> TestProduct = ProductRepos.findById((long) id);
        if(TestProduct.isEmpty()){
            return new ResponseEntity<String>("Bad", HttpStatus.BAD_REQUEST);
        }
        Product product = TestProduct.get();
        ProductRepos.delete(product);
        return new ResponseEntity<String>("Good", HttpStatus.OK);
    }
}
