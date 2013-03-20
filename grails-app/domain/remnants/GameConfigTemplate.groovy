package remnants

class GameConfigTemplate {
    String name
    String description
    Long firstRoomID = -1
    Interlude intro

    static hasMany = [rooms:RoomTemplate, items:ItemTemplate]

    static constraints = {
        intro nullable: true
    }

    def setIntro(intro){
        this.intro = intro
        intro.game = this
    }

    String toString(){
        name
    }
}
