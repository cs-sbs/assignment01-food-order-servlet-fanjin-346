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
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String path = request.getPathInfo();

        // 解析订单 ID
        int orderId;
        try {
            orderId = Integer.parseInt(path.substring(1));
        } catch (Exception e) {
            out.println("Error: invalid order ID");
            return;
        }

        // 查询订单
        Order found = null;
        for (Order o : OrderCreateServlet.orderList) {
            if (o.getOrderId() == orderId) {
                found = o;
                break;
            }
        }

        if (found == null) {
            out.println("Error: order not found");
            return;
        }

        // 返回格式必须严格匹配
        out.println("Order Detail");
        out.println();
        out.println("Order ID: " + found.getOrderId());
        out.println("Customer: " + found.getCustomer());
        out.println("Food: " + found.getFood());
        out.println("Quantity: " + found.getQuantity());
    }
}