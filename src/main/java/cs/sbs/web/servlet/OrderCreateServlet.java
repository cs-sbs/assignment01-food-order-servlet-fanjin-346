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
    // 公共静态订单列表，确保OrderDetailServlet可正常访问
    public static final List<Order> orderList = new ArrayList<>();
    private static int nextOrderId = 1001;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 1. 获取表单参数
        String customer = request.getParameter("customer");
        String food = request.getParameter("food");
        String quantityStr = request.getParameter("quantity");

        // 2. 非空校验
        if (customer == null || customer.isBlank() || food == null || food.isBlank()) {
            out.println("Error: customer and food cannot be empty");
            return;
        }

        // 3. 数量合法性校验
        int quantity;
        try {
            quantity = Integer.parseInt(quantityStr);
            if (quantity <= 0) {
                out.println("Error: quantity must be greater than 0");
                return;
            }
        } catch (NumberFormatException e) {
            out.println("Error: quantity must be a valid number");
            return;
        }

        // 4. 创建订单并存储
        Order order = new Order(nextOrderId++, customer, food, quantity);
        orderList.add(order);

        // 5. 返回结果（符合作业要求）
        out.println("Order Created: " + order.getOrderId());
        out.println("\nOrder #" + order.getOrderId() + " (Click to view details)");
        out.println("http://localhost:8080/order/" + order.getOrderId());
    }
}