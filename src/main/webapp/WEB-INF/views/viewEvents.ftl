<html>
<head>
    <meta charset="UTF-8">
    <title>Events list</title>
    <link href="static/css/bootstrap.css" rel="stylesheet"/>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <fieldset>
                <legend>Event Details</legend>
                <table class="table table-striped">
                    <tr>
                        <th>Name</th>
                        <th>Date</th>
                        <th>Price</th>
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