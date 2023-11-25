<%-- 
    Document   : admission.jsp
    Created on : 25-Nov-2023, 10:25:22 AM
    Author     : satyajit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
        
    </head>
    <body>
        <%
    int uid = 1;

    try {
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");


        String userQuery = "SELECT * FROM USERS WHERE UID = ?";
        try (PreparedStatement userPst = conn.prepareStatement(userQuery)) {
            userPst.setInt(1, uid);
            ResultSet userRs = userPst.executeQuery();

            if (userRs.next()) {
                out.println("<h2>User Information:</h2>");
                out.println("<p>UID: " + userRs.getInt("UID") + "</p>");
                out.println("<p>First Name: " + userRs.getString("FNAME") + "</p>");
                out.println("<p>Last Name: " + userRs.getString("LNAME") + "</p>");

                String appQuery = "SELECT * FROM APPLICATIONS WHERE UID = ?";
                try (PreparedStatement appPst = conn.prepareStatement(appQuery)) {
                    appPst.setInt(1, uid);
                    ResultSet appRs = appPst.executeQuery();

                    if (appRs.next()) {
                        out.println("<h2>Application Information:</h2>");
                        out.println("<p>Application ID: " + appRs.getInt("APPID") + "</p>");
                        out.println("<p>User ID: " + appRs.getInt("UID") + "</p>");
                        out.println("<p>Course Code: " + appRs.getString("COURSECODE") + "</p>");
             
                    } else {
                        out.println("<p>No application found for this user.</p>");
                    }
                }
            } else {
                out.println("<p>User not found.</p>");
            }
        }

        // Close the connection
        conn.close();

    } catch (Exception e) {
        out.println("<h2>Error: " + e.getMessage() + "</h2>");
    }
%>
    </body>
</html>
