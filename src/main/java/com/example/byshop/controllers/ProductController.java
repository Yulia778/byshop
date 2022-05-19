package com.example.byshop.controllers;

import com.example.byshop.models.Product;
import com.example.byshop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    @GetMapping("/")
    public String products(@RequestParam(name = "title",required = false) String title,Principal principal, Model model) {
        model.addAttribute("products", productService.listProducts(title));
        model.addAttribute("user",productService.getUserByPrincipal(principal));

        return "products";
    }@GetMapping("/product/{ID}")
    public String productInfo(@PathVariable Long ID,Model model){
model.addAttribute("product",productService.getProductById(ID));
return "product-info";
    }

    @PostMapping("/product/create")
    public String createProduct(@RequestParam("file1") MultipartFile file1, @RequestParam("file1") MultipartFile file2, @RequestParam("file1") MultipartFile file3, Product product, Principal principal) throws IOException {
        productService.saveProduct(principal,product,file1,file2,file3);
        return "redirect:/";
    }

    @PostMapping("/product/delete/{ID}")
    public String deleteProduct(@PathVariable Long ID) {
        productService.deleteProduct(ID);
        return "redirect:/";
    }

}
