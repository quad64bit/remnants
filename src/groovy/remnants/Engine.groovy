package remnants

/**
 * Created with IntelliJ IDEA.
 * User: Steffen Gates
 * Date: 3/19/13
 * Time: 10:54 PM
 * To change this template use File | Settings | File Templates.
 */
class Engine {
    static def String submitActionStatement(String actionStatement){
        List commandList = CommandParser.getCommandList(actionStatement)
        ActionProcessor.processCommandList(commandList)
    }
}
