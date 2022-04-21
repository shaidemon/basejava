package ru.daemon75.basejava.storage;

import ru.daemon75.basejava.exception.StorageException;
import ru.daemon75.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    //actual number of resumes;
    protected int size;

    public int size() {
        return size;
    }

    @Override
    public void saveToStorage(Resume resume, Object key) {
        int index = (Integer) key;
        String uuid = resume.getUuid();
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Sorry, exceeded the limit of storage. Can't save the resume", uuid);
        }
        saveToArray(resume, index);
        size++;
        System.out.printf("resume %s saved \n", uuid);


    }

    @Override
    public Resume getFromStorage(Object index) {
        return storage[(Integer) index];
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    public void updateStorage(Resume resume, Object index) {
        storage[(Integer) index] = resume;
        System.out.printf("Resume %s updated \n", resume.getUuid());
    }

    @Override
    public void deleteFromStorage(Object index) {
        deleteFromArray((Integer) index);
        // last resume to null
        storage[size - 1] = null;
        size--;
        System.out.printf("Resume %s deleted \n", index);
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    protected abstract void saveToArray(Resume resume, int index);

    protected abstract void deleteFromArray(int index);
}
