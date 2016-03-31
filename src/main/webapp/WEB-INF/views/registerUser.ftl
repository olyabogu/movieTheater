<#import "/spring.ftl" as spring/>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add new event</title>
    <link href="static/css/bootstrap.css" rel="stylesheet"/>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <fieldset>
                <legend>Register new user</legend>
                <form class="form-horizontal" name="user" action="registerUser" method="post" role="form">
                    <div class="control-group">
                        <label class="control-label">Name:</label>

                        <div class="controls"><input type="text" name="name"/></div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">Birth Date: </label>

                        <div class="controls"><input type="date" name="birthDate"/></div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">Email: </label>

                        <div class="controls"><input type="text" name="email"/></div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">Role:</label>

                        <div class="controls">
						<#macro enumSelect selectName enumValues>
                            <select name="${selectName}">
								<#list enumValues as enum>
                                    <option value="${enum}">${enum.description}</option>
								</#list>
                            </select>
						</#macro>
						<@enumSelect "role" roles/></div>
                    </div>

                    <div class="form-actions">
                        <button type="submit" class="btn btn-success">Submit</button>
                        <button type="submit" class="btn" name="cancel">Cancel</button>
                    </div>
                </form>
            </fieldset>
        </div>
    </div>
</div>
</body>
</html>