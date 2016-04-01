<html>
<head>
    <meta charset="UTF-8">
    <title>Actions</title>
    <link href="static/css/bootstrap.css" rel="stylesheet"/>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <fieldset>
                <legend>Actions</legend>
                <table class="table table-striped">
                <#list commands as command>
                    <a href="${command.name}"> ${command.description} </a> <br/>
                </#list>
                </table>
                <form action="logout" method="POST" role="form">
                    <div class="form-actions">
                        <button type="submit" class="btn">Logout</button>
                    </div>
                </form>
            </fieldset>
        </div>
    </div>
</div>
</body>
</html>