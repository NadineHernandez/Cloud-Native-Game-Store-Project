package com.company.retailapi.controller;

import com.company.retailapi.models.CustomerViewModel;
import com.company.retailapi.models.InvoiceItemViewModel;
import com.company.retailapi.models.LevelUpViewModel;
import com.company.retailapi.models.ProductViewModel;
import com.company.retailapi.service.ServiceLayer;
import com.company.retailapi.util.feign.*;
import com.company.retailapi.viewmodel.RetailInvoiceViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RetailApiController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
class RetailApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceLayer serviceLayer;

    @MockBean
    private RabbitTemplate rabbitTemplate;

//    @MockBean
//    private CustomerClient customerClient;
//
//    @MockBean
//    private InventoryClient inventoryClient;
//
//    @MockBean
//    private InvoiceClient invoiceClient;
//
//    @MockBean
//    private LevelUpClient levelUpClient;
//
//    @MockBean
//    private ProductClient productClient;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void submitInvoice() throws Exception{
        List<InvoiceItemViewModel> items = new ArrayList<>();
        InvoiceItemViewModel item =  new InvoiceItemViewModel(1, 1, 10, new BigDecimal("10.00"));
        item.setInvoiceItemId(1);
        items.add(item);
        RetailInvoiceViewModel inputRivm = new RetailInvoiceViewModel(1, LocalDate.of(2019, 7, 22), items);
        String inputJson = mapper.writeValueAsString(inputRivm);


        RetailInvoiceViewModel outputRivm = new RetailInvoiceViewModel(1, LocalDate.of(2019, 7, 22), items);
        outputRivm.setInvoiceId(1);
        outputRivm.setTotal(new BigDecimal("100.00"));
        String outputJson = mapper.writeValueAsString(outputRivm);

        when(serviceLayer.submitInvoice(inputRivm)).thenReturn(outputRivm);

        this.mockMvc.perform(post("/invoices")
        .content(inputJson)
        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    void getInvoiceById() throws Exception{
        List<InvoiceItemViewModel> items = new ArrayList<>();
        InvoiceItemViewModel item =  new InvoiceItemViewModel(1, 1, 10, new BigDecimal("10.00"));
        item.setInvoiceItemId(1);
        items.add(item);
        RetailInvoiceViewModel outputRivm = new RetailInvoiceViewModel(1, LocalDate.of(2019, 7, 22), items);
        outputRivm.setInvoiceId(1);
        outputRivm.setTotal(new BigDecimal("100.00"));
        String outputJson = mapper.writeValueAsString(outputRivm);

        when(serviceLayer.getInvoiceById(1)).thenReturn(outputRivm);

        this.mockMvc.perform(get("/invoices/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void getInvoiceByIdThatDoesNotExistShouldReturn404()throws Exception{
        int idDNE = 90000;
        when(serviceLayer.getInvoiceById(idDNE)).thenReturn(null);

        this.mockMvc.perform(get("/invoices/" + idDNE))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllInvoices() throws Exception{
        List<InvoiceItemViewModel> items = new ArrayList<>();
        InvoiceItemViewModel item =  new InvoiceItemViewModel(1, 1, 10, new BigDecimal("10.00"));
        item.setInvoiceItemId(1);
        items.add(item);
        RetailInvoiceViewModel outputRivm = new RetailInvoiceViewModel(1, LocalDate.of(2019, 7, 22), items);
        outputRivm.setInvoiceId(1);
        outputRivm.setTotal(new BigDecimal("100.00"));

        List<RetailInvoiceViewModel> rivms = new ArrayList<>();
        rivms.add(outputRivm);

        when(serviceLayer.getAllInvoices()).thenReturn(rivms);

        List<RetailInvoiceViewModel> listChecker = new ArrayList<>();
        listChecker.addAll(rivms);

        String outputJson = mapper.writeValueAsString(listChecker);

        this.mockMvc.perform(get("/invoices"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    void getInvoicesByCustomerId() throws Exception{
        List<InvoiceItemViewModel> items = new ArrayList<>();
        InvoiceItemViewModel item =  new InvoiceItemViewModel(1, 1, 10, new BigDecimal("10.00"));
        item.setInvoiceItemId(1);
        items.add(item);
        RetailInvoiceViewModel outputRivm = new RetailInvoiceViewModel(1, LocalDate.of(2019, 7, 22), items);
        outputRivm.setInvoiceId(1);
        outputRivm.setTotal(new BigDecimal("100.00"));

        List<RetailInvoiceViewModel> rivms = new ArrayList<>();
        rivms.add(outputRivm);

        when(serviceLayer.getInvoicesByCustomerId(1)).thenReturn(rivms);

        List<RetailInvoiceViewModel> listChecker = new ArrayList<>();
        listChecker.addAll(rivms);

        String outputJson = mapper.writeValueAsString(listChecker);

        this.mockMvc.perform(get("/invoices/customer/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    void getProductsInInventory() throws Exception{
        ProductViewModel product = new ProductViewModel(1,"Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));

        List<ProductViewModel> products = new ArrayList<>();
        products.add(product);

        when(serviceLayer.getProductsInInventory()).thenReturn(products);

        List<ProductViewModel> listChecker = new ArrayList<>();
        listChecker.addAll(products);

        String outputJson = mapper.writeValueAsString(listChecker);

        this.mockMvc.perform(get("/products/inventory"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    void getProductById() throws Exception{
        ProductViewModel product = new ProductViewModel(1,"Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        String outputJson = mapper.writeValueAsString(product);

        when(serviceLayer.getProductById(1)).thenReturn(product);

        this.mockMvc.perform(get("/products/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    void getProductByInvoiceId() throws Exception{
        ProductViewModel product = new ProductViewModel(1,"Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        List<ProductViewModel> products = new ArrayList<>();
        products.add(product);
        String outputJson = mapper.writeValueAsString(products);

        when(serviceLayer.getProductByInvoiceId(1)).thenReturn(products);

        this.mockMvc.perform(get("/products/invoice/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    void getLevelUpPointsByCustomerId() throws Exception{
        CustomerViewModel customer = new CustomerViewModel(1,"John", "Doe", "123 Abc Street", "Atlanta", "11111", "john@doe.com", "555-5555");
        LevelUpViewModel levelUp = new LevelUpViewModel(1,50, LocalDate.of(2019,7,22));
        levelUp.setLevelUpId(1);

        int expectedOutput = 50;

        when(serviceLayer.getLevelUpPointsByCustomerId(customer.getCustomerId())).thenReturn(levelUp.getPoints());

        this.mockMvc.perform(get("/levelup/customer/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}