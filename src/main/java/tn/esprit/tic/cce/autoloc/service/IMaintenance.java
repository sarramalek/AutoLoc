package tn.esprit.tic.cce.autoloc.service;
import org.springframework.stereotype.Service;
import tn.esprit.tic.cce.autoloc.domain.Maintenance;
import tn.esprit.tic.cce.autoloc.repository.MaintenanceRepository;
import tn.esprit.tic.cce.autoloc.service.IMaintenanceService;

import java.util.List;

public class IMaintenance implements IMaintenanceService {

    private final MaintenanceRepository maintenanceRepository;

    public IMaintenance(
            MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    @Override
    public List<Maintenance> retrieveAllMaintenances() {
        return maintenanceRepository.findAll();
    }

    @Override
    public Maintenance addMaintenance(Maintenance m) {
        return maintenanceRepository.save(m);
    }

    @Override
    public Maintenance updateMaintenance(Maintenance m) {
        return maintenanceRepository.save(m);
    }

    @Override
    public Maintenance retrieveMaintenance(Long idMaintenance) {
        return maintenanceRepository.findById(idMaintenance).orElse(null);
    }

    @Override
    public void removeMaintenance(Long idMaintenance) {
        maintenanceRepository.deleteById(idMaintenance);
    }

    @Override
    public List<Maintenance> addMaintenances(
            List<Maintenance> maintenances) {
        return maintenanceRepository.saveAll(maintenances);
    }
}
