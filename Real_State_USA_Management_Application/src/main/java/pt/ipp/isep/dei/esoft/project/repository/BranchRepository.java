package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Store;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Branch repository.
 */
public class BranchRepository implements Serializable {

 private final List<Store> storeList = new ArrayList<>();

    /**
     * Gets store list.
     *
     * @return the store list
     */
    public List<Store> getStoreList() {
        return storeList;
    }


    /**
     * Add store.
     *
     * @param store the store
     */
    public void addStore (Store store){
        storeList.add(store);
    }
}

