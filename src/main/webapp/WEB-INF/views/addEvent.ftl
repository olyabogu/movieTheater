<#import "/spring.ftl" as spring/>
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
        <form name="event" action="addEvent" method="post">
            Name: <input type="text" name="name"/> <br/>
            Date: <input type="date" name="date"/> <br/>
            Price: <input type="number" min="0" name="basePrice"/> <br/>
            Rating: <input type="text" name="rating"/> <br/>
        <#--<@spring.bind "options" />-->
            <#--<@spring.formSingleSelect "rating", options, " " /><br/>-->
            <input type="submit" value="   Save   "/>
        </form>
    </fieldset>
</div>
</body>
</html>