<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Restaurant Menu</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        h1 { color: #333; text-align: center; }
        h2 { color: #555; border-bottom: 2px solid #ddd; padding-bottom: 5px; }
        .menu-container { max-width: 600px; margin: 0 auto; }
        .menu-item { margin-bottom: 15px; padding: 10px; border: 1px solid #ddd; border-radius: 5px; }
        .menu-item strong { font-size: 18px; }
        .menu-item .price { color: #28a745; font-weight: bold; }
        .error { color: red; text-align: center; }
    </style>
</head>
<body>

    <h1>Restaurant Menu</h1>
    <div class="menu-container">
        <p id="loading-message">Loading menu items...</p>
        <div id="menu-content"></div>
    </div>

    <script>
        $(document).ready(function () {
            $.ajax({
                url: "menu", // Calls MenuServlet
                method: "GET",
                dataType: "json",
                success: function (data) {
                    $("#loading-message").hide();
                    let menuContent = "";
                    let categories = {};

                    // Group menu items by category
                    data.forEach(function(item) {
                        let category = item.categoryName || "Uncategorized";
                        if (!categories[category]) {
                            categories[category] = [];
                        }
                        categories[category].push(item);
                    });

                    // Generate HTML
                    for (let category in categories) {
                        menuContent += `<h2>${category}</h2>`;
                        categories[category].forEach(function(item) {
                            menuContent += `
                                <div class="menu-item">
                                    <strong>` + item.name + `</strong> - <span class="price">$` + parseFloat(item.price).toFixed(2) + `</span><br>
                                    <em>` + (item.description || "No description available") + `</em>
                                </div>
                            `;
                        });
                    }

                    $("#menu-content").html(menuContent);
                },
                error: function () {
                    $("#loading-message").hide();
                    $("#menu-content").html("<p class='error'>Error loading menu. Please try again later.</p>");
                }
            });
        });
    </script>

</body>
</html>