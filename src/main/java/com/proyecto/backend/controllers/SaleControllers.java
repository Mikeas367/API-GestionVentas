package com.proyecto.backend.controllers;

import com.proyecto.backend.models.Sale;
import com.proyecto.backend.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api")
public class SaleControllers {
    @Autowired
    private SaleService saleService;

    @GetMapping("/sales")
    public List<Sale> findAllSales(){
        return saleService.getAllSales();
    }

    @PostMapping("/sales")
    public Sale saveSale(@RequestBody Sale sale){
        return saleService.saveSale(sale);
    }

}
