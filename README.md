# Treasure-Hunter

The famous pirate Redbeard master ship sunk in 17-th century in the Sea Of 1000 Islands after a fierce
battle with Bluebeard’s ship. The sunken ship contains a large bounty. You are navigating the Sea Of 1000
Islands in your ship provided with modern technology. The Sea Of 1000 Islands has been mapped into a
grid where a navigable square is denoted by a dot (”.”), and a square that belongs to one of many islands is
denoted by a ”+”.

You have a number of sonar devices. If you drop a sonar device from your ship, if the treasure is in the range
of sonar, the device will give you the grid coordinates of the treasure. Once you have the coordinates, your
AI engine will run a clever algorithm, called A-star search algorithm, that is capable of plotting the shortest
way from the current position of your ship to the detected treasure avoiding the obstacles (in this case, the
obstacles are the numerous islands).

If the sonar device detects nothing, you can navigate in any direction you like (north, south, east, west,
north-east, north-west, south-east, south-west) one navigable square at a time. You can move as many
squares as you like. Whenever you feel like it, you drop another sonar. And so on until the treasure has
been found, or you run out of the sonars. Please note once a sonar has been dropped, it is not recoverable.
The goal of the game is to find the treasure and plot the navigation route to the treasure. If you run out of
the sonar devices before finding the treasure, you lose the game.
