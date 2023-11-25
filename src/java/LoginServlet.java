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

                       
                        query = "SELECT * FROM USERS U,APPLICATIONS A,COURSE C WHERE U.UID = A.UID AND A.COURSECODE=C.COURSECODE AND U.UID = ?";
                        try (PreparedStatement pst1 = conn.prepareStatement(query)) {
                            pst1.setInt(1, uid);
                            try (ResultSet rs1 = pst1.executeQuery()) {
                                if (rs1.next()) {
                                    out.println("   <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN\" crossorigin=\"anonymous\">");
                                    out.println("<div class=\"p-5\">");
                                    out.println("<h2>User Information:</h2>");
                                    out.println("<b><p>First Name: </b>" + rs1.getString("FNAME") + "</p>");
                                    out.println("<b><p>Last Name: </b>" + rs1.getString("LNAME") + "</p>");
                                    out.println("<b><p>Date of Birth: </b>" + rs1.getDate("DOB") + "</p>");
                                    out.println("<b><p>Address: </b>" + rs1.getString("ADDRESS") + "</p>");
                                    out.println("<b><p>Gender: </b>" + rs1.getString("GENDER") + "</p>");
                                    out.println("<b><p>Mobile: </b>" + rs1.getLong("MOBILE") + "</p>");
                                    out.println("<b><p>Email: </b>" + rs1.getString("EMAIL") + "</p>");

                                    out.println("<h2>Application Information:</h2>");
                                    out.println("<b><p>Course Code: </b>" + rs1.getString("COURSECODE") + "</p>");
                                    out.println("<b><p>10<sup>th</sup> Marks: </b>" + rs1.getInt("TENTHMARKS") + "</p>");
                                    out.println("<b><p>12<sup>th</sup> Marks: </b>" + rs1.getInt("TWELMARKS") + "</p>");
                                    out.println("<b><p>UG Marks: </b>" + rs1.getInt("UGMARKS") + "</p>");
                                    out.println("<b><p>UG University: </b>" + rs1.getString("UGUNIVERSITY") + "</p>");
                                  
                                    
                                    
                                    
                                //  delete button
                                out.println("<br><br><form action='DeleteUserServlet' method='post'>");
                                out.println("<input type='hidden' name='uid' value='" + uid + "'>");
                                out.println("<input class=\"btn btn-danger\"type='submit' value='Delete Application'>");
                                out.println("<a class=\"btn btn-primary\" href=/JavaHackathon/>Back</a>");
                                out.println("</form>");
                                out.println("</div>");
                                } else {
                                    out.println("<p>User not found.</p>");
                                }
                            }
                        } catch (Exception e) {
                            out.println("<h2>Error in inner query: " + e.getMessage() + "</h2>");
                        }
                    } else {
                    
                        out.println("<h2>Incorrect username or password!</h2>");
                         out.println("<a href=/JavaHackathon/>Back</a>");
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
