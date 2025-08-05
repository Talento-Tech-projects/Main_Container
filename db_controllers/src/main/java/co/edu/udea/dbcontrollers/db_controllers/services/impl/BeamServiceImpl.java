package co.edu.udea.dbcontrollers.db_controllers.services.impl;

// Imports de DTOs
import co.edu.udea.dbcontrollers.db_controllers.dtos.BeamDTO;
import co.edu.udea.dbcontrollers.db_controllers.dtos.SupportDTO;
import co.edu.udea.dbcontrollers.db_controllers.dtos.PointLoadDTO;
import co.edu.udea.dbcontrollers.db_controllers.dtos.PointMomentDTO;
import co.edu.udea.dbcontrollers.db_controllers.dtos.DistributedLoadDTO;

// Imports de Entidades
import co.edu.udea.dbcontrollers.db_controllers.entities.Beam;
import co.edu.udea.dbcontrollers.db_controllers.entities.User;
import co.edu.udea.dbcontrollers.db_controllers.entities.Support;
import co.edu.udea.dbcontrollers.db_controllers.entities.PointLoad;
import co.edu.udea.dbcontrollers.db_controllers.entities.PointMoment;
import co.edu.udea.dbcontrollers.db_controllers.entities.DistributedLoad;

// Imports de Repositorios y Servicios
import co.edu.udea.dbcontrollers.db_controllers.repositories.BeamRepository;
import co.edu.udea.dbcontrollers.db_controllers.repositories.UserRepository;
import co.edu.udea.dbcontrollers.db_controllers.services.BeamService;

// Imports de Spring y Java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BeamServiceImpl implements BeamService {

    @Autowired
    private BeamRepository beamRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public BeamDTO save(BeamDTO beamDTO) {
        Beam beam = convertToEntity(beamDTO);
        Beam savedBeam = beamRepository.save(beam);
        return convertToDTO(savedBeam);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BeamDTO> findAll() {
        return beamRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BeamDTO> findById(Long id) {
        return beamRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BeamDTO> findByUserId(Long userId) {
        return beamRepository.findByUserId(userId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BeamDTO update(Long id, BeamDTO beamDTO) {
        Beam existingBeam = beamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viga no encontrada con id: " + id));

        // Actualizar propiedades de la viga
        existingBeam.setBeamLength(beamDTO.getBeamLength());
        existingBeam.setE(beamDTO.getE());
        existingBeam.setI(beamDTO.getI());

        // Actualizar componentes: se borran los existentes y se añaden los nuevos del DTO.
        // Esto funciona gracias a 'orphanRemoval=true' en la entidad Beam.
        existingBeam.getSupports().clear();
        beamDTO.getSupports().stream().map(this::convertSupportToEntity).forEach(support -> {
            existingBeam.getSupports().add(support);
            support.setBeam(existingBeam);
        });

        existingBeam.getPointLoads().clear();
        beamDTO.getPointLoads().stream().map(this::convertPointLoadToEntity).forEach(load -> {
            existingBeam.getPointLoads().add(load);
            load.setBeam(existingBeam);
        });

        existingBeam.getPointMoments().clear();
        beamDTO.getPointMoments().stream().map(this::convertPointMomentToEntity).forEach(moment -> {
            existingBeam.getPointMoments().add(moment);
            moment.setBeam(existingBeam);
        });

        existingBeam.getDistributedLoads().clear();
        beamDTO.getDistributedLoads().stream().map(this::convertDistributedLoadToEntity).forEach(dload -> {
            existingBeam.getDistributedLoads().add(dload);
            dload.setBeam(existingBeam);
        });

        Beam updatedBeam = beamRepository.save(existingBeam);
        return convertToDTO(updatedBeam);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!beamRepository.existsById(id)) {
            throw new RuntimeException("Viga no encontrada con id: " + id);
        }
        beamRepository.deleteById(id);
    }


    // --- MÉTODOS DE CONVERSIÓN ANIDADOS ---

    private Beam convertToEntity(BeamDTO dto) {
        Beam beam = new Beam();
        
        User user = userRepository.findById(dto.getUserId())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + dto.getUserId()));
        beam.setUser(user);
        
        beam.setBeamLength(dto.getBeamLength());
        beam.setE(dto.getE());
        beam.setI(dto.getI());

        // Convertir y añadir cada componente, estableciendo la referencia bidireccional.
        if (dto.getSupports() != null) {
            dto.getSupports().stream().map(this::convertSupportToEntity).forEach(support -> {
                beam.getSupports().add(support);
                support.setBeam(beam);
            });
        }
        if (dto.getPointLoads() != null) {
            dto.getPointLoads().stream().map(this::convertPointLoadToEntity).forEach(load -> {
                beam.getPointLoads().add(load);
                load.setBeam(beam);
            });
        }
        if (dto.getPointMoments() != null) {
            dto.getPointMoments().stream().map(this::convertPointMomentToEntity).forEach(moment -> {
                beam.getPointMoments().add(moment);
                moment.setBeam(beam);
            });
        }
        if (dto.getDistributedLoads() != null) {
            dto.getDistributedLoads().stream().map(this::convertDistributedLoadToEntity).forEach(dload -> {
                beam.getDistributedLoads().add(dload);
                dload.setBeam(beam);
            });
        }
        
        return beam;
    }

    private BeamDTO convertToDTO(Beam beam) {
        BeamDTO dto = new BeamDTO();
        dto.setId(beam.getId());
        dto.setBeamLength(beam.getBeamLength());
        dto.setE(beam.getE());
        dto.setI(beam.getI());
        dto.setUserId(beam.getUser().getId());

        dto.setSupports(beam.getSupports().stream().map(this::convertSupportToDTO).collect(Collectors.toList()));
        dto.setPointLoads(beam.getPointLoads().stream().map(this::convertPointLoadToDTO).collect(Collectors.toList()));
        dto.setPointMoments(beam.getPointMoments().stream().map(this::convertPointMomentToDTO).collect(Collectors.toList()));
        dto.setDistributedLoads(beam.getDistributedLoads().stream().map(this::convertDistributedLoadToDTO).collect(Collectors.toList()));
        
        return dto;
    }
    
    // --- Métodos de utilidad para convertir cada tipo de componente ---

    // Conversión para Support
    private SupportDTO convertSupportToDTO(Support s) {
        SupportDTO dto = new SupportDTO();
        dto.setId(s.getId());
        dto.setType(s.getType());
        dto.setPosition(s.getPosition());
        return dto;
    }

    private Support convertSupportToEntity(SupportDTO dto) {
        Support s = new Support();
        s.setType(dto.getType());
        s.setPosition(dto.getPosition());
        return s;
    }
    
    // Conversión para PointLoad
    private PointLoadDTO convertPointLoadToDTO(PointLoad p) {
        PointLoadDTO dto = new PointLoadDTO();
        dto.setId(p.getId());
        dto.setMagnitude(p.getMagnitude());
        dto.setPosition(p.getPosition());
        return dto;
    }

    private PointLoad convertPointLoadToEntity(PointLoadDTO dto) {
        PointLoad p = new PointLoad();
        p.setMagnitude(dto.getMagnitude());
        p.setPosition(dto.getPosition());
        return p;
    }
    
    // Conversión para PointMoment
    private PointMomentDTO convertPointMomentToDTO(PointMoment m) {
        PointMomentDTO dto = new PointMomentDTO();
        dto.setId(m.getId());
        dto.setMagnitude(m.getMagnitude());
        dto.setPosition(m.getPosition());
        return dto;
    }

    private PointMoment convertPointMomentToEntity(PointMomentDTO dto) {
        PointMoment m = new PointMoment();
        m.setMagnitude(dto.getMagnitude());
        m.setPosition(dto.getPosition());
        return m;
    }
    
    // Conversión para DistributedLoad
    private DistributedLoadDTO convertDistributedLoadToDTO(DistributedLoad d) {
        DistributedLoadDTO dto = new DistributedLoadDTO();
        dto.setId(d.getId());
        dto.setStartMagnitude(d.getStartMagnitude());
        dto.setEndMagnitude(d.getEndMagnitude());
        dto.setStartPosition(d.getStartPosition());
        dto.setEndPosition(d.getEndPosition());
        return dto;
    }
    
    private DistributedLoad convertDistributedLoadToEntity(DistributedLoadDTO dto) {
        DistributedLoad d = new DistributedLoad();
        d.setStartMagnitude(dto.getStartMagnitude());
        d.setEndMagnitude(dto.getEndMagnitude());
        d.setStartPosition(dto.getStartPosition());
        d.setEndPosition(dto.getEndPosition());
        return d;
    }
}