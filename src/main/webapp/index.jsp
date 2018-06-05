
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
function hideLong() {
    var x = document.getElementById("longdiv");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
} 
function hideShort() {
    var x = document.getElementById("shortdiv");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
} 
</script>
<html>

<head>
<title>Prajval Tiny Url Generator</title>
Prajval Tiny Url Generator
</head>
<body>

<form action="/TinyUrlGenerator/urlgenerator/shortUrl" method="post">
 <input type="text" id="longUrl" name="longUrl" placeholder="Enter long Url" required>
<input type="submit" value="Get Short Url" onclick="hideShort()">
</form>
<div id="longdiv">
<c:if test="${not empty response.shortUrl}">
<td><p>Short URL= <a href="<c:url value='${response.longUrl}' />" class="btn btn-success custom-width">${response.shortUrl}</a></p></td>
</c:if>
</div>


<form action="/TinyUrlGenerator/urlgenerator/longUrl" method="post">
 <input type="text" id="shortUrl" name="shortUrl" placeholder="Enter Short Url" required>
<input type="submit" value="Get Long Url" onclick="hideLong()">
</form>
<div id="shortdiv">
<c:if test="${not empty response.longUrl}">
<td><p>Long URL = <a href="<c:url value='${response.shortUrl}' />" class="btn btn-success custom-width">${response.longUrl}</a></p></td>
</c:if>
<c:if test="${response.code == '402'}">
<td><h4>${response.status}</h4></td>
</c:if>
</div>
</body>
</html>
