package Services;

import Beans.UserBean;
import Entities.UserEntity;
import Interfaces.UserInterface;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserService extends DataRepository<UserEntity,Integer> implements UserInterface {
    @PersistenceContext
    private EntityManager em;

    public UserService(){super(UserEntity.class);}

    @Override
    public void addUser(UserBean p)
    {
        if(p.getEntity().getUsername().equals("admin") && p.getEntity().getPassword().equals("admin")){
            p.getEntity().setRole("admin");
        }
        else{
            p.getEntity().setRole("guest");
        }

        this.persist(p.getEntity());
    }

    @Override
    public List<UserBean> getUsers()
    {
        List<UserEntity> entities = this.findAll();

        List<UserBean> ls = new ArrayList<>();
        for (UserEntity entity : entities)
        {
            UserBean userBean = new UserBean();
            userBean.setEntity(entity);
            ls.add(userBean);
        }
        return ls;
    }

    @Override
    public UserEntity findUser(String username, String password) {
        String qlString =
                "select e from " + entityClass.getSimpleName() + " e where e.username = '"+username+
                "' and e.password = '"+password+"'";
        List<UserEntity> userList = (List<UserEntity>)em.createQuery(qlString).getResultList();
        if(userList.size()==0){
            return null;
        }
        return userList.get(0);
    }

    @Override
    public UserEntity findUser(String username) {
        String qlString =
                "select e from " + entityClass.getSimpleName() + " e where e.username = '"+username+"'";
        List<UserEntity> userList = (List<UserEntity>)em.createQuery(qlString).getResultList();
        if(userList.size()==0){
            return null;
        }
        return userList.get(0);
    }
}
