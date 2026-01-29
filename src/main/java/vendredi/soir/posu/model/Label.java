package vendredi.soir.posu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Label {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String name;

  @Column(nullable = false)
  private String reference;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @CreationTimestamp private Instant createdAt;

  @UpdateTimestamp private Instant updatedAt;
}
