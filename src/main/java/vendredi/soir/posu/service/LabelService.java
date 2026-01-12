package vendredi.soir.posu.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vendredi.soir.posu.endpoint.rest.mapper.LabelMapper;
import vendredi.soir.posu.endpoint.rest.model.LabelMinimalInfo;
import vendredi.soir.posu.model.Label;
import vendredi.soir.posu.repository.LabelRepository;

@Service
@AllArgsConstructor
public class LabelService {
  private final LabelRepository labelRepository;
  private final LabelMapper labelMapper;

  public List<Label> createLabels(List<LabelMinimalInfo> labels) {
    List<Label> toSave =
        labels.stream()
            .map(labelMapper::toDomain)
            .peek(
                label -> {
                  if (labelRepository.existsByReference(label.getReference())) {
                    throw new IllegalArgumentException(
                        "Label with reference " + label.getReference() + " already exists");
                  }
                })
            .collect(Collectors.toList());
    return labelRepository.saveAll(toSave);
  }

  public List<Label> getAllLabels() {
    return labelRepository.findAll();
  }
}
