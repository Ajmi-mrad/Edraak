package projet.spring.edraak.service.formation;

import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.stereotype.Service;
import projet.spring.edraak.exceptions.FormationNotFoundException;
import projet.spring.edraak.exceptions.InstructorNotFoundException;
import projet.spring.edraak.model.Formation;
import projet.spring.edraak.model.Instructor;
import projet.spring.edraak.model.TypeFormation;
import projet.spring.edraak.repository.FormationRepository;
import projet.spring.edraak.repository.InstructorRepository;
import projet.spring.edraak.repository.TypeFormationRepository;
import projet.spring.edraak.request.formation.AddFormationRequest;
import projet.spring.edraak.request.formation.FormationUpdateRequest;
import projet.spring.edraak.request.typeFormation.TypeFormationUpdateRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class FormationService implements IFormationService{
    private final FormationRepository formationRepository;
    private final TypeFormationRepository typeFormationRepository;
    private final InstructorRepository instructorRepository;

    public FormationService(FormationRepository formationRepository,TypeFormationRepository typeFormationRepository,InstructorRepository instructorRepository) {
        this.formationRepository = formationRepository;
        this.typeFormationRepository = typeFormationRepository;
        this.instructorRepository = instructorRepository;
    }

    @Override
    public List<Formation> getAllFormations() {
        try {
            List<Formation> formations = formationRepository.findAll();
            if (formations.isEmpty()) {
                throw new FormationNotFoundException("No formations found in the database");
            }
            return formations;
        } catch (Exception e) {
            throw new FormationNotFoundException(e.getMessage());
        }
    }
    @Override
    public Formation getFormationById(Long id) {
        return formationRepository.findByIdWithInstructor(id);
    }
    private boolean areTrainingDatesWithinRange(LocalDate startDate, LocalDate endDate, List<LocalDateTime> trainingDates) {
        if (trainingDates == null || trainingDates.isEmpty()) return false;

        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);

        return trainingDates.stream()
                .allMatch(date -> !date.isBefore(startDateTime) && !date.isAfter(endDateTime));
    }

    @Override
    public Formation addFormation(AddFormationRequest request) {
        TypeFormation typeFormation = Optional.ofNullable(request.getTypeFormation())
                .orElseGet(()->{
                    TypeFormation newTypeFormation = new TypeFormation(request.getTypeFormation().getName());
                    return typeFormationRepository.save(newTypeFormation);
                });
        request.setTypeFormation(typeFormation);
        Instructor instructor = instructorRepository.findById(request.getInstructor().getId())
                .orElseThrow(() -> new RuntimeException("Instructor not found"));
        request.setInstructor(instructor);

        // check if trainingDates are between startDate and endDate
        if (!areTrainingDatesWithinRange(request.getStartDate(), request.getEndDate(), request.getTrainingDates())){
            throw new FormationNotFoundException("Training dates are not between start date and end date");
        }
        // check if the number of training sessions equals to the number of training dates (DurationTotal / DurationOfSession == TrainingDates.size())
        //if (formationRepository.existsByDurationTotalAndDurationOfSessionAndTrainingDatesSize(request.getDurationTotal(), request.getDurationOfSession(), request.getTrainingDates().size())) {
          //  throw new FormationNotFoundException("Number of training sessions is not equal to the number of training dates");
        //}

        //formation.setInstructor(instructor);
        //Instructor instructor = instructorRepository.findById(request.getInstructor().getId())
         //       .orElseThrow(()-> new InstructorNotFoundException("Instructor not found"));
        //Instructor instructor = Optional.ofNullable(request.getInstructor())
          //      .orElseThrow(()-> new InstructorNotFoundException("Instructor not found"));
        Formation formation = formationRepository.save(createFormation(request,typeFormation, instructor));
        formation.setInstructor(instructor);
        return formation;
    }
    public Formation createFormation(AddFormationRequest request, TypeFormation typeFormation, Instructor instructor){
        return new Formation(
                request.getName(),
                request.getDescription(),
                typeFormation,
                request.getStartDate(),
                request.getEndDate(),
                request.getPrice(),
                request.getMaxParticipants(),
                request.getMinParticipants(),
                request.getDurationTotal(),
                request.getDurationOfSession(),
                request.getTrainingDates(),
                instructor
        );
    }

    @Override
    public void deleteFormation(Long id) {
        formationRepository.findById(id)
                .ifPresentOrElse(formationRepository::delete,()->{
                    throw new FormationNotFoundException("Formation not found");
                });
    }

    @Override
    public Formation updateFormation(FormationUpdateRequest request, Long id) {
        return Optional.ofNullable(getFormationById(id)).map(existingFormation->{
            Formation updatedFormation = updateExistingFormation(existingFormation,request);
            return formationRepository.save(updatedFormation);
        }).orElseThrow(()-> new FormationNotFoundException("Formation not found"));

    }
    private Formation updateExistingFormation(Formation existingFormation, FormationUpdateRequest request){
        existingFormation.setName(request.getName());
        existingFormation.setDescription(request.getDescription());
        TypeFormation typeFormation = typeFormationRepository.findByName(request.getTypeFormation().getName());
        existingFormation.setTypeFormation(typeFormation);
        existingFormation.setStartDate(request.getStartDate());
        existingFormation.setEndDate(request.getEndDate());
        existingFormation.setPrice(request.getPrice());
        existingFormation.setMaxParticipants(request.getMaxParticipants());
        existingFormation.setMinParticipants(request.getMinParticipants());
        existingFormation.setDurationTotal(request.getDurationTotal());
        existingFormation.setDurationOfSession(request.getDurationOfSession());
        existingFormation.setTrainingDates(request.getTrainingDates());
        if (!areTrainingDatesWithinRange(request.getStartDate(), request.getEndDate(), request.getTrainingDates())) {
            throw new FormationNotFoundException("Training dates are not between start date and end date");
        }
        existingFormation.setInstructor(request.getInstructor());
        return existingFormation;
    }

    @Override
    public List<Formation> getFormationByType(String typeFormationName) {
        TypeFormation typeFormation = typeFormationRepository.findByName(typeFormationName);
        if (typeFormation == null) {
            return List.of(); // Return an empty list if the typeFormation is not found
        }
        return formationRepository.findByTypeFormation(typeFormation);
    }
    @Override
    public Formation getFormationByName(String name) {
        return formationRepository.findByName(name);
    }
}
