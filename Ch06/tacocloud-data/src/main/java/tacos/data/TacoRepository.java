package tacos.data;

import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import tacos.Taco;


public interface TacoRepository 
         extends ReactiveCrudRepository<Taco, Long> {

}
