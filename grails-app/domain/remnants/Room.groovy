package remnants

class Room {
    transient static def stringToolsService

    String name
    String description
    Boolean hidden = false
    RoomTemplate template
    String searchName

    Room north
    Room south
    Room east
    Room west
    Room up
    Room down

    static hasMany = [items:Item]
    static belongsTo = [game:GameConfig]

    static constraints = {
        description maxSize: 2048
        searchName nullable: true
        north nullable: true
        south nullable: true
        east nullable: true
        west nullable: true
        up nullable: true
        down nullable: true
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

    String getExitString(){
        def exits = north ? "<span>(North) ${north.name}</span><br/>"   : ""
        exits +=    east ?  "<span>(East) ${east.name}</span><br/>"     : ""
        exits +=    south ? "<span>(South) ${south.name}</span><br/>"   : ""
        exits +=    west ?  "<span>(West) ${west.name}</span><br/>"     : ""
        exits +=    up ?    "<span>(Up) ${up.name}</span><br/>"         : ""
        exits +=    down ?  "<span>(Down) ${down.name}</span><br/>"     : ""

        "<span>== ${name} ==</span><br/>${exits}"
    }

    Map getExits(){
        [north:north, south:south, east:east, west:west, up:up, down:down]
    }

    boolean hasExit(Room dest){
        def exits = getExits()
        exits.values().each { exit ->
            if(exit == dest){
                return true
            }
        }
        return false
    }
}
