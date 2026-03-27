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
        // 路径参数为空校验
        if (path == null || path.length() <= 1) {
            out.println("Error: order ID not found");
            return;
        }

        // 解析订单ID
        int orderId;
        try {
            orderId = Integer.parseInt(path.substring(1));
        } catch (NumberFormatException e) {
            out.println("Error: invalid order ID");
            return;
        }

        // 查询订单
        Order targetOrder = null;
        for (Order order : OrderCreateServlet.orderList) {
            if (order.getOrderId() == orderId) {
                targetOrder = order;
                break;
            }
        }

        // 订单不存在处理
        if (targetOrder == null) {
            out.println("Error: order not found");
            return;
        }

        // 返回订单详情
        out.println("Order Detail\n");
        out.println("Order ID: " + targetOrder.getOrderId());
        out.println("Customer: " + targetOrder.getCustomer());
        out.println("Food: " + targetOrder.getFood());
        out.println("Quantity: " + targetOrder.getQuantity());
    }
}