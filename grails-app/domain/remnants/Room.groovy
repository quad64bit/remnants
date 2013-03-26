package remnants

class Room {
    transient static def stringToolsService

    String name
    String description
    Boolean hidden = false
    RoomTemplate template
    String searchName

    static hasMany = [exits:Room, items:Item]
    static belongsTo = [game:GameConfig]

    static constraints = {
        description maxSize: 2048
        searchName nullable: true
    }

    def beforeUpdate = {
        searchName = stringToolsService.cleanAndCatName(name)
    }

    def beforeInsert = {
        searchName = stringToolsService.cleanAndCatName(name)
    }

    String toString(){
        name
    }
}
