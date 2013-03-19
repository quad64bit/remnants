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
}
