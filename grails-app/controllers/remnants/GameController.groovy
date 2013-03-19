package remnants

import grails.plugins.springsecurity.Secured

class GameController {
    def gameSessionService
    def springSecurityService

    def index() { }

    @Secured(['ROLE_USER'])
    def startGame(){
        initGame(getUser())
        [intro:getUser().currentGame.intro.text]
    }

    @Secured(['ROLE_USER'])
    def clientAction(){
        render "<p style='margin:0px;'>> ${params.actionText}</p>"
        processCommand(params.actionText)
    }

    @Secured(['ROLE_USER'])
    def processCommand(String command){
        switch(command){
            case 'help' :
                pp getHelp(); break
            case 'look' :
                pp getCurrentRoomDescription(); break
            case 'search':
                pp getRoomItems(); break
            case 'exits':
                pp listExits(); break
        }
    }

    private String getHelp(){
        "Commands are:<br/>- help (this list)<br/>- look (Describe the room)<br/>- search (Find items in the current room)<br/>- exits (Show exits to other rooms)"
    }

    private String listExits(){
        initGame(getUser())
        String rooms = ""
        getUser().currentRoom.exits.each{ room ->
            rooms += "- ${room.name}"
        }
        rooms
    }

    private pp(String str){
        render "<p style='margin:0px;'>${str}</p>"
    }

    private getRoomItems() {
        initGame(getUser())
        def items = getUser().currentRoom.items
        String itemStr = "You found:<br/>"
        items.each{
            itemStr += "- ${it}<br/>"
        }
        itemStr
    }

    private User getUser(){
        springSecurityService.getCurrentUser()
    }

    private String getCurrentRoomDescription(){
        initGame(getUser())
        Room currentRoom = getUser().currentRoom
        "== ${currentRoom.name} ==<br/>${currentRoom.description}"
    }

    private void initGame(User u){
        if (!u.currentGame){
            u.currentGame = GameConfig.load(1)
        }

        if (!u.currentRoom){
            u.currentRoom = Room.load(u.currentGame.firstRoomID)
        }

        assert u.save()
    }
}
