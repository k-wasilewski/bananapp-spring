<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Spring Boot</title>
</head>
<body>
<h3>from Spring MVC controller</h3>
<a href="/">back to React App</a>

<!-- not working!!!!!!!!!! -->
<spring:url value="./another.js" var="another" />
<script src="${another}" type="text/javascript"></script>
</body>
</html>