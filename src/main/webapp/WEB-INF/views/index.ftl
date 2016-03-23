<html>
<head>
    <title>Choose action</title>
</head>
<body>
<#list commands as command>
<a href="${command.name}">${command.description}</a>
</#list>
</body>
</html>
