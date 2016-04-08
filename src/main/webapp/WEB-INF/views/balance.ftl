<html>
<head>
    <meta charset="UTF-8">
    <title>View user balance</title>
    <link href="static/css/bootstrap.css" rel="stylesheet"/>
</head>
<body>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <fieldset>
                <legend>View user balance</legend>
                <table class="table table-striped">
                    <tr>
                        <th>Name</th>
                        <th>Current balance</th>
                        <th>Currency</th>
                    </tr>
                    <tr>
                        <td>${user.username}</td>
                        <td>${user.account.amount}</td>
                        <td>${user.account.currency}</td>
                    </tr>
                </table>
                <form class="form-horizontal" name="amount" action="balance" method="POST" role="form">
                    <div class="control-group">
                        <!-- Amount -->
                        <label class="control-label" for="name">Add amount</label>

                        <div class="controls">
                            <input type="number" id="amount" name="amount" placeholder="">
                        </div>
                    </div>
                    <div class="form-actions">
                        <button type="submit" class="btn btn-success">Refill balance</button>
                        <button type="submit" class="btn" name="cancel">Cancel</button>
                    </div>
                </form>
            </fieldset>
        </div>
    </div>
</div>
</body>
</html>