package remnant

class Pack {
    static hasMany = [items:Item]

    static belongsTo = [user:User]

    static constraints = {
    }
}
