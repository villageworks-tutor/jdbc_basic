<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Show All Item</title>
	<style>
		input[type="text"] {
			width: 10rem;
			margin: 0 0.75rem 0 0.25rem;
		}
		input[type="number"] {
			width: 5rem;
			margin: 0 0.75rem 0 0.25rem;
		}
		button {
			margin: 0 0.75rem;
		}
	</style>
</head>
<body>
	ソート：<a href="ItemServlet?action=sort&key=price_asc">値段の低い順</a>、<a href="ItemServlet?action=sort&key=price_desc">値段の高い順</a><br />	
	
	<form action="ItemServlet" method="post">
		追加：商品名<input type="text" name="name" /> 価格<input type="number" name="price" />円を<button type="submit" name="action" value="add">追加</button>
	</form>
<!--
	検索：円以下の商品を
	削除：商品番号
	修正：商品番号の価格
-->
	<hr />
	<table border="1">
		<tr>
			<th>No</th>
			<th>商品名</th>
			<th>価格</th>
		</tr>
		<c:forEach items="${requestScope.items}" var="item">
		<tr>
			<td>${item.code}</td>
			<td>${item.name}</td>
			<td>${item.price}</td>
		</tr>
		</c:forEach>
	</table>
	<p><a href="ItemServlet">トップページに戻る</a></p>
</body>
</html>