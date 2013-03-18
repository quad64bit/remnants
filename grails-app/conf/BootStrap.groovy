import remnants.*

class BootStrap {

    def init = { servletContext ->
        log.info "Creating initial items"
        def shoe = new Item(  luggable: true,  name:"Old Brown Shoe",       description: "This is the nastiest shoe ever").save()
        def beaker = new Item(luggable: true,  name:"Cracked class beaker", description: "The edges are sharp, and might cut me").save()
        def banana = new Item(luggable: true,  name:"Rotten Banana",        description: "I shouldn't eat this").save()
        def can = new Item(   luggable: true,  name:"Rusty Can",            description: "Looks like it used to contain peaches").save()
        def finger = new Item(luggable: true,  name:"Severed Finger",       description: "It's rotten.").save()
        def box = new Item(   luggable: false, name:"Heavy wooden box",     description: "It's too heavy to lift, but maybe I can open it").save()
        def book = new Item(  luggable: true,  name:"Dusty tome",           description: "What strange ink, could it be blood?").save()

        box.addToContains(finger)

        def drawingRoom = new Room(name: "Drawing Room", description: "The room smells of must and rotten leather.").save()
        def parlor = new Room(name: "Front Parlor", description: "The damp draft coming in from the fireplace is intolerable").save()
        def kitchen = new Room(name: "Kitchen", description: "Nothing wholesome could be prepared here").save()

        drawingRoom.addToExits(parlor)
        parlor.addToExits(drawingRoom)
        kitchen.addToExits(drawingRoom)
        drawingRoom.addToExits(kitchen)

        drawingRoom.addToItems(book)
        drawingRoom.addToItems(box)

        kitchen.addToItems(banana)
        kitchen.addToItems(can)

        parlor.addToItems(shoe)
        parlor.addToItems(beaker)
        log.info "Initial items created"
    }
    def destroy = {
    }
}
