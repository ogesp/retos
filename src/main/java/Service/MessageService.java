package Service;

import Model.Message;
import Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAll(){
        return (List<Message>) messageRepository.getAll();
    }

    public Optional<Message> getMessage(int id) {
        return messageRepository.getMessage(id);
    }

    public Message save(Message message){
        if(message.getId() == null){
            return messageRepository.save(message);
        } else {
            Optional<Message> messageEncontrado = messageRepository.getMessage(message.getId());
            if (messageEncontrado.isEmpty()) {
                return messageRepository.save(message);
            } else {
                return message;
            }
        }
    }

    public Message update(Message p) {
        if (p.getId() != null) {
            Optional<Message> q = messageRepository.getMessage(p.getId());
            if (q.isPresent()) {
                if (p.getMessageText() != null) {
                    q.get().setMessageText(p.getMessageText());
                }
                if (p.getCostume() != null) {
                    q.get().setCostume(p.getCostume());
                }
                if (p.getClient() != null) {
                    q.get().setClient(p.getClient());
                }
                messageRepository.save(q.get());
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
        Optional<Message>p= messageRepository.getMessage(id);
        if(p.isPresent()){
            messageRepository.delete(p.get());
            flag=true;
        }
        return flag;
    }


}
