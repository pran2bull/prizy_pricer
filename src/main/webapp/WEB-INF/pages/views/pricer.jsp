<%@include file="../includes/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Prizy Pricer</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/alert.css" />
</head>
<body>
	<div class="form">
		<h1>Prizy Pricer</h1>
		<br />
		<form id="formId">
			<table>
				<tr>
					<td>Store:</td>
					<td><input required type="text" maxlength="199" name="storeName" /></td>
				</tr>
				<tr>
					<td>Product:</td>
					<td><select name="productId" required>
							<option value="">Select Product</option>
							<c:forEach items="${productList}" var="product">
								<option value="${product.productId}">Bar Code :
									${product.barCode} - ${product.productName}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>Price:</td>
					<td><input required type="number" pattern="^\d+(\.|\,)\d{2}$"
						id="priceId" maxlength="20" name="price" /></td>
				</tr>
				<tr>
					<td>Notes:</td>
					<td><textarea name="notes"></textarea></td>
				</tr>
				<tr>
					<td><input type="submit" id="submitId" value="Submit Price" /></td>
				</tr>
			</table>
		</form>
	</div>
	<code class="form">
		<h3>For Login</h3>
		<span>Please use username: admin and password: admin</span><br/><br/>
		<a href="${pageContext.request.contextPath}/product">Click here to access Product List</a>
	</code>
	<div class="overlay" id="overlayDivId">
		<div>
			<span>Thank You. We appreciate your response.</span>
			<a href="${pageContext.request.contextPath}/">Close</a>
		</div>
	</div>
	<jsp:include page="../includes/footer.jsp"></jsp:include>
	<script>
		function submitResult() {
			$("#priceId").val(parseInt($("#priceId").val()));
			$("#submitId").attr("disabled","disabled");
			$.ajax({
				url : '${pageContext.request.contextPath}/api/user/save',
				type : 'POST',
				data : $("#formId").serialize(),
				success : function(response) {
					if (response == 1) {
						$(".form").hide();
						$("#overlayDivId").show();
					} else {
						$("#overlayDivId").find("span").html("Error !!! Please Try Again.");
						$(".form").hide();
						$("#overlayDivId").show();
					}
				}
			});
		}
		$('body').on('submit', 'form', function(e) {
			e.preventDefault();
			submitResult();
		});
	</script>
</body>
</html>