package cs.sbs.web.servlet;

import cs.sbs.web.model.MenuItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MenuListServlet extends HttpServlet {
    private List<MenuItem> menuList;

    @Override
    public void init() {
        menuList = new ArrayList<>();
        menuList.add(new MenuItem(1, "Fried Rice", 8.0));
        menuList.add(new MenuItem(2, "Fried Noodles", 9.0));
        menuList.add(new MenuItem(3, "Burger", 10.0));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String keyword = request.getParameter("name");

        out.println("Menu List:\n");
        for (MenuItem item : menuList) {
            if (keyword == null || keyword.isBlank() || item.getName().contains(keyword)) {
                out.printf("%d. %s - $%.0f%n", item.getId(), item.getName(), item.getPrice());
            }
        }
    }
}