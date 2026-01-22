package vendredi.soir.posu.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import vendredi.soir.posu.endpoint.rest.mapper.LabelMapper;
import vendredi.soir.posu.endpoint.rest.model.LabelMinimalInfo;
import vendredi.soir.posu.model.Label;
import vendredi.soir.posu.model.User;
import vendredi.soir.posu.repository.LabelRepository;

class LabelServiceTest {

  @Mock private LabelRepository labelRepository;
  @Mock private LabelMapper labelMapper;
  @InjectMocks private LabelService labelService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  private void mockUser(User user) {
    SecurityContext securityContext = mock(SecurityContext.class);
    when(securityContext.getAuthentication())
        .thenReturn(new UsernamePasswordAuthenticationToken(user, null));
    SecurityContextHolder.setContext(securityContext);
  }

  @Test
  void createLabels_should_succeed_if_reference_unique_for_user() {
    User user = User.builder().id("user1").username("user1").build();
    mockUser(user);

    LabelMinimalInfo info = LabelMinimalInfo.builder().name("L1").reference("REF1").build();
    Label label = Label.builder().name("L1").reference("REF1").build();

    when(labelMapper.toDomain(info)).thenReturn(label);
    when(labelRepository.existsByReferenceAndUser("REF1", user)).thenReturn(false);
    when(labelRepository.saveAll(any())).thenAnswer(i -> i.getArguments()[0]);

    List<Label> result = labelService.createLabels(List.of(info));

    assertEquals(1, result.size());
    assertEquals("REF1", result.get(0).getReference());
    assertEquals(user, result.get(0).getUser());
  }

  @Test
  void createLabels_should_throw_exception_if_reference_already_exists_for_user() {
    User user = User.builder().id("user1").username("user1").build();
    mockUser(user);

    LabelMinimalInfo info = LabelMinimalInfo.builder().name("L1").reference("REF1").build();
    Label label = Label.builder().name("L1").reference("REF1").build();

    when(labelMapper.toDomain(info)).thenReturn(label);
    when(labelRepository.existsByReferenceAndUser("REF1", user)).thenReturn(true);

    assertThrows(IllegalArgumentException.class, () -> labelService.createLabels(List.of(info)));
  }

  @Test
  void getAllLabels_should_return_only_user_labels() {
    User user = User.builder().id("user1").username("user1").build();
    mockUser(user);

    Label label = Label.builder().name("L1").reference("REF1").user(user).build();
    when(labelRepository.findByUser(user)).thenReturn(List.of(label));

    List<Label> result = labelService.getAllLabels();

    assertEquals(1, result.size());
    assertEquals(user, result.get(0).getUser());
    verify(labelRepository).findByUser(user);
  }
}
