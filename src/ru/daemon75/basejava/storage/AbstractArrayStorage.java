package ru.daemon75.basejava.storage;

import ru.daemon75.basejava.exception.ExistStorageException;
import ru.daemon75.basejava.exception.NotExistStorageException;
import ru.daemon75.basejava.exception.StorageException;
import ru.daemon75.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    //number of resumes;
    protected int size;

    public int size() {
        return size;
    }

    //template method pattern here
    public final void save(Resume resume) {
        String uuid = resume.getUuid();
        int index = findIndex(uuid);
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Sorry, exceeded the limit of storage. Can't save the resume", uuid);
        } else if (index >= 0) {
            throw new ExistStorageException(uuid);
        } else {
            saveToArray(resume, index);
            size++;
            System.out.printf("resume %s saved \n", uuid);
        }

    }

    //template method pattern here
    public final Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        throw new NotExistStorageException(uuid);
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        int index = findIndex(uuid);
        if (index >= 0) {
            storage[index] = resume;
            System.out.printf("Resume %s updated \n", uuid);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    //template method pattern here
    public final void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteFromArray(index);
            // last resume to null
            storage[size - 1] = null;
            size--;
            System.out.printf("Resume %s deleted \n", uuid);
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }


    protected abstract int findIndex(String uuid);

    protected abstract void saveToArray(Resume resume, int index);

    protected abstract void deleteFromArray(int index);
}
