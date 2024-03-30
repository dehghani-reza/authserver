import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

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
    def insertSql = "INSERT INTO oauth2_registered_client " +
            "(id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name, client_authentication_methods, authorization_grant_types, redirect_uris, post_logout_redirect_uris, scopes, client_settings,token_settings) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)"
    println insertSql
    println 'secret hash in bcrypt ->   $2a$10$jdJGhzsiIqYFpjJiYWMl/eKDOd8vdyQis2aynmFN0dgJ53XvpzzwC'
    def params = [
            UUID.randomUUID().toString(),
            'client',
            Instant.now(),
            '$2a$10$jdJGhzsiIqYFpjJiYWMl/eKDOd8vdyQis2aynmFN0dgJ53XvpzzwC',
            Instant.ofEpochSecond(LocalDate.of(2025, 1, 1).atStartOfDay(ZoneId.systemDefault()).toEpochSecond()),
            'sample',
            'client_secret_basic',
            'authorization_code',
            'http://127.0.0.1:8082/login/oauth2/code/spring',
            'http://127.0.0.1:8082/login/oauth2/code/spring',
            'user.read,user.write,openid',
            '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":true}',
            '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",300.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000],"settings.token.device-code-time-to-live":["java.time.Duration",300.000000000]}'
    ]
    println params.toString()
    def keys = db.executeInsert insertSql, params
} catch (Exception e) {
    e.printStackTrace()
}
println "finish creating sample user"