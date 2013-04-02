package remnants

class RoomTemplate {
    String name
    String description
    Boolean hidden = false

    RoomTemplate north
    RoomTemplate south
    RoomTemplate east
    RoomTemplate west
    RoomTemplate up
    RoomTemplate down

    static hasMany = [items:ItemTemplate]
    static belongsTo = [game:GameConfigTemplate]

    static constraints = {
        description maxSize: 2048
        north nullable: true
        south nullable: true
        east nullable: true
        west nullable: true
        up nullable: true
        down nullable: true
    }

    String toString(){
        name
    }

    Map getExits(){
        [north:north, south:south, east:east, west:west, up:up, down:down]
    }
}
