<%@page contentType="text/html;charset=UTF-8" %>


<html>
<head>
  <title>转账页面</title>
  <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
</head>
<body>
<h1>转账设置</h1>
<hr>
<form action="transfer" method="post">
  转出账户<input type="text" name="fromActno" value="布布" readonly><br>
  转入账户<input type="text" name="toActno" value="一二" readonly><br>
  转账金额<input type="text" name="money"><br>
  <input type="submit" value="提交">
</form>
</body>
</html>

