package com.example.sales;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.example.sales.controllers.SalesController;
import com.example.sales.entities.Sales;
import com.example.sales.repositories.ItemsRepository;
import com.example.sales.repositories.SalesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Optional;

@WebMvcTest(SalesController.class)
public class SalesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemsRepository itemsRepository;

    @MockBean
    private SalesRepository salesRepository;

    @Test
    public void testDisplay() throws Exception {

        when(itemsRepository.findAll()).thenReturn(new ArrayList<>());
        when(salesRepository.findAll()).thenReturn(new ArrayList<>());


        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void testUpdateSales() throws Exception {
        Sales existingSale = new Sales();

        mockMvc.perform(post("/updateSales")
                        .flashAttr("sale", existingSale))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void testDelete() throws Exception {

        mockMvc.perform(get("/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));


        verify(salesRepository).deleteById(1L);
    }

    @Test
    public void testSaveSales() throws Exception {
        Sales newSale = new Sales();

        mockMvc.perform(post("/saveSales")
                        .flashAttr("sale", newSale))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void testDisplaySales() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void testDeleteSales() throws Exception {
        Long idToDelete = 1L;

        mockMvc.perform(get("/delete/" + idToDelete))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
}