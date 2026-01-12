package vendredi.soir.posu.endpoint.rest.controller;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vendredi.soir.posu.endpoint.rest.mapper.LabelMapper;
import vendredi.soir.posu.endpoint.rest.model.Label;
import vendredi.soir.posu.endpoint.rest.model.LabelMinimalInfo;
import vendredi.soir.posu.service.LabelService;

@RestController
@RequestMapping("/label")
@AllArgsConstructor
public class LabelController {
  private final LabelService labelService;
  private final LabelMapper labelMapper;

  @PostMapping
  public ResponseEntity<List<Label>> createLabels(@RequestBody List<LabelMinimalInfo> labels) {
    List<Label> createdLabels =
        labelService.createLabels(labels).stream()
            .map(labelMapper::toRest)
            .collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.CREATED).body(createdLabels);
  }

  @GetMapping
  public ResponseEntity<List<Label>> getAllLabels() {
    return ResponseEntity.ok(
        labelService.getAllLabels().stream()
            .map(labelMapper::toRest)
            .collect(Collectors.toList()));
  }
}
