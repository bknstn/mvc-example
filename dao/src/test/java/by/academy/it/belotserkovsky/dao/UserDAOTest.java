package by.academy.it.belotserkovsky.dao;

import by.academy.it.belotserkovsky.dto.UserDTO;
import by.academy.it.belotserkovsky.pojos.User;
import by.academy.it.belotserkovsky.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Kostya on 09.05.2016.
 */
public class UserDAOTest {
    private String PARAM_NAME_LOGIN = "login";
    private String PARAM_NAME_PASS = "password";
    private UserDAO userDAO = new UserDAO();
    private Session session = null;
    private Transaction transaction = null;

    @Before
    public void before(){
        session = HibernateUtil.getSession();
        transaction = session.beginTransaction();
    }


    @Test
    public void getDTO() throws Exception {
        UserDTO udto = userDAO.getDTO(Long.parseLong("1"));
        assertNotNull(udto);
        HibernateUtil.closeSession();

    }

    @Test
    public void get()throws Exception {
        User user = userDAO.get(PARAM_NAME_LOGIN, PARAM_NAME_PASS);
        assertNotNull(user);
    }

    @Test
    public void getAll() throws Exception{
        List<UserDTO> list = userDAO.getAll(0,1);
        assertNotNull(list);
    }

    @Test
    public void getFoundRows(){
        int result = userDAO.getFoundRows();
        assertEquals((result > 0), true);
    }

    @After
    public void after() {
        if(transaction != null){
            transaction.commit();
        }
        HibernateUtil.closeSession();
    }
}