package remnants

/**
 * Created with IntelliJ IDEA.
 * User: Steffen Gates
 * Date: 3/19/13
 * Time: 5:02 PM
 * To change this template use File | Settings | File Templates.
 */
class CommandParser {

    static List getCommandList(String inputString){
        def tokens = tokenize(inputString)
        tokens = dropCrap(tokens)
        identifyCommandWords(tokens)
    }

    static private tokenize(String inputString) {
        inputString.toLowerCase().split(" ") as List
    }

    static private identifyCommandWords(List inputList){
        for(int i=0; i<inputList.size(); i++){
            String exchangeWord = commandTable[inputList[i]]
            if(exchangeWord){
                inputList[i] = exchangeWord
            }
        }
        inputList
    }

    static private List dropCrap(List inputList){
        List newSet = []
        inputList.each { word ->
            if(!crapWords.contains(word)){
                newSet << word
            }
        }
        newSet
    }

    static private final List crapWords = [
        "to",
        "with",
        "and"
    ]

    static final Map commandTable = [
        move:       "go",
        go:         "go",
        travel:     "go",
        walk:       "go",
        run:        "go",
        sprint:     "go",
        jump:       "go",
        climb:      "go",
        exits:      "look",
        look:       "look",
        examine:    "look",
        check:      "look",
        search:     "search",
        help:       "help",
        use:        "use",
        craft:      "craft",
        combine:    "craft",
        take:       "take",
        pickup:     "take",
        grab:       "take",
        steal:      "take",
        pocket:     "take"
    ]
}
