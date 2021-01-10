package Interfaces;

import Beans.UserBean;
import Entities.Upload;
import Entities.UserEntity;
import Services.DataRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public interface UserInterface {
        public void addUser(UserBean p);

        public List<UserBean> getUsers();

        public UserEntity findUser(String username, String password);

        public UserEntity findUser(String username);

        public UserEntity createUser(InputStream is);
}
