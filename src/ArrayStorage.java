/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    //last-resume position in storage;
    int cursor = 0;

    void clear() {
        for (int i = 0; i < cursor; i++) {
            storage[i] = null;
        }
        cursor = 0;
    }

    void save(Resume resume) {
        storage[cursor] = resume;
        cursor++;
    }

    Resume get(String uuid) {
        if (storage[0] != null) {
            for (int i = 0; i < cursor; i++) {
                if (storage[i].uuid.equals(uuid)) {
                    return storage[i];
                }
            }
        }
        return null;
    }

    void delete(String uuid) {
        boolean deleted = false;
        for (int i = 0; i < cursor; i++) {
            if (storage[i].uuid.equals(uuid)) {
                // replace deleted element by last value
                 storage[i] = storage[cursor - 1];
                deleted = true;
            }
        }
        // resume deleted ? last resume to null & move cursor left
        if (deleted) {
            storage[cursor-1] = null;
            cursor--;
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] allResumes = new Resume[cursor];
        System.arraycopy(storage, 0, allResumes, 0, cursor);
        return allResumes;
    }

    int size() {
        return cursor;
    }
}
