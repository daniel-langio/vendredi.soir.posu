package vendredi.soir.posu.model;

import jakarta.persistence.*;
import java.time.Instant;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "\"user\"")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(unique = true, nullable = false)
  private String username;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(unique = true, nullable = false)
  private String apiKey;

  @Column(nullable = false)
  @CreationTimestamp
  private Instant creationDatetime;
}
