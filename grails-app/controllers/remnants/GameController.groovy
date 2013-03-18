package remnants

class GameController {
    def gameSessionService

    def index() { }

    def startGame(){ }

    def clientAction(){
        println params

        gameSessionService.gameSession.newGame()

        render "<p style='margin:0px;'>> ${params.actionText}</p>"
        render "<p style='margin:0px;'>The game did something based on this</p>"
    }
}
