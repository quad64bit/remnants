package remnants

class StringToolsService {
    static transactional = false

    def String cleanAndCatName(String name){
        name.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    }
}
