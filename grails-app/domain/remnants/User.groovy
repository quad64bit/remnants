package remnants

class User {
    transient springSecurityService

    Room currentRoom
    GameConfig currentGame
    Pack pack

    String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

    static hasMany = [games:GameConfig, gameTemplates:GameConfigTemplate]

	static constraints = {
		username blank: false, unique: true
		password blank: false
        pack nullable: true
        currentRoom nullable: true
        currentGame nullable: true
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}

    String toString(){
        username
    }
}
