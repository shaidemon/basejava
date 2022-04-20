package ru.daemon75.basejava.storage;

import ru.daemon75.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> storage = new ArrayList<>();

    @Override
    protected void updateStorage(Resume resume, Object key) {
        storage.set((Integer) key, resume);
        System.out.printf("Resume %s updated \n", resume.getUuid());
    }

    @Override
    protected Resume getFromStorage(Object key) {
        return storage.get((Integer) key);
    }

    @Override
    protected void saveToStorage(Resume resume, Object key) {
        int index = -((Integer) key) - 1;
        storage.add(index, resume);
        System.out.printf("resume %s saved \n", resume.getUuid());
    }

    @Override
    protected boolean isExist(Object key) {
        return (Integer) key >= 0;
    }

    @Override
    protected Object findKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Collections.binarySearch(storage, searchKey);
    }

    @Override
    protected void deleteFromStorage(Object key) {
        int index = (Integer) key;
        storage.remove(index);
        System.out.printf("Resume %s deleted \n", index);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
