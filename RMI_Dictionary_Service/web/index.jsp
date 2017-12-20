<%--
  Created by IntelliJ IDEA.
  User: kyle
  Date: 12/17/17
  Time: 5:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>RMI Dictionary Service</title>
  </head>
  <body>
    <form bgcolor="white" method="POST"  action="doProcess">
      <h1>Dictionary Service</h1>
      <p />
      <input type="text" name="txtQuery" placeholder="Enter query here" />
      <input type="submit" value="Submit" />
    </form>
    <p />
    <label>NOTE: the first time setup the server, you need do the query first (loading data), </label>
    <br />
    <label>then using the function blow.</label>
    <p />
    <a href="/doOperation"><button>manage entries in the dictionary</button></a>
  </body>
</html>
