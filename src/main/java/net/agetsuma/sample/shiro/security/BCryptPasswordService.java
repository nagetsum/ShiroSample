/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.agetsuma.sample.shiro.security;

import org.apache.shiro.authc.credential.PasswordService;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Implementation of the {@link PasswordService} interface 
 * that relies on {@link BCrypt}.
 * @see <a href="http://www.mindrot.org/projects/jBCrypt/">jBCrypt</a>
 * @author Norito AGETSUMA
 */
public class BCryptPasswordService implements PasswordService {

    public static final int DEFAULT_BCRYPT_ROUND = 10;
    private int logRounds;

    public BCryptPasswordService() {
        this.logRounds = DEFAULT_BCRYPT_ROUND;
    }

    public BCryptPasswordService(int logRounds) {
        this.logRounds = logRounds;
    }

    @Override
    public String encryptPassword(Object plaintextPassword) {
        if (plaintextPassword instanceof String) {
            String password = (String) plaintextPassword;
            return BCrypt.hashpw(password, BCrypt.gensalt(logRounds));
        }
        throw new IllegalArgumentException("BCryptPasswordService encryptPassword only support java.lang.String credential.");
    }

    @Override
    public boolean passwordsMatch(Object submittedPlaintext, String encrypted) {
        if (submittedPlaintext instanceof char[]) {
            String password = String.valueOf((char[]) submittedPlaintext);
            return BCrypt.checkpw(password, encrypted);
        }
        throw new IllegalArgumentException("BCryptPasswordService passwordsMatch only support char[] credential.");
    }

    public void setLogRounds(int logRounds) {
        this.logRounds = logRounds;
    }

    public int getLogRounds() {
        return logRounds;
    }

}
