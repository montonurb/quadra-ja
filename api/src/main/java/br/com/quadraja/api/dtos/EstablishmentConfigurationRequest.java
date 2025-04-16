package br.com.quadraja.api.dtos;

import java.time.LocalTime;
import java.util.List;

public record EstablishmentConfigurationRequest(LocalTime start, LocalTime finish, List<Integer> daysWeek) {

}
