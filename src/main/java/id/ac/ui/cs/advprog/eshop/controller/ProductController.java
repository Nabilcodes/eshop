package id.ac.ui.cs.advprog.eshop.controller;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductCreateService productCreateService;

    @Autowired
    private ProductDeleteService productDeleteService;

    @Autowired
    private ProductRetrieveService productRetrieveService;

    @Autowired
    private ProductUpdateService productUpdateService;

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product",product);
        return "CreateProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product, Model model) {
        productCreateService.create(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String productListPage(Model model){
        List<Product> allProducts = productRetrieveService.findAll();
        model.addAttribute("products",allProducts);
        return "ProductList";
    }

    @GetMapping("/edit/{productId}")
    public String editProductPage(@PathVariable String productId, Model model) {
        Product product = productRetrieveService.findById(productId);
        model.addAttribute("product", product);
        return "EditProduct";
    }

    @PostMapping("/edit") // salah di bagian ini juga
    public String editProductPost(@ModelAttribute Product product, Model model) {
        System.out.println(product.getProductId());
        productUpdateService.update(product.getProductId(), product);
        return "redirect:list";
    }

    @GetMapping("/delete") // salah di bagian ini juga
    public String deleteProduct(@RequestParam("productId") String productId) {
        productDeleteService.deleteById(productId);
        return "redirect:list";
    }

}
