package br.com.quadraja.api.services;

import br.com.quadraja.api.dtos.request.BookingRequest;
import br.com.quadraja.api.dtos.response.BookingResponse;
import br.com.quadraja.api.enums.Rule;
import br.com.quadraja.api.enums.Status;
import br.com.quadraja.api.exceptions.BookingException;
import br.com.quadraja.api.infra.security.SecurityFilter;
import br.com.quadraja.api.models.Booking;
import br.com.quadraja.api.models.Court;
import br.com.quadraja.api.models.User;
import br.com.quadraja.api.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    SecurityFilter securityFilter;
    CourtService courtService;
    UserService userService;

    public BookingResponse create(BookingRequest bookingRequest, String token) throws BookingException {

        Booking booking = new Booking();
        Court court = new Court();
        User userRegister = new User();
        User userReservation = new User();
        boolean bookingFree = false;

        if (bookingRequest.startTime() != null && bookingRequest.endTime() != null && bookingRequest.idCourt() != null) {
            bookingFree = bookingFree(bookingRequest.startTime(), bookingRequest.endTime(), bookingRequest.idCourt());
        } else {
            if (bookingRequest.startTime() != null) {
                throw new BookingException("O campo hora inicial do agendamento não foi informado!");
            }
            if (bookingRequest.endTime() != null) {
                throw new BookingException("O campo da hora final do agendamento não foi informado!");
            }
            if (bookingRequest.idCourt() != null) {
                throw new BookingException("O campo ID da quadra não foi informado!");
            }
        }

        if (!bookingFree) {
            throw new BookingException("Já existe um agendamento com esta mesma data e horário!");
        }

        booking.setStartTime(bookingRequest.startTime());
        booking.setEndTime(bookingRequest.endTime());

        court = courtService.getById(bookingRequest.idCourt());

        if (!court.isActive() || court.getId() == null) {
            throw new BookingException("A quadra informada não foi encontrada ou está inativa!");
        }

        if (bookingRequest.idUserReservation() != null) {
            userReservation = userService.findById(bookingRequest.idUserReservation());
        } else {
            throw new BookingException("O campo ID do usuário de reserva não foi informado!");
        }

        userRegister = securityFilter.userLoggedIn(token);

        if (userRegister != null && userRegister.getId() != null || userRegister.isActive()) {
            booking.setUserRegister(userRegister);
        } else {
            throw new BookingException("Ocorreu um erro! Usuário logado pode estar inativado!");
        }

        if (!userRegister.getId().equals(userReservation.getId())) {
            if (!userRegister.getAuthorities().contains(Rule.ADMIN)) {
                throw new BookingException("Usuário logado nao pode reservar por outra pessoa");
            } else {
                booking.setUserReservation(userReservation);
            }
        }

        booking.setActive(true);
        booking.setStatus(Status.PENDING);
        booking.setRegistrationDate(LocalDateTime.now());
        booking.setCourt(court);

        bookingRepository.save(booking);

        return new BookingResponse(booking.getId(), booking.getCourt().getName(), booking.getUserReservation().getName(), booking.getStartTime(), booking.getEndTime(), booking.getRegistrationDate(), booking.getStatus().name());
    }

    public boolean bookingFree(LocalDateTime startTime, LocalDateTime endTime, Long idCourt) {
        List<Booking> bookingList = bookingRepository.isThereTimeAlreadyReserved(startTime, endTime, idCourt);

        return bookingList.isEmpty();
    }
}
