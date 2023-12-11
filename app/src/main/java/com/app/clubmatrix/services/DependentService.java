package com.app.clubmatrix.services;

import com.app.clubmatrix.models.Dependent;
import com.app.clubmatrix.models.Member;
import com.app.clubmatrix.repositories.DependentRepository;
import com.app.clubmatrix.services.dto.DependentRegistrationDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DependentService {

  @Autowired
  private DependentRepository dependentRepository;

  @Autowired
  private UserService userService;

  public void registerDependent(DependentRegistrationDTO dependentDTO) {
    Dependent dependent = new Dependent();

    dependent.setName(dependentDTO.getName());

    Member member = userService
      .getUserByUsername(dependentDTO.getMemberUsername())
      .getMember();
    if (member != null) {
      dependent.setMember(member);
    }

    dependent.setRelationship(dependentDTO.getRelationship());
    dependent.setDateOfBirth(dependentDTO.getDateOfBirth());

    dependentRepository.save(dependent);
  }

  public List<Dependent> getAllDependents() {
    return dependentRepository.findAll();
  }

  public void deleteDependent(Dependent dependent) {
    dependentRepository.delete(dependent);
  }

  public void updateDependent(Dependent dependent) {
    dependentRepository.save(dependent);
  }
}
