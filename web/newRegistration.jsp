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
    </head>
    <body>
        <h1>New Student Registration</h1>
        <form action="/JavaHackathon/newuserreg" >
            First Name:
            <input type="text" name="FNAME"> <br>
            Last Name:
            <input type="text" name="LNAME"> <br>
            Date of Birth:
            <input type="date" name="DOB"> <br>
            Address:
            <input type="text" name="ADDRESS"> <br>
            
           Gender: 
          <input type="radio" name="GENDER" value="Male"> 
          <label for="male">Male</label>
          <input type="radio" name="GENDER" value="Female"> 
          <label for="female">Female</label><br>
            
            Mobile:
            <input type="number" name="MOBILE"> <br>
            Email:
            <input type="email" name="EMAIL"> <br>
            Password:
            <input type="password" name="PASSWORD"> <br>
            
            
            <h2>Academic Details</h2>
            10th Marks
            <input type="number" name="TENTHMARKS"> <br>
            12th Marks
            <input type="number" name="TWELMARKS"> <br>
            UG Marks
            <input type="number" name="UGMARKS"> <br>
            UG University
            <input type="text" name="UGUNIVERSITY"> <br>
            
            <h2>Course Choice</h2>
<%
        try {
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
        String query = "SELECT * FROM COURSE";
        PreparedStatement pst = conn.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            out.println("<input type=\"radio\" name=\"COURSE\" value="+rs.getString(1)+">");
            out.println("<label for=\"html\">"+rs.getString(2)+"</label><br>");
            
        }

        rs.close();
        pst.close();
        conn.close();

    } catch (Exception e) {
        out.println("<h2>Error: " + e.getMessage() + "</h2>");
    }
  
    %>
    
    <input type="submit" value="Submit">
        </form>
    </body>
</html>
