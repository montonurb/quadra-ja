package br.com.quadraja.api.dtos.request;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

public record EstablishmentConfigurationRequest(LocalTime start, LocalTime finish, Set<DayOfWeek> daysWeek) {

}
