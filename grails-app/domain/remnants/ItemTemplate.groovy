package remnants

class ItemTemplate {
    String name
    String description
    Boolean lugable

    static constraints = {
        name blank: false
        description blank: false, maxSize: 2048
    }

    static hasMany = [compatibleItems:ItemTemplate, contains:ItemTemplate]
    static belongsTo = [game:GameConfigTemplate]

    String toString(){
        name
    }
}
