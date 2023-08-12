package com.example.sales.controllers;

import com.example.sales.entities.Items;
import com.example.sales.entities.Sales;
import com.example.sales.repositories.ItemsRepository;
import com.example.sales.repositories.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final ItemsRepository itemsRepository;

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    public HomeController(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Items> itemsList = itemsRepository.findAll();
        List<Sales> salesList = salesRepository.findAll(); // Fetching sales data


        model.addAttribute("items", itemsList);
        model.addAttribute("sales", salesList); // Adding sales data to the model
        model.addAttribute("sale", new Sales());

        return "index";
    }


}
