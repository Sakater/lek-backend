import com.example.lekbackend.dao.Tasks
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
@Transactional(readOnly = false)
interface TasksRepository:JpaRepository<Tasks, Long> {

}