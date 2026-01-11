package vendredi.soir.posu.model;

import jakarta.persistence.*;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private Instant date;

  @Enumerated(EnumType.STRING)
  private TransactionType transactionType;

  private String walletReference;

  private Double amount;

  private String categoryReference;

  private String subCategoryReference;

  private String description;

  @CreationTimestamp private Instant createdAt;

  @UpdateTimestamp private Instant updatedAt;
}
