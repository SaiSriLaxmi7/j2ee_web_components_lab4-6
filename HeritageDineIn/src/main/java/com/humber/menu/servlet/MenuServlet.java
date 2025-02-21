package com.humber.menu.servlet;

import com.google.gson.Gson;
import com.humber.menu.dao.MenuItemDAO;
import com.humber.menu.model.MenuItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MenuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MenuItemDAO menuItemDAO = new MenuItemDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<MenuItem> menuItems = menuItemDAO.getAllMenuItems(); 
        List<Map<String, Object>> menuItemList = new ArrayList<>();

        for (MenuItem menuItem : menuItems) {
            Map<String, Object> menuItemMap = new HashMap<>();
            menuItemMap.put("id", menuItem.getId());
            menuItemMap.put("name", menuItem.getName());
            menuItemMap.put("description", menuItem.getDescription());
            menuItemMap.put("price", menuItem.getPrice());

            if (menuItem.getCategory() != null) {
                menuItemMap.put("categoryId", menuItem.getCategory().getId());
                menuItemMap.put("categoryName", menuItem.getCategory().getName()); // ✅ Ensures categoryName is included
            } else {
                menuItemMap.put("categoryId", null);
                menuItemMap.put("categoryName", "Uncategorized"); // ✅ Handles missing category
            }

            menuItemList.add(menuItemMap);
        }

        String json = new Gson().toJson(menuItemList); 
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
}