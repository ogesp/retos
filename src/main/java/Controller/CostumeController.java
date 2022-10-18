package Controller;

import Model.Costume;
import Service.CostumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Costume")
public class CostumeController {
    @Autowired
    private CostumeService costumeService;

    @GetMapping("/all")
    public List<Costume> getAll(){
        return costumeService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Costume> getTool(@PathVariable("id") int id){
        return costumeService.getCostume(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Costume save (@RequestBody Costume costume){
        return costumeService.save(costume);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Costume update(@RequestBody Costume games){
        return costumeService.update(games);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable ("id")int id){
        return costumeService.delete(id);
    }






}
