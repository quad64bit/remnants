package remnants

class RoomTemplate {
    String name
    String description
    Boolean hidden = false

    static hasMany = [exits:RoomTemplate, items:ItemTemplate]
    static belongsTo = [game:GameConfigTemplate]

    static constraints = {
        description maxSize: 2048
    }

    String toString(){
        name
    }
}
