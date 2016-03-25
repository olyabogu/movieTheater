<#import "/spring.ftl" as spring/>
<head>
    <meta charset="UTF-8">
    <title>Add new event</title>
</head>
<body>
<div id="header">
    <H2>
        Add event form
    </H2>
</div>

<div id="content">
    <fieldset>
        <legend>Register new user</legend>
        <form name="user" action="registerUser" method="post">
            Name: <input type="text" name="name"/> <br/>
            Birth Date: <input type="date" name="birthDate"/> <br/>
            Email: <input type="text" name="email"/> <br/>
            Rating:
		<#macro enumSelect selectName enumValues>
            <select name="${selectName}">
				<#list enumValues as enum>
                    <option value="${enum}">${enum.description}</option>
				</#list>
            </select>
		</#macro>
		<@enumSelect "role" roles/><br/>
            <input type="submit" value="   Save   "/>
        </form>
    </fieldset>
</div>
</body>
</html>