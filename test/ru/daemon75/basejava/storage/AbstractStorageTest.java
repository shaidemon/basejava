package ru.daemon75.basejava.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.daemon75.basejava.exception.ExistStorageException;
import ru.daemon75.basejava.exception.NotExistStorageException;
import ru.daemon75.basejava.model.Resume;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractStorageTest {

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_TEST = "uuid0_test";
    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_TEST;

    static {
        RESUME_1 = new Resume(UUID_1);
        RESUME_2 = new Resume(UUID_2);
        RESUME_3 = new Resume(UUID_3);
        RESUME_TEST = new Resume(UUID_TEST);
    }

    protected Storage storage;
    private int initSize;

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    protected void init() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
        initSize = storage.size();
    }

    @Test
    void size() {
        assertEquals(3, storage.size());
    }

    @Test
    void save() {
        storage.save(RESUME_TEST);
        assertEquals(RESUME_TEST, storage.get(UUID_TEST));
        // size increased
        assertEquals(initSize + 1, storage.size());
    }

    @Test
    protected void saveExist() {
        assertThrows(ExistStorageException.class, () -> storage.save(RESUME_1));
    }


    @Test
    void get() {
        assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test
    protected void getNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
    }


    @Test
    void getAll() {
        Resume[] expected = new Resume[]{RESUME_1, RESUME_2, RESUME_3};
        assertArrayEquals(expected, storage.getAll());
    }

    @Test
    void update() {
        Resume r = new Resume(UUID_3);
        storage.update(r);
        assertEquals(r, storage.get(UUID_3));
    }

    @Test
    protected void updateNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.update(new Resume("dummy")));
    }

    @Test
    void delete() {
        Resume[] expected = new Resume[]{RESUME_1, RESUME_2};
        storage.delete(UUID_3);
        assertArrayEquals(expected, storage.getAll());
        // size decreased - over-check
        assertEquals(initSize - 1, storage.size());
    }

    @Test
    protected void deleteNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.delete("dummy"));
    }

    @Test
    void clear() {
        storage.clear();
        assertArrayEquals(new Resume[0], storage.getAll());
    }
}