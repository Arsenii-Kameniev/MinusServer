package com.example.MinusServer.Controllers;

import com.example.MinusServer.Classes.CardClass;
import com.example.MinusServer.Classes.PatchClass;
import com.example.MinusServer.Classes.PutCardClass;
import com.example.MinusServer.Models.Card;
import com.example.MinusServer.Models.MyUser;
import com.example.MinusServer.Repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class CardController {

    @Autowired
    private CardRepository CardRepos;
    @GetMapping("/getCard")
    public ResponseEntity<Card> getCard(@RequestBody int id){

        Optional<Card> card = CardRepos.findById((long) id);

        if(card.isEmpty()){
            return  new ResponseEntity<Card>(card.get(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Card>(card.get(), HttpStatus.OK);

    }
    @PostMapping("/checkCard")
    public ResponseEntity<Card> checkCard(@RequestBody Card card){
        Optional<Card> myCard = CardRepos.findByNumber(card.getNumber());
        if(myCard.isEmpty()){
            postCard(card);
            return new ResponseEntity<Card>(card, HttpStatus.OK);
        }
        return new ResponseEntity<Card>(card, HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/postCard")
    public ResponseEntity<String> postCard(@RequestBody Card newCard){
        try {
            CardRepos.save(newCard);
        }catch (IndexOutOfBoundsException e){
            return new ResponseEntity<String>("Bad", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>("Good", HttpStatus.OK);

    }
    @PatchMapping("/patchCard")
    public ResponseEntity<String> patchCard(@RequestBody PatchClass changer){
        try {
            Optional<Card> TestCard = CardRepos.findById((long) changer.getId());
            if(TestCard.isEmpty()){
                return new ResponseEntity<String>("Bad", HttpStatus.BAD_REQUEST);
            }
            Card newCard = TestCard.get();
            changer.setType(changer.getType().toLowerCase());
            if(changer.getType().equals("company")){
                newCard.setCompany(changer.getPatch());
            }
            else if(changer.getType().equals("data")){
                newCard.setData(changer.getPatch());
            }
            else if(changer.getType().equals("number")){
                newCard.setNumber(changer.getPatch());
            }
            else if(changer.getType().equals("name")){
                newCard.setName(changer.getPatch());
            }
            else if(changer.getType().equals("surname")){
                newCard.setSurname(changer.getPatch());
            }
            else if(changer.getType().equals("cvv")){
                newCard.setCVV(changer.getPatch());
            }
            CardRepos.save(newCard);
        }catch (IndexOutOfBoundsException e){
            return new ResponseEntity<String>("Bad", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>("Good", HttpStatus.OK);
    }
    @PutMapping("/putCard")
    public ResponseEntity<String> putCard(@RequestBody PutCardClass CardChanger){
        try {
            Optional<Card> TestCard = CardRepos.findById((long) CardChanger.getId());
            if(TestCard.isEmpty()){
                return new ResponseEntity<String>("Bad", HttpStatus.BAD_REQUEST);
            }
            Card ChangeCard = TestCard.get();
            ChangeCard.setCVV(CardChanger.getCard().getCVV());
            ChangeCard.setSurname(CardChanger.getCard().getSurname());
            ChangeCard.setName(CardChanger.getCard().getName());
            ChangeCard.setData(CardChanger.getCard().getData());
            ChangeCard.setNumber(CardChanger.getCard().getNumber());
            ChangeCard.setCompany(CardChanger.getCard().getCompany());
            CardRepos.save(ChangeCard);
        }catch (IndexOutOfBoundsException e){
            return new ResponseEntity<String>("Bad", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>("Good", HttpStatus.OK);
    }
    @DeleteMapping("/deleteCard")
    public ResponseEntity<String> deleteCard(@RequestBody int id){
        try {
            Optional<Card> TestCard = CardRepos.findById((long) id);
            if(TestCard.isEmpty()){
                return new ResponseEntity<String>("Bad", HttpStatus.BAD_REQUEST);
            }
            Card card = TestCard.get();
            CardRepos.delete(card);
        }catch (IndexOutOfBoundsException e){
            return new ResponseEntity<String>("Bad", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>("Good", HttpStatus.OK);
    }
}
