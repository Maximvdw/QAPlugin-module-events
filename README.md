# QAPlugin Module
### Trigger API.AI events with context

## Installation

* /qaplugin module add Events

## Usage

### PLAYER_JOIN
Triggered when a player joins (not for the first time)

[](http://i.mvdw-software.com/2017-01-27_22-50-03.png)
[](http://i.mvdw-software.com/2017-01-27_22-53-06.png)

#### Placeholders

* {#player.username}
* {#player.uuid}

### PLAYER_FIRST_JOIN
Triggered when a player joins for the first time.
(Similar to PLAYER_JOIN)

#### Placeholders

* {#player.username}
* {#player.uuid}

### PLAYER_QUIT
Triggered when a player quits
(Similar to PLAYER_JOIN)

#### Placeholders

* {#player.username}
* {#player.uuid}
