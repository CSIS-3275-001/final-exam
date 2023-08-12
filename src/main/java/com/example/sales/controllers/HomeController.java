package com.example.sales.controllers;

import com.example.sales.SalesSummary;
import com.example.sales.entities.Items;
import com.example.sales.entities.Sales;
import com.example.sales.repositories.ItemsRepository;
import com.example.sales.repositories.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        // Creating a Map to hold sales summary by category
        Map<String, SalesSummary> salesSummaryMap = new HashMap<>();
//        for (Sales sale : salesList) {
//            // Here, you would use your logic to get the category code and description based on the icode
//            String categoryCode = getCategoryCodeByIcode(sale.getIcode(), itemsList);
//            String categoryDesc = getCategoryDescByIcode(sale.getIcode(), itemsList);
//            double qty = sale.getQty();
//
//            // Adding sales quantity to the corresponding category
//            salesSummaryMap.computeIfAbsent(categoryCode, k -> new SalesSummary(categoryCode, categoryDesc, 0))
//                    .setTotalSales(salesSummaryMap.get(categoryCode).getTotalSales() + qty);
//        }

        // Converting the summary Map to a List
        List<SalesSummary> salesSummaryList = new ArrayList<>(salesSummaryMap.values());

        model.addAttribute("items", itemsList);
        model.addAttribute("sales", salesList); // Adding sales data to the model
        model.addAttribute("sale", new Sales());
        model.addAttribute("salesSummary", salesSummaryList); // Adding sales summary data to the model

        return "index";
    }


}
