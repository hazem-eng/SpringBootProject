package com.example.microfinancepi.services;

import com.example.microfinancepi.entities.Event;
import com.example.microfinancepi.entities.ShareHolder;
import com.example.microfinancepi.repositories.IEventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private IEventRepository Ieventrepository;

    @Override
    public List<Event> retrieveAllEvents() {
        return Ieventrepository.findAll();
    }

    @Override
    public Event AddEvent(Event event) {
        return Ieventrepository.save(event);
    }


    @Override
    public void removeEvent(Integer numEvent) {
        Ieventrepository.deleteById(numEvent);
    }

    @Override
    public Event retrieveEvent(Integer numEvent) {
        return Ieventrepository.findById(numEvent).orElse(null);
    }

    @Override
    public Event updateEvent(Event event) {
        return Ieventrepository.save(event);
    }

    @Override
    @Transactional
    public List<Event> findByShareHolders_LastNameShareholderAndShareHolders_FirstNameShareholder(String lastNameShareholder, String FirstNameShareholder) {

        return Ieventrepository.findByShareHolders_LastNameShareholderAndShareHolders_FirstNameShareholder(lastNameShareholder, FirstNameShareholder);
    }

    @Override
    public Double getTotalInvestmentInEvent(int eventId) {
        Optional<Event> eventOptional = Ieventrepository.findById(eventId);
        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            List<ShareHolder> shareholders = event.getShareHolders();
            double totalInvestment = 0.0;
            for (ShareHolder shareholder : shareholders) {
                totalInvestment += shareholder.getInvestment();
            }
            return totalInvestment;
        } else {
            throw new EntityNotFoundException("Event not found with id " + eventId);
        }
    }


    @Override
    public List<Event> getEventsWithin24Hours(){
        LocalDate now = LocalDate.now();
        LocalDate tomorrow = now.plusDays(1);
        return Ieventrepository.findByEventDateBetween(now,tomorrow);
    }
}
   /* @Override
    public Event AddEvent(Event event) {
        emailSenderService.sendSimpleEmail("samar.saidana@esprit.tn","event","nouvelle event");
        return Ieventrepository.save(event);
    }*/

/*
    private IEventRepository eventRepository;

    private final IShareholderRepository shareHolderRepository;
@Override
    public void sendEventReminderEmail(int eventId) {
       Event event = eventRepository.findById(eventId).orElse(null);
        List<ShareHolder> shareholders = shareHolderRepository.findByEventId(eventId);
        for (ShareHolder shareholder : shareholders) {
            String toEmail = shareholder.getEmail();
            String body = "Bonjour " + shareholder.getFirstNameShareholder() + ",\n\n" +
                    "Ceci est un rappel pour l'événement " + event.getNameEvent() + " qui aura lieu dans 24 heures.\n\n" +
                    "Cordialement,\n" +
                    "L'équipe de microfinance.";
            String subject = "Rappel d'événement: " + event.getNameEvent();
            emailSenderService.sendSimpleEmail(toEmail, body, subject);
        }
    }

    public EmailSenderService getEmailSenderService() {
        return emailSenderService;
    }

    public void setEmailSenderService(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }*/

// Autres méthodes de service









   /* public List<Event> getEventsWithin24Hours() {
        LocalDate now = LocalDate.now();
        LocalDate tomorrow = now.plusDays(1);
        return Ieventrepository.findBydateEvent(now,tomorrow);
    }*/
