package Service;

import Model.Costume;
import Repository.CostumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CostumeService {

    @Autowired
    private CostumeRepository costumeRepository;

    public List<Costume> getAll(){
        return costumeRepository.getAll();
    }

    public Optional<Costume> getCostume(int id){
        return costumeRepository.getCostume(id);
    }

    public Costume save(Costume costume){
        if(costume.getId() == null){
            return costumeRepository.save(costume);
        } else {
            Optional<Costume> costumeEncontrado = costumeRepository.getCostume(costume.getId());
            if (costumeEncontrado.isEmpty()) {
                return costumeRepository.save(costume);
            } else {
                return costume;
            }
        }
    }

    public Costume update(Costume p) {
        if (p.getId() != null) {
            Optional<Costume> q = costumeRepository.getCostume(p.getId());
            if (q.isPresent()) {
                if (p.getName() != null) {
                    q.get().setName(p.getName());
                }
                if (p.getBrand() != null) {
                    q.get().setBrand(p.getBrand());
                }
                if (p.getYear() != null) {
                    q.get().setYear(p.getYear());
                }
                if (p.getDescription() != null) {
                    q.get().setDescription(p.getDescription());
                }
                if (p.getCategory() != null) {
                    q.get().setCategory(p.getCategory());
                }
                if (p.getMessages() != null) {
                    q.get().setMessages(p.getMessages());
                }
                if (p.getReservations() != null) {
                    q.get().setReservations(p.getReservations());
                }

                costumeRepository.save(q.get());
                return q.get();
            } else {
                return p;
            }
        }else{
            return p;
        }
    }

    public boolean delete(int id){
        boolean flag=false;
        Optional<Costume>p=costumeRepository.getCostume(id);
        if(p.isPresent()){
            costumeRepository.delete(p.get());
            flag=true;
        }
        return flag;
    }



}
