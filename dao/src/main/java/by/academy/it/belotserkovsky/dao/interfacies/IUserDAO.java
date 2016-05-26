package by.academy.it.belotserkovsky.dao.interfacies;

import by.academy.it.belotserkovsky.pojos.User;
import java.util.List;

/**
 * Created by K.Belotserkovsky
 */
public interface IUserDao extends Dao<User>{

    /**
     * @param login
     * @param password
     * @return
     */
    User getByLoginPass(String login, String password);

    /**
     * This method use for pagination
     * @param offset
     * @param numberOfRecords
     * @return List<User>
     */
    List<User> getAll(int offset, int numberOfRecords);

    /**
     * @return Number of rows in user's table (for pagination)
     */
    int getFoundRows();
}