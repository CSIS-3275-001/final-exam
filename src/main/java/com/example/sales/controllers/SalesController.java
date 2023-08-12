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
            List<Sales> salesList = salesRepository.findAll();

            model.addAttribute("items", itemsList);
            model.addAttribute("sales", salesList);
            model.addAttribute("sale", new Sales());
            model.addAttribute("recno", "Receipt number and item code already exist");

            return "index";
        } else if (existingSales.isPresent()) {

            List<Items> itemsList = itemsRepository.findAll();
            List<Sales> salesList = salesRepository.findAll();

            model.addAttribute("items", itemsList);
            model.addAttribute("sales", salesList);
            model.addAttribute("sale", new Sales());
            model.addAttribute("recno", "Receipt number already exists");

            return "index";
        }
        salesRepository.save(sale);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        List<Items> itemsList = itemsRepository.findAll();
        model.addAttribute("items", itemsList);

        Optional<Sales> saleOptional = salesRepository.findById(id);
        if (saleOptional.isPresent()) {
            Sales sale = saleOptional.get();
            model.addAttribute("sale", sale);
            return "edit";
        }

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        salesRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/updateSales")
    public String updateSales(@ModelAttribute("sale") Sales sale, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit";
        }

        Optional<Sales> existingSales = salesRepository.findById(sale.getId());
        if (!existingSales.isPresent()) {
            return "redirect:/";
        }

        salesRepository.save(sale);

        return "redirect:/";
    }

}
