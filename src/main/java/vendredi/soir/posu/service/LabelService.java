package vendredi.soir.posu.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import vendredi.soir.posu.endpoint.rest.mapper.LabelMapper;
import vendredi.soir.posu.endpoint.rest.model.LabelMinimalInfo;
import vendredi.soir.posu.model.Label;
import vendredi.soir.posu.model.User;
import vendredi.soir.posu.repository.LabelRepository;

@Service
@AllArgsConstructor
public class LabelService {
  private final LabelRepository labelRepository;
  private final LabelMapper labelMapper;

  public List<Label> createLabels(List<LabelMinimalInfo> labels) {
    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    List<Label> toSave =
        labels.stream()
            .map(labelMapper::toDomain)
            .peek(
                label -> {
                  if (labelRepository.existsByReferenceAndUser(label.getReference(), currentUser)) {
                    throw new IllegalArgumentException(
                        "Label with reference " + label.getReference() + " already exists");
                  }
                  label.setUser(currentUser);
                })
            .collect(Collectors.toList());
    return labelRepository.saveAll(toSave);
  }

  public List<Label> getAllLabels() {
    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return labelRepository.findByUser(currentUser);
  }
}
