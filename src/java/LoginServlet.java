/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author satyajit
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {

        String username = request.getParameter("uname");
        String password = request.getParameter("passwd");

        if (username.isEmpty() || password.isEmpty()) {
            out.println("<h2>Please enter both username and password!</h2>");
            return;
        }

        try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app")) {
            String query = "SELECT * FROM users WHERE mobile = ? AND password = ?";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, username);
                pst.setString(2, password);

                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        int uid = rs.getInt("UID");

                        // Retrieve user and application information using INNER JOIN
                        query = "SELECT * FROM USERS U,APPLICATIONS A,COURSE C WHERE U.UID = A.UID AND A.COURSECODE=C.COURSECODE AND U.UID = ?";
                        try (PreparedStatement pst1 = conn.prepareStatement(query)) {
                            pst1.setInt(1, uid);
                            try (ResultSet rs1 = pst1.executeQuery()) {
                                if (rs1.next()) {
                                    out.println("<h2>User Information:</h2>");
                                    out.println("<p>First Name: " + rs1.getString("FNAME") + "</p>");
                                    out.println("<p>Last Name: " + rs1.getString("LNAME") + "</p>");
                                    out.println("<p>Date of Birth: " + rs1.getDate("DOB") + "</p>");
                                    out.println("<p>Address: " + rs1.getString("ADDRESS") + "</p>");
                                    out.println("<p>Gender: " + rs1.getString("GENDER") + "</p>");
                                    out.println("<p>Mobile: " + rs1.getLong("MOBILE") + "</p>");
                                    out.println("<p>Email: " + rs1.getString("EMAIL") + "</p>");

                                    out.println("<h2>Application Information:</h2>");
                                    out.println("<p>Course Code: " + rs1.getString("COURSECODE") + "</p>");
                                    out.println("<p>Tenth Marks: " + rs1.getInt("TENTHMARKS") + "</p>");
                                    out.println("<p>Twelfth Marks: " + rs1.getInt("TWELMARKS") + "</p>");
                                    out.println("<p>UG Marks: " + rs1.getInt("UGMARKS") + "</p>");
                                    out.println("<p>UG University: " + rs1.getString("UGUNIVERSITY") + "</p>");
                                  
                                    
                                    
                                } else {
                                    out.println("<p>User not found.</p>");
                                }
                            }
                        } catch (Exception e) {
                            out.println("<h2>Error in inner query: " + e.getMessage() + "</h2>");
                        }
                    } else {
                        // Authentication failed, display an error message
                        out.println("<h2>Incorrect username or password!</h2>");
                    }
                }
            } catch (Exception e) {
                out.println("<h2>Error in outer query: " + e.getMessage() + "</h2>");
            }
        } catch (Exception e) {
            out.println("<h2>Error connecting to the database: " + e.getMessage() + "</h2>");
        }
    }
}


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
