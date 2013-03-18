package remnants

class GameConfig {
    String name
    String description

    Interlude intro
    Room firstRoom
    Room winRoom
    Room loseRoom

    static constraints = {
        firstRoom nullable: false
        winRoom nullable: false
        loseRoom nullable: false
        intro nullable: false
    }
}
