package remnants

class Room {
    String name
    String description
    Boolean hidden = false

    static hasMany = [exits:Room, items:Item]

    static constraints = {
    }

    String toString(){
        name
    }
}
