package com.example.MinusServer.Controllers;

import com.example.MinusServer.Classes.*;
import com.example.MinusServer.Models.*;
import com.example.MinusServer.Repositories.CategoryRepository;
import com.example.MinusServer.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class CategoryController {
    @Autowired
    private CategoryRepository CategoryRepos;
    @Autowired
    private UserRepository UserRepos;
    @GetMapping("/getCategory")
    public ResponseEntity<Category> getCategory(@RequestBody int id){
        Optional<Category> category = CategoryRepos.findById((long) id);

        if(category.isEmpty()){
            return new ResponseEntity<Category>(category.get(), HttpStatus.BAD_REQUEST);
        }




        return new ResponseEntity<Category>(category.get(), HttpStatus.OK);

    }
    @PostMapping("/postCategory")
    public ResponseEntity<String> postCategory(@RequestBody Category category){
        try {
            CategoryRepos.save(category);
        }catch (IndexOutOfBoundsException e){
            return new ResponseEntity<String>("Bad", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>("Good", HttpStatus.OK);
    }
    @PostMapping("/postCategoryAdmin")
    public ResponseEntity<Category> postCategoryAdmin(@RequestBody CategoryAdder categoryAdder){
        Optional<MyUser> user = UserRepos.findById(categoryAdder.getPId());
        if(user.isEmpty() || user.get().getLevel()<=3
//                || !CategoryRepos.findByName(categoryAdder.getIdea()).isEmpty()
        ){
            Category o = new Category();
            return new ResponseEntity<Category>(o, HttpStatus.BAD_REQUEST);
        }
        Category category = new Category();
        category.setName(categoryAdder.getIdea());
        CategoryRepos.save(category);
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }
    @PutMapping("/putCategory")
    public ResponseEntity<String> putCategory(@RequestBody PutCategoryClass CategoryChanger){
        try {
            Optional<Category> TestCategory = CategoryRepos.findById((long) CategoryChanger.getId());
            if(TestCategory.isEmpty()){
                return new ResponseEntity<String>("Bad", HttpStatus.BAD_REQUEST);
            }
            Category ChangeCategory = TestCategory.get();
            ChangeCategory.setName(CategoryChanger.getCategory().getName());
            CategoryRepos.save(ChangeCategory);
        }catch (IndexOutOfBoundsException e){
            return new ResponseEntity<String>("Bad", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>("Good", HttpStatus.OK);
    }
    @DeleteMapping("/deleteCategory")
    public ResponseEntity<String> deleteCategory(@RequestBody int id){
        try {
            Optional<Category> TestCategory = CategoryRepos.findById((long) id);
            if(TestCategory.isEmpty()){
                return new ResponseEntity<String>("Bad", HttpStatus.BAD_REQUEST);
            }
            Category category = TestCategory.get();
            CategoryRepos.delete(category);
        }catch (IndexOutOfBoundsException e){
            return new ResponseEntity<String>("Bad", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>("Good", HttpStatus.OK);
    }
}
