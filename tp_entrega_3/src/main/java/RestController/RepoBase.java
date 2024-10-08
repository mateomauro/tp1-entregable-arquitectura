package RestController;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface RepoBase<T, ID extends Serializable> extends JpaRepository<T, ID> {

}