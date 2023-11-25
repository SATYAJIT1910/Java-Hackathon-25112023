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
import java.sql.*;


/**
 *
 * @author satyajit
 */
public class NewUserRegistrationServlet extends HttpServlet {

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
        
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
                
        String firstName = request.getParameter("FNAME");
        String lastName = request.getParameter("LNAME");
        String dob = request.getParameter("DOB");
        String address = request.getParameter("ADDRESS");
        String gender = request.getParameter("GENDER");
        String mobile = request.getParameter("MOBILE");
        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");
        String tenthmarks=request.getParameter("TENTHMARKS");
        String twelmarks=request.getParameter("TWELMARKS");
        String ugmarks=request.getParameter("UGMARKS");
        String uguni=request.getParameter("UGUNIVERSITY");
        String course=request.getParameter("COURSE");
       
        
        if (firstName.isEmpty() || lastName.isEmpty() || dob.isEmpty() || address.isEmpty() ||
            gender == null || mobile.isEmpty() || email.isEmpty() || password.isEmpty() || tenthmarks.isEmpty()
                ||twelmarks.isEmpty() || ugmarks.isEmpty() || uguni.isEmpty() || course == null
                ) {
        out.println("<h2>Please enter all the fields!</h2>");
        out.println("<a href=\"/JavaHackathon/newRegistration.html\">Back</a>");
        return; // stop further processing
    }
        
        
        try{
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
            
            String query = "INSERT INTO users (FNAME, LNAME, DOB, ADDRESS, GENDER, MOBILE, EMAIL, PASSWORD) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, firstName);
                pst.setString(2, lastName);
                pst.setString(3, dob);
                pst.setString(4, address);
                pst.setString(5, gender);
                pst.setString(6, mobile);
                pst.setString(7, email);
                pst.setString(8, password);

                int rowsAffectedUser = pst.executeUpdate();
                
              
                Statement stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ResultSet rs=stmt.executeQuery("SELECT UID FROM USERS");
                rs.last();
                int uid=rs.getInt(1);
                
                query="INSERT INTO APPLICATIONS (UID,COURSECODE,TENTHMARKS,TWELMARKS,UGMARKS,UGUNIVERSITY) VALUES(?,?,?,?,?,?)";
                PreparedStatement pst2=conn.prepareStatement(query);
                pst2.setInt(1, uid);
                pst2.setString(2,course);
                pst2.setInt(3,Integer.parseInt(tenthmarks));
                pst2.setInt(4,Integer.parseInt(twelmarks));
                pst2.setInt(5,Integer.parseInt(ugmarks));
                pst2.setString(6,uguni);
                
                int rowsAffectedApplication = pst2.executeUpdate();
                
                if (rowsAffectedUser>0 &&  rowsAffectedApplication> 0) {
                    out.println("<h2>User registered successfully!</h2>");
                    out.println("<a href=\"/JavaHackathon\">Back</a>");
                } else {
                    out.println("<h2>Error registering user.</h2>");
                }
            }

            // Close the connection
            conn.close();
            
            
            
        }catch (Exception e) {
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        }
        
        out.close();
        
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
