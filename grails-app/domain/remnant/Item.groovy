package remnant

class Item {
    String name
    String description

    static hasMany = [compatibleItems:Item]

    static constraints = {
        name blank: false
        description blank: false
    }
}
