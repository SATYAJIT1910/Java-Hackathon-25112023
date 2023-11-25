<%-- 
    Document   : newRegistration
    Created on : 25-Nov-2023, 9:30:09 AM
    Author     : satyajit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.ResultSet, java.sql.SQLException, java.sql.PreparedStatement" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    </head>
    <body>
        <h1 style="text-align: center">New Student Registration</h1>
        <div class="container mt-5  w-50">
        <form action="/JavaHackathon/newuserreg">
    <div class="form-group">
      <label for="firstName">First Name:</label>
      <input type="text" class="form-control" id="firstName" name="FNAME">
    </div>

    <div class="form-group">
      <label for="lastName">Last Name:</label>
      <input type="text" class="form-control" id="lastName" name="LNAME">
    </div>

    <div class="form-group">
      <label for="dob">Date of Birth:</label>
      <input type="date" class="form-control" id="dob" name="DOB">
    </div>

    <div class="form-group">
      <label for="address">Address:</label>
      <input type="text" class="form-control" id="address" name="ADDRESS">
    </div>

    <div class="form-group">
      <label>Gender:</label>
      <div class="form-check">
        <input type="radio" class="form-check-input" id="male" name="GENDER" value="Male">
        <label class="form-check-label" for="male">Male</label>
      </div>
      <div class="form-check">
        <input type="radio" class="form-check-input" id="female" name="GENDER" value="Female">
        <label class="form-check-label" for="female">Female</label>
      </div>
    </div>

    <div class="form-group">
      <label for="mobile">Mobile:</label>
      <input type="number" class="form-control" id="mobile" name="MOBILE">
    </div>

    <div class="form-group">
      <label for="email">Email:</label>
      <input type="email" class="form-control" id="email" name="EMAIL">
    </div>

    <div class="form-group">
      <label for="password">Password:</label>
      <input type="password" class="form-control" id="password" name="PASSWORD">
    </div>

            <h2 style="margin: 2rem 0">Academic Details</h2>

    <div class="form-group">
      <label for="tenthMarks">10th Marks:</label>
      <input type="number" class="form-control" id="tenthMarks" name="TENTHMARKS">
    </div>

    <div class="form-group">
      <label for="twelfthMarks">12th Marks:</label>
      <input type="number" class="form-control" id="twelfthMarks" name="TWELMARKS">
    </div>

    <div class="form-group">
      <label for="ugMarks">UG Marks:</label>
      <input type="number" class="form-control" id="ugMarks" name="UGMARKS">
    </div>

    <div class="form-group">
      <label for="ugUniversity">UG University:</label>
      <input type="text" class="form-control" id="ugUniversity" name="UGUNIVERSITY">
    </div>
            
            

            
            <h2 style="margin: 2rem 0">Course Choice</h2>
<%
        try {
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
        String query = "SELECT * FROM COURSE";
        PreparedStatement pst = conn.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        
        out.println("<div class\"form-group\">");
        while (rs.next()) {
            out.println("<input type=\"radio\" name=\"COURSE\" value="+rs.getString(1)+">");
            out.println("<label for=\"html\">"+rs.getString(2)+"</label><br>");
            
        }
        out.println("</div><br><br>");
        rs.close();
        pst.close();
        conn.close();

    } catch (Exception e) {
        out.println("<h2>Error: " + e.getMessage() + "</h2>");
    }
  
    %>
    
    <input type="submit" class="btn btn-primary" value="Submit">
    <%
     out.println("<br><br>");
    %>
   
    </form>
     </div>
    </body>
</html>
