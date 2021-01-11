package Services;

import Entities.GroupEntity;

import Entities.Upload;
import Interfaces.GroupInterface;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

@Stateless
@PermitAll
public class GroupService extends DataRepository<GroupEntity,Integer> implements GroupInterface {

    @PersistenceContext
    private EntityManager em;

    public GroupService() {
        super(GroupEntity.class);
    }

    @Override
    public GroupEntity findGroup(String name) {
        String qlString =
                "select e from " + entityClass.getSimpleName() + " e where e.name = '" + name + "'";
        List<GroupEntity> userList = (List<GroupEntity>) em.createQuery(qlString).getResultList();
        if (userList.size() == 0) {
            return null;
        }
        return userList.get(0);
    }
}

