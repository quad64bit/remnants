package remnants

class Item {
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
        updateSearchName()
    }

    def beforeInsert = {
        updateSearchName()
    }

    private void updateSearchName(){
        searchName = name.replaceAll("[^a-zA-Z0-9]", "");
    }

    String toString(){
        name
    }
}
