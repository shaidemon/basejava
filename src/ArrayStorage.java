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
                storage[i] = storage[i + 1];
                deleted = true;
                if (storage[i] != null) {
                    //pushing rest resumes to the left by forcing equals(uuid) condition
                    uuid = storage[i].uuid;
                }
            }
        }
        // resume deleted ? move cursor left
        if (deleted) {
            cursor--;
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] allResumes = new Resume[cursor];
        for (int i = 0; i < cursor; i++) allResumes[i] = storage[i];
        return allResumes;
    }

    int size() {
        return cursor;
    }
}
