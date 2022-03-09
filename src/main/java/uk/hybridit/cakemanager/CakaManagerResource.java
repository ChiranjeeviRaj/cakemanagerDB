package uk.hybridit.cakemanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uk.hybridit.cakemanager.uk.waracle.cakemanager.entity.Cake;
import uk.hybridit.cakemanager.uk.waracle.cakemanager.entity.CakeManagerRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class CakaManagerResource {

        @Autowired
        private CakeManagerRepository cakeManagerRepository;

        @GetMapping("/cakes")
        public List<Cake> retrieveAllCakes(){ return cakeManagerRepository.findAll(); }

        @PostMapping("/cakes")
        public Cake createCake(@Valid @RequestBody Cake cake){
                Cake savedCake = cakeManagerRepository.save( cake );
                return savedCake;
        }

        @GetMapping("/cake/{id}")
        public Cake getCake(@PathVariable Long id){
                Optional<Cake> cake = cakeManagerRepository.findById(id);
                if(!cake.isPresent())
                        throw new CakeNotFoundException("id-" + id);

                return cake.get();
        }


}
