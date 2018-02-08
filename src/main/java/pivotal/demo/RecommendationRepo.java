package pivotal.demo;

import org.springframework.stereotype.Repository;
import com.microsoft.azure.spring.data.documentdb.repository.DocumentDbRepository;


@Repository
public interface RecommendationRepo extends DocumentDbRepository<RecommendationItem, String> {
}
