package vendredi.soir.posu.conf;

import org.springframework.test.context.DynamicPropertyRegistry;
import vendredi.soir.posu.PojaGenerated;

@PojaGenerated
public class EmailConf {

  void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("aws.ses.source", () -> "dummy-ses-source");
  }
}
