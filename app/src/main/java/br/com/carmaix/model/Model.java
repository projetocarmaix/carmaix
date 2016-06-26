package br.com.carmaix.model;

public class Model {

    boolean refreshListData = false;

    boolean verifiCache = false;

    public boolean isRefreshListData() {
        return refreshListData;
    }

    public void setRefreshListData(boolean refreshListData) {
        this.refreshListData = refreshListData;
    }

    public boolean isVerifiCache() {
        return verifiCache;
    }

    public void setVerifiCache(boolean verifiCache) {
        this.verifiCache = verifiCache;
    }

}
