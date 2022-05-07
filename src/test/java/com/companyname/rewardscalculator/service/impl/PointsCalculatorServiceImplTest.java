package com.companyname.rewardscalculator.service.impl;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.companyname.rewardscalculator.errorhandling.CustomerNotFound;
import com.companyname.rewardscalculator.errorhandling.PurchasesNotFound;
import com.companyname.rewardscalculator.repository.CustomerRepository;
import com.companyname.rewardscalculator.repository.PointsRepository;
import com.companyname.rewardscalculator.repository.PurchasesRepository;
import com.companyname.rewardscalculator.service.model.Customer;
import com.companyname.rewardscalculator.service.model.Points;
import com.companyname.rewardscalculator.service.model.Purchase;


@ExtendWith(MockitoExtension.class)
public class PointsCalculatorServiceImplTest {
   
    
    @Test
    public void testGetPointsForValidInput_ReturnsValidPoints(){
    	 long customerId = 1L;
    	 String firstName = "test";
    	 String lastName = "test";
    	 PointsCalculatorServiceImpl pointsCalculatorService = new PointsCalculatorServiceImpl();
    	 Customer customer = new Customer();
    	 customer.setFirstName(firstName);
    	 customer.setLastName(lastName);
    	 customer.setId(customerId);
         Purchase purchase = new Purchase();
         purchase.setAmount(new BigDecimal(122.44));
         purchase.setId(1L);
         purchase.setTime(LocalDate.of(2022, 01, 03));
         List<Purchase> purchases = new ArrayList<Purchase>();
         purchases.add(purchase);
         Points point1 = new Points();
         point1.setId(1);
         point1.setMinValue(new BigDecimal(50));
         point1.setMaxValue(new BigDecimal(100));
         point1.setPoints(1);
         Points point2 = new Points();
         point2.setId(2);
         point2.setMinValue(new BigDecimal(100));
         point2.setPoints(2);
         List<Points> points = new ArrayList<Points>();
         points.add(point1);
         points.add(point2);
         LocalDate date= LocalDate.now().minusMonths(3);
         PurchasesRepository purchasesRepository = mock(PurchasesRepository.class);
         when(purchasesRepository.findAllPurchasesByTime(date, customerId)).thenReturn(purchases);
         PointsRepository pointsRepository = mock(PointsRepository.class);
         when(pointsRepository.findAll()).thenReturn(points);
         CustomerRepository customerRepository = mock(CustomerRepository.class);
         when(customerRepository.getById(customerId)).thenReturn(customer);
         pointsCalculatorService.setCustomerRepository(customerRepository);
         pointsCalculatorService.setPointsRepository(pointsRepository);
     	 pointsCalculatorService.setPurchasesRepository(purchasesRepository);
         assertEquals(94,pointsCalculatorService.getPoints(customerId).getPoints());
    }
    
  
    @Test
    public void testGetPointsWithoutValidInput_ThrowCustomerNotFoundException(){
    	 long customerId = 1L;
    	 String firstName = "test";
    	 String lastName = "test";
    	 PointsCalculatorServiceImpl pointsCalculatorService = new PointsCalculatorServiceImpl();
    	 Customer customer = new Customer();
    	 customer.setFirstName(firstName);
    	 customer.setLastName(lastName);
    	 customer.setId(customerId);
         List<Purchase> purchases = new ArrayList<Purchase>();
         Points point1 = new Points();
         point1.setId(1);
         point1.setMinValue(new BigDecimal(50));
         point1.setMaxValue(new BigDecimal(100));
         point1.setPoints(1);
         Points point2 = new Points();
         point2.setId(2);
         point2.setMinValue(new BigDecimal(100));
         point2.setPoints(2);
         List<Points> points = new ArrayList<Points>();
         points.add(point1);
         points.add(point2);
         LocalDate date= LocalDate.now().minusMonths(3);
         PurchasesRepository purchasesRepository = mock(PurchasesRepository.class);
         when(purchasesRepository.findAllPurchasesByTime(date, customerId)).thenReturn(purchases);
         PointsRepository pointsRepository = mock(PointsRepository.class);
         when(pointsRepository.findAll()).thenReturn(points);
         CustomerRepository customerRepository = mock(CustomerRepository.class);
         when(customerRepository.getById(customerId)).thenReturn(customer);
         pointsCalculatorService.setCustomerRepository(customerRepository);
         pointsCalculatorService.setPointsRepository(pointsRepository);
     	 pointsCalculatorService.setPurchasesRepository(purchasesRepository);
         assertThrows(PurchasesNotFound.class, () -> {
        	 pointsCalculatorService.getPoints(customerId);
     	});
    }
    
    


	@Test
    public void testGetPointsForValidInput_ForTestingRounding(){
    	 long customerId = 1L;
    	 String firstName = "test";
    	 String lastName = "test";
    	 PointsCalculatorServiceImpl pointsCalculatorService = new PointsCalculatorServiceImpl();
    	 Customer customer = new Customer();
    	 customer.setFirstName(firstName);
    	 customer.setLastName(lastName);
    	 customer.setId(customerId);
         Purchase purchase = new Purchase();
         purchase.setAmount(new BigDecimal(102.56));
         purchase.setId(1L);
         purchase.setTime(LocalDate.of(2022, 01, 03));
         List<Purchase> purchases = new ArrayList<Purchase>();
         purchases.add(purchase);
         Points point1 = new Points();
         point1.setId(1);
         point1.setMinValue(new BigDecimal(50));
         point1.setMaxValue(new BigDecimal(100));
         point1.setPoints(1);
         Points point2 = new Points();
         point2.setId(2);
         point2.setMinValue(new BigDecimal(100));
         point2.setPoints(2);
         List<Points> points = new ArrayList<Points>();
         points.add(point1);
         points.add(point2);
         LocalDate date= LocalDate.now().minusMonths(3);
         PurchasesRepository purchasesRepository = mock(PurchasesRepository.class);
         when(purchasesRepository.findAllPurchasesByTime(date, customerId)).thenReturn(purchases);
         PointsRepository pointsRepository = mock(PointsRepository.class);
         when(pointsRepository.findAll()).thenReturn(points);
         CustomerRepository customerRepository = mock(CustomerRepository.class);
         when(customerRepository.getById(customerId)).thenReturn(customer);
         pointsCalculatorService.setCustomerRepository(customerRepository);
         pointsCalculatorService.setPointsRepository(pointsRepository);
     	 pointsCalculatorService.setPurchasesRepository(purchasesRepository);
         assertEquals(56,pointsCalculatorService.getPoints(customerId).getPoints());
    }
    
    @Test
    public void testGetPointsForValidInput_ForTestingRounding1(){
    	 long customerId = 1L;
    	 String firstName = "test";
    	 String lastName = "test";
    	 PointsCalculatorServiceImpl pointsCalculatorService = new PointsCalculatorServiceImpl();
    	 Customer customer = new Customer();
    	 customer.setFirstName(firstName);
    	 customer.setLastName(lastName);
    	 customer.setId(customerId);
         Purchase purchase = new Purchase();
         purchase.setAmount(new BigDecimal(102.43));
         purchase.setId(1L);
         purchase.setTime(LocalDate.of(2022, 01, 03));
         List<Purchase> purchases = new ArrayList<Purchase>();
         purchases.add(purchase);
         Points point1 = new Points();
         point1.setId(1);
         point1.setMinValue(new BigDecimal(50));
         point1.setMaxValue(new BigDecimal(100));
         point1.setPoints(1);
         Points point2 = new Points();
         point2.setId(2);
         point2.setMinValue(new BigDecimal(100));
         point2.setPoints(2);
         List<Points> points = new ArrayList<Points>();
         points.add(point1);
         points.add(point2);
         LocalDate date= LocalDate.now().minusMonths(3);
         PurchasesRepository purchasesRepository = mock(PurchasesRepository.class);
         when(purchasesRepository.findAllPurchasesByTime(date, customerId)).thenReturn(purchases);
         PointsRepository pointsRepository = mock(PointsRepository.class);
         when(pointsRepository.findAll()).thenReturn(points);
         CustomerRepository customerRepository = mock(CustomerRepository.class);
         when(customerRepository.getById(customerId)).thenReturn(customer);
         pointsCalculatorService.setCustomerRepository(customerRepository);
         pointsCalculatorService.setPointsRepository(pointsRepository);
     	 pointsCalculatorService.setPurchasesRepository(purchasesRepository);
         assertEquals(54,pointsCalculatorService.getPoints(customerId).getPoints());
    }
    
    @Test
    public void testGetPointsForValidInput_ForTestingPurchaseBelow50(){
    	 long customerId = 1L;
    	 String firstName = "test";
    	 String lastName = "test";
    	 PointsCalculatorServiceImpl pointsCalculatorService = new PointsCalculatorServiceImpl();
    	 Customer customer = new Customer();
    	 customer.setFirstName(firstName);
    	 customer.setLastName(lastName);
    	 customer.setId(customerId);
         Purchase purchase = new Purchase();
         purchase.setAmount(new BigDecimal(48));
         purchase.setId(1L);
         purchase.setTime(LocalDate.of(2022, 01, 03));
         List<Purchase> purchases = new ArrayList<Purchase>();
         purchases.add(purchase);
         Points point1 = new Points();
         point1.setId(1);
         point1.setMinValue(new BigDecimal(50));
         point1.setMaxValue(new BigDecimal(100));
         point1.setPoints(1);
         Points point2 = new Points();
         point2.setId(2);
         point2.setMinValue(new BigDecimal(100));
         point2.setPoints(2);
         List<Points> points = new ArrayList<Points>();
         points.add(point1);
         points.add(point2);
         LocalDate date= LocalDate.now().minusMonths(3);
         PurchasesRepository purchasesRepository = mock(PurchasesRepository.class);
         when(purchasesRepository.findAllPurchasesByTime(date, customerId)).thenReturn(purchases);
         PointsRepository pointsRepository = mock(PointsRepository.class);
         when(pointsRepository.findAll()).thenReturn(points);
         CustomerRepository customerRepository = mock(CustomerRepository.class);
         when(customerRepository.getById(customerId)).thenReturn(customer);
         pointsCalculatorService.setCustomerRepository(customerRepository);
         pointsCalculatorService.setPointsRepository(pointsRepository);
     	 pointsCalculatorService.setPurchasesRepository(purchasesRepository);
         assertEquals(0,pointsCalculatorService.getPoints(customerId).getPoints());
    }
    
    @Test
    public void testGetPointsForValidInput_ForTestingPurchaseAbove50(){
    	 long customerId = 1L;
    	 String firstName = "test";
    	 String lastName = "test";
    	 PointsCalculatorServiceImpl pointsCalculatorService = new PointsCalculatorServiceImpl();
    	 Customer customer = new Customer();
    	 customer.setFirstName(firstName);
    	 customer.setLastName(lastName);
    	 customer.setId(customerId);
         Purchase purchase = new Purchase();
         purchase.setAmount(new BigDecimal(50.56));
         purchase.setId(1L);
         purchase.setTime(LocalDate.of(2022, 01, 03));
         List<Purchase> purchases = new ArrayList<Purchase>();
         purchases.add(purchase);
         Points point1 = new Points();
         point1.setId(1);
         point1.setMinValue(new BigDecimal(50));
         point1.setMaxValue(new BigDecimal(100));
         point1.setPoints(1);
         Points point2 = new Points();
         point2.setId(2);
         point2.setMinValue(new BigDecimal(100));
         point2.setPoints(2);
         List<Points> points = new ArrayList<Points>();
         points.add(point1);
         points.add(point2);
         LocalDate date= LocalDate.now().minusMonths(3);
         PurchasesRepository purchasesRepository = mock(PurchasesRepository.class);
         when(purchasesRepository.findAllPurchasesByTime(date, customerId)).thenReturn(purchases);
         PointsRepository pointsRepository = mock(PointsRepository.class);
         when(pointsRepository.findAll()).thenReturn(points);
         CustomerRepository customerRepository = mock(CustomerRepository.class);
         when(customerRepository.getById(customerId)).thenReturn(customer);
         pointsCalculatorService.setCustomerRepository(customerRepository);
         pointsCalculatorService.setPointsRepository(pointsRepository);
     	 pointsCalculatorService.setPurchasesRepository(purchasesRepository);
         assertEquals(1,pointsCalculatorService.getPoints(customerId).getPoints());
    }
    
    @Test
    public void testGetPointsForValidInput_ForTestingPurchaseBelow100(){
    	 long customerId = 1L;
    	 String firstName = "test";
    	 String lastName = "test";
    	 PointsCalculatorServiceImpl pointsCalculatorService = new PointsCalculatorServiceImpl();
    	 Customer customer = new Customer();
    	 customer.setFirstName(firstName);
    	 customer.setLastName(lastName);
    	 customer.setId(customerId);
         Purchase purchase = new Purchase();
         purchase.setAmount(new BigDecimal(99.96));
         purchase.setId(1L);
         purchase.setTime(LocalDate.of(2022, 01, 03));
         List<Purchase> purchases = new ArrayList<Purchase>();
         purchases.add(purchase);
         Points point1 = new Points();
         point1.setId(1);
         point1.setMinValue(new BigDecimal(50));
         point1.setMaxValue(new BigDecimal(100));
         point1.setPoints(1);
         Points point2 = new Points();
         point2.setId(2);
         point2.setMinValue(new BigDecimal(100));
         point2.setPoints(2);
         List<Points> points = new ArrayList<Points>();
         points.add(point1);
         points.add(point2);
         LocalDate date= LocalDate.now().minusMonths(3);
         PurchasesRepository purchasesRepository = mock(PurchasesRepository.class);
         when(purchasesRepository.findAllPurchasesByTime(date, customerId)).thenReturn(purchases);
         PointsRepository pointsRepository = mock(PointsRepository.class);
         when(pointsRepository.findAll()).thenReturn(points);
         CustomerRepository customerRepository = mock(CustomerRepository.class);
         when(customerRepository.getById(customerId)).thenReturn(customer);
         pointsCalculatorService.setCustomerRepository(customerRepository);
         pointsCalculatorService.setPointsRepository(pointsRepository);
     	 pointsCalculatorService.setPurchasesRepository(purchasesRepository);
         assertEquals(50,pointsCalculatorService.getPoints(customerId).getPoints());
    }
    
    @Test
    public void testGetPointsForValidInput_ForTestingMultiplePurchases(){
    	 long customerId = 1L;
    	 String firstName = "test";
    	 String lastName = "test";
    	 PointsCalculatorServiceImpl pointsCalculatorService = new PointsCalculatorServiceImpl();
    	 Customer customer = new Customer();
    	 customer.setFirstName(firstName);
    	 customer.setLastName(lastName);
    	 customer.setId(customerId);
         Purchase purchase = new Purchase();
         purchase.setAmount(new BigDecimal(99.96));
         purchase.setId(1L);
         purchase.setTime(LocalDate.of(2022, 01, 03));
         Purchase purchase1 = new Purchase();
         purchase1.setAmount(new BigDecimal(99.96));
         purchase1.setId(2L);
         purchase1.setTime(LocalDate.of(2022, 01, 03));
         List<Purchase> purchases = new ArrayList<Purchase>();
         purchases.add(purchase);
         purchases.add(purchase1);
         Points point1 = new Points();
         point1.setId(1);
         point1.setMinValue(new BigDecimal(50));
         point1.setMaxValue(new BigDecimal(100));
         point1.setPoints(1);
         Points point2 = new Points();
         point2.setId(2);
         point2.setMinValue(new BigDecimal(100));
         point2.setPoints(2);
         List<Points> points = new ArrayList<Points>();
         points.add(point1);
         points.add(point2);
         LocalDate date= LocalDate.now().minusMonths(3);
         PurchasesRepository purchasesRepository = mock(PurchasesRepository.class);
         when(purchasesRepository.findAllPurchasesByTime(date, customerId)).thenReturn(purchases);
         PointsRepository pointsRepository = mock(PointsRepository.class);
         when(pointsRepository.findAll()).thenReturn(points);
         CustomerRepository customerRepository = mock(CustomerRepository.class);
         when(customerRepository.getById(customerId)).thenReturn(customer);
         pointsCalculatorService.setCustomerRepository(customerRepository);
         pointsCalculatorService.setPointsRepository(pointsRepository);
     	 pointsCalculatorService.setPurchasesRepository(purchasesRepository);
         assertEquals(100,pointsCalculatorService.getPoints(customerId).getPoints());
    }
    
    @Test
    public void testGetPointsForValidInput_ForTestingWhenMaxValueNull(){
    	 long customerId = 1L;
    	 String firstName = "test";
    	 String lastName = "test";
    	 PointsCalculatorServiceImpl pointsCalculatorService = new PointsCalculatorServiceImpl();
    	 Customer customer = new Customer();
    	 customer.setFirstName(firstName);
    	 customer.setLastName(lastName);
    	 customer.setId(customerId);
         Purchase purchase = new Purchase();
         purchase.setAmount(new BigDecimal(175));
         purchase.setId(1L);
         purchase.setTime(LocalDate.of(2022, 01, 03));
         List<Purchase> purchases = new ArrayList<Purchase>();
         purchases.add(purchase);
         Points point1 = new Points();
         point1.setId(1);
         point1.setMinValue(new BigDecimal(50));
         point1.setMaxValue(new BigDecimal(100));
         point1.setPoints(1);
         Points point2 = new Points();
         point2.setId(2);
         point2.setMinValue(new BigDecimal(100));
         point2.setPoints(2);
         List<Points> points = new ArrayList<Points>();
         points.add(point1);
         points.add(point2);
         LocalDate date= LocalDate.now().minusMonths(3);
         PurchasesRepository purchasesRepository = mock(PurchasesRepository.class);
         when(purchasesRepository.findAllPurchasesByTime(date, customerId)).thenReturn(purchases);
         PointsRepository pointsRepository = mock(PointsRepository.class);
         when(pointsRepository.findAll()).thenReturn(points);
         CustomerRepository customerRepository = mock(CustomerRepository.class);
         when(customerRepository.getById(customerId)).thenReturn(customer);
         pointsCalculatorService.setCustomerRepository(customerRepository);
         pointsCalculatorService.setPointsRepository(pointsRepository);
     	 pointsCalculatorService.setPurchasesRepository(purchasesRepository);
         assertEquals(200,pointsCalculatorService.getPoints(customerId).getPoints());
    }
    
    @Test
    public void testGetPointsWithoutValidInput_ThrowCustomerNotFound(){
    	 long customerId = 1L;
    	 PointsCalculatorServiceImpl pointsCalculatorService = new PointsCalculatorServiceImpl();
         CustomerRepository customerRepository = mock(CustomerRepository.class);
         when(customerRepository.getById(customerId)).thenReturn(null);
         pointsCalculatorService.setCustomerRepository(customerRepository);
         assertThrows(CustomerNotFound.class, () -> {
        	 pointsCalculatorService.getPoints(customerId).getPoints();
     	});
    }

}
