package com.fahmi.crm2.testdb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
* No need to use web.xml at all when using @WebServlet annotation. Easy way to avoid manual mapping in web.xml.
* It replaces servlet and servlet-mapping tags.
* */
@WebServlet("/TestDbServlet")
public class TestDbServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = new PrintWriter(System.out);
        // set connection variables
        String username = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:3306/web_customer_tracker";
        String driver = "com.mysql.jdbc.Driver";
        // get connection to database
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try(Connection connection = DriverManager.getConnection(url, username, password)){
            out.println("Connection with database is established.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
