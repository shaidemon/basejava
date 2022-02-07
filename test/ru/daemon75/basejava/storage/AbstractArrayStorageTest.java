package ru.daemon75.basejava.storage;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.daemon75.basejava.exception.ExistStorageException;
import ru.daemon75.basejava.exception.NotExistStorageException;
import ru.daemon75.basejava.exception.StorageException;
import ru.daemon75.basejava.model.Resume;

import static ru.daemon75.basejava.storage.AbstractArrayStorage.STORAGE_LIMIT;

abstract class AbstractArrayStorageTest {

    protected Storage storage;
    protected int initSize;

    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    protected void init() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
        initSize = storage.size();
    }

    @Test
    void size() {
        assertEquals(3, storage.size());
    }

    @Test
    void save() {
        Resume r = new Resume("uuid0_save_test");
        storage.save(r);
        assertEquals(r, storage.get("uuid0_save_test"));
        // size increased
        assertEquals(initSize + 1, storage.size());
    }

    @Test
    protected void saveExist() {
        assertThrows(ExistStorageException.class, () -> storage.save(new Resume(UUID_1)));
    }

    @Test
    protected void saveOverflow() {
        try {
            for (int i = initSize; i < STORAGE_LIMIT; i++) storage.save(new Resume());
        } catch (StorageException e) {
            fail("Unexpected storage overflow while filling");
        }
        assertThrows(StorageException.class, () -> storage.save(new Resume(UUID_1)));
    }

    @Test
    void get() {
        assertEquals(new Resume(UUID_1), storage.get(UUID_1));
    }

    @Test
    protected void getNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
    }


    @Test
    void getAll() {
        Resume[] expected = new Resume[]{new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
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
        Resume[] expected = new Resume[2];
        expected[0] = new Resume(UUID_1);
        expected[1] = new Resume(UUID_2);
        storage.delete(UUID_3);
        assertArrayEquals(expected, storage.getAll());
        // size decreased
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