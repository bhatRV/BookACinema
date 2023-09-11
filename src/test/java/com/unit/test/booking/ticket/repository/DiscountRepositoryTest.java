package com.unit.test.booking.ticket.repository;


import com.rv.booking.ticket.BookACinemaApplication;
import com.rv.booking.ticket.entities.model.Discounts;
import com.rv.booking.ticket.entities.model.TicketType;
import com.rv.booking.ticket.repository.DiscountRepository;
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
public class DiscountRepositoryTest  {
    @Autowired
    private TestEntityManager entityManager;
    @MockBean
    private Discounts discounts;
    @Autowired
    DiscountRepository repository;

    @Test
    public void should_find_no_resources_if_repository_is_empty() {

        Iterable response = repository.findAll();

        assertThat(response);

    }


    @Test
    public void should_find_all_resources() {
        Discounts dis1 =   new Discounts();
        dis1.setTicketType(TicketType.ADULT);
        dis1.setDiscount(BigDecimal.valueOf(30.00));
        dis1.setId(10L);
        entityManager.persist(dis1);

        Discounts dis2 =   new Discounts();
        dis2.setTicketType(TicketType.ADULT);
        dis2.setDiscount(BigDecimal.valueOf(35.00));
        dis2.setId(20L);
        entityManager.persist(dis2);

        Iterable<Discounts> response = repository.findAll();

        assertThat(response).contains(dis1);
        assertThat(response).contains(dis2);

    }

}