<%@include file="../includes/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Prizy Pricer - Product</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/alert.css" />
</head>
<body>
	<h1>Product</h1>
	<form action="${pageContext.request.contextPath}/product/save" method="POST">
		<table>
			<tr>
				<td>Product Name:</td>
				<td><input required type="text" maxlength="199" value="${product.productName}" name="productName" /></td>
			</tr>
			<tr>
				<td>Product Description:</td>
				<td><textarea name="productDesc">${product.productDesc}</textarea>
					<c:if test="${product!=null && product.productId!=0}">
						<input type="hidden" value="${product.productId}" name="productId" maxlength="10"/>
					</c:if>
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit" /></td>
				<td><a href="${pageContext.request.contextPath}/product">Cancel</a></td>
			</tr>
		</table>
	</form>
</body>
</html>