package com.example.CaseStudy.controller;

import com.example.CaseStudy.entity.Product;
import com.example.CaseStudy.service.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.nio.file.Files;
import java.util.List;

@Controller
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping({"/",""})
    public String showProductList(Model model){
        List<Product> productList = productRepository.findAll();
        model.addAttribute("products", productList);
        return "listProducts";
    }

    @GetMapping("/create")
    public ModelAndView showCreatePage(Model model){
        ModelAndView mav = new ModelAndView("createProduct");
        Product product = new Product();
        mav.addObject("product", product);
        return mav;
    }

    @PostMapping("/saveProduct")
    public String saveItem(@ModelAttribute Product product)  {
        productRepository.save(product);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public ModelAndView editItem(@RequestParam Long productId){
        ModelAndView mav = new ModelAndView("createProduct");
        Product product = productRepository.findById(productId);
        mav.addObject("product", product);
        return mav;
    }

    @GetMapping("/delete")
    @Transactional
    public String deleteItem(@RequestParam Long productId) {
        productRepository.deleteById(productId);
        return "redirect:/";
    }
}
