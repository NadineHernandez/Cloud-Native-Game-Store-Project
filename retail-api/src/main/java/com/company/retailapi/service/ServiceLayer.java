package com.company.retailapi.service;

import com.company.retailapi.models.*;
import com.company.retailapi.util.feign.*;
import com.company.retailapi.viewmodel.RetailInvoiceViewModel;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
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
        RetailInvoiceViewModel rivm = new RetailInvoiceViewModel();
        rivm.setInvoiceId(ivm.getInvoiceId());
        rivm.setCustomerId(ivm.getCustomerId());
        rivm.setPurchaseDate(ivm.getPurchaseDate());
        rivm.setItems(invoiceClient.getInvoiceItemsByInvoiceId(rivm.getInvoiceId()));

        BigDecimal totalCalculator = new BigDecimal("0.00");
        for (int i = 0; i < rivm.getItems().size(); i ++){
            totalCalculator = totalCalculator.add(rivm.getItems().get(i).getUnitPrice()
                    .multiply(new BigDecimal(rivm.getItems().get(i).getQuantity())));
        }

        rivm.setTotal(totalCalculator);

        int levelUpPnts = Integer.parseInt(String.valueOf(totalCalculator.divide(new BigDecimal("50.00")).setScale(0,BigDecimal.ROUND_DOWN)))*10;

        if (levelUpClient.getLevelUpByCustomerId(rivm.getCustomerId()) != null) {
            LevelUpViewModel myLvl = levelUpClient.getLevelUpByCustomerId(rivm.getCustomerId());
            myLvl.setPoints(
                    levelUpClient.getLevelUpByCustomerId(rivm.getCustomerId()).getPoints() + levelUpPnts
            );
            rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, myLvl);
        }
        else {
            LocalDate today = LocalDate.of(2019,10,28);
            LevelUpViewModel myLvl = new LevelUpViewModel(rivm.getCustomerId(), levelUpPnts, today);

            rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, myLvl);
        }

        return rivm;
    }

    private InvoiceViewModel buildInvoiceViewModel(RetailInvoiceViewModel rivm){
        InvoiceViewModel ivm = new InvoiceViewModel(rivm.getCustomerId(), rivm.getPurchaseDate());
        ivm = invoiceClient.addInvoice(ivm);
        return ivm;
    }

    public RetailInvoiceViewModel submitInvoice(RetailInvoiceViewModel rivm){
        rivm.getItems().stream().forEach(item -> {
            //check against quantity and decrement if stock available
            if (inventoryClient.getInventory(item.getInventoryId()).getQuantity() >= item.getQuantity()){
                invoiceClient.addInvoiceItem(item);
                inventoryClient.getInventory(item.getInventoryId()).setQuantity(
                        inventoryClient.getInventory(item.getInventoryId()).getQuantity()
                        - item.getQuantity()
                );
            } else {
                throw new IllegalArgumentException("Insufficient quantity");
            }

        });
        InvoiceViewModel ivm = buildInvoiceViewModel(rivm);

        return buildRetailInvoiceViewModel(ivm);
    }

    public RetailInvoiceViewModel getInvoiceById(int id){
        return buildRetailInvoiceViewModel(invoiceClient.getInvoice(id));
    }

    public List<RetailInvoiceViewModel> getAllInvoices(){
        List<RetailInvoiceViewModel> rivms = new ArrayList<>();

        invoiceClient.getAllInvoices().stream().forEach(invoice -> {
            rivms.add(buildRetailInvoiceViewModel(invoice));
        });

        return rivms;
    }

    public List<RetailInvoiceViewModel> getInvoicesByCustomerId(int id){
        List<RetailInvoiceViewModel> rivms = new ArrayList<>();
        invoiceClient.getInvoicesByCustomerId(id).stream().forEach(invoice -> {
            rivms.add(buildRetailInvoiceViewModel(invoice));
        });

        return rivms;
    }

    public List<ProductViewModel> getProductsInInventory(){
        List<ProductViewModel> products = new ArrayList<>();

        inventoryClient.getAllInventories().stream().forEach(inventory -> {
            products.add(productClient.getProduct(inventory.getProductId()));
        });

        return products;
    }

    public ProductViewModel getProductById(int id){
        return productClient.getProduct(id);
    }

    public List<ProductViewModel> getProductByInvoiceId(int id){
        List<ProductViewModel> products = new ArrayList<>();
        List<InvoiceItemViewModel> itemsList = invoiceClient.getInvoiceItemsByInvoiceId(id);
        List<InventoryViewModel> inventories = new ArrayList<>();
        itemsList.stream().forEach(item -> {
            inventories.add(inventoryClient.getInventory(item.getInventoryId()));
        });
        inventories.stream().forEach(inventory -> {
            products.add(productClient.getProduct(inventory.getProductId()));
        });
        return products;
    }

    @HystrixCommand(fallbackMethod = "getLevelUpPointByCustomerIdFailsAndReturnsDefault")
    public int getLevelUpPointsByCustomerId(int id){
        return levelUpClient.getLevelUpByCustomerId(id).getPoints();
    }

    public int getLevelUpPointByCustomerIdFailsAndReturnsDefault(){
        return 0;
    }
}
