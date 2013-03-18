package remnants

class Item {
    String name
    String description
    Boolean luggable

    static hasMany = [compatibleItems:Item, contains:Item]

    static constraints = {
        name blank: false
        description blank: false
    }

    String toString(){
        name
    }
}
