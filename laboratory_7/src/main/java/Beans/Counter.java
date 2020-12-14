/*
 * Copyright (c) 17.2020 - Owned by lapachitei
 */

package Beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import java.io.Serializable;

@ManagedBean(name = "counterBean")
@ApplicationScoped
public class Counter implements Serializable {
    int count;

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return CounterManager.getSingletonInstance().getCounter();
    }
}
