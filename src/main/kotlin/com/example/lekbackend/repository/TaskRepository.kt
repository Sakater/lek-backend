package  com.example.lekbackend.repository
import com.example.lekbackend.dao.Task
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
@Transactional(readOnly = false)
interface TaskRepository:JpaRepository<Task, Long> {

}