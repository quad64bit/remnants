package remnants

class Pack {
    static hasMany = [items:Item]

    static belongsTo = [user:User]

    static constraints = {
    }

    String toString(){
        "${user.username}'s Pack"
    }
}
