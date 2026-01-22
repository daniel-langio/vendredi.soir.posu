package vendredi.soir.posu.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Wallet {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String name;

  @Column(nullable = false)
  private String reference;

  @Enumerated(EnumType.STRING)
  @JdbcTypeCode(SqlTypes.NAMED_ENUM)
  private WalletType type;

  @CreationTimestamp private Instant createdAt;

  @ManyToMany
  @JoinTable(
      name = "user_wallet",
      joinColumns = @JoinColumn(name = "wallet_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"))
  private List<User> users;
}
