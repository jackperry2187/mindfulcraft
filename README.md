![MindfulCraft](./src/main/resources/assets/mindfulcraft/icon_lg.png)  

**MindfulCraft** is a mod that sends mindful messages to the player every so often based on a customizable config file.

## Features  
- [x] Generates a config file on client startup if one doesn't exist
- [x] Checks version of config file to guarantee latest message updates
- [x] Client reads from config file to get settings
- [X] On world join, sends an initial message if the mod is enabled
- [X] Every X ticks, based on config, sends a mindful message
- [X] When all messages are sent (in a random order with no repeats), resets the list and starts over
- [ ] Add a drastically large amount of messages  
  - Current # of simple messages: **4**
  - Current # of unique messages: **1**
- [ ] Improve config understandability
- [ ] Implement more options into config, possibly split into multiple files
- [ ] Super stretch: create own UI popup with more customization as opposed to using Minecraft's built-in toast system

## Message Examples
// TODO (after adding a bunch of messages)

## Config Guide
The config file can be found at `config/mindfulcraft-config.toml` (after running the game once), which can be edited with any text editor. You can change the values for the interval between messages, add or remove messages, and enable or disable the mod.   
  
Adding messages should be done in the following format:
```js
  {id=#, title="String", message="String", enabled=boolean, titleColor="String", messageColor="String"},
```
- `id` is a whole number that should be unique between messages  
- `title` is the title of the message that will be displayed at the top of the toast
- `message` is the message that will be displayed in the body of the toast
- `enabled` is a boolean that determines if the message can be sent - if you're setting to false anyway, you can also just remove the message unless you think you'll want to re-enable it later
- `titleColor` is a string that represents the color of the title text, which can be any of the colors below:
- `messageColor` is a string that represents the color of the message text, which can be any of the colors below:
```js
 "black", "dark_blue", "dark_green", "dark_aqua", "dark_red", "dark_purple", "gold", "gray", "dark_gray", "blue", "green", "aqua", "red", "light_purple", "yellow", "white"
```