# Discalendar
Discalendar is a bot for Discord which handles event scheduling and calendars.

## Usage
Install the bot in your channel and use these commands:
 - `/createEvent` - creates an event
 - `/listEvents` - lists all the events registered for the current channel
 
## Developing
It's built using gradle, so make sure you have that installed. You'll need to create your own Discord bot which you can do through the Discord website. Once you have that key, you can run the following command to run the code for that bot:

`gradle execute -Pcargs="['MY_BOT_KEY_HERE']"`
