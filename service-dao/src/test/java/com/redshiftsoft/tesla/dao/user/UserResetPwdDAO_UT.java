package com.redshiftsoft.tesla.dao.user;

import com.redshiftsoft.tesla.dao.DAOConfiguration;
import com.redshiftsoft.tesla.dao.TestUsers;
import com.redshiftsoft.util.RandomUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DAOConfiguration.class)
public class UserResetPwdDAO_UT {

    @Resource
    private UserResetPwdDAO userResetPwdDAO;
    @Resource
    private UserDAO userDAO;
    @Resource
    private JdbcTemplate template;

    @Test
    public void insertKey_validateKey_markUsed() {
        /* Generate user */
        User user = TestUsers.createUser();
        userDAO.insert(user);

        /* Insert key */
        String key = userResetPwdDAO.insertKey(user.getId());
        assertEquals(key.length(), 26);

        /* Now test getting */
        ResetPwdResult result = userResetPwdDAO.validateKey(key);
        assertNotNull(result);
        assertEquals(result.getUserId(), user.getId());

        /* Mark used */
        userResetPwdDAO.markUsed(user.getId());
        result = userResetPwdDAO.validateKey(key);
        assertNotNull(result);
        assertTrue(result.isUsed());
    }

    @Test
    public void validateKey_random() {
        ResetPwdResult result = userResetPwdDAO.validateKey(RandomUtils.fast().getString(12, 'a', 'z'));
        assertNull(result);
    }

    @Test
    public void delete() {
        // given nothing older than 14 days in database
        /* Generate user */
        User user = TestUsers.createUser();
        userDAO.insert(user);
        userResetPwdDAO.deleteOld(14);
        int count2 = userResetPwdDAO.deleteOld(14);
        assertEquals(0, count2);

        // given -- an old entry or two
        template.update("insert into user_reset_password values(?,?,'2016-01-01',null)", user.getId(), "abcd123456789a");
        template.update("insert into user_reset_password values(?,?,'2017-01-01',null)", user.getId(), "abcd123456789b");
        template.update("insert into user_reset_password values(?,?,'2018-01-01',null)", user.getId(), "abcd123456789c");

        // when
        int count3 = userResetPwdDAO.deleteOld(14);

        // then
        assertEquals(3, count3);

    }
}
