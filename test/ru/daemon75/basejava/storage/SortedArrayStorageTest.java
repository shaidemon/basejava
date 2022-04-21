package ru.daemon75.basejava.storage;

import org.junit.jupiter.api.Test;
import ru.daemon75.basejava.exception.StorageException;
import ru.daemon75.basejava.model.Resume;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static ru.daemon75.basejava.storage.AbstractArrayStorage.STORAGE_LIMIT;

public class SortedArrayStorageTest extends AbstractStorageTest {

    private SortedArrayStorageTest() {

        super(new SortedArrayStorage());
    }

    @Test
    protected void saveOverflow() {
        int initSize = storage.size();
        try {
            for (int i = initSize; i < STORAGE_LIMIT; i++) storage.save(new Resume());
        } catch (StorageException e) {
            fail("Unexpected storage overflow while filling");
        }
        assertThrows(StorageException.class, () -> storage.save(new Resume("TEST")));
    }
}
