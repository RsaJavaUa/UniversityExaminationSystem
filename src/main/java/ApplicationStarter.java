import dao.UserDao;
import entities.User;

import java.util.List;

public class ApplicationStarter {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();

        List<User> all = userDao.getAll();
        all.forEach(System.out::println);
        User newUser = new User().setLastName("Temp").setFirstName("Tamara").setEmail("1@1.net");
        userDao.saveEntity(newUser);

        System.out.println();
        List<User> allAfter = userDao.getAll();
        allAfter.forEach(System.out::println);

        System.out.println();
        userDao.deleteEntityByEmail(newUser);
        allAfter = userDao.getAll();
        allAfter.forEach(System.out::println);
      //delete isnt ready couse on java level we dont have an id, so we should find in base by values and then
//        get an id and delete.



    }
}
