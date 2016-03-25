<html>
<head>
    <meta charset="UTF-8">
    <title>Events list</title>
</head>
<body>
<div id="header">
    <H2>
        Events list
    </H2>
</div>

<div id="content">
    <table class="datatable">
        <tr>
            <th>Name</th>
            <th>Rating</th>
        </tr>
    <#list events as event>
        <tr>
            <td>${event.name}</td>
            <td>${event.date}</td>
            <td>${event.basePrice}</td>
            <td>${event.rating}</td>
        </tr>
    </#list>
    </table>
</div>
</body>
</html>