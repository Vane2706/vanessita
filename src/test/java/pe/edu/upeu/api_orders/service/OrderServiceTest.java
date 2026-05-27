package pe.edu.upeu.api_orders.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pe.edu.upeu.api_orders.model.Order;
import pe.edu.upeu.api_orders.repository.OrderRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository repository;

    @InjectMocks
    private OrderService service;

    @Test
    void whenAmountLessThan1000_thenNoDiscountApplied() {

        Order order = new Order(null, "Vanessa", 500.0);

        when(repository.save(any(Order.class))).thenReturn(order);

        Order saved = service.createOrder(order);

        assertNotNull(saved);
        assertEquals(500.0, saved.getAmount());

        verify(repository, times(1)).save(order);
    }

    @Test
    void whenAmountGreaterThan1000_thenApplyDiscount() {

        Order order = new Order(null, "Carlos", 2000.0);

        when(repository.save(any(Order.class))).thenReturn(order);

        Order saved = service.createOrder(order);

        assertNotNull(saved);

        // 10% descuento = 1800
        assertEquals(1800.0, saved.getAmount());

        verify(repository, times(1)).save(order);
    }

    @Test
    void whenGetAllOrders_thenReturnList() {

        List<Order> orders = Arrays.asList(
                new Order(1L, "Teresa", 300.0),
                new Order(2L, "Pedrito", 700.0)
        );

        when(repository.findAll()).thenReturn(orders);

        List<Order> result = service.getAllOrders();

        assertEquals(2, result.size());

        verify(repository, times(1)).findAll();
    }
}