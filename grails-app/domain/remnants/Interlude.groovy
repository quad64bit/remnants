package remnants

class Interlude {
    String text

    static belongsTo = [game:GameConfigTemplate]

    static constraints = {
        text blank: false, maxSize: 5096
    }

    String toString(){
        name
    }
}
