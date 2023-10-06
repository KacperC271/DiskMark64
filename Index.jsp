<%@ page import="ENTITY.ENTITY_MD5" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="EJB_JPA.EJB_JPA" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>MD5_HASH</title>
  <style>
    body {
      text-align: center;
    }
    table {
      margin: 0 auto;
    }
  </style>
</head>
<body>
<h1>MD5_HASH</h1>
<form action="Hash_Servlet" method="post">
  WPROWADŹ TEXT DO HASZOWANIA: <label>
  <input type="text" name="TEXT_TO_HASH">
</label>
  <input type="submit" value="HASH">
</form>
<% String InputText = (String) request.getSession().getAttribute("TEXT_TO_HASH"); %>
<% String MD5Hash = (String) request.getSession().getAttribute("MD5_HASH"); %>

<form action="DATA_BASE" method="post">
  <table border="3">
    <tr>
      <th>TEXT_BEFORE_HASH</th>
      <th>TEXT_AFTER_HASH</th>
      <th>HASH_ALGORITHM</th>
      <th>SAVE</th>
    </tr>
    <tr>
      <td><input type="text" name="InputText" value="<%=InputText%>" /></td>
      <td><input type="text" name="MD5Hash" value="<%=MD5Hash%>"/></td>
      <td><input type="text" name="Hash" value="MD5"/></td>
      <td><input name="action" type="submit" value="SAVE"></td>
    </tr>
  </table>
</form>

<h2>HASH HISTORY</h2>
<form action="DATA_BASE" method="POST">
<table border="3">

  <tr>
    <th>TEXT_BEFORE_HASH</th>
    <th>TEXT_AFTER_HASH</th>
    <th>HASH_ALGORITHM</th>
  </tr>
  <% EJB_JPA History = new EJB_JPA();
    List<ENTITY_MD5> ENT_HIS = History.HISTORY();
    if (ENT_HIS != null && !ENT_HIS.isEmpty()) {
      for (ENTITY_MD5 ENT : ENT_HIS) { %>
  <tr>
    <td><%= ENT.GET_TEXT_BEFORE_HASH() %></td>
    <td><%= ENT.Get_TEXT_AFTER_HASH() %></td>
    <td><%= "MD5" %></td>
  </tr>
  <% } } %>
</table>
  <label>WPROWADŻ_HASH_DO_USUNIĘCIA<input type="text" name="ID"></label> <input type="submit" name="action" value="DELETE" />
</form>

</body>
</html>
