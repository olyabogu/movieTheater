<html>
<head><title>Add new event</title>
<body>
<div id="header">
    <H2>
        Add event form
    </H2>
</div>

<div id="content">
    <fieldset>
        <legend>Add Event</legend>
        <form name="event" action="add-event" method="post">
            Name: <input type="text" name="firstname"/> <br/>
            Date: <input type="date" name="date"/> <br/>
            Price: <input type="number" name="basePrice"/> <br/>
        <#--<#list reatings as rating>-->
            <#--Rating: <select>-->
            <#--<option value="rating">${rating}</option>-->
        <#--</select></#list> <br/>-->
            <input type="submit" value="   Save   "/>
        </form>
    </fieldset>
</div>
</body>
</html>