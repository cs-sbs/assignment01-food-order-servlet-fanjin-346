package cs.sbs.web.servlet;

import cs.sbs.web.model.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class OrderDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String path = request.getPathInfo();
        if (path == null || path.length() <= 1) {
            out.println("Error: order ID not found");
            return;
        }

        int orderId;
        try {
            orderId = Integer.parseInt(path.substring(1));
        } catch (NumberFormatException e) {
            out.println("Error: invalid order ID");
            return;
        }

        Order target = null;
        for (Order o : OrderCreateServlet.orderList) {
            if (o.getOrderId() == orderId) {
                target = o;
                break;
            }
        }

        if (target == null) {
            out.println("Error: order not found");
            return;
        }

        out.println("Order Detail\n");
        out.println("Order ID: " + target.getOrderId());
        out.println("Customer: " + target.getCustomer());
        out.println("Food: " + target.getFood());
        out.println("Quantity: " + target.getQuantity());
    }
}