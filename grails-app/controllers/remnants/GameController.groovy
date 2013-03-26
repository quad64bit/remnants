package remnants

import grails.plugins.springsecurity.Secured

class GameController {
    def springSecurityService

    def index() { }

    @Secured(['ROLE_USER'])
    def startGame(){
        initGame()
        [intro:"<p class='sepia'>${getUser().currentGame.intro.text}</p>"]
    }

    @Secured(['ROLE_USER'])
    def clientAction(){
        render "<p style='margin:0px;'>> ${params.actionText}</p>"
        processAction(params.actionText.trim())
    }

    @Secured(['ROLE_USER'])
    def processAction(String actionString){
        pp Engine.submitActionStatement(actionString)
    }

//    @Secured(['ROLE_USER'])
//    def processCommand(String command){
//        switch(command){
//            case 'help' :
//                pp getHelp(); break
//            case 'look' :
//                pp getCurrentRoomDescription(); break
//            case 'search':
//                pp getRoomItems(); break
//            case 'exits':
//                pp listExits(); break
//        }
//    }


    private pp(String str){
        render "<p class='sepia' style='margin:0px;'>${str}</p>"
    }

    private User getUser(){
        springSecurityService.getCurrentUser()
    }

    private void initGame(){
        User u = getUser()
        if (!u.currentGame){
            u.currentGame = GameConfig.load(1)
        }

        if (!u.currentRoom){
            u.currentRoom = Room.load(u.currentGame.firstRoomID)
        }

        assert u.save()
    }
}
