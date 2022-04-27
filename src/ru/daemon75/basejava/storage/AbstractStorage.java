package ru.daemon75.basejava.storage;

import ru.daemon75.basejava.exception.ExistStorageException;
import ru.daemon75.basejava.exception.NotExistStorageException;
import ru.daemon75.basejava.model.Resume;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {
    private final Comparator<Resume> RESUME_COMPARATOR = new fullNameComparator().thenComparing(new uuidComparator());

    static class uuidComparator implements Comparator<Resume> {
        @Override
        public int compare(Resume o1, Resume o2) {
            return o1.getUuid().compareTo(o2.getUuid());
        }
    }

    static class fullNameComparator implements Comparator<Resume> {
        @Override
        public int compare(Resume o1, Resume o2) {
            return o1.getFullName().compareTo(o2.getFullName());
        }
    }

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
        List<Resume> allList = Arrays.asList(getAll());
        allList.sort(RESUME_COMPARATOR);
        return allList;
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

