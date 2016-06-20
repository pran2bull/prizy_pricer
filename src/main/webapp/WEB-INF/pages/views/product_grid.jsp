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
	<h1>Product List</h1>
	<a href="${pageContext.request.contextPath}/product/new">Add
		Product</a>
	<br />
	<br />
	<div>
		Search By Bar Code : <input type="text" id="searchId" style="width: 20%;" placeholder="Please Enter Bar Code"/>
		<br /> <br />
	</div>
	<table border=1>
		<tr>
			<th>S No.</th>
			<th>Product Name</th>
			<th>Product Bar Code</th>
			<th>Action</th>
			<th>MIS</th>
		</tr>
		<tbody id="tableId">
			<c:forEach items="${productList}" var="product" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${product.productName}</td>
					<td class="bar-code">${product.barCode}</td>
					<td><a
						href="${pageContext.request.contextPath}/product/${product.productId}/edit">Edit</a></td>
					<td><a
						href="${pageContext.request.contextPath}/product/${product.productId}/view">MIS</a></td>
				</tr>
			</c:forEach>
		<tbody>
	</table>
	<div class="alert-success">
		<b id="alertTextId">SUBMIT SUCCESSFULLY</b>
	</div>
	<div class="alert-danger">
		<b id="alertTextId">SUBMIT FAILURE</b>
	</div>
</body>
<jsp:include page="../includes/footer.jsp"></jsp:include>
<c:if test="${!empty param.status}">
	<c:choose>
		<c:when test="${param.status=='true'}">
			<script>
				$(function() {
					$(".alert-success").show();
					$(".alert-success").delay(2000).fadeOut('slow');
				});
			</script>
		</c:when>
		<c:when test="${param.status=='false'}">
			<script>
				$(function() {
					$(".alert-danger").show();
					$(".alert-danger").delay(2000).fadeOut('slow');
				});
			</script>
		</c:when>
	</c:choose>
</c:if>
<script>
	$(function() {
		$("#searchId").keyup(function() {
			var val = $(this).val();
			if (val != null && val.length > 0) {
				$("#tableId tr td.bar-code:not(:contains('" + val + "'))").parent().hide();
				$("#tableId tr td.bar-code:contains('" + val + "')").parent().show();
			} else {
				$("table tr").show();
			}
		});
	});
</script>
</html>