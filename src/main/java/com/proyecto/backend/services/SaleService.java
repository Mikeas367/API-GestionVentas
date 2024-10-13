package com.proyecto.backend.services;

import com.proyecto.backend.models.Product;
import com.proyecto.backend.models.Sale;
import com.proyecto.backend.models.SaleDetail;
import com.proyecto.backend.repositories.IProductRepository;
import com.proyecto.backend.repositories.ISaleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional // ----> Etiqueta que me permite que al haber algun error no se guarden cambios en la DB
public class SaleService {
    @Autowired
    IProductRepository productRepository;
    @Autowired
    ISaleRepository saleRepository;
    @Autowired
    ProductService productService;
    public Sale saveSale(Sale sale){

        for (SaleDetail saleDetail : sale.getDetails()){
            System.out.println("---->El ID es: " + saleDetail.getProduct().getId());
            Product product  = productService.getProductById(saleDetail.getProduct().getId());

            saleDetail.setProduct(product);
            saleDetail.setSale(sale);

            if (saleDetail.getQuantity() > saleDetail.getProduct().getProductStock()){
                throw new RuntimeException(" ----> No hay stock suficiente stock <---- ");
            }

            saleDetail.setSubtotal(saleDetail.getQuantity()   *  product.getUnitPrice());
            long actualizedStock  = product.getProductStock() - saleDetail.getQuantity();
            product.setProductStock(actualizedStock);
            productRepository.save(product);

        }
        return saleRepository.save(sale);
    }

    public List<Sale> getAllSales(){
        return saleRepository.findAll();
    }

    public Sale getSaleById(long id) {
        return saleRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Sale not found"));
    }
}
