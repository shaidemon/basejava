package ru.daemon75.basejava.storage;

import org.junit.jupiter.api.Test;
import ru.daemon75.basejava.exception.StorageException;
import ru.daemon75.basejava.model.Resume;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static ru.daemon75.basejava.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test
    protected void saveOverflow() {
        try {
            for (int i = storage.size(); i < STORAGE_LIMIT; i++) storage.save(new Resume());
        } catch (StorageException e) {
            fail("Unexpected storage overflow while filling");
        }
        assertThrows(StorageException.class, () -> storage.save(new Resume("TEST")));
    }
}