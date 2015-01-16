import lock.locker2.Role
import lock.locker2.User
import lock.locker2.UserRole

class BootStrap {

    def init = { servletContext ->
        def users = User.list () ?: []
        def user
        if (!users){
             user  = new User (
                    username: 'ben',
                    password: 'ben')
            user.save()
        }
        def userRole = Role.findByAuthority('ROLE_USER')  ?: new Role (authority: "ROLE_USER").save()
        UserRole.create user,userRole
    }
    def destroy = {
    }
}
