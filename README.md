![Mindfulcraft](./src/main/resources/assets/mindfulcraft/icon_lg.png)  

**Mindfulcraft** is a mod that sends mindful messages to the player every so often based on a customizable config file.

## Features  
- [x] Generates a config file on client startup if one doesn't exist
- [x] Checks version of config file to guarantee latest message updates
- [x] Client reads from config file to get settings
- [X] On world join, sends an initial message if the mod is enabled
- [X] Every X ticks, based on config, sends a mindful message
- [X] When all messages are sent (in a random order with no repeats), resets the list and starts over
- [ ] Add a drastically large amount of messages
- [ ] Improve config understandability
- [ ] Implement more options into config, possibly split into multiple files
- [ ] Super stretch: create own UI popup with more customization as opposed to using Minecraft's built-in toast system
