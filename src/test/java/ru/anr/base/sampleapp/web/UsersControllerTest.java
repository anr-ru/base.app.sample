/**
 * 
 */
package ru.anr.base.sampleapp.web;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import ru.anr.base.sampleapp.services.users.UserModel;
import ru.anr.base.sampleapp.services.users.UserModelResponse;
import ru.anr.base.sampleapp.tests.BaseIntegrationTestCase;

/**
 * 'Users' REST services.
 *
 * @author Alexey Romanchuk
 * @created Nov 28, 2014
 *
 */

public class UsersControllerTest extends BaseIntegrationTestCase {

    /**
     * User registration
     * 
     * @param fullName
     *            Full name
     * @return User model
     */
    private UserModel register(String fullName) {

        UserModel m = new UserModel();

        m.setLogin(guid());
        m.setPassword("DerPassword");
        m.setFullName(fullName);

        ResponseEntity<String> r = client.post("/api/v1/users", json.toStr(m));
        Assert.assertEquals(HttpStatus.OK, r.getStatusCode());

        UserModelResponse rs = json.fromStr(r.getBody(), UserModelResponse.class);
        Assert.assertEquals(0, rs.getCode());

        return m;
    }

    /**
     * Use case : registration - supplied a user and password. Then - confirmed
     * authentication
     */
    @Test
    public void registration() {

        UserModel m = register("Alexey");

        try {
            client.get("/api/v1/users");
            Assert.fail();
        } catch (HttpClientErrorException ex) {
            Assert.assertEquals(HttpStatus.UNAUTHORIZED, ex.getStatusCode());
        }

        logger.debug("Applying Basic Authorization headers");
        client.setBasicCredentials(m.getLogin(), m.getPassword());

        ResponseEntity<String> r = client.get("/api/v1/users");

        UserModelResponse rs = json.fromStr(r.getBody(), UserModelResponse.class);
        Assert.assertEquals(m.getFullName(), rs.getFullName());
        Assert.assertEquals(m.getLogin(), rs.getLogin());
    }

    /**
     * Use case : registration - supplied a user and password. Then - confirmed
     * authentication
     */
    @Test
    public void updatingUser() {

        UserModel m = register("Alexey");

        String login = m.getLogin();
        String pwd = m.getPassword();

        m = new UserModel();
        m.setFullName("John");

        // Update
        client.setBasicCredentials(login, pwd);
        ResponseEntity<String> r = client.put("/api/v1/users", json.toStr(m));

        UserModelResponse rs = json.fromStr(r.getBody(), UserModelResponse.class);
        Assert.assertEquals("John", rs.getFullName());

        // reload
        r = client.get("/api/v1/users");

        rs = json.fromStr(r.getBody(), UserModelResponse.class);
        Assert.assertEquals("John", rs.getFullName());
        Assert.assertEquals(login, rs.getLogin());
    }
}
