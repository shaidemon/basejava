/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int cursor = 0; //last-resume position in storage;

    void clear() {
        if (cursor > 0) {
            for (int i = 0; i < cursor; i++) {
                storage[i] = null;
            }
            cursor = 0;
        }
    }

    void save(Resume resume) {
        storage[cursor] = new Resume();
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
        for (int i = 0; i < cursor; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = storage[i + 1];
                if (storage[i] != null) {
                    uuid = storage[i].uuid;
                }
            }
        }
        cursor--;
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
