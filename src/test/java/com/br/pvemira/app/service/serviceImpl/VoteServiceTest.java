package com.br.pvemira.app.service.serviceImpl;

import com.br.pvemira.app.BO.VoteBO;
import com.br.pvemira.app.BO.VoterBO;
import com.br.pvemira.app.UtilDataTest;
import com.br.pvemira.app.model.Restaurant;
import com.br.pvemira.app.model.StrawPoll;
import com.br.pvemira.app.model.Voter;
import com.br.pvemira.app.repository.RestaurantRepository;
import com.br.pvemira.app.repository.VoteRepository;
import com.br.pvemira.app.service.RestaurantServiceLocal;
import com.br.pvemira.app.service.StrawPollServiceLocal;
import com.br.pvemira.app.service.VoterServiceLocal;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by pvmeira on 17/06/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class VoteServiceTest extends UtilDataTest {
    @Mock
    private VoteRepository voteRepository;
    @Mock
    private RestaurantService restaurantService;
    @Mock
    private VoterService voterService;
    @Mock
    private StrawPollService strawPollService;
    @Mock
    private VoteBO voteBO;
    @Mock
    private VoterBO voterBO;

    @InjectMocks
    private VoteService voteService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void voteRecivesIdRestaurantNULL() {

        when(this.strawPollService.findCurrentStrawPoll()).thenReturn(getNewStrawPoll());
        when(this.restaurantService.findByid(Mockito.anyLong())).thenReturn(null);
        when(this.voterService.findByEmail(Mockito.anyString())).thenReturn(getNewVoter());
        when(this.voteRepository.findVotesByPollEquals(Mockito.any(StrawPoll.class))).thenReturn(getListOfVotesFromTheSameStrawPoll());

        Assert.assertFalse(this.voteService.vote("email", null));

    }

    @Test
    public void voteRecivesEmailNULL() {

        when(this.strawPollService.findCurrentStrawPoll()).thenReturn(getNewStrawPoll());
        when(this.restaurantService.findByid(Mockito.anyLong())).thenReturn(getNewRestaurant());
        when(this.voterService.findByEmail(Mockito.anyString())).thenReturn(getNewVoter());
        when(this.voteRepository.findVotesByPollEquals(Mockito.any(StrawPoll.class))).thenReturn(getListOfVotesFromTheSameStrawPoll());

        Assert.assertFalse(this.voteService.vote(null, 1l));
    }

    @Test
    public void voteReciveRestaurantThatDosentBelongToVoteList() {

        when(this.strawPollService.findCurrentStrawPoll()).thenReturn(getNewStrawPoll());
        when(this.restaurantService.findByid(Mockito.anyLong())).thenReturn(getNewRestaurant());
        when(this.voterService.findByEmail(Mockito.anyString())).thenReturn(getNewVoter());
        when(this.voteRepository.findVotesByPollEquals(Mockito.any(StrawPoll.class))).thenReturn(getListOfVotesFromTheSameStrawPoll());
        when(this.voteBO.validateRestaurantIsOnCurrentPoll(Mockito.anyList(), Mockito.any(Restaurant.class))).thenReturn(Boolean.TRUE);

        Assert.assertFalse(this.voteService.vote("email", 1l));

    }

    @Test
    public void voteReciveRestauranThatHasAValidRestaurantButNoVoter() {

        when(this.strawPollService.findCurrentStrawPoll()).thenReturn(getNewStrawPoll());
        when(this.restaurantService.findByid(Mockito.anyLong())).thenReturn(getNewRestaurant());
        when(this.voterService.findByEmail(Mockito.anyString())).thenReturn(getNewVoter());
        when(this.voteRepository.findVotesByPollEquals(Mockito.any(StrawPoll.class))).thenReturn(getListOfVotesFromTheSameStrawPoll());
        when(this.voteBO.validateRestaurantIsOnCurrentPoll(Mockito.anyList(), Mockito.any(Restaurant.class))).thenReturn(Boolean.FALSE);
        when(this.voterBO.validateRestaurant(Mockito.any(Restaurant.class), Mockito.any(LocalDate.class))).thenReturn(Boolean.TRUE);
        when(this.voterBO.validateVoter(Mockito.any(Voter.class), Mockito.any(LocalDate.class))).thenReturn(Boolean.FALSE);

        Assert.assertFalse(this.voteService.vote("email", 1l));

    }

    @Test
    public void voteReciveRestauranThatHasAValidVoterButNorRestaurant() {

        when(this.strawPollService.findCurrentStrawPoll()).thenReturn(getNewStrawPoll());
        when(this.restaurantService.findByid(Mockito.anyLong())).thenReturn(getNewRestaurant());
        when(this.voterService.findByEmail(Mockito.anyString())).thenReturn(getNewVoter());
        when(this.voteRepository.findVotesByPollEquals(Mockito.any(StrawPoll.class))).thenReturn(getListOfVotesFromTheSameStrawPoll());
        when(this.voteBO.validateRestaurantIsOnCurrentPoll(Mockito.anyList(), Mockito.any(Restaurant.class))).thenReturn(Boolean.FALSE);
        when(this.voterBO.validateRestaurant(Mockito.any(Restaurant.class), Mockito.any(LocalDate.class))).thenReturn(Boolean.FALSE);
        when(this.voterBO.validateVoter(Mockito.any(Voter.class), Mockito.any(LocalDate.class))).thenReturn(Boolean.TRUE);

        Assert.assertFalse(this.voteService.vote("email", 1l));

    }

    @Test
    public void voteReciveRestauranThatHasAValidVoterAndRestaurant() {

        when(this.strawPollService.findCurrentStrawPoll()).thenReturn(getNewStrawPoll());
        when(this.restaurantService.findByid(Mockito.anyLong())).thenReturn(getNewRestaurant());
        when(this.voterService.findByEmail(Mockito.anyString())).thenReturn(getNewVoter());
        when(this.voteRepository.findVotesByPollEquals(Mockito.any(StrawPoll.class))).thenReturn(getListOfVotesFromTheSameStrawPoll());
        when(this.voteBO.validateRestaurantIsOnCurrentPoll(Mockito.anyList(), Mockito.any(Restaurant.class))).thenReturn(Boolean.FALSE);
        when(this.voterBO.validateRestaurant(Mockito.any(Restaurant.class), Mockito.any(LocalDate.class))).thenReturn(Boolean.TRUE);
        when(this.voterBO.validateVoter(Mockito.any(Voter.class), Mockito.any(LocalDate.class))).thenReturn(Boolean.TRUE);

        Assert.assertTrue(this.voteService.vote("email", 1l));

    }

}