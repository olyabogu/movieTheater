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
                <legend>Add Event</legend>
                <form class="form-horizontal" name="event" action="addEvent" method="post" role="form">
                    <div class="control-group">
                        <label class="control-label"> Name:</label>

                        <div class="controls"><input type="text" name="name" placeholder="" class="input-xlarge"/></div>
                    </div>
                    <div class="control-group">
                        <label class="control-label"> Date: </label>

                        <div class="controls"><input type="date" name="date" placeholder="" class="input-xlarge"/></div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">Price: </label>

                        <div class="controls"><input type="number" min="1" name="basePrice" placeholder="" class="input-xlarge"/></div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">Rating:</label>

                        <div class="controls">
						<#macro enumSelect selectName enumValues>
                            <select name="${selectName}" class="input-xlarge">
								<#list enumValues as enum>
                                    <option value="${enum}">${enum.description}</option>
								</#list>
                            </select>
						</#macro>
						<@enumSelect "rating" ratings/>
                        </div>
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