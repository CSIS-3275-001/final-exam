package com.example.sales.controllers;

import com.example.sales.entities.Items;
import com.example.sales.entities.Sales;
import com.example.sales.repositories.ItemsRepository;
import com.example.sales.repositories.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        List<Sales> salesList = salesRepository.findAll();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = formatter.format(new Date());

        model.addAttribute("items", itemsList);
        model.addAttribute("sales", salesList);
        model.addAttribute("sale", new Sales());
        model.addAttribute("todayDate", todayDate);

        return "index";
    }


}
