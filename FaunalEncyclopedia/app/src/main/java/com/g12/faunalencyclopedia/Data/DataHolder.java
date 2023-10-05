package com.g12.faunalencyclopedia.Data;

import java.util.List;

public class DataHolder {
    private static final DataHolder ourInstance = new DataHolder();

    public static DataHolder getInstance() {
        return ourInstance;
    }

    private List<Animal> dataset;

    private DataHolder() {
    }

    public List<Animal> getDataset() {
        return dataset;
    }

    public void setDataset(List<Animal> dataset) {
        this.dataset = dataset;
    }
}

