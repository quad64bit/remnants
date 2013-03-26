package remnants

class ActionProcessor {
    private static def springSecurityService
    private static def stringToolsService

    static {
        springSecurityService = ApplicationContextHolder.getBean("springSecurityService");
        stringToolsService = ApplicationContextHolder.getBean("stringToolsService")
    }

    static def processCommandList(commandList) {
        String commandPattern = buildPattern(commandList)
        if (!commandPattern.contains("unknown")) {
            return processPattern(commandPattern, commandList)
        } else {
            return "Unknown command!\nCommand List: ${commandList}\nCommandPattern:${commandPattern}"
        }
    }

    private static buildPattern(List commandList) {
        String commandPattern = ""
        commandList.each { commandToken ->
            commandPattern += "${determineType(commandToken)} "
        }
        commandPattern.trim()
    }

    private static String determineType(String commandToken) {
        switch (commandToken) {
            case { isAction(it) }: return isAction(commandToken)
            case { isDirection(it) }: return "direction"
            case { isItem(it) }: return "item"
            case { isRoom(it) }: return "room"
            default: "unknown"
        }
    }

    private static final List directions = ["north", "south", "east", "west", "up", "down", "under", "over", "above", "below"]

    private static boolean isDirection(String potentialDir) {
        potentialDir in directions
    }

    private static final List actions = (CommandParser.commandTable.values() as List).unique()

    private static String isAction(String potentialAction) {
        potentialAction in actions ? potentialAction : null
    }

    private static boolean isRoom(String potentialRoom) {
        Room.findBySearchNameIlike("%${potentialRoom}%") != null
    }

    private static boolean isItem(String potentialItem) {
        Item.findBySearchNameIlike("%${potentialItem}%") != null
    }

    private static String processPattern(String pattern, List commandList) {
        def result = combineSplitNames(pattern, commandList)
        pattern = result.pattern
        commandList = result.commandList

        switch (pattern) {
            case "go room":              //go bedroom
                return goLoc(commandList[1])
            case "go direction":        //go north | go up
                return goDir(commandList[1])
            case "go direction item":    //go up ladder
                return goDirItem(commandList[1], commandList[2])
            case "look":                //look
                return look()
            case "look item":            //look spoon
                return lookItem(commandList[1])
            case "look direction":      //look down
                return lookDir(commandList[1])
            case "look direction item":  //look up ladder
                return lookDirItem(commandList[1], commandList[2]);
            case "use item":             //use gun
                return useItem(commandList[1])
            case "craft item":           //crush can
                return craftItem(commandList[1])
            case "craft item item":       //combine batteries and flashlight
                return craftItemItem(commandList[1], commandList[2])
            case "craft item item item":   //combine bucket water soap
                return craftItemItemItem(commandList[1], commandList[2], commandList[3])
            case "take item":                //pickup cloth
                return takeItem(commandList[1])
            case "help":
                return help()
            case "search":
                return search()
        }
    }

    private static combineSplitNames(String pattern, List commandList) {
        def parts = pattern.split(" ") as List
        int firstRoom = 0
        int lastRoom = 0

        //Identify first room location
        while (firstRoom < parts.size() && parts[firstRoom] != "room") {
            firstRoom++
        }
        lastRoom = firstRoom    //Start last room index at first room
        //Find last room (consecutive)
        while (lastRoom < parts.size() && parts[lastRoom] == "room") {
            lastRoom++
        }
        //Step back 1 room because we went past the index to terminate the loop
        lastRoom--

        //If the last room is in fact beyond the first room
        if (lastRoom > firstRoom) {
            //while the indexes are not aligned
            while (lastRoom > firstRoom) {
                String cat = commandList[firstRoom..lastRoom].join()        //join the names together
                cat = stringToolsService.cleanAndCatName(cat)
                Room realRoom = Room.findBySearchNameIlike("%${cat}%")
                //if a room is still found, then all the parts cat'ed together form the whole room name
                if (realRoom) {
                    //we need to replace the separate elements with the new single element
                    List sub = commandList[firstRoom..lastRoom]
                    sub.clear()
                    sub << cat

                    sub = parts[firstRoom..lastRoom]
                    sub.clear()
                    sub << "room"
                    pattern = parts.join(" ")
                    break
                } else {
                    lastRoom--
                }
            }
        }

        //Items --------------------------------------------------
        int firstItem = 0
        int lastItem = 0

        //Identify first Item location
        while (firstItem < parts.size() && parts[firstItem] != "item") {
            firstItem++
        }
        lastItem = firstItem    //Start last item index at first item
        //Find last item (consecutive)
        while (lastItem < parts.size() && parts[lastItem] == "item") {
            lastItem++
        }
        //Step back 1 item because we went past the index to terminate the loop
        lastItem--

        //If the last item is in fact beyond the first item
        if (lastItem > firstItem) {
            //while the indexes are not aligned
            while (lastItem > firstItem) {
                String cat = commandList[firstItem..lastItem].join()        //join the names together
                cat = stringToolsService.cleanAndCatName(cat)
                Item realItem = Item.findBySearchNameIlike("%${cat}%")
                //if a item is still found, then all the parts cat'ed together form the whole item name
                if (realItem) {
                    //we need to replace the separate elements with the new single element
                    List sub = commandList[firstItem..lastItem]
                    sub.clear()
                    sub << cat

                    sub = parts[firstItem..lastItem]
                    sub.clear()
                    sub << "item"
                    pattern = parts.join(" ")
                    break
                } else {
                    lastItem--
                }
            }
        }

        return [pattern: pattern, commandList: commandList]
    }

    private static String search() {
        initGame()
        "${getCurrentRoomName()}<br/>You found:<br/>${listCurrentRoomItems()}"
    }

    private static String listExits() {
        initGame()
        String rooms = ""
        getUser().currentRoom.exits.each { room ->
            rooms += "- ${room.name}<br/>"
        }
        rooms
    }

    private static String goLoc(String location) {
        initGame()
        Room dest = Room.findBySearchNameIlike(location)
        if (getCurrentRoom().exits.contains(dest)) {
            setCurrentRoom(dest)
            return "You enter the ${dest.name}."
        } else {
            return "You cannot reach ${location} from here."
        }
    }

    private static String help() {
        return """Available Commands:<br/>- ${CommandParser.commandTable.keySet().join('<br/>- ')}"""
    }

    private static String goDir(String direction) {
        return "goDir $direction"
    }

    private static String goDirItem(String direction, String item) {
        return "goDirItem $direction $item"
    }

    private static String look() {
        String exitList = "<strong>Exits:</strong><br/>${listExits()}"
        getCurrentRoomDescription() + exitList
    }

    private static String lookItem(String itemName) {
        Item item = Item.findBySearchNameIlike("%${itemName}%")
        if (item){
            return "== ${item.name} ==<br/>${item.description}"
        } else{
            return "I can't find anything by that name."
        }
    }

    private static String lookDir(String direction) {
        return "lookDir $direction"
    }

    private static String lookDirItem(String direction, String item) {
        return "lookDirItem $direction $item"
    }

    private static String useItem(String item) {
        return "useItem $item"
    }

    private static String craftItem(String item) {
        return "craftItem $item"
    }

    private static String craftItemItem(String item1, String item2) {
        return "craftItemItem $item1 $item2"
    }

    private static String craftItemItemItem(String item1, String item2, String item3) {
        return "craftItemItemItem $item1 $item2 $item3"
    }

    private static String takeItem(String item) {
        return "takeItem $item"
    }

    private static listCurrentRoomItems() {
        initGame()
        def items = getUser().currentRoom.items
        String itemStr = ""
        items.each {
            itemStr += "- ${it}<br/>"
        }
        itemStr
    }

    private static User getUser() {
        springSecurityService.getCurrentUser()
    }

    private static String getCurrentRoomDescription() {
        initGame()
        Room currentRoom = getUser().currentRoom
        "<span class='red'><strong>== ${currentRoom.name} ==</strong></span><br/>${currentRoom.description}<br/>"
    }

    private static Room getCurrentRoom() {
        initGame()
        getUser().currentRoom
    }

    private static String getCurrentRoomName() {
        "== ${getCurrentRoom().name} =="
    }

    private static Room getCurrentGame() {
        getUser().currentGame
    }

    private static void setCurrentRoom(Room dest) {
        User u = getUser()
        u.currentRoom = dest
        u.save()
    }

    private static void initGame() {
        User u = getUser()
        if (!u.currentGame) {
            u.currentGame = GameConfig.load(1)
        }

        if (!u.currentRoom) {
            u.currentRoom = Room.load(u.currentGame.firstRoomID)
        }

        assert u.save()
    }
}