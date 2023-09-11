package com.unit.test.booking.ticket.mapper;


import com.rv.booking.ticket.entities.dto.DiscountDTO;
import com.rv.booking.ticket.entities.dto.PriceDTO;
import com.rv.booking.ticket.entities.model.Discounts;
import com.rv.booking.ticket.entities.model.PriceDetails;
import com.rv.booking.ticket.entities.model.TicketType;
import com.rv.booking.ticket.mapper.MapStructMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MapStructMapperTest {

    private MapStructMapper mapStructMapper = Mappers.getMapper(MapStructMapper.class);

    @Test
    public void givenDiscountToDiscountDto_mapsSuccess() {
        Discounts source = new Discounts();
        source.setTicketType(TicketType.ADULT);
        source.setDiscount(new BigDecimal(20.0));

        DiscountDTO dto = mapStructMapper.toDiscountsDTO(source);

        assertEquals(source.getTicketType(), dto.getTicketType());
        assertEquals(source.getDiscount(), dto.getDiscount());
    }

    @Test
    public void givenDiscountToPriceDetailsDto_mapsSuccess() {
        PriceDetails source = new PriceDetails();
        source.setTicketType(TicketType.ADULT);
        source.setPrice(new BigDecimal(20.0));

        PriceDTO dto = mapStructMapper.toPriceDTO(source);

        assertEquals(source.getPrice(), dto.getPrice());

        assertEquals(source.getTicketType(), dto.getTicketType());
    }


    @Test
    public void givenDiscountToPriceDetailsDtoAsList_mapsSuccess() {
        Discounts source =   new Discounts();
        source.setTicketType(TicketType.ADULT);
        source.setDiscount(new BigDecimal(20.0));

        Discounts source2 =   new Discounts();
        source2.setTicketType(TicketType.CHILD);
        source2.setDiscount(new BigDecimal(22.0));

        List<Discounts> list = new ArrayList<>(Arrays.asList(source, source2));

        List<DiscountDTO>  dto = mapStructMapper.toDiscountsDTOs(list);

        assertEquals(dto.size(),2);
        assertEquals(source.getDiscount(), dto.get(0).getDiscount());
        assertEquals(source2.getDiscount(), dto.get(1).getDiscount());

    }


    @Test
    public void givenDiscountToDiscountDtoAsList_mapsSuccess() {
        PriceDetails source =   new PriceDetails();
        source.setTicketType(TicketType.ADULT);
        source.setPrice(new BigDecimal(20.0));

        PriceDetails source2 =   new PriceDetails();
        source2.setTicketType(TicketType.ADULT);
        source2.setPrice(new BigDecimal(22.0));

        List<PriceDetails> list = new ArrayList<>(Arrays.asList(source, source2));

        List<PriceDTO>  dto = mapStructMapper.toPriceDTOs(list);

        assertEquals(dto.size(),2);
        assertEquals(source.getPrice(), dto.get(0).getPrice());
        assertEquals(source2.getPrice(), dto.get(1).getPrice());

    }

}