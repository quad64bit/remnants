package remnants

class GameController {
    def gameSessionService

    def index() { }

    def startGame(){ }

    def clientAction(){
        println params

        render "<p style='margin:0px;'>> ${params.actionText}</p>"
        render "<p style='margin:0px;'>The game did something based on this</p>"
    }
}
