package ru.daemon75.basejava.storage;

import ru.daemon75.basejava.exception.ExistStorageException;
import ru.daemon75.basejava.exception.NotExistStorageException;
import ru.daemon75.basejava.model.Resume;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {
    protected final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName).
            thenComparing(Resume::getUuid);

    public void save(Resume resume) {
        Object key = receiveNotExistedKey(resume.getUuid());
        saveToStorage(resume, key);
    }

    public final Resume get(String uuid) {
        Object key = receiveExistedKey(uuid);
        return getFromStorage(key);
    }

    public void update(Resume resume) {
        Object key = receiveExistedKey(resume.getUuid());
        updateStorage(resume, key);
    }

    public void delete(String uuid) {
        Object key = receiveExistedKey(uuid);
        deleteFromStorage(key);
    }

    public List<Resume> getAllSorted() {
        List<Resume> listResume = Arrays.asList(getAll());
        listResume.sort(RESUME_COMPARATOR);
        return listResume;
    }

    private Object receiveNotExistedKey(String uuid) {
        Object key = findKey(uuid);
        if (isExist(key)) {
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    private Object receiveExistedKey(String uuid) {
        Object key = findKey(uuid);
        if (!isExist(key)) {
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    protected abstract Resume[] getAll();

    protected abstract void updateStorage(Resume resume, Object key);

    protected abstract Resume getFromStorage(Object key);

    protected abstract void saveToStorage(Resume resume, Object key);

    protected abstract boolean isExist(Object key);

    protected abstract Object findKey(String uuid);

    protected abstract void deleteFromStorage(Object key);
}

