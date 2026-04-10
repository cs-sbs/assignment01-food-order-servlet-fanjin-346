package cs.sbs.web.servlet;

import cs.sbs.web.model.MenuItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MenuListServlet extends HttpServlet {
    private static final List<MenuItem> menuList = new ArrayList<>();

    static {
        menuList.add(new MenuItem(1, "Fried Rice", 8));
        menuList.add(new MenuItem(2, "Fried Noodles", 9));
        menuList.add(new MenuItem(3, "Burger", 10));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");

        out.println("Menu List:\n");

        if (name == null || name.isBlank()) {
            for (MenuItem item : menuList) {
                out.printf("%d. %s - $%.0f%n", item.getId(), item.getName(), item.getPrice());
            }
        } else {
            for (MenuItem item : menuList) {
                if (item.getName().contains(name)) {
                    out.printf("%d. %s - $%.0f%n", item.getId(), item.getName(), item.getPrice());
                }
            }
        }
    }
}
