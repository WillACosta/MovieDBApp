import com.will.moviedbapp.data.repository.userPreferences.CUserPreferencesRepository
import com.will.moviedbapp.data.repository.userPreferences.UserPreferencesRepository
import com.will.moviedbapp.utils.DataStoreTest
import org.junit.Before
import org.junit.Test

class CUserPreferencesRepositoryTest : DataStoreTest() {
    private lateinit var repository: UserPreferencesRepository

    @Before
    fun setUp() {
        repository = CUserPreferencesRepository(dataStore)
    }

    @Test
    fun defaultMessageDisplayedWhenNoPreferenceAvailable() = coTest {
        val actual = repository.getPreferences()

        print(actual)
    }
}
