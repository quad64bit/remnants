package remnants

class GameConfig {
    String name
    String description
    Long firstRoomID = -1
    Interlude intro

    GameConfigTemplate template

    static hasMany = [rooms:Room, items:Item]
    static belongsTo = [user:User]

    static constraints = {
        intro nullable: true
        template nullable: false
        user nullable: true
    }
}
