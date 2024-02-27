package com.embarkx.ChallengeApp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenges")
public class ChallengeController {

    private ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService){
//        challengeService = new ChallengeService() //Initialization
        this.challengeService = challengeService;//provided by springboot by IOC at runtime
    }

    @GetMapping
    public ResponseEntity<List<Challenge>> getAllChallenges(){
        return new ResponseEntity<>(challengeService.getAllChallenges(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addChallenge(@RequestBody Challenge challenge){
        boolean isAdded =  challengeService.addChallenges(challenge);
        if(isAdded){
            return new ResponseEntity<>("Challenge added successfully",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Challenge not added successfully", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{month}")
    public ResponseEntity<Challenge> getChallenges(@PathVariable String month){
        Challenge challenge = challengeService.getChallenge(month);
        if(challenge!=null)
            return new ResponseEntity<>(challenge, HttpStatus.OK);
        else
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateChallenege(@PathVariable Long id, @RequestBody Challenge updatedChallenge){
        boolean isChallenegeUpdated = challengeService.updateChallenge(id,updatedChallenge);
        if(isChallenegeUpdated){
            return new ResponseEntity<>("Challenge added successfully",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Challenge not added successfully", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteChalleneg(@PathVariable Long id){
        boolean isDeleted = challengeService.deleteChallenge(id);
        if(isDeleted){
            return new ResponseEntity<>("Challenge Deleted successfully",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Challenge not deleted", HttpStatus.NOT_FOUND);
        }
    }
}
