/*
 * Copyright (c) 17.2020 - Owned by lapachitei
 */

package DAO;

import Beans.LocationBean;
import Entities.Location;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class LocationManager extends DataRepository<Location,Integer>{

    public LocationManager(){
        super(Location.class);
    }
    public void addLocation(LocationBean p)
    {
        this.persist(p.getEntity());
    }

    public List<LocationBean> getLocations()
    {
        List<Location> entities = this.findAll();

        List<LocationBean> ls = new ArrayList<>();
        for (Location entity : entities)
        {
            LocationBean locationBean = new LocationBean();
            locationBean.setEntity(entity);
            ls.add(locationBean);
        }
        return ls;
    }
}
