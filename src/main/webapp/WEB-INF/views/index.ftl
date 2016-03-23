<html>
<head>
    <title>Choose action</title>
</head>
<body>
<h3>Actions</h3>
<#list commands as command>
<a href="${command.name}"> ${command.description} </a> <br/>
</#list>
</body>
</html>
