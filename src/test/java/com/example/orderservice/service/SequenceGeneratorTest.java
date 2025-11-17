package com.example.orderservice.service;

import com.example.orderservice.entity.Sequence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SequenceGeneratorTest {


    @InjectMocks SequenceGenerator svc;
    @Mock
    MongoOperations mongoOperations;
    @Test void returnsIncrementedValue() {
        Sequence doc = new Sequence();        // ensure it has a public setter/getter
        doc.setId("sequence");
        doc.setSequence(101);

        when(mongoOperations.findAndModify(any(), any(), any(), eq(Sequence.class))).thenReturn(doc);
        assertEquals(101, svc.generateNextOrderId());
    }
}
