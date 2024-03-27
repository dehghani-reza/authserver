@Grab('com.mysql:mysql-connector-j:8.3.0')
@GrabConfig(systemClassLoader = true)
import groovy.sql.*

println "start creating sample user"
try {
    def dbURL = 'jdbc:mysql://localhost:3306/auth?sessionVariables=sql_mode=\'NO_ENGINE_SUBSTITUTION\'&jdbcCompliantTruncation=false'
    def dbUserName = 'root'
    def dbPassword = 'root'
    def dbDriver = 'com.mysql.cj.jdbc.Driver'
    def db = Sql.newInstance(dbURL, dbUserName, dbPassword, dbDriver)
    def insertSql = "INSERT INTO app_user " +
            "(id, version, USERNAME, PASSWORD, IS_ACCOUNT_EXPIRED, IS_ACCOUNT_LOCKED, IS_CREDENTIAL_EXPIRED, IS_ENABLE, ROLE) " +
            "VALUES (?,?,?,?,?,?,?,?,?)"
    println insertSql
    println '123 hash in bcrypt ->   $2a$10$XXaAXLsoMnieirmjXSlnzerdpfJlBLSRrzzQWFigtYnVrIhqL8Zxu'
    def params = [UUID.randomUUID().toString(), 0, 'groovy', '$2a$10$XXaAXLsoMnieirmjXSlnzerdpfJlBLSRrzzQWFigtYnVrIhqL8Zxu', false, false, false, true, 'ROLE_ADMIN,ROLE_EDITOR']
    println params.toString()
    def keys = db.executeInsert insertSql, params
} catch (Exception e) {
    e.printStackTrace()
}
println "finish creating sample user"