/*
 * Copyright 2015 Len Payne <len.payne@lambtoncollege.ca>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cpd4414.assign2;

import cpd4414.assign2.OrderQueue;
import cpd4414.assign2.Purchase;
import cpd4414.assign2.Order;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Madhuri Shah
 */
public class OrderQueueTest {
    
    public OrderQueueTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testWhenCustomerExistsAndPurchasesExistThenTimeReceivedIsNow() {
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase(1, 450));
        order.addPurchase(new Purchase(2, 250));
        orderQueue.add(order);
        
        long expResult = new Date().getTime();
        long result = order.getTimeReceived().getTime();
        assertTrue(Math.abs(result - expResult) < 1000);
    }
    
     @Test
    public void testWhenCustomerIdAndNameNotExistThenthrowException(){
        boolean result=false;
         OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("","");
        order.addPurchase(new Purchase(1, 450));
        order.addPurchase(new Purchase(2, 250));
        try{
            orderQueue.add(order);     
        }
        catch(Exception e){
            result=true;
        }
        assertTrue(result);
    }
      @Test
    public void testWhenPurchaseListNotExistThenthrowException(){
        boolean result=false;
         OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00001","ABC Construction");
        try{
            orderQueue.add(order);     
        }
        catch(Exception e){
            result=true;
        }
        assertTrue(result);
    }
    @Test
    public void testWhenRequestOrderAndReturnOrderWithEarliestTime(){
       OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase(1, 450));
        order.addPurchase(new Purchase(2, 250));
        orderQueue.add(order); 
        Order result1=orderQueue.requestOrder();
        Order result2=orderQueue.orderQueue.peek();
        assertEquals(result1,result2);
        
    }
    
    @Test
    public void testWhenRequestOrderAndNoOrderThenReturnNull(){
          OrderQueue orderQueue = new OrderQueue();
           Order result1=orderQueue.requestOrder();
           assertEquals(result1,null);
    }
    
      @Test
      public void testWhenOrderHasTimeReeceivedAndAllofPurchaseAreInStockThenTimeProcessedIsNow(){
          OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase(1, 450));
        order.addPurchase(new Purchase(2, 250));
        order.setTimeReceived(new Date());
        orderQueue.add(order);
        orderQueue.processOrder(order);
        Date expresult=new Date();
        assertEquals(expresult,order.getTimeProcessed());
          
      }
    
      @Test
    public void testWhenNoTimeReceivedThenThrowException(){
        boolean result; 
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase(1, 450));
        order.addPurchase(new Purchase(2, 250));
        orderQueue.add(order); 
        try{
            orderQueue.processOrder(order);
        }
        catch(Exception e){
            result=true;
        }
    }
     @Test
      public void testWhenOrderHasTimeProcessedAndAllofPurchaseAreInStockThenTimeFulfilledIsNow(){
          OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase(1, 450));
        order.addPurchase(new Purchase(2, 250));
        order.setTimeProcessed(new Date());
        order.setTimeReceived(new Date());
        orderQueue.fulfillOrder(order);
        Date expresult=new Date();
        assertEquals(expresult,order.getTimeFulfilled());
          
      }
       @Test
    public void testWhenFulfillOrderAndTimeProcessedNotExistsThenThrowException(){
        boolean result; 
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase(1, 450));
        order.addPurchase(new Purchase(2, 250));
        orderQueue.add(order); 
        try{
            orderQueue.fulfillOrder(order);
        }
        catch(Exception e){
            result=true;
        }
    }
    @Test
    public void testWhenFulfillOrderAndTimeReceivedNotExistsThenThrowException(){
        boolean result; 
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase(1, 450));
        order.addPurchase(new Purchase(2, 250));
        orderQueue.add(order); 
        try{
            orderQueue.fulfillOrder(order);
        }
        catch(Exception e){
            result=true;
        }
    }
     @Test
    public void testWhenRequestForOrderAndOrdersDoesNotNotExistsThenReturnEmptyString(){ 
        OrderQueue orderQueue = new OrderQueue();
        String result=orderQueue.report();
        assertEquals(result,"");
    }
    
}


