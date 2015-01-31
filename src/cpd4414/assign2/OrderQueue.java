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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Madhuri Shah
 */
public class OrderQueue {
    Queue<Order> orderQueue = new ArrayDeque<>();
    List<Order> orderList=new ArrayList();
    
   public void add(Order order) {
        if(order.getCustomerId().isEmpty() && order.getCustomerName().isEmpty())
            throw new NoCustomerException();
        if(order.getListOfPurchases().isEmpty())
            throw new NoPurchaseException();
        orderQueue.add(order);
        order.setTimeReceived(new Date());
    }
    
    public Order requestOrder(){
        if(orderQueue.isEmpty())
            return null;
        
        return orderQueue.peek();
    }
    
    public void processOrder(Order order){
        if(order.getTimeReceived()!= null){
            order.setTimeProcessed(new Date());
            orderQueue.remove(order);
            orderList.add(order);
            
        }
        else
            throw new noTimeReceivedException();
        
    }
    
     public void fulfillOrder(Order order){
         if(order.getTimeProcessed()!=null && order.getTimeReceived()!=null){
             order.setTimeFulfilled(new Date());
         }
         else if(order.getTimeProcessed()==null){
             throw new noTimeProcessedException();
         }
     }
         public class NoCustomerException extends RuntimeException{
     }
         
     public class NoPurchaseException extends RuntimeException{
     }
     public class noTimeReceivedException extends RuntimeException{
     }
      public class noTimeProcessedException extends RuntimeException{
     }
}
