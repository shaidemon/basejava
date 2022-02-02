package ru.daemon75.basejava.storage;

import ru.daemon75.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayClass implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    //number of resumes;
    protected int size = 0;

    public int size() {
        return size;
    }

    //template method pattern here, doXxx()-have_to_override operations
    public final void save(Resume resume) {
        String uuid = resume.getUuid();
        int index = doFindIndex(uuid);
        if (size >= STORAGE_LIMIT) {
            System.out.printf("Sorry, exceeded the limit of storage. Can't save the resume %s \n", uuid);
        } else {
            if (index >= 0) {
                System.out.printf(" Sorry, resume %s already present in storage.\n", uuid);
            } else {
                doAddElement(resume, index);
                size++;
                System.out.printf("resume %s saved \n", uuid);
            }
        }
    }

    //template method pattern here, doXxx()-have_to_override operations
    public final Resume get(String uuid) {
        int index = doFindIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.printf("Sorry, resume %s not found! \n", uuid);
        return null;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        int index = doFindIndex(uuid);
        if (index >= 0) {
            storage[index] = resume;
            System.out.printf("Resume %s updated \n", uuid);
        } else {
            System.out.printf("Sorry, resume %s is not present on storage. Nothing to update \n", uuid);
        }
    }

    //template method pattern here, doXxx()-have_to_override operations
    public final void delete(String uuid) {
        int index = doFindIndex(uuid);
        if (index < 0) {
            System.out.printf("Sorry, resume %s not present in storage. Nothing to delete \n", uuid);
        } else {
            doReplaceElement(index);
            size--;
            System.out.printf("Resume %s deleted \n", uuid);
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    //doXxx()-have_to_override operations
    protected abstract int doFindIndex(String uuid);

    protected abstract void doAddElement(Resume resume, int index);

    protected abstract void doReplaceElement(int index);
}
