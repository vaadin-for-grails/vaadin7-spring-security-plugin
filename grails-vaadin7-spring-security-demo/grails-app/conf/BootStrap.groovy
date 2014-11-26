import demo.Role
import demo.User
import demo.UserRole

class BootStrap {

    def init = { servletContext ->

        def admins = Role.findOrSaveByAuthority("ROLE_ADMIN")
        def users = Role.findOrSaveByAuthority("ROLE_USER")

        def admin = User.findOrSaveByUsernameAndPassword("admin", "admin")
        def user = User.findOrSaveByUsernameAndPassword("user", "user")

        UserRole.create(admin, admins, true)
        UserRole.create(user, users, true)
    }
    def destroy = {
    }
}
