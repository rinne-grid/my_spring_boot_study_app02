package jp.co.rngd.ss.todo.entity;
 
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
 
@ConfigurationProperties(prefix="spring.datasource")
@Component
@Data
public class RngdDataSource {
 
    /** URL */
    private String url;
 
    /** ユーザー名 */
    private String username;
 
    /** パスワード */
    private String password;
    
    private String driverClassName;

}
