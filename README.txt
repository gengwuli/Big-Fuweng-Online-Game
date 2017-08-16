**Project Final is created by Gengwu Li and Henry Lin** 

To run the program, simply run MainController.launch in the root folder.

Design Description:
	Milestone is based on HW08 API, the change we make is simply add a 
	GameDataPacketAlgoCmd and GameInfo, The GameInfo contains all
	the information of all players in that game. So in the remote server
	side, the server will create a MapPanel and the GameInfo will be reflected
	on the MapPanel and then appended to the view.
	
	At the beginning, a GameInfo message is being sent to remote server which
	is basically an unknown data type to the remote server.
	The remote server doesn't know it, so it ask for the command to execute 
	the task. A GameDataPacketAlgoCmd is sent back and then the MapPanel is 
	created and initialized with the GameInfo and displayed in the view. 
	
Game Description:
	-Name:
		Big Fu-Weng
	-Goal of the Game:
		Players/Teams travel around the world to buy estates and pay for tolls
		when they stop by others estates. The player/team who still has money 
		until others go bankrupted is the winner, Big Fu-Weng. Player/Team who 
		loses his/her all money loses the game.
	-Game Play:
		Players should try hard to buy as many estates on the earth as possible. 
		Owning estates grants him/her the right to take toll(money) from other 
		players whenever they stop by his/her estates. Every player knows who 
		owns which estates and how much money other players/teams have.
	-Collaborative Play:
		Money owned by players can be share within a team to ensure players in 
		the same team stays in the game. Moreover, a player doesn't need to pay
		for the toll when he/she stops by estates owned by other player in the 
		same team.
	-Using GIS:
		The estates are marked on the GIS system. All players travel around 
		the world, which is provided by the NASA GIS system, to proceed the game.
		