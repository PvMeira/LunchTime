package com.br.pvemira.app.BO;

import com.br.pvemira.app.UtilDataTest;
import com.br.pvemira.app.model.DTO.VoterDTO;
import com.br.pvemira.app.model.Restaurant;
import com.br.pvemira.app.model.Voter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Created by pvmeira on 19/06/17.
 */
public class VoterBOTest extends UtilDataTest {
    private VoterBO voterBO;

    private LocalDate date1a;
    private LocalDate date1b;

    private LocalDate date2a;
    private LocalDate date2b;

    private LocalDate dateRestaurant1A;
    private LocalDate dateRestaurant1B;

    private LocalDate dateRestaurant2A;
    private LocalDate dateRestaurant2B;

    @Before
    public void init() {
        this.voterBO = new VoterBO();
        date1a = LocalDate.of(2017, 01, 01);
        date1b = LocalDate.of(2017, 01, 02);

        date2a = LocalDate.of(2017, 01, 01);
        date2b = LocalDate.of(2017, 01, 01);

        dateRestaurant1A = LocalDate.of(2017, 01, 01);
        dateRestaurant1B = LocalDate.of(2017, 01, 07);

        dateRestaurant2A = LocalDate.of(2017, 01, 01);
        dateRestaurant2B = LocalDate.of(2017, 01, 01);
    }

    @Test
    public void transformVoterDTO2Voter() {
        Voter voter = this.voterBO.transformVoterDTO2Voter(getNewVoterDTO());
        Assert.assertEquals(getNewVoter().getEmail(), voter.getEmail());
        Assert.assertEquals(getNewVoter().getName(), voter.getName());
    }

    @Test
    public void validateVoterTrueNotNull() {
        Voter voter = getNewVoter();
        voter.setLastVoted(date1a);
        Assert.assertTrue(this.voterBO.validateVoter(voter, date1b));

    }

    @Test
    public void validateVoterTrueNull() {
        Assert.assertTrue(this.voterBO.validateVoter(getNewVoter(), date1b));

    }

    @Test
    public void validateVoterFalse() {
        Voter voter = getNewVoter();
        voter.setLastVoted(date2a);
        Assert.assertFalse(this.voterBO.validateVoter(voter, date2b));
    }

    @Test
    public void transformVoter2VoterDTO() {
        Voter voter = getNewVoter();
        VoterDTO dto = this.voterBO.transformVoter2VoterDTO(voter);

        Assert.assertEquals(voter.getEmail(), dto.getEmail());
        Assert.assertEquals(voter.getName(), dto.getName());
    }

    @Test
    public void validateRestaurantTrueNotNull() {
        Restaurant restaurant = getNewRestaurant();
        restaurant.setAddOnStrawPoll(dateRestaurant1A);
        Assert.assertTrue(this.voterBO.validateRestaurant(restaurant, dateRestaurant1B));

    }

    @Test
    public void validateRestaurantTrueNull() {
        Restaurant restaurant = getNewRestaurant();
        Assert.assertTrue(this.voterBO.validateRestaurant(restaurant, dateRestaurant1B));

    }

    @Test
    public void validateRestaurantFalse() {
        Restaurant restaurant = getNewRestaurant();
        restaurant.setAddOnStrawPoll(dateRestaurant2A);
        Assert.assertFalse(this.voterBO.validateRestaurant(restaurant, dateRestaurant2B));
    }

}