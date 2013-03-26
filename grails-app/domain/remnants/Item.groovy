package remnants

class Item {
    transient def stringToolsService

    String name
    String description
    Boolean lugable
    ItemTemplate template
    String searchName

    static hasMany = [compatibleItems:Item, contains:Item]
    static belongsTo = [game:GameConfig]

    static constraints = {
        name blank: false
        description blank: false, maxSize: 2048
        template nullable: false
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
