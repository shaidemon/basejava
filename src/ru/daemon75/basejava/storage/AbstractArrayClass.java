package ru.daemon75.basejava.storage;

import ru.daemon75.basejava.model.Resume;

public abstract class AbstractArrayClass implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    //number of resumes;
    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.printf("Sorry, resume %s not found! \n", uuid);
        return null;
    }

    protected abstract int findIndex(String uuid);
}
