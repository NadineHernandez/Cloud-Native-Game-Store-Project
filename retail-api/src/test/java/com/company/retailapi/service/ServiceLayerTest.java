package com.company.retailapi.service;

import com.company.retailapi.models.*;
import com.company.retailapi.util.feign.*;
import com.company.retailapi.viewmodel.RetailInvoiceViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ServiceLayerTest {
    private CustomerClient customerClient;
    private InventoryClient inventoryClient;
    private InvoiceClient invoiceClient;
    private LevelUpClient levelUpClient;
    private ProductClient productClient;
    private RabbitTemplate rabbitTemplate;
    private ServiceLayer serviceLayer;

    public static final String EXCHANGE = "levelup-exchange";
    public static final String ROUTING_KEY = "levelup.create";

    void setUpCustomerClientMock(){
        customerClient = mock(CustomerClient.class);
        CustomerViewModel customer = new CustomerViewModel(101,"John", "Doe", "123 Abc Street", "Atlanta", "11111", "john@doe.com", "555-5555");
        CustomerViewModel customer2 = new CustomerViewModel("John", "Doe", "123 Abc Street", "Atlanta", "11111", "john@doe.com", "555-5555");

        List<CustomerViewModel> customers = new ArrayList<>();
        customers.add(customer);

        doReturn(customer).when(customerClient).addCustomer(customer2);
        doReturn(customer).when(customerClient).getCustomer(101);
        doReturn(customers).when(customerClient).getAllCustomers();
    }

    void setUpInventoryClientMock(){
        inventoryClient = mock(InventoryClient.class);
        InventoryViewModel inventory = new InventoryViewModel(1, 10);
        inventory.setInventoryId(1);
        InventoryViewModel inventory1 = new InventoryViewModel(1, 10);

        List<InventoryViewModel> inventories = new ArrayList<>();
        inventories.add(inventory);

        doReturn(inventory).when(inventoryClient).addInventory(inventory1);
        doReturn(inventory).when(inventoryClient).getInventory(1);
        doReturn(inventories).when(inventoryClient).getAllInventories();
    }

    void setUpInvoiceClientMock(){
        invoiceClient = mock(InvoiceClient.class);
        InvoiceViewModel invoice = new InvoiceViewModel(1, LocalDate.of(2019, 7, 22));
        invoice.setInvoiceId(1);
        InvoiceViewModel invoice1 = new InvoiceViewModel(1, LocalDate.of(2019, 7, 22));

        List<InvoiceViewModel> invoices = new ArrayList<>();
        invoices.add(invoice);

        doReturn(invoice).when(invoiceClient).addInvoice(invoice1);
        doReturn(invoice).when(invoiceClient).getInvoice(1);
        doReturn(invoices).when(invoiceClient).getAllInvoices();
        doReturn(invoices).when(invoiceClient).getInvoicesByCustomerId(1);

        InvoiceItemViewModel invoiceItem = new InvoiceItemViewModel(1, 1, 10, new BigDecimal("10.00"));
        invoiceItem.setInvoiceItemId(1);
        InvoiceItemViewModel invoiceItem1 = new InvoiceItemViewModel(1, 1, 10, new BigDecimal("10.00"));

        List<InvoiceItemViewModel> invoiceItems = new ArrayList<>();
        invoiceItems.add(invoiceItem);

        doReturn(invoiceItem).when(invoiceClient).addInvoiceItem(invoiceItem1);
        doReturn(invoiceItem).when(invoiceClient).getInvoiceItem(1);
        doReturn(invoiceItems).when(invoiceClient).getAllInvoiceItems();
        doReturn(invoiceItems).when(invoiceClient).getInvoiceItemsByInvoiceId(1);
    }

    void setUpLevelUpClientMock(){
        levelUpClient = mock(LevelUpClient.class);
        LevelUpViewModel levelUp = new LevelUpViewModel(1,50, LocalDate.of(2019,7,22));
        levelUp.setLevelUpId(1);
        LevelUpViewModel levelUp1 = new LevelUpViewModel(1,50, LocalDate.of(2019,7,22));

        List<LevelUpViewModel> levelUps = new ArrayList<>();
        levelUps.add(levelUp);

        doReturn(levelUp).when(levelUpClient).addLevelUp(levelUp1);
        doReturn(levelUp).when(levelUpClient).getLevelUp(1);
        doReturn(levelUps).when(levelUpClient).getAllLevelUps();
        doReturn(levelUp).when(levelUpClient).getLevelUpByCustomerId(1);
    }

    void setUpProductClient(){
        productClient = mock(ProductClient.class);
        ProductViewModel product = new ProductViewModel(1,"Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        ProductViewModel product2 = new ProductViewModel("Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));

        List<ProductViewModel> products = new ArrayList<>();
        products.add(product);

        doReturn(product).when(productClient).addProduct(product2);
        doReturn(product).when(productClient).getProduct(1);
        doReturn(products).when(productClient).getAllProducts();
    }

    void setUpRabbitTemplateMock(){
        rabbitTemplate = mock(RabbitTemplate.class);
    }

    @BeforeEach
    void setUp() {
        setUpCustomerClientMock();
        setUpInventoryClientMock();
        setUpInvoiceClientMock();
        setUpLevelUpClientMock();
        setUpProductClient();
        setUpRabbitTemplateMock();
        serviceLayer = new ServiceLayer(customerClient, inventoryClient, invoiceClient,
                levelUpClient, productClient, rabbitTemplate);
    }

    @Test
    void submitInvoice() {
        List<InvoiceItemViewModel> items = new ArrayList<>();
        InvoiceItemViewModel item =  new InvoiceItemViewModel(1, 1, 10, new BigDecimal("10.00"));
        item = invoiceClient.addInvoiceItem(item);
        items.add(item);

        RetailInvoiceViewModel rivm = new RetailInvoiceViewModel(1, LocalDate.of(2019, 7, 22), items);
        rivm = serviceLayer.submitInvoice(rivm);

        assertEquals(rivm, serviceLayer.getInvoiceById(rivm.getInvoiceId()));
    }

    @Test
    void getInvoiceById() {
        List<InvoiceItemViewModel> items = new ArrayList<>();
        InvoiceItemViewModel item =  new InvoiceItemViewModel(1, 1, 10, new BigDecimal("10.00"));
        item = invoiceClient.addInvoiceItem(item);
        items.add(item);

        RetailInvoiceViewModel rivm = new RetailInvoiceViewModel(1, LocalDate.of(2019, 7, 22), items);
        rivm = serviceLayer.submitInvoice(rivm);

        assertEquals(rivm, serviceLayer.getInvoiceById(rivm.getInvoiceId()));
    }

    @Test
    void getAllInvoices() {
        List<InvoiceItemViewModel> items = new ArrayList<>();
        InvoiceItemViewModel item =  new InvoiceItemViewModel(1, 1, 10, new BigDecimal("10.00"));
        item = invoiceClient.addInvoiceItem(item);
        items.add(item);

        RetailInvoiceViewModel rivm = new RetailInvoiceViewModel(1, LocalDate.of(2019, 7, 22), items);
        serviceLayer.submitInvoice(rivm);

        assertEquals(1, serviceLayer.getAllInvoices().size());
    }

    @Test
    void getInvoicesByCustomerId() {
        List<InvoiceItemViewModel> items = new ArrayList<>();
        InvoiceItemViewModel item =  new InvoiceItemViewModel(1, 1, 10, new BigDecimal("10.00"));
        item = invoiceClient.addInvoiceItem(item);
        items.add(item);

        RetailInvoiceViewModel rivm = new RetailInvoiceViewModel(1, LocalDate.of(2019, 7, 22), items);
        rivm = serviceLayer.submitInvoice(rivm);

        assertEquals(1, serviceLayer.getInvoicesByCustomerId(1).size());
    }

    @Test
    void getProductsInInventory() {
        ProductViewModel product2 = new ProductViewModel("Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        product2 = productClient.addProduct(product2);

        InventoryViewModel inventory1 = new InventoryViewModel(1, 10);
        inventory1 = inventoryClient.addInventory(inventory1);

        assertEquals(1, serviceLayer.getProductsInInventory().size());
    }

    @Test
    void getProductById() {
        ProductViewModel product2 = new ProductViewModel("Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        product2 = productClient.addProduct(product2);

        assertEquals(product2, serviceLayer.getProductById(product2.getProductId()));
    }

    @Test
    void getProductByInvoiceId() {
        ProductViewModel product2 = new ProductViewModel("Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        product2 = productClient.addProduct(product2);

        InventoryViewModel inventory1 = new InventoryViewModel(1, 10);
        inventory1 = inventoryClient.addInventory(inventory1);

        List<InvoiceItemViewModel> items = new ArrayList<>();
        InvoiceItemViewModel item =  new InvoiceItemViewModel(1, 1, 10, new BigDecimal("10.00"));
        item = invoiceClient.addInvoiceItem(item);
        items.add(item);
        RetailInvoiceViewModel rivm = new RetailInvoiceViewModel(1, LocalDate.of(2019, 7, 22), items);
        rivm = serviceLayer.submitInvoice(rivm);

        assertEquals(1, serviceLayer.getProductByInvoiceId(rivm.getInvoiceId()).size());
    }

    @Test
    void getLevelUpPointsByCustomerId() {
        LevelUpViewModel levelUp = new LevelUpViewModel(1,50, LocalDate.of(2019,7,22));
        levelUp = levelUpClient.addLevelUp(levelUp);

        assertEquals(levelUp.getPoints(), serviceLayer.getLevelUpPointsByCustomerId(levelUp.getCustomerId()));
    }
}