package br.com.quadraja.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.quadraja.api.dtos.request.EstablishmentConfigurationRequest;
import br.com.quadraja.api.models.EstablishmentConfiguration;
import br.com.quadraja.api.repositories.EstablishmentConfigurationRepository;

@Service
public class EstablishmentConfigurationService {

    @Autowired
    private EstablishmentConfigurationRepository establishmentConfigurationRepository;

    public EstablishmentConfiguration find() {
        Optional<EstablishmentConfiguration> establishmentConfiguration = establishmentConfigurationRepository.find();
        return establishmentConfiguration.orElse(null);
    }

    public EstablishmentConfiguration createOrSave(EstablishmentConfigurationRequest ecRequest) {
        Optional<EstablishmentConfiguration> es = establishmentConfigurationRepository.find();
        EstablishmentConfiguration newEs;

        if (es.isEmpty()) {
            newEs = new EstablishmentConfiguration();
            
        } else {
            newEs = es.get();
        }


        if (ecRequest != null) {
            newEs.setActive(true);
            if (ecRequest.daysWeek() != null) {
                newEs.setDays(ecRequest.daysWeek());
            }
            if (ecRequest.start() != null) {
                newEs.setStart(ecRequest.start());
            }
            if (ecRequest.finish() != null) {
                newEs.setFinish(ecRequest.finish());
            }
        }
        return establishmentConfigurationRepository.save(newEs);
    }
}
