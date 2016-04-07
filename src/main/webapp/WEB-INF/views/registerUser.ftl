<#import "/spring.ftl" as spring/>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register new user</title>
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
                        <!-- Username -->
                        <label class="control-label" for="name">Name</label>
                        <div class="controls">
                            <input type="text" id="name" name="name" placeholder="" class="input-xlarge">
                            <p class="help-block">User name can contain any letters or numbers, without spaces</p>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">Birth Date: </label>

                        <div class="controls"><input type="date" name="birthDate"  placeholder="" class="input-xlarge"/></div>
                    </div>

                    <div class="control-group">
                        <!-- E-mail -->
                        <label class="control-label" for="email">E-mail</label>
                        <div class="controls">
                            <input type="text" id="email" name="email" placeholder="" class="input-xlarge">
                            <p class="help-block">Please provide your E-mail</p>
                        </div>
                    </div>

                    <div class="control-group">
                        <!-- Balance -->
                        <label class="control-label" for="email">User balance</label>
                        <div class="controls">
                            <input type="number" id="balance" name="balance" placeholder="" class="input-xlarge">
                            <p class="help-block">Please provide your balance amount</p>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">Currency:</label>

                        <div class="controls">
		                <#macro enumSelect selectName enumValues>
                            <select name="${selectName}" class="input-xlarge">
				                <#list enumValues as enum>
                                    <option value="${enum}">${enum.description}</option>
				                </#list>
                            </select>
		                </#macro>
		                <@enumSelect "currency" currencyList/>
                        </div>
                    </div>
                    <div class="control-group">
                        <!-- Password-->
                        <label class="control-label" for="password">Password</label>
                        <div class="controls">
                            <input type="password" id="password" name="password" placeholder="" class="input-xlarge">
                            <p class="help-block">Password should be at least 4 characters</p>
                        </div>
                    </div>

                    <div class="form-actions">
                        <button type="submit" class="btn btn-success">Register</button>
                        <button type="submit" class="btn" name="cancel">Cancel</button>
                    </div>
                </form>
            </fieldset>
        </div>
    </div>
</div>
</body>
</html>