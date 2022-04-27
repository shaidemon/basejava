package ru.daemon75.basejava.storage;

import ru.daemon75.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

/**
 * ArrayList based storage for Resumes
 */

public class ListStorage extends AbstractStorage {
    protected List<Resume> storage = new ArrayList<>();

    @Override
    protected void updateStorage(Resume resume, Object index) {
        storage.set((Integer) index, resume);
        System.out.printf("Resume %s updated \n", resume.getUuid());
    }

    @Override
    protected Resume getFromStorage(Object index) {
        return storage.get((Integer) index);
    }

    @Override
    protected void saveToStorage(Resume resume, Object index) {
        storage.add(resume);
        System.out.printf("resume %s saved \n", resume.getUuid());
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    @Override
    protected Object findKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
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