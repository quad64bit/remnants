import remnants.*

class BootStrap {

    def init = { servletContext ->
        if (ItemTemplate.count() == 0) {
//Construction ---------------------------------------------------------
            println "Creating example game"
            GameConfigTemplate gameTemplate = new GameConfigTemplate(
                name: "Testing the Waters",
                description: "How does it feel to see what others cannot?",
            ).save()
            Interlude intro = new Interlude(text: "You awake on the floor of a strange room... A parlor of some sort.", game: gameTemplate).save()
            gameTemplate.intro = intro
            gameTemplate.save()
            assert gameTemplate
            assert intro

            println "Creating initial items"
            def shoe = new ItemTemplate(game: gameTemplate, lugable: true, name: "Old Brown Shoe", description: "This is the nastiest shoe ever").save()
            def beaker = new ItemTemplate(game: gameTemplate, lugable: true, name: "Cracked glass beaker", description: "The edges are sharp, and might cut me").save()
            def banana = new ItemTemplate(game: gameTemplate, lugable: true, name: "Rotten Banana", description: "I shouldn't eat this").save()
            def can = new ItemTemplate(game: gameTemplate, lugable: true, name: "Rusty Can", description: "Looks like it used to contain peaches").save()
            def finger = new ItemTemplate(game: gameTemplate, lugable: true, name: "Severed Finger", description: "It's rotten.").save()
            def box = new ItemTemplate(game: gameTemplate, lugable: false, name: "Heavy wooden box", description: "It's too heavy to lift, but maybe I can open it").save()
            def book = new ItemTemplate(game: gameTemplate, lugable: true, name: "Dusty tome", description: "What strange ink, could it be blood?").save()
            def smallerBox = new ItemTemplate(game: gameTemplate, lugable: true, name: "Smaller Box", description: "It's an even smaller box!").save()

            assert shoe

            box.addToContains(smallerBox)
            smallerBox.addToContains(finger)

            println box.contains
            println smallerBox.contains

            def drawingRoom = new RoomTemplate(game: gameTemplate, name: "Drawing Room", description: "The room smells of must and rotten leather.").save()
            def parlor = new RoomTemplate(game: gameTemplate, name: "Front Parlor", description: "The damp draft coming in from the fireplace is intolerable").save()
            def kitchen = new RoomTemplate(game: gameTemplate, name: "Kitchen", description: "Nothing wholesome could be prepared here").save()

            assert kitchen

            drawingRoom.addToExits(parlor)
            drawingRoom.addToExits(kitchen)

            parlor.addToExits(drawingRoom)
            kitchen.addToExits(drawingRoom)
            drawingRoom.addToExits(kitchen)

            drawingRoom.addToItems(book)
            drawingRoom.addToItems(box)

            kitchen.addToItems(banana)
            kitchen.addToItems(can)

            parlor.addToItems(shoe)
            parlor.addToItems(beaker)
            println "Initial items created"

            println "Creating initial roles"
            def adminRole = new Role(authority: "ROLE_ADMIN").save()
            def userRole = new Role(authority: "ROLE_USER").save()

            assert userRole
            println "Roles created"

            println "Creating initial users"
            def adminUser = new User(username: "admin", password: "password", enabled: true).save()
            def userUser = new User(username: "user", password: "password", enabled: true).save()
            assert userUser

            userUser.pack = new Pack(user: userUser)
            userUser.save()
            assert userUser

            UserRole.create(adminUser, adminRole, true)
            UserRole.create(userUser, userRole, true)

            println "Users created"

            gameTemplate.firstRoomID = parlor.id
            gameTemplate.save()
            RoomTemplate.list().each { roomTemplate ->
                gameTemplate.addToRooms(roomTemplate)
            }
            assert gameTemplate

            ItemTemplate.list().each{ itemTemplate ->
                gameTemplate.addToItems(itemTemplate)
            }

// Cloning ------------------------------------------------------------------------

            println "Loading level"
            //Make a new game instance clone of the master
            GameConfig gameInstance = new GameConfig(
                template: gameTemplate,
                name: gameTemplate.name,
                description: gameTemplate.description,
                intro: gameTemplate.intro,
                user: userUser
            )
            gameInstance.save()
            assert gameInstance

            def roomMap = [:]
            def itemMap = [:]

            def fr = RoomTemplate.get(gameTemplate.firstRoomID)
            assert fr != null
            def room = new Room(
                name: fr.name,
                description: fr.description,
                hidden: fr.hidden,
                template: fr,
                game: gameInstance
            )
            room.save()
            roomMap[fr.id] = room.id
            assert room != null

            gameInstance.firstRoomID = room.id


            println "Cloning room templates"
            //Clone the template rooms
            gameTemplate.rooms.each { roomTemplate ->
                //Skip first room since it's already there
                if (roomTemplate.id != gameTemplate.firstRoomID) {
                    //Make a new room based on the master room
                    Room tempRoom = new Room(
                        name: roomTemplate.name,
                        description: roomTemplate.description,
                        hidden: roomTemplate.hidden,
                        template: roomTemplate,
                        game: gameInstance
                    ).save()
                    assert tempRoom.id != null

                    roomMap[roomTemplate.id] = tempRoom.id

                    gameInstance.addToRooms(tempRoom)
                }
            }
            println "Cloning item templates"
            //Clone all the items
            gameTemplate.items.each { itemTemplate ->
                Item tempItem = new Item(
                    name: itemTemplate.name,
                    description: itemTemplate.description,
                    lugable: itemTemplate.lugable,
                    template: itemTemplate,
                    game: gameInstance
                ).save()
                itemMap[itemTemplate.id] = tempItem.id
                gameInstance.addToItems(tempItem)
            }

            println "Adding items to rooms"
            //Add items to each room
            gameTemplate.rooms.each{ roomTemplate ->
                Room roomInstance = Room.get(roomMap[roomTemplate.id])
                assert roomInstance
                roomTemplate.exits.each{ roomTemplateExit ->
                    roomInstance.addToExits(Room.get(roomMap[roomTemplateExit.id]))
                }
                roomTemplate.items.each{ itemTemplate ->
                    roomInstance.addToItems(Item.get(itemMap[itemTemplate.id]))
                    itemDive(itemTemplate, Item.get(itemMap[itemTemplate.id]), itemMap)
                }
            }

            userUser.addToGames(gameInstance)
            println "Loading Complete"
        }
    }

    private void itemDive(ItemTemplate itemTemplate, Item item, Map mapping){
        itemTemplate.contains.each { cItem ->
            Item subItem = Item.get(mapping[cItem.id])
            item.addToContains(subItem)
            itemDive(cItem, subItem, mapping)
        }
    }

    def destroy = {
    }
}
