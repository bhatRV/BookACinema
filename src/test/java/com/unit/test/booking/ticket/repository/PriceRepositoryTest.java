package com.unit.test.booking.ticket.repository;


import com.rv.booking.ticket.BookACinemaApplication;
import com.rv.booking.ticket.entities.model.PriceDetails;
import com.rv.booking.ticket.entities.model.TicketType;
import com.rv.booking.ticket.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = BookACinemaApplication.class)
public class PriceRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @MockBean
    private PriceDetails PriceDetails;
    @Autowired
    PriceRepository repository;

    @Test
    public void should_find_no_resources_if_repository_is_empty() {

        Iterable response = repository.findAll();

        assertThat(response);

    }


    @Test
    public void should_find_all_resources() {
        PriceDetails dis1 = new PriceDetails();
        dis1.setTicketType(TicketType.ADULT);
        dis1.setPrice(BigDecimal.valueOf(30.00));
        dis1.setId(10L);
        entityManager.persist(dis1);

        PriceDetails dis2 = new PriceDetails();
        dis2.setTicketType(TicketType.ADULT);
        dis2.setPrice(BigDecimal.valueOf(35.00));
        dis2.setId(20L);
        entityManager.persist(dis2);

        Iterable<PriceDetails> response = repository.findAll();

        assertThat(response).contains(dis1);
        assertThat(response).contains(dis2);

    }

}