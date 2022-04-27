package ru.daemon75.basejava.storage;

import ru.daemon75.basejava.model.Resume;
import java.util.HashMap;
import java.util.Map;

/**
 * ArrayList based storage for Resumes
 */

public class MapResumeStorage extends AbstractStorage {
    protected Map<String, Resume> storage = new HashMap<>();

    @Override
    protected void updateStorage(Resume resume, Object r) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getFromStorage(Object resume) {
        return (Resume) resume;
    }

    @Override
    protected void saveToStorage(Resume resume, Object r) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected boolean isExist(Object resume) {
        return resume != null;
    }

    @Override
    protected Resume findKey(String uuid) {
        return storage.getOrDefault(uuid, null);
    }

    @Override
    protected void deleteFromStorage(Object resume) {
        storage.remove(((Resume) resume).getUuid());
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
