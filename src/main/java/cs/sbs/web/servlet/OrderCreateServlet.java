package cs.sbs.web.servlet;

import cs.sbs.web.model.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class OrderCreateServlet extends HttpServlet {
    public static final List<Order> orderList = new ArrayList<>();
    private static int nextOrderId = 1001;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String customer = request.getParameter("customer");
        String food = request.getParameter("food");
        String qtyStr = request.getParameter("quantity");

        if(customer==null||customer.isBlank()||food==null||food.isBlank()){
            out.println("Error: customer and food cannot be empty");
            return;
        }
        int quantity;
        try{
            quantity = Integer.parseInt(qtyStr);
            if(quantity<=0){
                out.println("Error: quantity must be greater than 0");
                return;
            }
        }catch (NumberFormatException e){
            out.println("Error: quantity must be a valid number");
            return;
        }
        Order o = new Order(nextOrderId++,customer,food,quantity);
        orderList.add(o);
        out.println("Order Created: "+o.getOrderId());
        out.println("\nOrder #"+o.getOrderId()+" (Click to view details)");
        out.println("http://localhost:8080/order/"+o.getOrderId());
    }
}