package Service;


import Model.Client;
import Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll() {

        return (List<Client>) clientRepository.getAll();
    }

    public Optional<Client> getClient(int id){

        return clientRepository.getClient(id);
    }

    public Client save (Client client){
        if (client.getIdClient() == null){
            return clientRepository.save(client);
        } else {
            Optional<Client> client1 = clientRepository.getClient(client.getIdClient());
            if(client1.isEmpty()){
                return clientRepository.save(client);
            } else {
                return client;
            }
        }
    }

    public Client update(Client client){
        if(client.getIdClient()!=null){
            Optional<Client> e=clientRepository.getClient(client.getIdClient());
            if(!e.isEmpty()) {
                if (client.getName() != null) {
                    e.get().setName(client.getName());
                }
                if (client.getEmail() != null) {
                    e.get().setEmail(client.getEmail());
                }
                if (client.getAge() != null) {
                    e.get().setAge(client.getAge());
                }
                if (client.getPassword() != null){
                    e.get().setPassword(client.getPassword());
                }
                if (client.getMessages() != null){
                    e.get().setMessages(client.getMessages());
                }
                if (client.getReservations() != null){
                    e.get().setReservations(client.getReservations());
                }

            }
            clientRepository.save(e.get());
            return e.get();
        }else{
            return client;
        }
    }

    public boolean deleteClient(int clientId){
        Boolean aBoolean = getClient(clientId).map(client -> {
            clientRepository.delete(client);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}

