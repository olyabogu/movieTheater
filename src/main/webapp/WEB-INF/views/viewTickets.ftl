<html>
<head><title>Tickets for events</title>
<body>
<div id="header">
    <H2>
        Tickets for events
    </H2>
</div>

<div id="content">
    <table class="datatable">
        <tr>
            <th>Event name</th>
            <th>Tickets</th>
        </tr>
	<#list events as event>
        <tr>
            <td>${event.name}</td>
            <td>${event.tickets}</td>
        </tr>
	</#list>
    </table>
</div>
</body>
</html>