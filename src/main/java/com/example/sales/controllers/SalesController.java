package com.example.sales.controllers;

import com.example.sales.entities.Items;
import com.example.sales.entities.Sales;
import com.example.sales.repositories.ItemsRepository;
import com.example.sales.repositories.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class SalesController {

    private final ItemsRepository itemsRepository;

    @Autowired
    private final SalesRepository salesRepository;

    public SalesController(ItemsRepository itemsRepository, SalesRepository salesRepository) {
        this.itemsRepository = itemsRepository;
        this.salesRepository = salesRepository;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @PostMapping("/saveSales")
    public String saveSales(@ModelAttribute("sale") Sales sale, BindingResult result, Model model) {

        // Check if receipt number already exists
        Optional<Sales> existingSales = salesRepository.findByRecno(sale.getRecno());

        if (existingSales.isPresent() && existingSales.get().getIcode().equals(sale.getIcode())) {

            List<Items> itemsList = itemsRepository.findAll();
            List<Sales> salesList = salesRepository.findAll(); // Fetching sales data

            model.addAttribute("items", itemsList);
            model.addAttribute("sales", salesList); // Adding sales data to the model
            model.addAttribute("sale", new Sales());
            model.addAttribute("recno", "Receipt number and item code already exist");

            return "index";
        } else if (existingSales.isPresent()) {

            List<Items> itemsList = itemsRepository.findAll();
            List<Sales> salesList = salesRepository.findAll(); // Fetching sales data

            model.addAttribute("items", itemsList);
            model.addAttribute("sales", salesList); // Adding sales data to the model
            model.addAttribute("sale", new Sales());
            model.addAttribute("recno", "Receipt number already exists");

            return "index";
        }
        salesRepository.save(sale);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        List<Items> itemsList = itemsRepository.findAll(); // Fetching items
        model.addAttribute("items", itemsList); // Adding items to the model

        Optional<Sales> saleOptional = salesRepository.findById(id);
        if (saleOptional.isPresent()) {
            Sales sale = saleOptional.get(); // Extract the Sales object
            model.addAttribute("sale", sale); // Add it to the model
            return "edit"; // Return a view for editing the sales record
        }
        // Handle the case where the id was not found in the repository, e.g., redirect to an error page
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        salesRepository.deleteById(id);
        return "redirect:/"; // Redirect back to the index page after deleting
    }

    @PostMapping("/updateSales")
    public String updateSales(@ModelAttribute("sale") Sales sale, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Handle validation errors
            return "edit";
        }

        // Check if sales record with given ID exists
        Optional<Sales> existingSales = salesRepository.findById(sale.getId());
        if (!existingSales.isPresent()) {
            // Handle error if sales record not found
            return "redirect:/";
        }

        // Update the sales record
        salesRepository.save(sale);

        // Redirect to the index or another appropriate page
        return "redirect:/";
    }

}
