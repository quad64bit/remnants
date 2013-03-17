/**
 * Created with IntelliJ IDEA.
 * User: Steffen Gates
 * Date: 3/17/13
 * Time: 9:58 AM
 * To change this template use File | Settings | File Templates.
 */
class Game {
    def withState(savedGameState){
        //start a game from the saved user game state
    }

    def processAction(action){
        switch(action.type){
            case 'move': move(action.destination); break;
            case 'open': open(action.object); break;
            case 'spell': spell(action.spell); break;
            case 'pickup': pickup(action.object); break;
            case 'combine': combine(action.objectList); break;
            case 'use':use(action.object); break;
        }
    }

    def move(destination){}
    def open(object){}
    def spell(spell){}
    def pickup(object){}
    def combine(itemList){}
    def use(item){}
}
