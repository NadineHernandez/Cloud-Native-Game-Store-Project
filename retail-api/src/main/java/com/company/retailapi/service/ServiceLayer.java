package com.company.retailapi.service;

import com.company.retailapi.models.InvoiceViewModel;
import com.company.retailapi.models.ProductViewModel;
import com.company.retailapi.util.feign.*;
import com.company.retailapi.viewmodel.RetailInvoiceViewModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ServiceLayer {
    private CustomerClient customerClient;
    private InventoryClient inventoryClient;
    private InvoiceClient invoiceClient;
    private LevelUpClient levelUpClient;
    private ProductClient productClient;
    private RabbitTemplate rabbitTemplate;

    public static final String EXCHANGE = "levelup-exchange";
    public static final String ROUTING_KEY = "levelup.create";

    @Autowired

    public ServiceLayer(CustomerClient customerClient, InventoryClient inventoryClient, InvoiceClient invoiceClient, LevelUpClient levelUpClient, ProductClient productClient, RabbitTemplate rabbitTemplate) {
        this.customerClient = customerClient;
        this.inventoryClient = inventoryClient;
        this.invoiceClient = invoiceClient;
        this.levelUpClient = levelUpClient;
        this.productClient = productClient;
        this.rabbitTemplate = rabbitTemplate;
    }

    private RetailInvoiceViewModel buildRetailInvoiceViewModel(InvoiceViewModel ivm){
        return null;
    }

    private InvoiceViewModel buildInvoiceViewModel(RetailInvoiceViewModel rivm){
        return null;
    }

    public RetailInvoiceViewModel submitInvoice(RetailInvoiceViewModel rivm){
        return null;
    }

    public RetailInvoiceViewModel getInvoiceById(int id){
        return null;
    }

    public List<RetailInvoiceViewModel> getAllInvoices(){
        return null;
    }

    public List<RetailInvoiceViewModel> getInvoicesByCustomerId(int id){
        return null;
    }

    public List<ProductViewModel> getProductsInInventory(){
        return null;
    }

    public ProductViewModel getProductById(int id){
        return null;
    }

    public List<ProductViewModel> getProductByInvoiceId(int id){
        return null;
    }

    public int getLevelUpPointsByCustomerId(int id){
        return 0;
    }
}
