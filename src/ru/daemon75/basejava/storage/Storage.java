package ru.daemon75.basejava.storage;

import ru.daemon75.basejava.model.Resume;

/**
 * Interface for Array-based storage for Resumes
 */
public interface Storage {

    void clear();

    void save(Resume resume);

    void update(Resume resume);

    Resume get(String uuid);

    void delete(String uuid);

    Resume[] getAll();

    int size();

}

