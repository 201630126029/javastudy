<%@ page import="java.util.List" %>
<%@ page import="sun.util.resources.cldr.chr.CalendarData_chr_US" %>
<%@ page import="mvcapp.damain.Customer" %>
<%--
  Created by IntelliJ IDEA.
  User: xuanqis
  Date: 2019/5/23
  Time: 8:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="query.do">
    <table>
        <tr>
            <td>CustomerName:</td>
            <td><input type="text" name="name"/></td>
        </tr>
        <tr>
            <td>Address:</td>
            <td><input type="text" name="address"/></td>
        </tr>
        <tr>
            <td>Phone:</td>
            <td><input type="text" name="phone"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Query"/></td>
            <td><a href="">Add New Customer.</a> </td>
        </tr>
    </table>
</form>
<%
    List<Customer> customers = (List<Customer>) request.getAttribute("customers");
    if(customers != null && customers.size() > 0){
%>
<br>
<br>

<table>
    <tr>
        <th>ID</th>
        <th>CustomerName</th>
        <th>Address</th>
        <th>Phone</th>
        <th>UPDATE/DALETE</th>
    </tr>
    <% for (Customer customer : customers){ %>
    <tr>
        <td><%=customer.getId()%></td>
        <td><%=customer.getName()%></td>
        <td><%=customer.getAddress()%></td>
        <td><%=customer.getPhone()%></td>
        <td>
            <a href="">UPDATE</a>
            <a href="delete?id=<%=customer.getId()%>">DALETE</a>
        </td>
    </tr>
    <%
        }
    %>
    <% } %>
</table>
</body>
</html>
