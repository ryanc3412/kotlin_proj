import androidx.room.Room
import com.ryanchristensen.finalproject.models.Workout
import com.ryanchristensen.finalproject2410.AppDatabase
import com.ryanchristensen.finalproject2410.WorkoutsApplication

object WorkoutsRepository {
    private val db: AppDatabase;
    init {
        db = Room.databaseBuilder(
            WorkoutsApplication.getInstance(),
            AppDatabase::class.java,
            "database-workouts"
        ).build()
    }

    suspend fun createWorkout(workout: Workout): Long {
        return db.getWorkoutsDao().createWorkout(workout)
    }

    suspend fun getAllWorkouts():List<Workout> {
        return db.getWorkoutsDao().getAllWorkouts()
    }

    suspend fun update(workout: Workout) {
        db.getWorkoutsDao().updateWorkout(workout)
    }
}