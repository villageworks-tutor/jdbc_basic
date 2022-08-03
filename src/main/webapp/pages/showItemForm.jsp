<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Show All Item</title>
</head>
<body>
<!--
	ソート：値段の低い順、値段の高い順
	追加：商品名価格円
	検索：円以下の商品を
	削除：商品番号
	修正：商品番号の価格
-->
	<table border="1">
		<tr>
			<th>No</th>
			<th>商品名</th>
			<th>価格</th>
		</tr>
		<tr>
			<td>1</td>
			<td>Javaの基本</td>
			<td>2500</td>
		</tr>
		<tr>
			<td>2</td>
			<td>MLB Fun</td>
			<td>980</td>
		</tr>
		<tr>
			<td>3</td>
			<td>料理BOOK!</td>
			<td>1200</td>
		</tr>
		<tr>
			<td>4</td>
			<td>なつかしのアニメシリーズ</td>
			<td>2000</td>
		</tr>
		<tr>
			<td>5</td>
			<td>The Racer</td>
			<td>1000</td>
		</tr>
		<tr>
			<td>6</td>
			<td>Space Wars 3</td>
			<td>1800</td>
		</tr>
		<tr>
			<td>7</td>
			<td>パズルゲーム</td>
			<td>780</td>
		</tr>
		<tr>
			<td>8</td>
			<td>Invader Fighter</td>
			<td>3400</td>
		</tr>
		<tr>
			<td>9</td>
			<td>Play the BascketBall</td>
			<td>2200</td>
		</tr> 
	</table>
	<p><a href="ItemServlet">トップページに戻る</a></p>
</body>
</html>