package ru.daemon75.basejava.storage;

import ru.daemon75.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

/**
 * ArrayList based storage for Resumes
 */

public class MapStorage extends AbstractStorage {
    protected Map<String, Resume> storage = new HashMap<>();

    @Override
    protected void updateStorage(Resume resume, Object key) {
        storage.put((String) key, resume);
    }

    @Override
    protected Resume getFromStorage(Object key) {
        return storage.get((String) key);
    }

    @Override
    protected void saveToStorage(Resume resume, Object key) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected boolean isExist(Object key) {
        return key != null;
    }

    @Override
    protected Object findKey(String uuid) {
        return storage.containsKey(uuid) ? uuid : null;
    }

    @Override
    protected void deleteFromStorage(Object key) {
        storage.remove((String) key);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
