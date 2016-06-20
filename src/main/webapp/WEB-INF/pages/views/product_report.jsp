<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Prizy Pricer - MIS</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/alert.css" />
</head>
<body>
	<h1>Product MIS</h1>
	<table border=1>
		<tr>
			<td>Product Name</td>
			<td>${productMap.product.productName}</td>
		</tr>
		<tr>
			<td>Product Description</td>
			<td>${productMap.product.productDesc}</td>
		</tr>
		<tr>
			<td>Product Bar Code</td>
			<td>${productMap.product.barCode}</td>
		</tr>
		<tr>
			<td>Average Price</td>
			<td>${productMap.averagePrice}</td>
		</tr>
		<tr>
			<td>Highest Price</td>
			<td>${productMap.highestPrice}</td>
		</tr>
		<tr>
			<td>Lowest Price</td>
			<td>${productMap.lowestPrice}</td>
		</tr>
		<tr>
			<td>Ideal Price</td>
			<td>${productMap.idealPrice}</td>
		</tr>
		<tr>
			<td>Total Price Count</td>
			<td>${empty productMap.priceCount?0:productMap.priceCount}</td>
		</tr>
	</table>

	<code>Ideal Price would only be visible when TOTAL PRICE COUNT is
		greater than ${productMap.limitSum }</code>
</body>
</html>