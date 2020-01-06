package com.jack.store.Resources;

import com.jack.store.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryResource {

    @Autowired
    private CategoryRepository categoryRepository;


}
