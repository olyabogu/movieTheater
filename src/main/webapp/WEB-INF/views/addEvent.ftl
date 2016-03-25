<#import "/spring.ftl" as spring/>
<#import "ratings.ftl" as ratings />
<html xmlns:form="http://www.w3.org/1999/html">
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
        <legend>Add Event</legend>
        <form name="event" action="addEvent" method="post">
            Name: <input type="text" name="name"/> <br/>
            Date: <input type="date" name="date"/> <br/>
            Price: <input type="number" min="1" name="basePrice"/> <br/>
            Rating: <input type="text" name="rating"/> <br/>
        <#--<@spring.formSingleSelect path="event.rating" options = { "A": "Option A", "B": "Option B", "C": "Option C" } /><br/>-->
            <input type="submit" value="   Save   "/>
        </form>
    </fieldset>
</div>
</body>
</html>