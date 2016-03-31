<html>
<head><title>Tickets for events</title>
    <link href="static/css/bootstrap.css" rel="stylesheet"/>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <fieldset>
                <legend>Tickets for events</legend>
                <table class="table table-striped">
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
                <form action="index" method="post" role="form">
                    <div class="form-actions">
                        <button type="submit" class="btn">Back</button>
                    </div>
                </form>
            </fieldset>
        </div>
    </div>
</div>
</body>
</html>