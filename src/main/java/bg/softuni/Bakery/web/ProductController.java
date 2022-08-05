package bg.softuni.Bakery.web;

import bg.softuni.Bakery.model.dto.AddProductDTO;


import bg.softuni.Bakery.model.dto.ProductDTO;
import bg.softuni.Bakery.model.dto.UpdateProductDTO;
import bg.softuni.Bakery.service.ProductService;
import bg.softuni.Bakery.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public ProductController(ProductService productService, UserService userService, ModelMapper modelMapper) {
        this.productService = productService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("addProductDTO")
    public AddProductDTO initUserModel() {
        return new AddProductDTO();
    }

    @GetMapping("/all")
    public String allProducts(Model model) {
        List<ProductDTO> allProduct = productService.getAllProduct();
        if (allProduct.size() == 0) {
            return "redirect:/products/add";
        }
        model.addAttribute("allProduct", allProduct);
        return "products-all";
    }

    @GetMapping("/add")
    public String addProduct() {

        return "product-add";
    }

    @PostMapping("/add")
    public String addProduct(@Valid AddProductDTO addProductDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            if (bindingResult.hasErrors()) {
                redirectAttributes.addFlashAttribute("addProductDTO", addProductDTO);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addProductDTO",
                        bindingResult);
                return "redirect:/products/add";
            }
        }
        this.productService.addProduct(addProductDTO);

        return "redirect:/";
    }

    @GetMapping("/{id}/details")
    public String details(@PathVariable Long id, Model model, Principal principal) {
        ProductDTO productDTO = productService.findById(id);
        if (principal == null){
            model.addAttribute("productDTO", productDTO);
            model.addAttribute("isAdmin", false);
            return "product-details";
        }
        boolean isAdmin = userService.isCurrentUserAdmin(principal);
        model.addAttribute("productDTO", productDTO);
        model.addAttribute("isAdmin", isAdmin);
        return "product-details";
    }

    @GetMapping("/{id}/update")
    public String update(@PathVariable Long id, Model model) {
        ProductDTO productDTO = productService.findById(id);
        UpdateProductDTO updateProduct = modelMapper.map(productDTO, UpdateProductDTO.class);

        model.addAttribute("updateProduct", updateProduct);
        return "product-update";
    }
    @PatchMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @Valid UpdateProductDTO updateProductDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("updateProductDTO", updateProductDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.updateProductDTO",
                    bindingResult);
            return "redirect:/products/update/{id}";
        }

        updateProductDTO.setId(id);
        productService.update(updateProductDTO);
        return "redirect:/products/{id}/details";
    }
    @DeleteMapping("/{id}")
    public String deleteRecipe(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return "redirect:/products/all";
    }
    @GetMapping("/bread")
    public String breads(Model model){
        List<ProductDTO> allProduct = productService.getAllProductBread();
        if (allProduct.size() == 0) {
            //TODO
            return "";
        }
        model.addAttribute("bread", allProduct);
        return "products_by_category";
    }
}
